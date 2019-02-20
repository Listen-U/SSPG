package com.listen.sspg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.listen.sspg.entity.User;
import com.mysql.jdbc.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static com.listen.sspg.tools.LoginUtils.getSessionKeyOrOpenId;
import static com.listen.sspg.tools.LoginUtils.getUserInfo;

@RestController
@RequestMapping("/login")
public class LoginController {
    @ResponseBody
    @RequestMapping("/doLogin")
    public Map<String,Object> doLogin(Model model,
                                      @RequestParam(value = "code",required = false) String code,
                                      @RequestParam(value = "rawData",required = false) String rawData,
                                      @RequestParam(value = "signature",required = false) String signature,
                                      @RequestParam(value = "encrypteData",required = false) String encrypteData,
                                      @RequestParam(value = "iv",required = false) String iv){
//        log.info( "Start get SessionKey" );


        Map<String,Object> map = new HashMap<String, Object>(  );
        System.out.println("用户非敏感信息"+rawData);

        JSONObject rawDataJson = JSON.parseObject( rawData );

        System.out.println("签名"+signature);
        JSONObject SessionKeyOpenId = getSessionKeyOrOpenId( code );
        System.out.println("post请求获取的SessionAndopenId="+SessionKeyOpenId);

        String openid = SessionKeyOpenId.getString("openid" );

        String sessionKey = SessionKeyOpenId.getString( "session_key" );

        System.out.println("openid="+openid+",session_key="+sessionKey);

    /*    User user = userService.findByOpenid( openid );
        //uuid生成唯一key
        String skey = UUID.randomUUID().toString();
        if(user==null){
            //入库
            String nickName = rawDataJson.getString( "nickName" );
            String avatarUrl = rawDataJson.getString( "avatarUrl" );
            String gender  = rawDataJson.getString( "gender" );
            String city = rawDataJson.getString( "city" );
            String country = rawDataJson.getString( "country" );
            String province = rawDataJson.getString( "province" );


            user = new User();
            user.setUid( openid );
            user.setCreateTime( new Date(  ) );
            user.setSessionkey( sessionKey );
            user.setUbalance( 0 );
            user.setSkey( skey );
            user.setUaddress( country+" "+province+" "+city );
            user.setUavatar( avatarUrl );
            user.setUgender( Integer.parseInt( gender ) );
            user.setUname( nickName );
            user.setUpdateTime( new Date(  ) );

            userService.insert( user );
        }else {
            //已存在
//            log.info( "用户openid已存在,不需要插入" );
        }
        //根据openid查询skey是否存在
        String skey_redis = (String) redisTemplate.opsForValue().get( openid );
//        if(StringUtils.isNotBlank( skey_redis )){
        if(StringUtils.isNullOrEmpty(skey_redis)){
            //存在 删除 skey 重新生成skey 将skey返回
            redisTemplate.delete( skey_redis );
        }
        //  缓存一份新的
        JSONObject sessionObj = new JSONObject(  );
        sessionObj.put( "openId",openid );
        sessionObj.put( "sessionKey",sessionKey );
        redisTemplate.opsForValue().set( skey,sessionObj.toJSONString() );
        redisTemplate.opsForValue().set( openid,skey );

        //把新的sessionKey和oppenid返回给小程序
        map.put( "skey",skey );

        map.put( "result","0" );

        JSONObject userInfo = getUserInfo( encrypteData, sessionKey, iv );
        System.out.println("根据解密算法获取的userInfo="+userInfo);
        userInfo.put( "balance",user.getUbalance() );
        map.put( "userInfo",userInfo );
*/
        return map;
    }
}

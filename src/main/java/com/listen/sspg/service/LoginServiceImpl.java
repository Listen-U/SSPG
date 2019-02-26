package com.listen.sspg.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.entity.LoginInfo;
import com.listen.sspg.entity.User;
import com.listen.sspg.tools.FastJsonUtil;
import com.listen.sspg.tools.RedisUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.listen.sspg.tools.LoginUtils.getSessionKeyOrOpenId;
import static com.listen.sspg.tools.LoginUtils.getUserInfo;

/**
 *
 * @author Listen
 * @date 2019/2/26
 */
@Service
public class LoginServiceImpl {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    public RedisUtil redisUtil;

    public ApiReturnObj<Object> doLogin(ApiAcceptObj apiAcceptObj){
        ApiReturnObj<Object> apiReturnObj = new ApiReturnObj<>(2,"");
        String parameterJson = apiAcceptObj.getParameterJson();
        if (parameterJson != null && !"".equals(parameterJson)) {
            LoginInfo loginInfo = (LoginInfo) FastJsonUtil.jsonStrToObject(parameterJson,LoginInfo.class);
            Map<String,Object> map = new HashMap<>(16);

            System.out.println("用户非敏感信息"+loginInfo.getRawData());
            JSONObject rawDataJson = JSON.parseObject(loginInfo.getRawData());

            System.out.println("签名"+loginInfo.getSignature());
            JSONObject SessionKeyOpenId = getSessionKeyOrOpenId(loginInfo.getCode());

            System.out.println("Post请求获取的SessionAndopenId="+SessionKeyOpenId);
            String openid = SessionKeyOpenId.getString("openid");

            String sessionKey = SessionKeyOpenId.getString("session_key");
            System.out.println("Openid="+openid+",session_key="+sessionKey);

            User user = userService.findByOpenid(openid);
            //uuid生成唯一key
            String skey = UUID.randomUUID().toString();
            if(user==null){
                //入库
                user = new User();
                user.setUserId(openid);
                user.setCreateTime(new Date());
                user.setSessionKey(sessionKey);
                user.setBalance(0);
                user.setSkey(skey);
                user.setAddress(rawDataJson.getString("country")+" "+
                                    rawDataJson.getString("province")+" "+
                                    rawDataJson.getString("city"));
                user.setAvatar(rawDataJson.getString("avatarUrl"));
                user.setGender(Integer.parseInt(rawDataJson.getString("gender")));
                user.setUserName(rawDataJson.getString("nickName"));
                user.setUpdateTime(new Date());
                userService.insert(user);
            }else {
                System.out.println("用户openid已存在,不需要插入");
            }
            String skey_redis = (String) redisUtil.get(openid);
            //根据openid查询skey是否存在
            if(StringUtils.isNullOrEmpty(skey_redis)){
                //存在删除skey重新生成skey将skey返回
                redisUtil.remove(skey_redis);
            }
            //  缓存一份新的
            JSONObject sessionObj = new JSONObject();
            sessionObj.put("openId",openid);
            sessionObj.put("sessionKey",sessionKey);
            redisUtil.set(skey,sessionObj.toJSONString());
            redisUtil.set(openid,skey);
            //把新的sessionKey和oppenid返回给小程序
            map.put("skey",skey);
            map.put("result","0" );
            JSONObject userInfo = getUserInfo(loginInfo.getEncrypteData(),sessionKey, loginInfo.getIv());
            System.out.println("根据解密算法获取的userInfo="+userInfo);
            userInfo.put("balance",user.getBalance());
            map.put("userInfo",userInfo);
            apiReturnObj.setDatas(map);
        } else {
            // 参数为空
            apiReturnObj = new ApiReturnObj<Object>(10004);
        }
        return apiReturnObj;
    }
}

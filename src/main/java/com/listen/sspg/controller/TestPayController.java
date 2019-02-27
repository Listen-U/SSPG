package com.listen.sspg.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.listen.sspg.basecore.ApiAcceptObj;
import com.listen.sspg.basecore.ApiReturnObj;
import com.listen.sspg.wxpay.MyConfig;
import com.listen.sspg.wxpay.WXPay;
import com.listen.sspg.wxpay.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winfo064
 * @className TestPayController
 * @date 2019/2/27
 **/
@RestController
@RequestMapping("/wxpay")
public class TestPayController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 微信统一下单接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/doUnifiedOrder", method = RequestMethod.POST)
    public ApiReturnObj<Object> doUnifiedOrder(@Validated ApiAcceptObj baseRequest) {
        String appid = "";
        String mch_id = "";
        String notify_url = "";
        String TRADETYPE = "";
        String key = "";
        ApiReturnObj<Object> response = new ApiReturnObj<>();
        Map resultMap=new HashMap<>(16);
        String openid = baseRequest.getParameterJson();//.getOpenId();
        MyConfig config = null;
        WXPay wxpay =null;
        try {
            config = new MyConfig();
            wxpay= new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //生成的随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
        //获取客户端的ip地址
        //获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();
        //支付金额，需要转成字符串类型，否则后面的签名会失败
        int  total_fee=1;
        //商品描述
        String body = "车费支付";
        //商户订单号
        String out_trade_no= WXPayUtil.generateNonceStr();
        //统一下单接口参数
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", appid);
        data.put("mch_id", mch_id);
        data.put("nonce_str", nonce_str);
        data.put("body", body);
        data.put("out_trade_no",out_trade_no);
        data.put("total_fee", String.valueOf(total_fee));
        data.put("spbill_create_ip", spbill_create_ip);
        data.put("notify_url", notify_url);
        data.put("trade_type",TRADETYPE);
        data.put("openid", openid);
        try {
            Map<String, String> rMap = wxpay.unifiedOrder(data);
            System.out.println("统一下单接口返回: " + rMap);
            String return_code = (String) rMap.get("return_code");
            String result_code = (String) rMap.get("result_code");
            String nonceStr = WXPayUtil.generateNonceStr();
            resultMap.put("nonceStr", nonceStr);
            Long timeStamp = System.currentTimeMillis() / 1000;
            if ("SUCCESS".equals(return_code) && return_code.equals(result_code)) {
                String prepayid = rMap.get("prepay_id");
                resultMap.put("package", "prepay_id="+prepayid);
                resultMap.put("signType", "MD5");
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                resultMap.put("timeStamp", timeStamp + "");
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                resultMap.put("appId",appid);
                String sign = WXPayUtil.generateSignature(resultMap, key);
                resultMap.put("paySign", sign);
                System.out.println("生成的签名paySign : "+ sign);
                response.setDatas(resultMap);
                return response;
            }else{
                return  response;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  response;
        }
    }
}

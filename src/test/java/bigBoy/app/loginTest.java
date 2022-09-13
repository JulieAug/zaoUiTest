package bigBoy.app;

import bigBoyUtils.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.tools.ant.taskdefs.EchoXML;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

import static bigBoy.app.RedisTest.connect;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @program: hupuTest
 * @description: 大蓝书登录
 * @author: zhuli
 * @create: 2020-10-22 14:00
 **/
public class loginTest {

//    private final String  apiDomain = "http://msv-zuul-sit.hupu.io:8769/bigboy-app-api";

    private final String apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String sendSecurityCodeService = "/appapi/"+project+"/"+version+"/sendSecurityCode";

    private final String checkSecurityCodeService = "/appapi/"+project+"/"+version+"/checkSecurityCode";

    private String phone = "18260356798";
//    private String phone = "13262232583";//小明

    private String token;

    private Long targetId = DBUtil.getForValue("SELECT id from associate_thread ORDER BY create_dt desc limit 1;","bigBoy.properties");


    /**
     * 发送短信验证码
     * @throws Exception
     */
    @Test(priority = 1)
    public void sendSecurityCodeTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,sendSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        rc.body(JSONArray.toJSON(map));
        System.out.println("输入手机号为：" + map);
        JSONObject jsonObj = rc.post();
        System.out.println("调用发送验证码接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
//        Thread.sleep(200);
    }

    /**
     * 验证验证码登录
     * @throws Exception
     */
    @Test(priority = 2)
    public String  checkSecurityCodeSTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,checkSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        Jedis jedis = connect();
        String code=jedis.get("security_code_key_"+phone);
        map.put("code",code);
        jedis.close();
        rc.body(JSONArray.toJSON(map));
        System.out.println("手机号+验证码为：" + map);
        JSONObject jsonObj = rc.post();
        System.out.println("调用验证验证码接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        token = jsonObj.getJSONObject("data").getString("token");
        System.out.println("token："+token);
        return token;
    }

}
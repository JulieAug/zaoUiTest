package bigBoy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

import static bigBoy.RedisTest.connect;

/**
 * @program: hupuTest
 * @description: 大蓝书登录
 * @author: zhuli
 * @create: 2020-10-22 14:00
 **/
public class loginTest {

    private final String  apiDomain = "http://msv-zuul-sit.hupu.io:8769/bigboy-app-api";

    private String sendSecurityCodeService = "/appapi/1/1.0.0/sendSecurityCode";

    private String checkSecurityCodeService = "/appapi/1/1.0.0/checkSecurityCode";

    private String phone = "18260356798";

    /**
     * 发送短信验证码
     * @throws Exception
     */
    @Test(priority = 0)
    public void sendSecurityCodeTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,sendSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        rc.body(JSONArray.toJSON(map));
        System.out.println("*********** request:" + map);
        JSONObject jsonObj = rc.post();
        System.out.println("************ response:" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 验证验证码登录
     * @throws Exception
     */
    @Test(priority = 1)
    public void checkSecurityCodeService() throws Exception{
        RestClient rc = new RestClient(apiDomain,checkSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        Jedis jedis = connect();
        String code=jedis.get("security_code_key_"+phone);
//        map.put("code",code.substring(1,code.length()-1));
        map.put("code",code);
        jedis.close();
        rc.body(JSONArray.toJSON(map));
        System.out.println("************  request：" + map);
        JSONObject jsonObj = rc.post();
        System.out.println("************  response:" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

//    @Test
    public void test() throws Exception{
        Jedis jedis = connect();
        String code=jedis.get("security_code_key_"+phone);
        System.out.println(code);
    }
}



package api.bigBoy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

public class loginTest {

    private String apiDomain = "http://msv-zuul-sit.hupu.io:8769/bigboy-app-api";

    private String sendSecurityCodeService = "/appapi/1/1.0.0/sendSecurityCode";

    private String checkSecurityCodeService = "/appapi/1/1.0.0/checkSecurityCode";

    /**
     * 发送短信验证码
     * @throws Exception
     */
    @Test(priority = 0)
    public void sendSecurityCodeTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,sendSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone","18260356798");
        rc.body(JSONArray.toJSON(map));
        System.out.println("************" + map + "************");
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 验证验证码
     * @throws Exception
     */
    @Test(priority = 1)
    public void checkSecurityCodeService() throws Exception{
        RestClient rc = new RestClient(apiDomain,checkSecurityCodeService);
        Map<String, Object> map = new HashMap<>();
        map.put("phone","18260356798");
        map.put("code","123456");
        rc.body(JSONArray.toJSON(map));
        System.out.println("************" + map + "************");
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }



}



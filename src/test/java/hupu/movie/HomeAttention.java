package hupu.movie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import bigBoyUtils.DBUtil;
import util.RestClient;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhuli
 * @description 首页关注
 * @date 2020/3/30
 */
public class HomeAttention {

    private String apiDomain = "http://movie.sit.hupu.com";

    //添加关注
    private String attentionService = "/movieapi/attention/add";

    //取消关注
    private String cancelService = "/movieapi/attention/cancel";

    private Integer puid = 32133300;

    private BigInteger targetId = DBUtil.getForValue("select target_id from user_attention_record where user_id = 32133300 ORDER BY update_dt desc limit 1;","movie.properties");

    private Integer category = DBUtil.getForValue("select category from user_attention_record where user_id = 32133300 ORDER BY update_dt desc limit 1;","movie.properties");


    /**
     * 新增关注
     */
    @Test(priority = 0)
    public void attentionTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,attentionService);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("puid",puid);
        map.put("targetId",9);
        map.put("category",3);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        Thread.sleep(2000);
    }

    /**
     * 取消关注
     */
    @Test(priority = 1)
    public void cancelTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,cancelService);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("puid",puid);
        map.put("targetId",targetId);
        map.put("category",category);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}

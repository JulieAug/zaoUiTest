package hupu.pk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandomUtil;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhuli
 * @description 直播间PK&商品
 * @date 2020/6/22
 */
public class onlineTest {

    private String apiDomain = "http://movie.sit.hupu.com";
    //新增商品接口
    private String addGoodsService = "/movieapi/system/live/addGoodsToLive";


    @Test(priority = 0)
    /**
     * 添加商品
     */
    public void addGoodsTest() throws Exception{
        for(int i =0;i<10;i++){
            addGoods();
        }
    }

    /**
     * 在直播下添加商品
     * @throws Exception
     */
    public void  addGoods() throws Exception{
        RestClient rc = new RestClient(apiDomain,addGoodsService);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("liveId","42");
        data.put("name","测试商品"+ RandomUtil.getDigits(2));
        data.put("photo","http://movie-img.hupu.com/movie/693d0a6e-7c1a-44e0-86ea-27bfde1b866b");
        data.put("price",99);
        data.put("url","https://www.baidu.com/");
        data.put("isThirdParty",1);
        data.put("isSoldOut",0);
        data.put("isShow",0);
        data.put("isExplain",1);
        map.put("data",data);
        rc.body(JSONArray.toJSON(map));
        System.out.println("************" + map + "************");
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}

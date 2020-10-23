package hupu.list;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandomUtil;
import util.RestClient;

import java.util.*;

/**
 * @author:zhuli
 * @description 用户创建打榜
 * @date 2020/8/12
 */
public class addList {

    private String apiDomain = "http://movie-sit.hupu.com";

    //新增打榜接口
    private String addListService = "/movieapi/1/7.5.17/list/add";

    @Test(priority = 0)
    public void addlist() throws Exception{
        RestClient rc = new RestClient(apiDomain,addListService);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("name","用户打榜自定义"+ RandomUtil.getDigits(4));
        map.put("days",5);
        map.put("puid",32135154);

        List<Map<String,Object>> memberList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Map<String, Object> memeber = new HashMap<>();
            memeber.put("memberName","自定义用户"+RandomUtil.getDigits(2));
            memeber.put("memberAvatar","http://movie-img.hupu.com/movie/ea9bc731-cd60-420e-be87-87a7fa579bbe");
            memeber.put("memberCategory",3);
            memberList.add(memeber);
        }

        map.put("memberList",memberList);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}

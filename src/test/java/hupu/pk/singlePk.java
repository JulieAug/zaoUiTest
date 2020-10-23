package hupu.pk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandomUtil;
import util.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhuli
 * @description 单场赛PK
 * @date 2020/8/6
 */
public class singlePk {

    private String apiDomain = "http://movie.sit.hupu.com";

    //新增单场接口
    private String addSinglePkService = "/movieapi/system/pk/addMatch";

    private String voteService = "/movie/pk/voting";

    private String startTime = "2021-08-27 11:00:00";//比赛开始时间
    private String endTime = "2022-06-17 19:00:00";//比赛结束时间
    private Number redCategory = 3;//红方类型
    private Number blueCategory = 3;//蓝方类型


    @Test(priority = 0)
    public void addSinglePkTest() throws Exception{
        for(int i=0;i<1;i++){
            addSinglePk();
        }
    }


    /**
     * 新增PK单场赛
     * @throws Exception
     */
    public void addSinglePk() throws Exception{
        RestClient rc = new RestClient(apiDomain,addSinglePkService);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("title","巅峰赛"+ RandomUtil.getDigits(4));
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("background","http://movie-img.hupu.com/movie/48eeb6b7-b4c4-4bab-a84f-caa7394cd00f");

        map.put("redAvatar","http://movie-img.hupu.com/movie/84e12286-b704-415d-bbca-5f21f9f50d51");
        map.put("redCategory",redCategory);
        map.put("redTargetId","28570");
        map.put("redName","郭涛帅气帅气帅气帅气帅气帅气");

        map.put("blueAvatar","http://movie-img.hupu.com/movie/ea9bc731-cd60-420e-be87-87a7fa579bbe");
        map.put("blueCategory",blueCategory);
        map.put("blueTargetId","500000009");
        map.put("blueName","花花");

        //多维度
        List<Object> matchAttributeAddDtos = new ArrayList<>();
        Map<String, Object> matchAttributeAddDto1 = new HashMap<>();
        matchAttributeAddDto1.put("attribute","帅气帅气帅气帅气帅气帅气帅气");
        matchAttributeAddDtos.add(matchAttributeAddDto1);

        Map<String, Object> matchAttributeAddDto2 = new HashMap<>();
        matchAttributeAddDto2.put("attribute","可爱");
        matchAttributeAddDtos.add(matchAttributeAddDto2);

        Map<String, Object> matchAttributeAddDto3 = new HashMap<>();
        matchAttributeAddDto3.put("attribute","气质");
        matchAttributeAddDtos.add(matchAttributeAddDto3);

        Map<String, Object> matchAttributeAddDto4 = new HashMap<>();
        matchAttributeAddDto4.put("attribute","颜值");
        matchAttributeAddDtos.add(matchAttributeAddDto4);
        map.put("matchAttributeAddDtos",matchAttributeAddDtos);

        data.put("data",map);

        rc.body(JSONArray.toJSON(data));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }



//    @Test(priority = 1)
    public void vote() throws Exception{
        RestClient rc = new RestClient(apiDomain,voteService);
        rc.setHeader("Content-Type","application/json");
        rc.setHeader("","");
        Map<String, Object> map = new HashMap<>();
        map.put("matchId",1289);
        map.put("choice",1);
        map.put("userId","32135154");
        map.put("client","0CEDECCE-97B7-4EB4-A65E-5D7A6B90DD81");
        rc.body(JSONArray.toJSON(map));

        System.out.println("************" + JSONArray.toJSON(map) + "************");

        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");


    }


}

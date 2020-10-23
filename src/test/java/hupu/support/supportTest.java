package hupu.support;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import bigBoyUtils.DBUtil;
import util.RandomUtil;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zhuli
 * @description 打榜
 * @date 2020/7/3
 */
public class supportTest {

    private String apiDomain = "http://movie.sit.hupu.com";

    private String addListService = "/movieapi/system/list/add";

    private String updateListService = "/movieapi/system/list/update";

    private String deleteService = "/movieapi/system/list/delete";

    private String listVoteService= "/movieapi/1/7.5.13/list/addListVoteRecord";

    private String addMemberService= "/movieapi/system/list/addMember";

    private Number listId = DBUtil.getForValue("select id from list_info ORDER BY create_dt desc limit 1","movie.properties");

    private Number listMemberId = DBUtil.getForValue("select celebrity_id from movie_celebrity ORDER BY create_dt desc limit 1","movie.properties");

    private Number userId = 32135154;

    private Integer surplus;


    /**
     * 新增打榜
     */
    @Test(priority = 0)
    public void addListTest() throws Exception{
        for(int i =0;i<21;i++){
            addList();
        }
    }

    /**
     * 新增打榜
     * @throws Exception
     */
    @Test(priority = 1)
    public void addList() throws Exception{
        RestClient rc = new RestClient(apiDomain,addListService);
        Map<String,Object> map = new HashMap<>();
        map.put("name","排名小横线" + RandomUtil.getDigits(2));
        map.put("alias","别名披荆斩棘");
        map.put("avatar","http://tp1.hoopchina.com.cn/bytedance/88c0fe5bb8864948bfcc8953a67154b7.jpeg");
        map.put("banner","http://movie-img.hupu.com/movie/693d0a6e-7c1a-44e0-86ea-27bfde1b866b");
        map.put("description","你最喜欢谁？");
        map.put("startTime","2020-07-06 19:00:00");
        map.put("endTime","2022-06-17 19:00:00");
        Map<String,Object> page = new HashMap<>();
        page.put("day_mode_1","");
        page.put("day_mode_2","");
        page.put("night_mode_1","");
        page.put("night_mode_2","");
        page.put("rim_color","");
        map.put("pageElements",JSONObject.toJSONString(page));
        map.put("postId","");
        map.put("scoreName","力挺值");
        map.put("shield",0);
        map.put("voteIconDisableName","已力挺");//自定义打榜按钮文案
        map.put("voteIconEnableName","力挺");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");

    }


    /**
     * 更新最新一条打榜信息
     * @throws Exception
     */
//    @Test(priority = 2)
    public void updateListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateListService);
        Map<String,Object> map = new HashMap<>();
        map.put("id",listId);
        map.put("name","乘风破浪打榜" + RandomUtil.getDigits(3));
        map.put("alias","别名乘风破浪");
        map.put("avatar","http://movie-img.hupu.com/movie/693d0a6e-7c1a-44e0-86ea-27bfde1b866b");
        map.put("banner","http://movie-img.hupu.com/movie/693d0a6e-7c1a-44e0-86ea-27bfde1b866b");
        map.put("description","你最喜欢谁？");
        map.put("startTime","2020-06-17 19:00:00");
        map.put("endTime","2022-06-17 19:00:00");
        map.put("pageElements","");
        map.put("postId","123");
        map.put("scoreName","人气值");
        map.put("shield",0);
        map.put("voteIconDisableName","已撑腰");//自定义打榜按钮文案
        map.put("voteIconEnableName","撑腰");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }


    /**
     * 删除最后添加的一条打榜信息
     * @throws Exception
     */
//    @Test(priority = 3)
    public void deleteListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,deleteService);
        Map<String,Object> map = new HashMap<>();
        map.put("data",listId);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }


    /**
     * 新增打榜成员
     * @throws Exception
     */
    @Test(priority = 4)
    public void addMemberTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addMemberService);
        Map<String,Object> map = new HashMap<>();
        map.put("listInfoId",listId);
        map.put("memberId",listMemberId);
        map.put("memberCategory",3);//成员类型 1:电影 2:热点事件 3:影人
        map.put("memberAlias","打榜成员1");
        map.put("memberAvatar","http://movie-img.hupu.com/movie/693d0a6e-7c1a-44e0-86ea-27bfde1b866b");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }



    /**
     * 打榜投票
     * @throws Exception
     */
    @Test(priority = 5)
    public void listVoteTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,listVoteService);
        Map<String,Object> map = new HashMap<>();
//        map.put("userId",userId);
//        map.put("listInfoId",listId);
//        map.put("listMemberId",listMemberId);
        map.put("userId",RandomUtil.getDigits(8));
        map.put("listInfoId",209);
        map.put("listMemberId",3);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }


    @Test
    public void listVote() throws Exception{
        for(int i =0;i<40;i++){
            listVoteTest();
        }
    }

    @Test(priority = 6)
    public void surplus() throws Exception{
        surplus = 58041191%16;
        System.out.println("余数为："+surplus);
//        System.out.println("======"+listMemberId);
    }
}

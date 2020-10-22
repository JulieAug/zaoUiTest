package api.basketball;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DBUtil;
import util.DateUtil;
import util.RandomUtil;
import util.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhuli
 * @description 后台接口
 * @date 2019/8/30
 */
public class BasketballWebTest {

    private String apiDomain = "http://10.64.57.148:8081";
    private String addTeamsServive = "/basketballapi/background-management/v1/save-team";
    private String addMatchService = "/basketballapi/background-management/v1/save-match";
    private String addTvService = "/basketballapi/background-management/v1/matchTvs";
    private String teamId01;
    private String teamId02;
    private String matchId;
    private String matchTvName = "腾讯体育";


    /**
     * 新建篮球世界杯球队
     * @throws Exception
     */
    @Test(priority = 1)
    public void addTeamTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addTeamsServive);
        Map<String, Object> map = new HashMap<>();
        map.put("fullName","测试球队"+ RandomUtil.getDigits(3));
        map.put("name","测试"+ RandomUtil.getDigits(3));
        map.put("engFullName","testTeams"+ RandomUtil.getDigits(3));
        map.put("engName","test"+ RandomUtil.getDigits(3));
        map.put("leagueType","WORLD_CUP");
        List<String> playerID = new ArrayList();
        map.put("playerIds",playerID);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("msg"), "success");
        Assert.assertEquals(jsonObj.getString("status"), "200");
        teamId01 = DBUtil.getForValue("SELECT team_id FROM teams ORDER BY gmt_create DESC LIMIT 1","application.properties");
        teamId02 = DBUtil.getForValue("SELECT team_id FROM teams ORDER BY gmt_create ASC LIMIT 1","application.properties");
    }


    /**
     * 新建篮球世界杯比赛
     * @throws Exception
     */
    @Test(priority = 2)
    public void addMatchesTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addMatchService);
        Map<String, Object> map = new HashMap<>();
        map.put("leagueType","WORLD_CUP");//联赛类型
        map.put("chinaTime","2019-09-17 18:00:00");//比赛开始时间
        map.put("chinaTime", DateUtil.getCurrentDate());//比赛开始时间
        map.put("awayTeamId",teamId01);//主队Id
        map.put("homeTeamId",teamId02);//客队Id
        map.put("matchStatus","INPROGRESS");//比赛状态
        map.put("matchType","SECONDROUND");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("msg"), "success");
        Assert.assertEquals(jsonObj.getString("status"), "200");
        matchId = DBUtil.getForValue("SELECT match_id FROM matches ORDER BY gmt_create DESC LIMIT 1","application.properties");
    }

    /**
     * 新增视频直播源
     * @throws Exception
     */
    @Test(priority = 3)
    public void addTvTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addTvService);
        Map<String, Object> map = new HashMap<>();
        map.put("matchId",matchId);
        List<Object> matchTvs = new ArrayList<>();
        Map<String, Object> tv = new HashMap<>();
        tv.put("isPay",1);//是否付费
        tv.put("matchTvName",matchTvName);
        tv.put("matchTvUrl","www.baidu.com");
        matchTvs.add(tv);
        map.put("matchTvs",matchTvs);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("msg"), "success");
        Assert.assertEquals(jsonObj.getString("status"), "200");
        Assert.assertEquals(jsonObj.getJSONArray("result").getJSONObject(0).getString("tvName"),matchTvName);
    }


}

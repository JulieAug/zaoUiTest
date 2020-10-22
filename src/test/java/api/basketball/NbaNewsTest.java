package api.basketball;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;


/**
 * @author:zhuli
 * @description NBA新闻列表
 * @date 2019/6/18
 */
public class NbaNewsTest {

    private String apiDomain = "https://test.mobileapi.hupu.com";
    private String chuanshuoLoginService = "/chuanshuo/login";
    private String nbaNewsService = "/%s/%s/basketballapi/news/v1/getNewsList?%s";
    private String nbaMatchService ="/3/7.3.16/nba/getMatchs";
    private String projectId="1";
    private String version="7.3.15";
    private String newsFirstNavi ="NBA";



    /**
     * NBA新闻列表
     * @throws Exception
     */
    @Test(priority = 0)
    public void nbaNewsListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,String.format(nbaNewsService,projectId,version,newsFirstNavi));
        JSONObject jsonObj = rc.get();
        System.out.println("=========="+jsonObj);
        Assert.assertEquals(jsonObj.getString("msg"), "success");
    }

    /**
     * NBA比赛列表
     * @throws Exception
     */
    @Test(priority = 1)
    public void nbaMatchListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,nbaMatchService);
        JSONObject jsonObj = rc.get();
        JSONArray debug = jsonObj.getJSONObject("debug").getJSONArray("200");
        System.out.println(debug);
//        Assert.assertEquals(jsonObj.get("200"));
    }

    /**
     * 传说后台登录
     * @throws Exception
     */
    @Test(priority = 2)
    public void chuanshuoLoginTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,chuanshuoLoginService);
        rc.setHeader("cookie","");
        rc.params("token","chuanshuo");
        JSONObject jsonObj = rc.post();
        System.out.println("======="+jsonObj);

    }

}

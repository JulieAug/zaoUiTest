package hupu.basketball;

import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;


/**
 * @author:zhuli
 * @description 篮球世界杯
 * @date 2019/7/19
 */
public class BasketballTest {

    private String apiDomain = "https://test.mobileapi.hupu.com";
    private String worldCupMatchService = "/%s/%s/basketballapi/scheduleList";
    private String projectId="1";
    private String version="7.3.19";


    /**
     * 篮球世界杯赛程列表
     */
    @Test(priority = 1)
    public void worldCupMatchTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,String.format(worldCupMatchService,projectId,version));
        rc.params("leagueType", "WORLD_CUP");
        rc.params("time_zone", "Asia/Shanghai");
        rc.params("cursor", "20190831");
        rc.params("direc", "next");
        JSONObject jsonObj = rc.get();
        System.out.println("========" + jsonObj);
        Assert.assertEquals(jsonObj.getString("success"), "true");

    }
}

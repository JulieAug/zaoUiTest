package bigBoy.app;

import bigBoyUtils.DBUtil;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RestClient;


/**
 * @program: hupuTest
 * @description:
 * @author: zhuli
 * @create: 2020-11-05 17:50
 **/
public class bbsTest {

    private final String apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String queryThreadInfoService = "/appapi/"+project+"/"+version+"/bbs/queryThreadInfo";

    private final String queryReplyListService = "/appapi/"+project+"/"+version+"/bbs/queryReplyList";

    private Long targetId = DBUtil.getForValue("SELECT id from associate_thread ORDER BY create_dt desc limit 1;","bigBoy.properties");

    /**
     * 帖子详情接口
     */
    @Test(priority = 1)
    public void queryThreadInfoTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,queryThreadInfoService);
        rc.params("id",targetId);
//        rc.params("id","39088017");
        JSONObject jsonObj = rc.get();
        System.out.println("调用查看贴子详情页接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        String title = DBUtil.getForValue("SELECT title FROM associate_thread where id="+targetId,"bigBoy.properties");
        //校验数据库这个帖子标题与接口返回的帖子标题是否为同一个
        Assert.assertEquals(jsonObj.getJSONObject("data").getString("title"),title);
    }

    /**
     * 回帖列表
     * @throws Exception
     */
    @Test(priority = 2)
    public void queryReplyListTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,queryReplyListService);
        rc.params("targetId",targetId);
        rc.params("type",0);
        rc.params("pageSize",10);
        rc.params("pageNum",1);
        JSONObject jsonObj = rc.get();
        System.out.println("调用查看回帖列表接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

}

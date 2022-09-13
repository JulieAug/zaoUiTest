package bigBoy.app;

import bigBoyUtils.DBUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.RestClient;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * @program: hupuTest
 * @description: 用户点赞/收藏
 * @author: zhuli
 * @create: 2020-11-09 14:10
 **/
public class behaviorTest {
    private final String apiDomain = "http://bigboy-sit.hupu.com";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String addBehaviorServive = "/appapi/"+project+"/"+version+"/users/behavior/add";

    private final String cancelBehaviorServive = "/appapi/"+project+"/"+version+"/users/behavior/cancel";

    private Long targetId = DBUtil.getForValue("SELECT id from associate_thread ORDER BY create_dt desc limit 1;","bigBoy.properties");


    @DataProvider
    public static Object[][] actionType(){
        return new Object[][]{
                {0},{1}
        };
    }
    /**
     * 用户点赞/收藏
     * @throws Exception
     */
    @Test(priority = 1,dataProvider = "actionType")
    public void addBehaviorTest(Integer actionType) throws Exception{
        RestClient rc = new RestClient(apiDomain,addBehaviorServive);
        loginTest login = new loginTest();
        String token = login.checkSecurityCodeSTest();
        rc.setHeader("token",token);
        Map<String,Object> map = new HashMap<>();
        map.put("targetId",targetId);
        map.put("type",1); //0:商品 1：帖子===目前只有帖子
        map.put("actionType",actionType);//0：点赞 1：收藏===目前只有点赞
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("调用点赞/收藏接口，接口返回信息：" + jsonObj);
        //判断更新接口是否调用成功
        assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     *取消点赞
     */
    @Test(priority = 2,dataProvider = "actionType")
    public void cancelBehaviorTest(Integer actionType) throws Exception{
        RestClient  rc = new RestClient(apiDomain,cancelBehaviorServive);
        loginTest login = new loginTest();
        String token = login.checkSecurityCodeSTest();
        rc.setHeader("token",token);
        Map<String,Object> map = new HashMap<>();
        map.put("targetId",targetId);
        map.put("type",1); //0:商品 1：帖子
        map.put("actionType",actionType);//0：点赞 1：收藏
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("调用取消点赞/收藏接口，接口返回信息：" + jsonObj);
        //判断更新接口是否调用成功
        assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}

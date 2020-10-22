package api.threads;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.RandomUtil;
import util.RestClient;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author:zhuli
 * @description 社区发帖
 * @date 2020/7/9
 */
public class threasTest {

    private String apiDomain = "http://10.64.56.218:8080";

    private String threadPublishService = "/api/threads/publish";

    /**
     * 社区发帖接口
     */
    @Test(priority = 0)
    public void threadPublishTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,threadPublishService);
        Map<String,Object> map = new HashMap<>();
        map.put("title","测试"+ RandomUtil.getDigits(3));
        map.put("content","帖子内容");
        map.put("puid","32133300");
        map.put("topicId",38);
        map.put("downloadUrl","");
        map.put("videoSnapshotUrl","");
        map.put("videoUrl","");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "1");
    }
}

package hupu.movie;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import bigBoyUtils.DBUtil;
import util.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhuli
 * @description 影视后台接口测试
 * @date 2019/10/21
 */
public class MovieTest {
    private String apiDomain = "http://movie-sit.hupu.com";
    //新增电影事件线
    private String addMovieEventService = "/system/movie/addMovieEvent";
    //更新电影事件线
    private String updateMovieEventService = "/system/movie/updateMovieEvent";
    //删除电影事件线
    private String deleteMovieEventService = "/system/movie/deleteMovieEvent";

    //新增电影关键词
    private String addMovieKeywordService = "/system/movie/addMovieKeyword";
    //更新电影关键词
    private String updateMovieKeywordService = "/system/movie/updateMovieKeyword";
    //删除电影关键词
    private String deleteMovieKeywordService = "/system/movie/deleteMovieKeyword";

    //新增电影播放源
    private String addMovieReleaseService = "/system/movie/addMovieRelease";
    //更新电影播放源
    private String updateMovieReleaseService = "/system/movie/updateMovieRelease";
    //删除电影播放源
    private String deleteMovieReleaseService = "/system/movie/deleteMovieRelease";

    //查询电影播放源logo配置信息
    private String findAllMovieReleaseConfigsService = "/system/movie/findAllMovieReleaseConfigs";
    //根据电影ID查询电影详情
    private String searchMovieDetailsByMovieIdService = "/system/movie/searchMovieDetailsByMovieId";
    //根据电影名称模糊搜索电影
    private String searchMoviesService = "/system/movie/searchMovies";
    //根据上映类型搜索电影
    private String searchMoviesByMovieTypeService = "/system/movie/searchMoviesByMovieType";
    //更新电影基本信息(传需要更新的字段,ID必传)
    private String updateMovieByIdService = "/system/movie/updateMovieById";
    //更新聚合页颜色等额外信息(传需要更新的字段,ID必传)
    private String updateMovieExtraService = "/system/movie/updateMovieExtra";

    //影片id
    private Long movieId = DBUtil.getForValue("SELECT id FROM movie ORDER BY create_dt DESC LIMIT 1","movie.properties");
    //影片事件线id
    private Long eventId = DBUtil.getForValue("SELECT id FROM movie_event ORDER BY create_dt DESC LIMIT 1","movie.properties");
    //影片关键词id
    private Long keywordId = DBUtil.getForValue("SELECT id FROM movie_keyword ORDER BY create_dt DESC LIMIT 1","movie.properties");
    //电影播放源id
    private Long movieReleaseId = DBUtil.getForValue("SELECT id FROM movie_release ORDER BY create_dt DESC LIMIT 1","movie.properties");

    /**
     * 新增电影事件线
     * @throws Exception
     */
    @Test(priority = 0)
    public void addMovieEventTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addMovieEventService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("event","此影片荣获特等奖啦！");
        data.put("eventUrl","www.baidu.com");
        data.put("movieId",movieId);
        data.put("time","2019年10月");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 更新电影事件线（传需要更新的字段，ID必传）
     * @throws Exception
     */
    @Test(priority = 1)
    public void updateMovieEventTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateMovieEventService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",eventId);
        data.put("event","此影片荣获奥斯卡啦！");
        data.put("eventUrl","www.hupu.com");
        data.put("movieId",movieId);
        data.put("time","2019年11月");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     *删除影片事件线
     * @throws Exception
     */
    @Test(priority = 2)
    public void deleteMovieEventTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,deleteMovieEventService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        datas.add(eventId);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 新增电影关键词
     * @throws Exception
     */
    @Test(priority = 3)
    public void addMovieKeywordTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addMovieKeywordService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("movieId",movieId);
        data.put("keyword","祖国我爱你");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 更新电影关键词
     * @throws Exception
     */
    @Test(priority = 4)
    public void updateMovieKeywordTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateMovieKeywordService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",keywordId);
        data.put("movieId",movieId);
        data.put("keyword","祖国母亲mua!!");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");

    }

    /**
     * 删除电影关键词
     * @throws Exception
     */
    @Test(priority = 5)
    public void deleteMovieKeywordTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,deleteMovieKeywordService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        datas.add(keywordId);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 新增电影播放源
     * @throws Exception
     */
    @Test(priority = 6)
    public void addMovieReleaseTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,addMovieReleaseService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("category","免费观看");
        data.put("movieId",movieId);
        data.put("movieType",3);
        data.put("name","腾讯视频");
        data.put("url","https://v.qq.com");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 更新电影播放源
     * @throws Exception
     */
    @Test(priority = 7)
    public void updateMovieReleaseTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateMovieReleaseService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",movieReleaseId);
        data.put("category","VIP免费观看");
        data.put("movieId",movieId);
        data.put("movieType",3);
        data.put("name","腾讯视频");
        data.put("url","https://v.qq.com");
        datas.add(data);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 删除电影播放源(传电影播放源Id)
     * @throws Exception
     */
    @Test(priority = 8)
    public void deleteMovieReleaseTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,deleteMovieReleaseService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        datas.add(movieReleaseId);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 查询电影播放源logo配置信息
     * @throws Exception
     */
    @Test(priority = 9)
    public void findAllMovieReleaseConfigsTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,findAllMovieReleaseConfigsService);
        JSONObject jsonObj = rc.get();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        Assert.assertEquals(jsonObj.getJSONArray("data").getJSONObject(0).getString("releaseName"),"芒果TV");
        Assert.assertEquals(jsonObj.getJSONArray("data").getJSONObject(0).getString("logo"),"http://tp1.hoopchina.com.cn/gdc/vastevent/pic/3889dcb826e4f7829aba759812ef7f2d.png");
    }

    /**
     * 根据电影ID查询电影详情
     * @throws Exception
     */
//    @Test(priority = 10)
    public void searchMovieDetailsByMovieIdTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,searchMovieDetailsByMovieIdService);
        Map<String, Object> map = new HashMap<>();
        List<Object> datas = new ArrayList<>();
        datas.add(movieId);
        map.put("data",datas);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 根据电影名称模糊搜索电影
     * @throws Exception
     */
    @Test(priority = 11)
    public void searchMoviesTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,searchMoviesService);
        Map<String, Object> map = new HashMap<>();
        map.put("data","Enjoy");
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
        Assert.assertEquals(jsonObj.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("movieId"),"790405");
    }

    /**
     *根据上映类型搜索电影
     * @throws Exception
     */
//    @Test(priority = 12)
    public void searchMoviesByMovieTypeTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,searchMoviesByMovieTypeService);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("releaseType",1);
        map.put("data",data);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 更新电影基本信息(传需要更新的字段,ID必传)
     * @throws Exception
     */
    @Test(priority = 13)
    public void updateMovieByIdTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateMovieByIdService);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",movieId);
        data.put("englishName","The Making of Madden");
        map.put("data",data);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     *更新聚合页颜色等额外信息(传需要更新的字段,ID必传)
     * @throws Exception
     */
    @Test(priority = 14)
    public void updateMovieExtraTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,updateMovieExtraService);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",movieId);
        data.put("backgroundColor","");
        map.put("data",data);
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + jsonObj + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }
}

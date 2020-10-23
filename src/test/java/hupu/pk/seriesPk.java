package hupu.pk;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import bigBoyUtils.JdbcTool;
import util.RestClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zhuli
 * @description PK系列赛
 * @date 2020/6/17
 */
public class seriesPk {

    private String apiDomain = "http://movie.sit.hupu.com";

    //新增系列赛接口
    private String addSeriesPk = "/movieapi/system/matchSeries/add";

    private String startTime = "2020-09-21 11:00:00";//比赛开始时间
    private String endTime = "2022-06-17 19:00:00";//比赛结束时间
    private Integer redCategory = 1;//红方类型
    private Integer blueCategory = 1;//蓝方类型


    //新增系列赛
    @Test
    public void addSeriesPkTest() throws Exception{
        createSeries(64,4);
    }

    /**
     * 系列赛新增
     * @param numberCount
     * @param groupCount
     * @throws Exception
     */
    public void createSeries(Integer numberCount, Integer groupCount) throws Exception{
        RestClient rc = new RestClient(apiDomain,addSeriesPk);
        rc.setHeader("Content-Type","application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("title","亚男测试");
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("avatar","http://movie-img.hupu.com/movie/48eeb6b7-b4c4-4bab-a84f-caa7394cd00f");
        map.put("banner","http://movie-img.hupu.com/movie/9df5db8a-5eff-4bb0-8f2e-6edba142ab1b");
        map.put("description","64强系列赛");
        map.put("customScheduleName","赛程A");
        map.put("customAgainstPlanName","对阵图A");
        map.put("tid","123");

        //从数据库中取movieId
        List<Long> movieIds = new ArrayList<>();
        String sql = "select movie_id from movie limit 0,"+numberCount;

        //连接数据库
        Connection connection = JdbcTool.getConnection("movie.properties");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            movieIds.add(resultSet.getLong("movie_id"));
        }

        //系列赛分组
        String[] groups = null;
             if(groupCount==1){
                 groups = new String[]{"A"};
             }else if(groupCount==2){
                 groups = new String[]{"A","B"};
             }else if(groupCount==4){
                 groups=new String[]{"A","B","C","D"};
             }

        int index = 0;
        List<Object> groupItemsList = new ArrayList<>();
        //取红蓝双方Id,分组
        for(int i =0;i<numberCount/2;i++){
            Long redMemberId = movieIds.remove(0);
            Long blueMemberId = movieIds.remove(0);
            //String group = groups[i%groups.length];
            String group=groups[index++];
            if(index>=groups.length){
                index = 0;
            }
            Map<String, Object> groupItems = new HashMap<>();
            groupItems.put("title","64强系列赛");
            groupItems.put("startTime",startTime);
            groupItems.put("endTime",endTime);
            groupItems.put("redName","");
            groupItems.put("redAvatar","");
            groupItems.put("blueName","");
            groupItems.put("blueAvatar","");
            groupItems.put("group",group);
            groupItems.put("redCategory",redCategory);
            groupItems.put("blueCategory",blueCategory);
            groupItems.put("redTargetId",redMemberId);
            groupItems.put("blueTargetId",blueMemberId);
            groupItemsList.add(groupItems);
            map.put("groupItems",groupItemsList);
        }
        rc.body(JSONArray.toJSON(map));
        JSONObject jsonObj = rc.post();
        System.out.println("************" + JSONArray.toJSON(map) + "************");
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");

    }

}

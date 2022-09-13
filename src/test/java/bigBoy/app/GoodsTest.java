package bigBoy.app;

import bigBoyUtils.DBUtil;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.RestClient;

import java.math.BigInteger;

/**
 * @program: hupuTest
 * @description: 热搜关键词
 * @author: zhuli
 * @create: 2020-11-10 19:10
 **/
public class GoodsTest {
//    private final String apiDomain = "http://bigboy-sit.hupu.com";
    private final String apiDomain = "http://msv-zuul-sit.hupu.io:8769/bigboy-app-api";

    private String project = "1";//Android--iOS

    private String version = "1";//版本号

    private final String hotSearchKeywords = "/appapi/"+project+"/"+version+"/goods/hotSearchKeyWords";

    private final String searchByKeywords = "/appapi/"+project+"/"+version+"/goods/searchByKeyWords";

    private final String getRecommendList = "/appapi/"+project+"/"+version+"/goods/getRecommendList";

    private final String getAllMenuGoods = "/appapi/"+project+"/"+version+"/goods/getAllMenuGoods";

    private final String getFlow = "/appapi/"+project+"/"+version+"/goods/getFlow";

    private final String getgoodsComments = "/appapi/"+project+"/"+version+"/goods/getComments";

    private final String queryGoodsShopViewById = "/appapi/"+project+"/"+version+"/goods/queryGoodsShopViewById";

    private BigInteger goodsId = DBUtil.getForValue("select id from goods_pro order by ct asc limit 1;","bigBoyData.properties");


    /**
     * 热搜关键词
     * @throws Exception
     */
    @Test(priority = 1)
    public void hotSearchKeywordsTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,hotSearchKeywords);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 根据关键词搜索商品
     * @throws Exception
     */
    @DataProvider
    public static Object[][] sort(){
        return new Object[][]{
                {"单品",null,1,1}, {"单品",null,1,1},{"单品",null,1,1},{"单品",null,1,1},
                {"高达",1,1,1}, {"手办",2,1,1},{"周边",3,1,1},{"乐高",4,1,1},
                {"高达",1,2,1}, {"手办",2,2,1},{"模型",3,2,1},{"乐高",4,2,1},
                {"高达",1,3,1}, {"手办",2,3,1},{"模型",3,3,1},{"乐高",4,3,1},
                {"高达",1,1,2}, {"手办",2,1,2},{"模型",3,1,2},{"乐高",4,1,2},
                {"高达",1,2,2}, {"手办",2,2,2},{"模型",3,2,2},{"乐高",4,2,2},
                {"高达",1,3,2}, {"手办",2,3,2},{"模型",3,3,2},{"乐高",4,3,2}
        };
    }
    @Test(priority = 2,dataProvider = "sort")
    public void searchByKeywordsTest(String keywords,Integer category,Integer sortCategory,Integer sortType) throws Exception{
        RestClient rc = new RestClient(apiDomain,searchByKeywords);
        rc.params("keyword",keywords);
        rc.params("category",category);//分类 1：高达/2：手办/3：模型/4：乐高（没有值代表全部类型）
        rc.params("sortCategory",sortCategory);//排序类目 1：综合/2：销量/3：价格
        rc.params("sortType",sortType);//排序方式 1：升序/2：降序
        rc.params("pageNum",1);
        rc.params("pageSize",10);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }


    /**
     * 获取推荐列表
     * @throws Exception
     */
    @DataProvider
    public static Object[][] category(){
        return new Object[][]{
                {null},{1},{2},{3},{4}
        };
    }
    @Test(priority = 3,dataProvider = "category")
    public void getRecommendListTest(Integer category) throws Exception{
        RestClient rc = new RestClient(apiDomain,getRecommendList);
        rc.params("category",category);//分类 1：高达/2：手办/3：模型/4：乐高（没有值代表全部类型）
        rc.params("pageNum",1);
        rc.params("pageSize",10);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 获取商品分类目录及商品
     * @param category
     * @throws Exception
     */
    @Test(priority = 4,dataProvider = "category")
    public void getAllMenuGoodsTest(Integer category) throws Exception{
        RestClient rc = new RestClient(apiDomain,getAllMenuGoods);
        rc.params("category",category);//分类 1：高达/2：手办/3：模型/4：乐高（没有值代表全部类型）
        rc.params("pageNum",1);
        rc.params("pageSize",10);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 获取目录下的商品
     * @throws Exception
     */
    @DataProvider
    public static Object[][] flow(){
        return new Object[][]{
                {1,1,1},{2,1,1},{3,1,1},{4,1,1},
                {1,2,1},{2,2,1},{3,2,1},{4,2,1},
                {1,3,1},{2,3,1},{3,3,1},{4,3,1},

                {1,1,2},{2,1,2},{3,1,2},{4,1,2},
                {1,2,2},{2,2,2},{3,2,2},{4,2,2},
                {1,3,2},{2,3,2},{3,3,2},{4,3,2},
                {1,3,2},{2,3,2},{3,3,2},{4,3,2}
        };
    }
    @Test(priority = 5,dataProvider = "flow")
    public void getFlowTest(Integer category, Integer sortCategory,Integer sortType) throws Exception{
        RestClient rc = new RestClient(apiDomain,getFlow);
        rc.params("category",category);//分类 1：高达/2：手办/3：模型/4：乐高（没有值代表全部类型）
        rc.params("menuType2","");
        rc.params("menuType3","");
        rc.params("menuTargetId2","");
        rc.params("menuTargetId3","");
        rc.params("sortCategory",sortCategory);//排序类目 1：综合/2：销量/3：价格
        rc.params("sortType",sortType);//排序方式 1：升序/2：降序
        rc.params("pageNum",1);
        rc.params("pageSize",10);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

    /**
     * 获取商品的评论列表
     * @throws Exception
     */
//    @Test(priority = 3)
    public void getgoodsCommentsTest() throws Exception{
        RestClient rc = new RestClient(apiDomain,getgoodsComments);
        rc.params("goodsId",goodsId);
        rc.params("pageNum","1");
        rc.params("pageSize","10");
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }


    /**
     * 查询商品详情
     * @throws Exception
     */
    @Test(priority = 6)
    public void queryGoodsShopViewById() throws Exception{
        RestClient rc = new RestClient(apiDomain,queryGoodsShopViewById);
        rc.params("goodsId",goodsId);
        JSONObject jsonObj = rc.get();
        System.out.println("调用接口，接口返回信息：" + jsonObj);
        Assert.assertEquals(jsonObj.getString("code"), "SUCCESS");
    }

}


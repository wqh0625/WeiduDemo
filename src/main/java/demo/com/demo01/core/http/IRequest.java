package demo.com.demo01.core.http;

import java.util.List;

import demo.com.demo01.bean.BannerData;
import demo.com.demo01.bean.BottonBean;
import demo.com.demo01.bean.CircleData;
import demo.com.demo01.bean.CommentList;
import demo.com.demo01.bean.DingdanData;
import demo.com.demo01.bean.FootListData;
import demo.com.demo01.bean.GoodsByIdData;
import demo.com.demo01.bean.HomeListData;
import demo.com.demo01.bean.LoginData;
import demo.com.demo01.bean.MoneyData;
import demo.com.demo01.bean.MyAddressData;
import demo.com.demo01.bean.MyCircleData;
import demo.com.demo01.bean.QueryCarData;
import demo.com.demo01.bean.RegisterData;
import demo.com.demo01.bean.Result;
import demo.com.demo01.bean.SearchData;
import demo.com.demo01.bean.TopBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者: Wang on 2018/12/28 20:30
 * 寄语：加油！相信自己可以！！！
 */


public interface IRequest {

    // 注册
    @POST("user/v1/register")
    @FormUrlEncoded
    Observable<Result> getRegister(@Field("phone") String phone, @Field("pwd") String pwd);

    //登录
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<Result<LoginData>> getLoginRequest(
            @Field("phone") String phone,
            @Field("pwd") String pwd);

    // 得到Banner图片
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannerData>>> getBannerData();

    //commodity/v1/commodityList  homeFragment 的list数据
    @GET("commodity/v1/commodityList")
    Observable<Result<HomeListData>> getHomeListData();

    //   点击条目跳转 展示条目的具体数据
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Result<GoodsByIdData>> showGoodsData(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId
            , @Query("commodityId") String id);

    //   圈子数据
    @GET("circle/v1/findCircleList")
    Observable<Result<List<CircleData>>> circleData(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);


    @POST("user/findCircle/{uid}")
    @FormUrlEncoded
    Observable<Result<List<CircleData>>> getqzzz(
            @Path("uid") int uid,
            @Field("page") int page,
            @Field("count") int count);
    //  圈子点赞http://169.254.101.220:8080//{}
    @POST("circle/verify/v1/addCircleGreat")
    @FormUrlEncoded
    Observable<Result> getDianZan(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Field("circleId") int id);

    // circle/verify/v1/cancelCircleGreat 取消点赞
    @DELETE("circle/verify/v1/cancelCircleGreat")
    Observable<Result> getcacelDianZan(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Query("circleId") int id);


    //   根据关键字搜索
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchData>>> searchData(@Query("keyword") String keyword, @Query("page") int page, @Query("count") String count);

    //    我的圈子
    @GET("circle/verify/v1/findMyCircleById")
    Observable<Result<List<MyCircleData>>> getMyCircle(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    // 新增address 地址
    @POST("user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<Result> addMyAddress(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Field("realName") String realName,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("zipCode") String zipCode);

    // 收货地址列表 user/verify/v1/receiveAddressList
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<MyAddressData>>> getMyAddressList(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId);


    // pop  commodity/v1/findFirstCategory  // 一级类目
    @GET("commodity/v1/findFirstCategory")
    Observable<Result<List<TopBean>>> getOneCategory();

    //  二级类目
    @GET("commodity/v1/findSecondCategory")
    Observable<Result<List<BottonBean>>> getTwoCategory(@Query("firstCategoryId") String id);

    //根据二级类目查询商品信息
    // small/commodity/v1/findCommodityByCategory
    @GET("commodity/v1/findCommodityByCategory")
    Observable<Result<List<SearchData>>> getdataCategory(
            @Query("categoryId") String id
            , @Query("page") String page,
            @Query("count") String count);

    //  评论的列表commodity/v1/CommodityCommentList
    @GET("commodity/v1/CommodityCommentList")
    Observable<Result<List<CommentList>>> getCommentList(
            @Query("commodityId") String id
            , @Query("page") int page,
            @Query("count") int count);

    // 得到钱包数量  user/verify/v1/findUserWallet
    @GET("user/verify/v1/findUserWallet")
    Observable<Result<MoneyData>> getMoneyList(
            @Header("userId") int id,
            @Header("sessionId") String iaa,
            @Query("page") int page,
            @Query("count") int count);

    // 查询我的足迹的列表
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<FootListData>>> getFootList(
            @Header("userId") int id,
            @Header("sessionId") String iaa,
            @Query("page") int page,
            @Query("count") int count);

    // 查询购物车
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<QueryCarData>>> getCarList(
            @Header("userId") int id,
            @Header("sessionId") String sid);

    //同步购物车   添加购物车
    @FormUrlEncoded
    @PUT("order/verify/v1/syncShoppingCart")
    Observable<Result> addCarGoods(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Field("data") String dara);


    /// 根据订单状态查询订单信息  order/verify/v1/findOrderListByStatus
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<DingdanData>>> getDingdanData(
            @Header("userId") int id,
            @Header("sessionId") String iaa,
            @Query("status") int stacs,
            @Query("page") int page,
            @Query("count") int count);

    // 添加(创建)订单 order/verify/v1/createOrder
    @POST("order/verify/v1/createOrder")
    @FormUrlEncoded
    Observable<Result> createOrder(
            @Header("userId") int id,
            @Header("sessionId") String iaa,
            @Field("orderInfo") String orderInfo,
            @Field("totalPrice") double price,
            @Field("addressId") int addressId);

    // 取消订单  order/verify/v1/deleteOrder
    @DELETE("order/verify/v1/deleteOrder")
    Observable<Result> getcacelDan(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Query("orderId") String id);

    // 发布圈子
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> sendCircle(@Header("userId") int id,
                                  @Header("sessionId") String iaa,
                                  @Body MultipartBody multipart);
    // 支付  order/verify/v1/pay
    @POST("order/verify/v1/pay")
    @FormUrlEncoded
    Observable<Result> pay(@Header("userId") int id,
                           @Header("sessionId") String iaa,
                           @Field("orderId") String oid,
                           @Field("payType") int type);

    // 收货 order/verify/v1/confirmReceipt
    @FormUrlEncoded
    @PUT("order/verify/v1/confirmReceipt")
    Observable<Result> takeGoods(
            @Header("userId") int userid,
            @Header("sessionId") String sessionId,
            @Field("orderId") String orderId);

}

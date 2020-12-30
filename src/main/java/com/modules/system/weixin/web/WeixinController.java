package com.modules.system.weixin.web;///*
//package com.chunqiusoft.modules.weixin.web;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//import com.chunqiusoft.common.config.Global;
//import com.chunqiusoft.common.oauth.entity.Result;
//import com.chunqiusoft.common.oauth.entity.ResultStatusCode;
//import com.chunqiusoft.common.vo.GridParam;
//import com.chunqiusoft.common.web.BaseController;
//
//import com.chunqiusoft.modules.annotation.IgnoreSecurity;
//import com.chunqiusoft.modules.base.entity.BaseBanner;
//import com.chunqiusoft.modules.base.service.BaseBannerService;
//import com.chunqiusoft.modules.goods.entity.GoodsCategory;
//import com.chunqiusoft.modules.goods.entity.GoodsInfo;
//import com.chunqiusoft.modules.goods.entity.GoodsOrder;
//import com.chunqiusoft.modules.goods.service.GoodsInfoService;
//import com.chunqiusoft.modules.goods.service.GoodsOrderService;
//import com.chunqiusoft.modules.shops.entity.BusinessInfo;
//import com.chunqiusoft.modules.shops.entity.BusinessOrder;
//import com.chunqiusoft.modules.shops.service.BusinessInfoService;
//import com.chunqiusoft.modules.shops.service.BusinessOrderService;
//import com.chunqiusoft.modules.user.entity.UserDelivery;
//import com.chunqiusoft.modules.user.entity.UserInfo;
//import com.chunqiusoft.modules.user.entity.UserMobilePoint;
//import com.chunqiusoft.modules.user.service.UserDeliveryService;
//import com.chunqiusoft.modules.user.service.UserInfoService;
//import com.chunqiusoft.modules.user.service.UserMobilePointService;
//import com.chunqiusoft.modules.utils.KdniaoTrackQueryAPI;
//import com.chunqiusoft.modules.utils.RedisUtils;
//import com.chunqiusoft.modules.weixin.common.OpenApi;
//import com.chunqiusoft.modules.weixin.entity.WebAutho;
//import com.chunqiusoft.modules.weixin.utils.SignUtils;
//import com.chunqiusoft.modules.weixin.utils.WXPayUtil;
//import com.chunqiusoft.modules.weixin.utils.WXSignUtil;
//import com.chunqiusoft.modules.weixin.utils.XMLUtil;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.util.WebUtils;
//import org.weixin4j.WeixinException;
//import org.weixin4j.http.HttpsClient;
//import org.weixin4j.http.Response;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Controller
//@RequestMapping("/weixin")
//public class WeixinController extends BaseController {
//    private static final String href = "http://www.jufengou.com/ufenfu-api/weixin/";
//    public static final String baseUrl = "http://www.jufengou.com";
//
//    @Autowired
//    private UserInfoService userInfoService;
//    @Autowired
//    private BaseBannerService baseBannerService;
//    @Autowired
//    private BusinessInfoService businessInfoService;
//    @Autowired
//    private GoodsInfoService goodsInfoService;
//    @Autowired
//    private UserDeliveryService userDeliveryService;
//    @Autowired
//    private GoodsOrderService goodsOrderService;
//    @Autowired
//    private UserMobilePointService userMobilePointService;
//    @Autowired
//    private BusinessOrderService businessOrderService;
//
//
//    */
///**
//     * 去登录
//     * @return
//     *//*
//
//    @RequestMapping("toLogin")
//    public String toLogin(String code,HttpServletRequest request) throws WeixinException {
//        if(StringUtils.isEmpty(code)){
//            return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + OpenApi.appId + "&redirect_uri=" +href +"toLogin"+ "&response_type=code&scope=snsapi_base&state=886#wechat_redirect";
//        }
//
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
//                + "appid=" + OpenApi.appId + "&secret="
//                + OpenApi.secret
//                + "&code=" + code
//                + "&grant_type=authorization_code";
//
//        //创建请求对象
//        HttpsClient http = new HttpsClient();
//        //调用获取access_token接口
//        Response res = http.get(url);
//        //System.out.println(res.asString());
//        //根据请求结果判定，是否验证成功
//        com.alibaba.fastjson.JSONObject jsonObj = res.asJSONObject();
//
//        if(jsonObj != null){
//            Object errcode = jsonObj.get("errcode");
//            if (errcode != null) {
//                //返回异常信息
//                throw new WeixinException(getCause(Integer.parseInt(errcode.toString())));
//            }
//            WebAutho autho = JSON.parseObject(res.asString(), new TypeReference<WebAutho>() {});
//
//            String openId = autho.getOpenid();
//            System.out.println("我的openid======"+openId);
//            WebUtils.setSessionAttribute(request,"openId",openId);
//        }
//        return bgPath+"weixin/login";
//    }
//
//    */
///**
//     * 去首页
//     * @return
//     *//*
//
//    @RequestMapping("home")
//    public String home(HttpServletRequest request,Model model)throws WeixinException, IOException{
//        //热门商家
//        BusinessInfo businessInfo = new BusinessInfo();
//        businessInfo.setIsHot(1);
//        businessInfo.setSortStatus(0);
//        GridParam param = new GridParam();
//        param.setLimit(3);
//        param.setOffset(0);
//        //在线商城
//        GoodsInfo goodsInfo = new GoodsInfo();
//        goodsInfo.setSortStatus(0);
//        GridParam param2 = new GridParam();
//        param2.setLimit(6);
//        param2.setOffset(0);
//        model.addAttribute("bannerList",baseBannerService.findList(new BaseBanner()));
//        model.addAttribute("businessInfoHotList",businessInfoService.list(businessInfo,param));
//        model.addAttribute("goodsInfoHotList",goodsInfoService.list(goodsInfo,param2));
//        model.addAttribute("index",ONE);
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/home"));
//        model.addAttribute("appId", OpenApi.appId);
//        return bgPath+"weixin/index";
//    }
//
//    */
///**
//     * 去个人中心
//     * @return
//     *//*
//
//    @RequestMapping("toMine")
//    public String toMine(HttpServletRequest request,Model model)throws WeixinException, IOException{
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/toMine"));
//        model.addAttribute("appId", OpenApi.appId);
//        model.addAttribute("index",FOUR);
//        return bgPath+"weixin/mine";
//    }
//
//    */
///**
//     * 去修改个人信息
//     * @return
//     *//*
//
//    @RequestMapping("toMinexx")
//    public String toMinexx(){
//        return bgPath+"weixin/minexx";
//    }
//
//    */
///**
//     * 去用户反馈
//     * @return
//     *//*
//
//    @RequestMapping("toFeedBack")
//    public String toFeedBack(){
//        return bgPath+"weixin/advice";
//    }
//
//    */
///**
//     * 去申请入驻
//     * @return
//     *//*
//
//    @RequestMapping("toApply")
//    public String toApply(){
//        return bgPath+"weixin/settled";
//    }
//
//
//    */
///**
//     * 去订单页面
//     * @return
//     *//*
//
//    @RequestMapping("toOrder")
//    public String toOrder(String page,Model model){
//        model.addAttribute("page",page);
//        return bgPath+"weixin/order";
//    }
//
//
//    */
///**
//     * 签到页
//     * @return
//     *//*
//
//    @RequestMapping("toSign")
//    public String toSign(){
//        return bgPath+"weixin/sign";
//    }
//
//
//    */
///**
//     * 去订单详情
//     * @return
//     *//*
//
//    @RequestMapping("toOrderDetail")
//    public String toOrderDetail(){
//        return bgPath+"weixin/orderDetail";
//    }
//
//    */
///**
//     * 去查看物流
//     * @return
//     *//*
//
//    @RequestMapping("toOrderTraces")
//    public String toLogistics(GoodsOrder goodsOrder,Model model) throws Exception {
//        goodsOrder=goodsOrderService.get(goodsOrder.getId());
//        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
//        String orderTraces = api.getOrderTracesByJson(goodsOrder.getLogisticsCode(), goodsOrder.getLogisticsOrder());
//        JSONObject json = JSONObject.fromObject(orderTraces);
//        String tracesStr=json.getString("Traces");
//        JSONArray tracesArray = JSONArray.fromObject(tracesStr);
//        ArrayList traces=new ArrayList();
//        for(int i=tracesArray.size()-1;i>=0;i--){
//            traces.add(tracesArray.get(i));
//        }
//        model.addAttribute("traces",traces);
//        model.addAttribute("orderTraces",json);
//        model.addAttribute("goodsOrder",goodsOrder);
//        return bgPath+"weixin/orderTraces";
//    }
//
//
//
//    */
///**
//     * 商品详情
//     * @return
//     *//*
//
//    @RequestMapping("getGoodsInfo")
//    public String getGoodsInfo(GoodsInfo goodsInfo,Model model){
//        model.addAttribute("goodsInfo",goodsInfoService.get(goodsInfo.getId()));
//        return bgPath+"weixin/goodsInfo";
//    }
//
//    */
///**
//     * 立即购买
//     * @return
//     *//*
//
//    @RequestMapping(value = "buyGoods")
//    public String buyGoods(Model model, GoodsOrder goodsOrder,HttpServletRequest request){
//        goodsOrder.setQuantity(1);
//        //根据商品id获取商品详情
//        GoodsInfo goodsInfo = goodsInfoService.get(goodsOrder.getGoodsId());
//        goodsOrder.setGoodsName(goodsInfo.getGoodsName());
//        goodsOrder.setGoodsPrice(goodsInfo.getPrice());
//        goodsOrder.setGoodsImg(goodsInfo.getImgUrl());
//        goodsOrder.setFreight(goodsInfo.getFreight());
//        goodsOrder.setUserDelivery(userDeliveryService.get(goodsOrder.getUserId()));
//        model.addAttribute("goodsOrder",goodsOrder);
//        model.addAttribute("goodsInfo",goodsInfo);
//        return bgPath+"weixin/bugGoods";
//    }
//    */
///**
//     * 去消息列表
//     * @return
//     *//*
//
//    @RequestMapping("toInfo")
//    public String toInfo(){
//        return bgPath+"weixin/info";
//    }
//
//    */
///**
//     * 获取用户收货地址
//     * @param model
//     * @param request
//     * @return
//     *//*
//
//    @RequestMapping("getUserDelivery")
//    public String getUserDelivery(Model model,HttpServletRequest request,UserDelivery userDelivery){
//        UserDelivery userDelivery2 = userDeliveryService.get(userDelivery.getUserId());
//        if (userDelivery2==null){
//            model.addAttribute("userDelivery",userDelivery);
//        }else {
//            userDelivery2.setGoodsId(userDelivery.getGoodsId());
//            model.addAttribute("userDelivery",userDelivery2);
//        }
//
//        return bgPath+"weixin/userDelivery";
//    }
//
//    */
///**
//     * 去消息详情
//     * @return
//     *//*
//
//    @RequestMapping("toInfoDel")
//    public String toInfoDel(){
//        return bgPath+"weixin/infoDel";
//    }
//
//    */
///**
//     * 去猫眼消息详情
//     * @return
//     *//*
//
//    @RequestMapping("toCodeInfoDel")
//    public String toCodeInfoDel(){
//        return bgPath+"weixin/couponCode";
//    }
//
//    */
///**
//     * 去消息详情申请美年体检
//     * @return
//     *//*
//
//    @RequestMapping("toHealthInfo")
//    public String toHealthInfo(Integer messageId,Model model){
//
//        model.addAttribute("messageId",messageId);
//
//        return bgPath+"weixin/healthInfo";
//    }
//
//    */
///**
//     *  去预约成功
//     * @return
//     *//*
//
//    @RequestMapping("toAppointmentOk")
//    public String toAppointmentOk(){
//        return bgPath+"weixin/appointmentOk";
//    }
//
//    */
///**
//     * 去商品立即支付
//     * @return
//     *//*
//
//    @RequestMapping("toImmediatePay")
//    public String toImmediatePay(HttpServletRequest request,Model model,GoodsOrder goodsOrder,String token) throws Exception {
//        goodsOrder = goodsOrderService.get(goodsOrder.getId());
//        model.addAttribute("goodsOrder",goodsOrder);
//        model.addAttribute("openId",WebUtils.getSessionAttribute(request,"openId"));
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/toImmediatePay?id="+goodsOrder.getId()+"&token="+token));
//        model.addAttribute("appId", OpenApi.appId);
//        return bgPath+"weixin/immediatePay";
//    }
//
//    */
///**
//     * 商城获取微信支付需要参数
//     * @return
//     *//*
//
//    @RequestMapping(value="immediatePay", method = RequestMethod.POST)
//    @ResponseBody
//    public Result immediatePay(HttpServletRequest request,GoodsOrder goodsOrder,String openId,String token) throws Exception {
//        System.out.println("进入获取参数OPENID"+openId);
//        Map<String,Object> map=new SignUtils().wxPay(baseUrl + request.getContextPath() + "/weixin/toImmediatePay?id="+
//                goodsOrder.getId()+"&token="+token,goodsOrder.getGoodsName(),goodsOrder.getOrderNo(),String.valueOf( (int)(goodsOrder.getRealPrice()*100)),request.getRemoteAddr(),"http://www.jufengou.com/ufenfu-api/weixin/weixinNotify",openId);
//        return new Result(ResultStatusCode.OK,map);
//    }
//
//    */
///**
//     * 商铺获取微信支付需要参数
//     * @return
//     *//*
//
//    @RequestMapping(value="pay", method = RequestMethod.POST)
//    @ResponseBody
//    public Result Pay(HttpServletRequest request, BusinessOrder businessOrder, String openId,String token) throws Exception {
//        System.out.println("进入获取参数OPENID"+openId);
//        businessOrder=businessOrderService.get(businessOrder.getId());
//        Map<String,Object> map=new SignUtils().wxPay(baseUrl + request.getContextPath() + "/weixin/toPay?shopId="+
//                businessOrder.getBusinessId()+"&token="+token,"直接买单",businessOrder.getOrderNo(),String.valueOf( (int)(businessOrder.getRealMoney()*100)),request.getRemoteAddr(),"http://www.jufengou.com/ufenfu-api/weixin/weixinNotifyShop",openId);
//        return new Result(ResultStatusCode.OK,map);
//    }
//
//
//    */
///**
//     * 去支付成功
//     * @return
//     *//*
//
//    @RequestMapping("toPayOk")
//    public String toPayOk(Model model,GoodsOrder goodsOrder){
//        goodsOrder=goodsOrderService.get(goodsOrder.getId());
//        System.out.println("支付成功订单详情====="+JSON.toJSONString(goodsOrder));
//        model.addAttribute("goodsOrder",goodsOrder);
//        return bgPath+"weixin/payOk";
//    }
//
//    */
///**
//     * 去商家支付成功
//     * @return
//     *//*
//
//    @RequestMapping("toPayShopOk")
//    public String toPayShopOk(Model model,BusinessOrder businessOrder){
//        businessOrder = businessOrderService.get(businessOrder.getId());
//        model.addAttribute("businessOrder",businessOrder);
//        return bgPath+"weixin/payShopOk";
//    }
//
//    */
///**
//     * 去设置
//     * @return
//     *//*
//
//    @RequestMapping("toSetUp")
//    public String toSetUp(){
//        return bgPath+"weixin/setUp";
//    }
//
//    */
///**
//     * 去商城列表
//     * @return
//     *//*
//
//    @RequestMapping("toMallList")
//    public String toMallList(Model model, GoodsInfo goodsInfo){
//        model.addAttribute("goodsInfo",goodsInfo);
//        model.addAttribute("index",TWO);
//        return bgPath+"weixin/mallList";
//    }
//
//
//    */
///**
//     * 去商家列表
//     * @return
//     *//*
//
//    @RequestMapping("toShopList")
//    public String toShopList(HttpServletRequest request,Model model)throws WeixinException, IOException{
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/toShopList"));
//        model.addAttribute("appId", OpenApi.appId);
//        model.addAttribute("index",THREE);
//        return bgPath+"weixin/shopList";
//    }
//
//    */
///**
//     * 去商家详情
//     * @return
//     *//*
//
//    @RequestMapping("toShopDetail")
//    public String toShopDetail(HttpServletRequest request,BusinessInfo businessInfo,Model model)throws WeixinException, IOException{
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/toShopDetail?id="+businessInfo.getId()));
//        model.addAttribute("appId", OpenApi.appId);
//        model.addAttribute("businessInfo",businessInfoService.get(businessInfo.getId()));
//        return bgPath+"weixin/shopDetail";
//    }
//
//    */
///**
//     * 去商家支付
//     * @return
//     *//*
//
//    @RequestMapping("toPay")
//    public String toPay(HttpServletRequest request,Integer shopId,String token,Model model) throws WeixinException, IOException {
//        UserInfo userInfo=userInfoService.get(getUserId(request));
//        model.addAttribute("shopId",shopId);
//        model.addAttribute("userInfo",userInfo);
//        model.addAttribute("openId",WebUtils.getSessionAttribute(request,"openId"));
//        model.addAttribute("obj", new SignUtils().getTicket(baseUrl + request.getContextPath() + "/weixin/toPay?shopId="+shopId+"&token="+token));
//        model.addAttribute("appId", OpenApi.appId);
//        return bgPath+"weixin/pay";
//    }
//
//
//    */
///**
//     * 去分类
//     * @param model
//     * @return
//     *//*
//
//    @RequestMapping("toMallMore")
//    public String toMallMore(Model model){
//        model.addAttribute("index",TWO);
//        return bgPath+"weixin/mallmore";
//    }
//
//    */
///**
//     * 去我的代金券
//     * @param
//     * @return
//     *//*
//
//    @RequestMapping("toMyMoney")
//    public String toMyMoney(){
//        return bgPath+"weixin/money";
//    }
//
//    */
///**
//     * 去我的积分
//     * @return
//     *//*
//
//    @RequestMapping("toMyScore")
//    public String toMyScore(Model model, UserMobilePoint userMobilePoint){
//        userMobilePoint = userMobilePointService.get(userMobilePoint.getUserId());
//        model.addAttribute("userMobilePoint",userMobilePoint);
//        return bgPath+"weixin/score";
//    }
//
//    */
///**
//     * 去添加积分账户
//     * @return
//     *//*
//
//    @RequestMapping("toQuery")
//    public String toQuery(Model model,UserMobilePoint userMobilePoint){
//        model.addAttribute("userMobilePoint",userMobilePoint);
//        return bgPath+"weixin/query";
//    }
//
//    */
///**
//     * 去商家路线
//     * @param model
//     * @return
//     *//*
//
//    @RequestMapping("shopDelLine")
//    public String toMallMore(BusinessInfo businessInfo,Model model){
//        model.addAttribute("businessInfo",businessInfoService.get(businessInfo.getId()));
//        return bgPath+"weixin/shopDelLine";
//    }
//
//    */
///**
//     * 去更多积分
//     * @param model
//     * @param userMobilePoint
//     * @return
//     *//*
//
//    @RequestMapping("toMore")
//    public String toMore(Model model,UserMobilePoint userMobilePoint){
//        model.addAttribute("userMobilePoint",userMobilePoint);
//        return bgPath+"weixin/more";
//    }
//
//    */
///**
//     * 支付回调接口
//     *//*
//
//    @RequestMapping("weixinNotify")
//    public void weixinNotify(HttpServletRequest request, HttpServletResponse response){
//        String out_trade_no=null;
//        String return_code =null;
//        response.setContentType("text/xml;charset=UTF-8");
//        try {
//            InputStream is = request.getInputStream();
//            String resultStr = IOUtils.toString(is, "UTF-8");
//            if("".equals(resultStr)){
//                System.out.println("fail:result is null");
//                response.getWriter().write("<xm><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误！]]></return_msg></xml>");
//                return ;
//            }
//            //解析xml
//            SortedMap<Object,Object> resultMap=(TreeMap) XMLUtil.doXMLParse(resultStr);
////                System.out.println(resultMap);
//            //验证签名
//            String sign2 = WXSignUtil.createSign(resultMap, OpenApi.key);
//            //返回的签名
//            String sign = resultMap.get("sign")+"";
//            //业务结果
//            String result_code=resultMap.get("result_code")+"";
//            //微信支付订单号
//            String transaction_id = resultMap.get("transaction_id")+"";
//            //支付完成时间
//            String time_end = resultMap.get("time_end")+"";
//            //付款银行
//            String bank_type = resultMap.get("bank_type")+"";
//            //返回状态码
//            return_code=resultMap.get("return_code")+"";
//            //商户订单号
//            out_trade_no =resultMap.get("out_trade_no")+"";
//            //现金支付金额
//            Float cash_fee = Float.parseFloat(resultMap.get("cash_fee")+"");
//
//
//            if(sign.equals(sign2)){//校验签名，两者需要一致，防止别人绕过支付操作，不付钱直接调用你的业务
//                if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
//                    //业务逻辑(先判断数据库中订单号是否存在，并且订单状态为未支付状态)
//                    try {
//                        //resultMap.put("openid", request.getSession().getAttribute("openid"));
//                        GoodsOrder goodsOrder=new GoodsOrder();
//                        goodsOrder.setOrderNo(out_trade_no);
//                        goodsOrder=goodsOrderService.getByOrderNo(goodsOrder);
//                        if(goodsOrder != null && goodsOrder.getStatus() !=null && goodsOrder.getStatus() ==0){
//
//                            goodsOrder.setPayDate(new Date());
//                            goodsOrder.setPayTradeNo(transaction_id);
//                            goodsOrder.setUpdateDate(new Date());
//                            goodsOrder.setReceiveMoney(cash_fee/100);
//                            goodsOrderService.save(goodsOrder);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //request.setAttribute("out_trade_no", out_trade_no);
//                    //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                    response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
//                }else{
//                    response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
//                }
//            }else{
//                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名校验失败]]></return_msg></xml>");
//            }
//            response.getWriter().flush();
//            response.getWriter().close();
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    */
///**
//     * 商铺支付回调接口
//     *//*
//
//    @RequestMapping("weixinNotifyShop")
//    public void weixinNotifyShop(HttpServletRequest request, HttpServletResponse response){
//        String out_trade_no=null;
//        String return_code =null;
//        response.setContentType("text/xml;charset=UTF-8");
//        try {
//            InputStream is = request.getInputStream();
//            String resultStr = IOUtils.toString(is, "UTF-8");
//            if("".equals(resultStr)){
//                System.out.println("fail:result is null");
//                response.getWriter().write("<xm><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数错误！]]></return_msg></xml>");
//                return ;
//            }
//            //解析xml
//            SortedMap<Object,Object> resultMap=(TreeMap) XMLUtil.doXMLParse(resultStr);
////                System.out.println(resultMap);
//            //验证签名
//            String sign2 = WXSignUtil.createSign(resultMap, OpenApi.key);
//            //返回的签名
//            String sign = resultMap.get("sign")+"";
//            //业务结果
//            String result_code=resultMap.get("result_code")+"";
//            //微信支付订单号
//            String transaction_id = resultMap.get("transaction_id")+"";
//            //支付完成时间
//            String time_end = resultMap.get("time_end")+"";
//            //付款银行
//            String bank_type = resultMap.get("bank_type")+"";
//            //返回状态码
//            return_code=resultMap.get("return_code")+"";
//            //商户订单号
//            out_trade_no =resultMap.get("out_trade_no")+"";
//            //现金支付金额
//            Float cash_fee = Float.parseFloat(resultMap.get("cash_fee")+"");
//
//
//
//            if(sign.equals(sign2)){//校验签名，两者需要一致，防止别人绕过支付操作，不付钱直接调用你的业务
//                if(return_code.equals("SUCCESS") && result_code.equals("SUCCESS")){
//                    //业务逻辑(先判断数据库中订单号是否存在，并且订单状态为未支付状态)
//                    try {
//                        //resultMap.put("openid", request.getSession().getAttribute("openid"));
//                        BusinessOrder businessOrder=new BusinessOrder();
//                        businessOrder.setOrderNo(out_trade_no);
//                        businessOrder=businessOrderService.getByOrderNo(businessOrder);
//                        if(businessOrder != null && businessOrder.getStatus() !=null && businessOrder.getStatus() ==0){
//                            businessOrder.setPayDate(new Date());
//                            businessOrder.setPayTradeNo(transaction_id);
//                            businessOrder.setReceiveMoney((float) (cash_fee/100));
//                            businessOrder.setStatus(3);
//                            businessOrderService.updateOrder(businessOrder);
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    //request.setAttribute("out_trade_no", out_trade_no);
//                    //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                    response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
//                }else{
//                    response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[交易失败]]></return_msg></xml>");
//                }
//            }else{
//                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//                response.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名校验失败]]></return_msg></xml>");
//            }
//            response.getWriter().flush();
//            response.getWriter().close();
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
//*/

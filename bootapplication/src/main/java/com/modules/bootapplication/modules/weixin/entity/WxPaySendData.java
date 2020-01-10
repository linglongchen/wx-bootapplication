package com.modules.bootapplication.modules.weixin.entity;

public class WxPaySendData {
    //公众账号ID
    private String appid;
    //附加数据
    private String attach;
    //商品描述
    private String body;
    //商户号
    private String mch_id;
    //随机字符串
    private String nonce_str;
    //通知地址
    private String notify_url;
    //商户订单号
    private String out_trade_no;
    //标价金额
    private String total_fee;
    //交易类型
    private String trade_type;
    //终端IP
    private String spbill_create_ip;
    //用户标识
    private String openid;
    //签名
    private String sign;
    //预支付id
    private String prepay_id;
    //签名类型：MD5
    private String signType;
    //时间戳
    private String timeStamp;
    //微信支付时用到的prepay_id
    private String packageStr;

    private String return_code;
    private String return_msg;
    private String result_code;

    private String bank_type;
    private Integer cash_fee;
    private String fee_type;
    private String is_subscribe;
    private String time_end;
    //微信支付订单号
    private String transaction_id;
    private String ip;
    private Integer coupon_count;
    private Integer coupon_fee;
    private Integer coupon_fee_0;
    private String coupon_type_0;
    private String coupon_id_0;

    public String getCoupon_type_0() {
        return coupon_type_0;
    }
    public void setCoupon_type_0(String coupon_type_0) {
        this.coupon_type_0 = coupon_type_0;
    }
    public String getCoupon_id_0() {
        return coupon_id_0;
    }
    public void setCoupon_id_0(String coupon_id_0) {
        this.coupon_id_0 = coupon_id_0;
    }
    public Integer getCoupon_fee_0() {
        return coupon_fee_0;
    }
    public void setCoupon_fee_0(Integer coupon_fee_0) {
        this.coupon_fee_0 = coupon_fee_0;
    }
    public Integer getCoupon_fee() {
        return coupon_fee;
    }
    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }
    public Integer getCoupon_count() {
        return coupon_count;
    }
    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getBank_type() {
        return bank_type;
    }
    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }
    public Integer getCash_fee() {
        return cash_fee;
    }
    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }
    public String getFee_type() {
        return fee_type;
    }
    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }
    public String getIs_subscribe() {
        return is_subscribe;
    }
    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }
    public String getTime_end() {
        return time_end;
    }
    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getAppid() {
        return appid;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getAttach() {
        return attach;
    }
    public void setAttach(String attach) {
        this.attach = attach;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getMch_id() {
        return mch_id;
    }
    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getNonce_str() {
        return nonce_str;
    }
    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getNotify_url() {
        return notify_url;
    }
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getOut_trade_no() {
        return out_trade_no;
    }
    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
    public String getTotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }
    public String getTrade_type() {
        return trade_type;
    }
    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }
    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getOpenid() {
        return openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getReturn_code() {
        return return_code;
    }
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
    public String getReturn_msg() {
        return return_msg;
    }
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
    public String getResult_code() {
        return result_code;
    }
    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getPrepay_id() {
        return prepay_id;
    }
    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackageStr() {
        return packageStr;
    }
    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }
    @Override
    public String toString() {
        return "WxPaySendData [appid=" + appid + ", attach=" + attach
                + ", body=" + body + ", mch_id=" + mch_id + ", nonce_str="
                + nonce_str + ", notify_url=" + notify_url + ", out_trade_no="
                + out_trade_no + ", total_fee=" + total_fee + ", trade_type="
                + trade_type + ", spbill_create_ip=" + spbill_create_ip
                + ", openid=" + openid + ", sign=" + sign + ", prepay_id="
                + prepay_id + ", signType=" + signType + ", timeStamp="
                + timeStamp + ", packageStr=" + packageStr + ", return_code="
                + return_code + ", return_msg=" + return_msg + ", result_code="
                + result_code + ", bank_type=" + bank_type + ", cash_fee="
                + cash_fee + ", fee_type=" + fee_type + ", is_subscribe="
                + is_subscribe + ", time_end=" + time_end + ", transaction_id="
                + transaction_id + ", ip=" + ip + ", coupon_count="
                + coupon_count + ", coupon_fee=" + coupon_fee
                + ", coupon_fee_0=" + coupon_fee_0 + ", coupon_type_0="
                + coupon_type_0 + ", coupon_id_0=" + coupon_id_0 + "]";
    }
}

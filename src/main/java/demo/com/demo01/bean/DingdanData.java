package demo.com.demo01.bean;

import java.util.List;

/**
 * 作者: Wang on 2019/1/8 19:44
 * 寄语：加油！相信自己可以！！！
 */


public class DingdanData {

    /**
     * detailList : [{"commentStatus":1,"commodityCount":1,"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","commodityPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/5.jpg","commodityPrice":78,"orderDetailId":771}]
     * expressCompName : 京东快递
     * expressSn : 1001
     * orderId : 20190108194144907552
     * orderStatus : 1
     * payAmount : 78
     * payMethod : 1
     * userId : 552
     */

    private String expressCompName;
    private String expressSn;
    private String orderId;
    private int orderStatus;
    private double payAmount;
    private int payMethod;
    private int userId;
    private List<DetailListBean> detailList;

    public String getExpressCompName() {
        return expressCompName;
    }

    public void setExpressCompName(String expressCompName) {
        this.expressCompName = expressCompName;
    }

    public String getExpressSn() {
        return expressSn;
    }

    public void setExpressSn(String expressSn) {
        this.expressSn = expressSn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * commentStatus : 1
         * commodityCount : 1
         * commodityId : 19
         * commodityName : 环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋
         * commodityPic : http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/2/5.jpg
         * commodityPrice : 78
         * orderDetailId : 771
         */

        private int commentStatus;
        private int commodityCount;
        private int commodityId;
        private String commodityName;
        private String commodityPic;
        private double commodityPrice;
        private int orderDetailId;

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public int getCommodityCount() {
            return commodityCount;
        }

        public void setCommodityCount(int commodityCount) {
            this.commodityCount = commodityCount;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityPic() {
            return commodityPic;
        }

        public void setCommodityPic(String commodityPic) {
            this.commodityPic = commodityPic;
        }

        public double getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(double commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public int getOrderDetailId() {
            return orderDetailId;
        }

        public void setOrderDetailId(int orderDetailId) {
            this.orderDetailId = orderDetailId;
        }
    }
}

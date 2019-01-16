package demo.com.demo01.bean;

/**
 * 作者: Wang on 2019/1/3 12:01
 * 寄语：加油！相信自己可以！！！
 */


public class SearchData {

        /**
         * commodityId : 44
         * commodityName : 新品女鞋秋冬水钻粗跟深口单鞋新款夏季网红同款高跟鞋仙女的鞋超火的鞋子婚鞋韩版百搭乖乖鞋温柔鞋尖头晚晚鞋一脚蹬
         * masterPic : http://mobile.bwstudent.com/images/small/commodity/nx/ggx/6/1.jpg
         * price : 146
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private double price;
        private int saleNum;

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

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
}

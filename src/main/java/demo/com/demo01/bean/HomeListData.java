package demo.com.demo01.bean;

import java.util.List;

/**
 * 作者: Wang on 2018/12/30 15:45
 * 寄语：加油！相信自己可以！！！
 */


public class HomeListData {
        private List<RxxpBean> rxxp;
        private List<PzshBean> pzsh;
        private List<MlssBean> mlss;

        public List<RxxpBean> getRxxp() {
            return rxxp;
        }

        public void setRxxp(List<RxxpBean> rxxp) {
            this.rxxp = rxxp;
        }

        public List<PzshBean> getPzsh() {
            return pzsh;
        }

        public void setPzsh(List<PzshBean> pzsh) {
            this.pzsh = pzsh;
        }

        public List<MlssBean> getMlss() {
            return mlss;
        }

        public void setMlss(List<MlssBean> mlss) {
            this.mlss = mlss;
        }

        public static class RxxpBean {
            /**
             * commodityList : [{"commodityId":27,"commodityName":"休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg","price":139,"saleNum":0},{"commodityId":21,"commodityName":"【加绒休闲 舒适轻便】秋冬增高休闲鞋厚底棉鞋运动户外通勤简约韩版女鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/4/1.jpg","price":189,"saleNum":0},{"commodityId":5,"commodityName":"双头两用修容笔","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"saleNum":156}]
             * id : 1002
             * name : 热销新品
             */

            private int id;
            private String name;
            private List<CommodityListBean> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBean> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBean> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBean {
                /**
                 * commodityId : 27
                 * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
                 * masterPic : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg
                 * price : 139
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

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(int saleNum) {
                    this.saleNum = saleNum;
                }
            }
        }

        public static class PzshBean {
            /**
             * commodityList : [{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"saleNum":0},{"commodityId":15,"commodityName":"玻儿精灵美妆蛋一个","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/5/1.jpg","price":6,"saleNum":0},{"commodityId":7,"commodityName":"蓝色之恋","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/5/1.jpg","price":29,"saleNum":0},{"commodityId":3,"commodityName":"Lara style女神的魔盒全套彩妆","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499,"saleNum":2000}]
             * id : 1004
             * name : 品质生活
             */

            private int id;
            private String name;
            private List<CommodityListBeanX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanX {
                /**
                 * commodityId : 6
                 * commodityName : 轻柔系自然裸妆假睫毛
                 * masterPic : http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg
                 * price : 39
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
        }

        public static class MlssBean {
            /**
             * commodityList : [{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"saleNum":0},{"commodityId":18,"commodityName":"白色经典 秋季新款简约百搭轻便休闲女鞋板鞋小白鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/1/1.jpg","price":79,"saleNum":0}]
             * id : 1003
             * name : 魔力时尚
             */

            private int id;
            private String name;
            private List<CommodityListBeanXX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanXX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanXX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanXX {
                /**
                 * commodityId : 32
                 * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
                 * masterPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg
                 * price : 88
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
        }

}

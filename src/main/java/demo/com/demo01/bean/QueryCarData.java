package demo.com.demo01.bean;

/**
 * 作者: Wang on 2019/1/8 13:26
 * 寄语：加油！相信自己可以！！！
 */


public class QueryCarData {
    /**
     * commodityId : 27
     * commodityName : 休闲马衔扣保暖绒里棉鞋懒人鞋毛毛鞋平底女雪地靴女短靴子豆豆鞋女鞋
     * count : 1
     * pic : http://172.17.8.100/images/small/commodity/nx/ddx/3/1.jpg
     * price : 139
     */

    private int commodityId;
    private String commodityName;
    private int count;
    private String pic;
    private double price;
    boolean ischeck;

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

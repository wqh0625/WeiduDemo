package demo.com.demo01.bean;

import java.util.List;

/**
 * 作者: Wang on 2019/1/8 09:39
 * 寄语：加油！相信自己可以！！！
 */


public class MoneyData {

    private double balance;
    private List<DetailListBean> detailList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * amount : 2
         * createTime : 1542476199000
         */

        private int amount;
        private long createTime;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}

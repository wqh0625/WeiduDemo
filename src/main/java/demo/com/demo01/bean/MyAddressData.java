package demo.com.demo01.bean;

/**
 * 作者: Wang on 2019/1/5 17:18
 * 寄语：加油！相信自己可以！！！
 */


public class MyAddressData {
        /**
         * address : 北京市海淀区东北旺路八维研修学院
         * createTime : 1546730171000
         * id : 331
         * phone : 18210999490
         * realName : 王庆浩
         * userId : 552
         * whetherDefault : 1
         * zipCode : 100001
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

    @Override
    public String toString() {
        return "MyAddressData{" +
                "address='" + address + '\'' +
                ", createTime=" + createTime +
                ", id=" + id +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", userId=" + userId +
                ", whetherDefault=" + whetherDefault +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}

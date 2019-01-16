package demo.com.demo01.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者: Wang on 2018/12/28 19:49
 * 寄语：加油！相信自己可以！！！
 */

@Entity
public class LoginData {

        private String headPic;
        private String nickName;
        private String phone;
        private String sessionId;
        private int sex;
        private int status;
        @Id
        private long userId;

        private String pwd;

        @Generated(hash = 731369371)
        public LoginData(String headPic, String nickName, String phone,
                String sessionId, int sex, int status, long userId, String pwd) {
            this.headPic = headPic;
            this.nickName = nickName;
            this.phone = phone;
            this.sessionId = sessionId;
            this.sex = sex;
            this.status = status;
            this.userId = userId;
            this.pwd = pwd;
        }

        @Generated(hash = 1578814127)
        public LoginData() {
        }

    @Override
    public String toString() {
        return "LoginData{" +
                "nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public String getHeadPic() {
            return this.headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSessionId() {
            return this.sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getSex() {
            return this.sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getUserId() {
            return this.userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getPwd() {
            return this.pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
        
}

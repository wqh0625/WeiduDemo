package demo.com.demo01.bean;

/**
 * 作者: Wang on 2018/12/28 19:49
 * 寄语：加油！相信自己可以！！！
 */


public class Result<T> {

    /**
     * result : {"headPic":"http://172.17.8.100/images/small/default/user.jpg","nickName":"oO_73Wv7","phone":"18210999490","sessionId":"1546000454683552","sex":1,"userId":552}
     * message : 登录成功
     * status : 0000
     */

    private T result;
    private String message;
    private String status;
    private T orderList;

    private Object[] args;
    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                '}';
    }
}

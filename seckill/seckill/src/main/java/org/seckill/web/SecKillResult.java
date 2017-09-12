package org.seckill.web;

/**
 * Created by wangyonghua on 2017/9/12.
 */
public class SecKillResult<T> {
    private boolean status;
    private String msg;

    public SecKillResult(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public SecKillResult(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    private T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.sunlands.uedservice.bean;


/**
 * @Author : lvpenghui
 * @Description :
 * @Date : Created in 10:25 2017/12/6
 * @ModifiedBy :
 *
*/
public class ResultBean {
    private int code;
    private Object info;
    private Object data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}

package com.moti.dto;

/**
 * @ClassName: ResponseResult
 * @Description: 数据返回结果
 * @author: 莫提
 * @date 2020/9/1 10:50
 * @Version: 1.0
 **/
public class ResponseResult {

    private int code;
    private int success;
    private String message;
    private Object data;
    private String url;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", url='" + url + '\'' +
                '}';
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    public ResponseResult(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }
}

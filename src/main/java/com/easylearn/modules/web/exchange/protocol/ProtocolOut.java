package com.easylearn.modules.web.exchange.protocol;

/**
 * Created by CZH on 2017/6/27.
 */
public class ProtocolOut {
    private Boolean success;
    private String result;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

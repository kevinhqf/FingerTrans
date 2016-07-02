package com.source.kevin.fingertrans.data.webdata;

import java.util.List;

/**
 * the json result
 */
public class TransResult {
    List<String> translation;
    TransBasic basic;
    String query;
    int errorCode;
    List<WebTrans> web;



    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public TransBasic getBasic() {
        return basic;
    }

    public void setBasic(TransBasic basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<WebTrans> getWeb() {
        return web;
    }

    public void setWeb(List<WebTrans> web) {
        this.web = web;
    }
}

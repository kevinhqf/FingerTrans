package com.source.kevin.fingertrans.data.webdata;

import java.util.List;

/**
 * the json result
 */
public class TransResult {
    List<String> translation;
    TransBasic basic;
    String query;



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

}

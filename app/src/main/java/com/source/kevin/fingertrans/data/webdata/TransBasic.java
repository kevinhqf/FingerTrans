package com.source.kevin.fingertrans.data.webdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * the basic translation information
 */
public class TransBasic {

    List<String> explains;

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}

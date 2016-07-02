package com.source.kevin.fingertrans.data.webdata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * the basic translation information
 */
public class TransBasic {
    @SerializedName("us-phonetic")
    String us_phonetic;

    String phonetic;

    @SerializedName("uk-phonetic")
    String uk_phonetic;

    List<String> explains;


    public String getUs_phonetic() {
        return us_phonetic;
    }

    public void setUs_phonetic(String us_phonetic) {
        this.us_phonetic = us_phonetic;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getUk_phonetic() {
        return uk_phonetic;
    }

    public void setUk_phonetic(String uk_phonetic) {
        this.uk_phonetic = uk_phonetic;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}

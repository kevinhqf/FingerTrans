package com.source.kevin.fingertrans.data;

/**
 * the popView data
 */
public class TransInfo {
    private String query;
    private String translations;
    private String explains;

    public TransInfo(String query, String translations, String explains) {
        this.query = query;
        this.translations = translations;
        this.explains = explains;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }
}

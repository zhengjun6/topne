package com.example.hehe.topne;

/**
 * Created by hehe on 2016/4/22.
 */
public class data {
    private String url;
    private  String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public data(String url, String title) {
        this.url = url;
        this.title = title;
    }
}

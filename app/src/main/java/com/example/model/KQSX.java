package com.example.model;

import java.io.Serializable;

public class KQSX implements Serializable {
    private String title;
    private String pubDate;
    private String link;
    private String description;
    private String content;

    public KQSX(String title, String pubDate, String link, String description, String content) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.description = description;
        this.content = content;
    }

    public KQSX() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title;
    }
}

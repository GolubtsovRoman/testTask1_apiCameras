package ru.cameras.web.service.model;

public enum UrlType {
    LIVE("LIVE"),
    ARCHIVE("ARCHIVE");
    
    private String text;
    
    UrlType(String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
}

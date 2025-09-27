package com.example.test;

public class Comic {
    private int imageResId;
    private String title;
    private String chapter; // hoặc author

    public Comic(int imageResId, String title, String chapter) {
        this.imageResId = imageResId;
        this.title = title;
        this.chapter = chapter;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getChapter() {
        return chapter;
    }
}

package com.cse118.imagedownloader;

import java.sql.Blob;

public class Image {

    private int id;
    private String title;
    private byte[] image;

    public void setId(int integer) {
        id = integer;
    }

    public void setTitle(String string) {
        title = string;
    }

    public void setBlob(byte[] blob) {
        image = blob;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public byte[] getImage() {
        return image;
    }
}

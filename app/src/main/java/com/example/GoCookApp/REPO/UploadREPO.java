package com.example.GoCookApp.REPO;

public class UploadREPO {
    private String mName;
    private String mImageUrl;
    public UploadREPO() {
        //empty constructor needed
    }
    public UploadREPO(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        mName = name;
        mImageUrl = imageUrl;
    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        mName = name;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}

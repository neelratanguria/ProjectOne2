package com.estacy.neel.projectone;

/**
 * Created by Neel on 11/5/2016.
 */

public class Blog_post {

    private String title;
    private String desc;
    private String image;

    public Blog_post() {

    }

    public Blog_post(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

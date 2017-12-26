package com.leeway.templapp.Connection.Model;

/**
 * Created by Home on 1/11/2017.
 */

public class EventBean {
    String color;
    String title;
    String smalltiel;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventBean(String smalltiel, String title, String color, String date) {
        this.color = color;
        this.date = date;
        this.smalltiel = smalltiel;
        this.title = title;
    }

    public EventBean(String smalltiel, String title, String color) {
        this.smalltiel = smalltiel;
        this.title = title;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSmalltiel() {
        return smalltiel;
    }

    public void setSmalltiel(String smalltiel) {
        this.smalltiel = smalltiel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.rkjc.news_app_2;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "news_item")
public class NewsItem {



    @NonNull
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = true)
    int id;


    @NonNull
    @ColumnInfo(name="author")
    String author;

    @NonNull
    @ColumnInfo(name="title")
    String title;

    @NonNull
    @ColumnInfo(name="description")
    String description;

    @NonNull
    @ColumnInfo(name="url")
    String url;

    @NonNull
    @ColumnInfo(name="urlToImage")
    String urlToImage;

    @NonNull
    @ColumnInfo(name="publishedAt")
    String publishedAt;


    public NewsItem(int id, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @Ignore
    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public void setAuthorFromJSON(String aName){
        author = aName;
    }
    public void setTitleFromJSON(String tName){
        title = tName;
    }
    public void setDescriptionFromJSON(String dName){
        description = dName;
    }
    public void setUrlFromJSON(String uName){
        url = uName;
    }
    public void setUrlToImageFromJSON(String uImageName){
        urlToImage = uImageName;
    }
    public void setPublishedFromJSON(String pName){
        publishedAt = pName;
    }
    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }
    public String getAuthorFromJSON(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public String getUrlToImageFromJSON(){
        return urlToImage;
    }
    public String getPublishedAtFromJSON(){
        return publishedAt;
    }
}

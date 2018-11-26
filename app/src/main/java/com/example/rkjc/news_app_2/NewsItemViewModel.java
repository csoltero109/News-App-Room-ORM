package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel{
    private NewsItemRepository mRespository;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel(@NonNull Application application){
        super(application);
        mRespository = new NewsItemRepository(application);
        mAllNewsItems = mRespository.getmAllNewsItems();
    }

    public LiveData<List<NewsItem>> getmAllNewsItems() {
        return mAllNewsItems;
    }

    public void insert(List<NewsItem> newsItem){
        mRespository.insert(newsItem);
    }

    public void clearAll(){
        mRespository.clearAll();
    }

    public void databaseSyncSetter(){
        mRespository.databaseSyncSetter();
    }
}

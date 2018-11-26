package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsItemRepository {
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;

    NewsItemRepository(Application application){
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    public void clearAll(){
        new deleteNewsItemsAsync().execute(mNewsItemDao);
    }

    LiveData<List<NewsItem>> getmAllNewsItems(){
        return mAllNewsItems;
    }

    public void insert(List<NewsItem> newsItemList){
        mNewsItemDao.insert(newsItemList);
    }

    private static class deleteNewsItemsAsync extends AsyncTask<NewsItemDao, Void, Void>{
        @Override
        protected Void doInBackground(NewsItemDao...params){
            Log.e("Delete Async Task: ", "Deleting " + params[0].toString());
            params[0].clearAll();
            return null;
        }
    }


    private static class insertAsyncTask extends AsyncTask<URL,Void,Void> {
        private NewsItemDao mAsyncTaskDao;
        insertAsyncTask(NewsItemDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(URL...params){
            try {
                JsonUtils json = new JsonUtils();
                NetworkUtils response = new NetworkUtils();
                String text = response.getResponseFromHttpUrl(params[0]);
                mAsyncTaskDao.insert(json.parseNews(text));
                Log.e("Insert Async Task: ", "data inserted.");
            }
            catch(IOException e){
                e.printStackTrace();
                Log.e("Insert Async Task: ", "issue with inserting data.");
            }
            return null;
        }
    }

    public void databaseSyncSetter(){
        new deleteNewsItemsAsync().execute(mNewsItemDao);
        new insertAsyncTask(mNewsItemDao).execute(NetworkUtils.buildUrl());
    }


}

package com.example.rkjc.news_app_2;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;

import android.os.AsyncTask;
import android.util.Log;

public class JobServiceSync extends JobService {

    AsyncTask asyncTaskHandler;

    @Override
    public boolean onStartJob(JobParameters job) {
        final NewsItemRepository n = new NewsItemRepository(getApplication());
        asyncTaskHandler = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                NotificationUtils.closeNotifications(getApplicationContext());
                n.databaseSyncSetter();
                return null;
            }

        };
        asyncTaskHandler.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
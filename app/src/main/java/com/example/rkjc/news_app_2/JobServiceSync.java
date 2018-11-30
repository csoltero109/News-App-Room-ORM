package com.example.rkjc.news_app_2;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;
import android.util.Log;

public class JobServiceSync extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
        new NewsItemRepository(getApplication()).databaseSyncSetter();
        Log.e("Sync","Working Properly");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
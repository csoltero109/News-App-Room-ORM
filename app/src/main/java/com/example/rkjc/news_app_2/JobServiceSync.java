package com.example.rkjc.news_app_2;
import android.app.Application;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.JobParameters;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

public class JobServiceSync extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {
        NewsItemRepository n = new NewsItemRepository(getApplication());
        n.databaseSyncSetter();
        Log.e("Sync","Working Properly");
        Toast.makeText(getApplicationContext(),"Syncing", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}

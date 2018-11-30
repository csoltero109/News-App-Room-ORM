package com.example.rkjc.news_app_2;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class ScheduleUtilities {
    private static final String NEWS_JOB_TAG = "News Sync Tag";
    private static boolean sInitialized;
    private static final int SCHEDULE_INTERVAL_MINUTES = 1;
    private static final int SCHEDULE_INTERVAL_SECONDS = (int) (TimeUnit.MINUTES.toSeconds(SCHEDULE_INTERVAL_MINUTES));
    private static final int SYNC_FLEXTIME = SCHEDULE_INTERVAL_SECONDS;

    synchronized public static void scheduleRefresh(@NonNull final Context context){
        if(sInitialized) return;

        GooglePlayDriver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job constraintRefreshJob = dispatcher.newJobBuilder()
                .setService(JobServiceSync.class)
                .setTag(NEWS_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0,1))
//                .setTrigger(Trigger.executionWindow(SCHEDULE_INTERVAL_SECONDS,SCHEDULE_INTERVAL_SECONDS + SYNC_FLEXTIME))
                .setReplaceCurrent(true)
//                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build();

        dispatcher.mustSchedule(constraintRefreshJob);
        sInitialized = true;

    }
}

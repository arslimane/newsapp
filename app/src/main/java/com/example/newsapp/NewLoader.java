package com.example.newsapp;


import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {

    /* Query URL */
    private final String queryUrl;

    /**
     * @param context of the activity.
     * @param url     to load data from.
     */
    public NewsLoader(Context context, String url) {
        super(context);
        queryUrl = url;
    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     */
    @Override
    protected void onStartLoading() {
        // Trigger the loadInBackground() method to execute.
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public List<News> loadInBackground() {
        if (queryUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news.
        List<News> newsData;
        newsData = QueryUtils.fetchNewsData(queryUrl);
        return newsData;
    }
}


package com.example.newsapp;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<News>> {
    public String query = "";
    public Adapter newsAdapter;
    List<News> newsList;
    private ListView list_view;
    private TextView error;
    private SearchView searchView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = findViewById(R.id.list);
        searchView = findViewById(R.id.search);
        button = findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = searchView.getQuery().toString();
                searchView.setQuery("", true);
                LoaderManager loaderManager = getSupportLoaderManager();
                Constant.NEWS_LOADER_ID++;
                loaderManager.initLoader(Constant.NEWS_LOADER_ID, null, MainActivity.this);
            }
        });
        error = findViewById(R.id.message_error);

        if (newsList == null) newsList = new ArrayList<>();
        newsAdapter = new Adapter(this, newsList);
        list_view.setAdapter(newsAdapter);

        if (QueryUtils.isConnected(this)) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(Constant.NEWS_LOADER_ID, null, this);
        } else {
            upDateViewNoData("there is no connection");
        }

    }

    private void upDateViewNoData(String s) {
        error.setVisibility(View.VISIBLE);
        error.setText(s);
        list_view.setVisibility(View.GONE);


    }


    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String maxNews = sharedPreferences.getString(
                getString(R.string.settings_max_news_key),
                getString(R.string.settings_max_news_default));
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));
        Uri baseUri = Uri.parse(Constant.BASE_URL);
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(Constant.KEY_SHOW_TAGS, Constant.KEY_CONTRIBUTOR);
        builder.appendQueryParameter(Constant.KEY_ORDER_BY, orderBy);
        builder.appendQueryParameter(Constant.KEY_SHOW_FIELD, Constant.KEY_ALL);
        builder.appendQueryParameter(Constant.KEY_PAGE_SIZE, maxNews);
        builder.appendQueryParameter(Constant.API_KEY, Constant.KEY_TEST);
        if (!query.equals("")) {
            builder.appendQueryParameter("q", query);
        }
        return new NewsLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {

        newsAdapter.clearAll();

        if ((data != null) && !data.isEmpty()) {
            newsAdapter.addAll(data);
            error.setVisibility(View.GONE);
            list_view.setVisibility(View.VISIBLE);
        } else {
            upDateViewNoData("there is no news to show");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clearAll();
    }


}

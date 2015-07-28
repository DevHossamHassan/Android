package com.example.trunghoang.retrofit_webservicerest.activity;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.trunghoang.retrofit_webservicerest.R;
import com.example.trunghoang.retrofit_webservicerest.model.Repo;
import com.example.trunghoang.retrofit_webservicerest.utils.GithubService;

import java.util.List;

import retrofit.Callback;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        appelSynchrone();
        appelAsynchrone();
    }

    private void appelSynchrone() {
        new ListReposTask().execute("iamruby1991");
    }

    private void appelAsynchrone() {
        GithubService githubService = new RestAdapter.Builder()
                .setEndpoint(GithubService.ENDPOINT)
                .setLog(new AndroidLog("retrofitting"))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build().create(GithubService.class);

        githubService.listReposAsync("iamruby1991", new Callback<List<Repo>>() {
            @Override
            public void success(List<Repo> repoList, Response response) {
                afficherRepos(repoList);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void afficherRepos (List<Repo> repoList) {
        Toast.makeText(this, "Number of deposits: " + repoList.size(), Toast.LENGTH_LONG).show();
    }

    class ListReposTask extends AsyncTask<String, Void, List<Repo>> {

        @Override
        protected List<Repo> doInBackground(String... params) {
            try {
                GithubService githubService = new RestAdapter.Builder()
                        .setEndpoint(GithubService.ENDPOINT)
                        .build().create(GithubService.class);

                String user = params[0];
                List<Repo> repoList = githubService.listRepos(user);
                return repoList;
            }catch (Exception e) {
                Log.d("Error: ", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Repo> repos) {
            super.onPostExecute(repos);
            afficherRepos(repos);
        }
    }
}

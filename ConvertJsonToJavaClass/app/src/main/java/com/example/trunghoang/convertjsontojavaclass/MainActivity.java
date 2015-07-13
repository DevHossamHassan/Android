package com.example.trunghoang.convertjsontojavaclass;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.trunghoang.model.MonHoc;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    TextView tvMonHoc, tvNoiHoc, tvFanPage, tvWebSites;
    ImageView imgLogo;
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMonHoc = (TextView)findViewById(R.id.tvMonHoc);
        tvNoiHoc = (TextView)findViewById(R.id.tvNoiHoc);
        tvFanPage = (TextView)findViewById(R.id.tvFanpage);
        tvWebSites = (TextView)findViewById(R.id.tvWebsites);
        imgLogo = (ImageView)findViewById(R.id.imvLogo);
        btnLoad = (Button)findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DownloadInfoForMonHoc().execute("http://khoapham.vn/KhoaPhamTraining/android/json/demo3.json");
                    }
                });
            }
        });
    }

    private class DownloadInfoForMonHoc extends AsyncTask<String, Integer, MonHoc> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected MonHoc doInBackground(String... params) {
            String urlString = params[0];
            try {
                URL url = new URL(urlString);
                // đọc stream json
                InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                // chuyển định dạng json về java class
                MonHoc monhoc = new Gson().fromJson(reader, MonHoc.class);
                return monhoc;
            }catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(MonHoc monhoc) {
            tvWebSites.setText(monhoc.getWebsite());
            tvMonHoc.setText(monhoc.getMonhoc());
            tvNoiHoc.setText(monhoc.getNoihoc());
            tvFanPage.setText(monhoc.getFanpage());
            super.onPostExecute(monhoc);
            new DownloadLogo().execute(monhoc.getLogo());
        }
    }

    public Drawable LoadImageFromWeb(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    private class DownloadLogo extends AsyncTask<String, Integer, Drawable> {

        @Override
        protected Drawable doInBackground(String... params) {

            return LoadImageFromWeb(params[0]);
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            imgLogo.setImageDrawable(drawable);
            super.onPostExecute(drawable);
        }
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
}

package com.example.trunghoang.googlesearchtool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.com.trunghoang.utils.GoogleSearchAPI;
import com.trunghoang.model.GoogleData;
import com.trunghoang.model.Result;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    EditText txtQuery;
    Button btnSearch;

    ListView lvResult;
    List<Result> arrData;
    MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtQuery= (EditText) findViewById(R.id.txtQuery);
        txtQuery.setText("Hang sơn Đòng");
        btnSearch= (Button) findViewById(R.id.btnTimKiem);
        lvResult= (ListView) findViewById(R.id.lvResult);

        arrData=new ArrayList<Result>();
        adapter=new MyArrayAdapter(MainActivity.this,R.layout.custom_row_layout,arrData);
        lvResult.setAdapter(adapter);
    }

    public void addEvents() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSearch();
            }
        });

        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrData.get(position).getUrl()));
                startActivity(browserIntent);
            }
        });
    }

    public void processSearch() {
        arrData.clear();
        GoogleSearchThread thread = new GoogleSearchThread();
        thread.execute(txtQuery.getText().toString());
    }

    private class GoogleSearchThread extends AsyncTask<String, Result, List<Result>> {

        @Override
        protected List<Result> doInBackground(String... params) {
            String query = params[0];
            GoogleData data = GoogleSearchAPI.search(query, "UTF-8", 0, 8);
            if(data.getResponseData() == null) {
                return null;
            }

            for (Result result: data.getResponseData().getResults()) {
                publishProgress(result);
                SystemClock.sleep(1000);
            }
            return data.getResponseData().getResults();
        }

        @Override
        protected void onProgressUpdate(Result... values) {
            super.onProgressUpdate(values);
            arrData.add(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(List<Result> results) {
            super.onPostExecute(results);
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

package com.example.trunghoang.todolistwithparseloud;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button btnSave;
    EditText editTextWorking;
    ListView lvWorking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "WQEoNyBoly67NlQGWMBNvZzVcLaQPMhLlfxc3hWo", "knX0LtXl3vdmrr0EciAnrkddUDiajL3IUPa2sTm3");

        btnSave = (Button) findViewById(R.id.buttonSave);
        editTextWorking = (EditText) findViewById(R.id.editTextWorkName);
        lvWorking = (ListView) findViewById(R.id.listViewWork);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String work = editTextWorking.getText().toString();
                ParseObject parseObject = new ParseObject("WorkList");
                parseObject.put("WorkName", work);

                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        final ArrayList<String> arrayDataWokList = new ArrayList<String>();
                        // Parse get Data
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("WorkList");
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    for (ParseObject work : list) {
                                        arrayDataWokList.add(work.getString("WorkName"));
                                    }
                                    ArrayAdapter<String> adapterWork = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayDataWokList);
                                    lvWorking.setAdapter(adapterWork);
                                }
                            }
                        });

                    }
                });

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
}

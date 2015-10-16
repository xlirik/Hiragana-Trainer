package com.xlirik.hiragana;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResultsActivity extends ActionBarActivity {

    private DBHelper db;
    private List<Results> results;
    private ListView lvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        lvResults = (ListView)findViewById(R.id.listView);
        db = new DBHelper(this);
        results = db.getAllContacts();

        for (Results cn : results) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getResult() + " ,Phone: " + cn.getTime();
            System.out.print("Name: ");
            System.out.println(log);
        }

        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (Results item : results) {
            Map<String, String> datum = new HashMap<String, String>(2);
            datum.put("result", item.getResult());
            datum.put("time", item.getTime());
            data.add(datum);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.lv_custom,
                new String[] {"result", "time"}, new int[] {R.id.textView, R.id.textView2});
        lvResults.addFooterView(new View(this), null, true);
        lvResults.setAdapter(adapter);

        try {
	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            deleteDatabase("resultsManager");
            this.finish();
            return true;
        }
	    if (id == android.R.id.home){
			this.finish();
			return true;
	    }
        return super.onOptionsItemSelected(item);
    }
}

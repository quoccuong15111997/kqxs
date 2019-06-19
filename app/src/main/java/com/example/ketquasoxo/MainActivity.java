package com.example.ketquasoxo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.KQSX;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvDate;
    private ArrayAdapter<KQSX> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        getAPI();
    }

    private void addEvents() {
        lvDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("KQ",arrayAdapter.getItem(i));
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvDate=findViewById(R.id.lvDate);
        arrayAdapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1);
        lvDate.setAdapter(arrayAdapter);

    }

    private void getAPI() {
        GetAPIKQXS getAPIKQXS=new GetAPIKQXS();
        getAPIKQXS.execute();

    }
    class GetAPIKQXS extends AsyncTask<Void,Void, ArrayList<KQSX>>{
        @Override
        protected void onPostExecute(ArrayList<KQSX> kqsxes) {
            super.onPostExecute(kqsxes);
            arrayAdapter.addAll(kqsxes);
            arrayAdapter.notifyDataSetChanged();
        }

        @Override
        protected ArrayList<KQSX> doInBackground(Void... voids) {
            ArrayList<KQSX> kqsxes= new ArrayList<>();
            try {
                URL url= new URL(" https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fxskt.com.vn%2Frss-feed%2Ftien-giang-xstg.rss");
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                JSONObject jsonObject= new JSONObject(builder.toString());
                JSONArray arrItem=jsonObject.getJSONArray("items");
                for (int i=0;i<arrItem.length();i++){
                    JSONObject item=arrItem.getJSONObject(i);
                    KQSX kqsx= new KQSX();
                    kqsx.setTitle(item.getString("title"));
                    kqsx.setPubDate(item.getString("pubDate"));
                    kqsx.setLink(item.getString("link"));
                    kqsx.setDescription(item.getString("description"));
                    kqsx.setContent(item.getString("content"));
                    kqsxes.add(kqsx);
                }
            }
            catch (Exception ex){
                Log.e("MAIN",ex.toString());
            }
            return kqsxes;
        }
    }
}

package com.example.ketquasoxo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.KQSX;

public class DetailActivity extends AppCompatActivity {
    private TextView txtG1, txtG2, txtG3, txtG4, txtG5, txtG6, txtG7, txtG8, txtGDB,txtTitle;
    private KQSX kqsx;
    private String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent= getIntent();
        kqsx= (KQSX) intent.getSerializableExtra("KQ");
        data=kqsx.getDescription();
        addControls();
    }

    private void addControls() {
        txtTitle=findViewById(R.id.txtTitle);
        txtG1=findViewById(R.id.txtG1);
        txtG2=findViewById(R.id.txtG2);
        txtG3=findViewById(R.id.txtG3);
        txtG4=findViewById(R.id.txtG4);
        txtG5=findViewById(R.id.txtG5);
        txtG6=findViewById(R.id.txtG6);
        txtG7=findViewById(R.id.txtG7);
        txtG8=findViewById(R.id.txtG8);
        txtGDB=findViewById(R.id.txtGDB);
        convertData();
    }

    private void convertData() {
        txtTitle.setText(kqsx.getTitle());
        String []items=data.split(":");
        txtGDB.setText(doRegex(items[1].trim()));
        txtG1.setText(doRegex(items[2].trim()));
        txtG2.setText(doRegex(items[3].trim()));
        txtG3.setText(doRegex(items[4].trim()));
        txtG4.setText(doRegex(items[5].trim()));
        txtG5.setText(doRegex(items[6].trim()));
        txtG6.setText(doRegex(items[7].trim()));
        txtG7.setText(doRegex(items[8].trim()));
        txtG8.setText(doRegex(items[9].trim()));
    }
    private String doRegex(String s){
        String []kq=s.split("\n");
        return  kq[0];
    }
}

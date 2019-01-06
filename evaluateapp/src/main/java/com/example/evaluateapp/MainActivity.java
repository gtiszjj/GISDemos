package com.example.evaluateapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lvUserInfos);
        String[] data = {"姓名：赵境境", "岗位：高级工程师", "工号：M-00001", "星级：5级"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_lvitem, data);
        listView.setAdapter(adapter);
    }
}

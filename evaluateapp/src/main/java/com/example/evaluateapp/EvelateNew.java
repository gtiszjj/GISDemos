package com.example.evaluateapp;


import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvelateNew extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    public EvelateNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evelate_new, container, false);
        gridView = view.findViewById(R.id.gvEvaluate);
        //初始化数据
        initData();

        String[] from = {"img", "text"};
        int[] to = {R.id.ivGridPNG, R.id.tvGridContext};
        adapter = new SimpleAdapter(view.getContext(), dataList, R.layout.gridview_item, from, to);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);


        return view;
    }

    void initData() {
        //图标
        int icno[] = {R.mipmap.fcmy, R.mipmap.my, R.mipmap.yb};
        //图标下的文字
        String name[] = {"非常满意", "满意", "一般"};
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < icno.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icno[i]);
            map.put("text", name[i]);
            dataList.add(map);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                try {
                    // 不能使用localhost，否则会被解析为模拟器本身
                    URL url = new URL("http://192.168.1.100:8080/EvaluateServlet?username=admin");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(2000);
                    conn.setReadTimeout(2000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
                    conn.setDoInput(true);

                    // httpurlconnection是同步请求，必须放在子线程，否则connect卡死
                    conn.connect();

                    InputStream inputStream = conn.getInputStream();
                    // 字节流转字符流
                    reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        Looper.prepare();       // 下面的toast.maketext，在主线程会默认创建一个looper，但是在子线程不会
                        Toast.makeText(getActivity(), line, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

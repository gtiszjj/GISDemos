package com.example.evaluateapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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
        Toast.makeText(view.getContext(), dataList.get(position).get("text").toString(), Toast.LENGTH_SHORT).show();
    }
}

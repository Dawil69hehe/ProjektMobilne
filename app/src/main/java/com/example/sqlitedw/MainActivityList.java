package com.example.sqlitedw;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityList extends AppCompatActivity {
    ListView listview;
    HashMap<String,Object> hashMap;
    DataBaseHelper mydb;
    ArrayList<HashMap<String,Object>> list;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        listview=findViewById(R.id.simpleListView);
        list=new ArrayList<>();
        mydb=new DataBaseHelper(this);
        List<String> list1 = new ArrayList<String>();

        //
        Cursor cursor = mydb.getAlldata();
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append(cursor.getString(0)+ "\n" + cursor.getString(1)+ " " + cursor.getString(2)+"zł\n" + cursor.getString(3)+"\n" + cursor.getString(4));
            list1.add(buffer.toString());
            buffer = new StringBuffer();
        }

        ActionBar ab = getSupportActionBar();

        //Up button
        ab.setDisplayHomeAsUpEnabled(true);


        for(int i=0;i<list1.size();i++)
        {
            hashMap=new HashMap<>();
            hashMap.put("name",list1.get(i));
            list.add(hashMap);
        }

        String[] from={"name","image"};
        int[] to={
                R.id.textView
        };
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getApplicationContext(),
                list,
                R.layout.list_view_items,
                from,
                to
        );




        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),piecesName[i], Toast.LENGTH_SHORT).show();
            }

        });


    }


}
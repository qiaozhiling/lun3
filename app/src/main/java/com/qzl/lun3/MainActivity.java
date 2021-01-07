package com.qzl.lun3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Data data;//数据
    RecyclerAdapter adapter;//适配器

    /////////////////////// //////onCreate/////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new Data();

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter(this, data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                toDetail(v);
            }
        });
    }

    /////////////////////////////Detail///////////////////////////////////////
    public void toDetail(View v) {

        ImageView headPic = v.findViewById(R.id.headPic);
        TextView groupName = v.findViewById(R.id.groupName);
        TextView creator = v.findViewById(R.id.creator);


        Bundle bundle = new Bundle();


        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //////////////////////////////Menu////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.add):
                add();
                return true;
            case (R.id.clear):
                clear();
                return true;
            case (R.id.exit):
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void add() {
        /*if (data.creator.size() >= 4 || data.groupName.size() >= 4) {
            Toast.makeText(this, "群聊数量上限", Toast.LENGTH_SHORT).show();
            return;
        }*/

        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 0);
    }

    private void clear() {
        data.creator.clear();
        data.groupName.clear();
        adapter.notifyDataSetChanged();
    }

    ////////////////////////////onActivityResult///////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);

        if (dataIntent == null) {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        } else if ((data.groupName).contains(dataIntent.getExtras().getString("GroupName"))) {
            Toast.makeText(this, "添加失败,群聊已存在", Toast.LENGTH_SHORT).show();
        } else {
            data.groupName.add(dataIntent.getExtras().getString("GroupName"));
            data.creator.add(dataIntent.getExtras().getString("Creator"));
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "群聊创建成功", Toast.LENGTH_SHORT).show();
        }

    }

}
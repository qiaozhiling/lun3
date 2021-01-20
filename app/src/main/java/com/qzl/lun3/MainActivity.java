package com.qzl.lun3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
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

    //////////////////////////////onCreate/////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new Data();//实例数据

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter(this, data);//实例适配器
        recyclerView.setAdapter(adapter);//设置适配器

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int headPicId) {
                toDetail(v, headPicId);
            }
        });//
    }

    /////////////////////////////toDetail///////////////////////////////////////

    /**
     * 跳转到DetailActivity
     *
     * @param v         被点击的Item
     * @param headPicId 头像id
     */
    public void toDetail(View v, int headPicId) {

        TextView groupName = v.findViewById(R.id.groupName);
        TextView creator = v.findViewById(R.id.creator);

        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("picId", headPicId);//put头像图片id
        bundle.putString("groupName", groupName.getText().toString());
        bundle.putString("creator", creator.getText().toString());

        intent.putExtras(bundle);
        startActivity(intent);
    }

    //////////////////////////////Menu////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用)
         * Inflate the menu; this adds items to the action barif it is present.
         */
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_, menu);

        //return true; ???
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
        }//通过item的id判断执行

        return super.onOptionsItemSelected(item);
    }//菜单点击事件

    private void add() {
        /*if (data.creator.size() >= 4 || data.groupName.size() >= 4) {
            Toast.makeText(this, "群聊数量上限", Toast.LENGTH_SHORT).show();
            return;
        }*/

        Intent intent = new Intent(this, AddActivity.class);

        //启动一个带请求码的Activity，Activity结束时候回调onActivutyResult()方法
        startActivityForResult(intent, 0);

    }//增加群聊

    private void clear() {
        data.creator.clear();
        data.groupName.clear();
        adapter.notifyDataSetChanged();
    }//清除群聊 清空创建者和群聊名列表

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

            adapter.notifyDataSetChanged();//刷新列表
            Toast.makeText(this, "群聊创建成功", Toast.LENGTH_SHORT).show();

        }

    }//Activity结束时候回调onActivutyResult()

}
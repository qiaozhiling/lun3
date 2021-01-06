package com.qzl.lun3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Integer> headPic;//头像
    ArrayList<String> groupName = new ArrayList<>();//群名
    ArrayList<String> creator = new ArrayList<>();//群聊创造者
    Adapter adapter;//适配器

    /////////////////////// //////onCreate/////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headPic = new ArrayList<>();//头像
        groupName = new ArrayList<>();//群名
        creator = new ArrayList<>();//群聊创造者

        headPic.add(R.drawable.head1);
        headPic.add(R.drawable.head2);
        headPic.add(R.drawable.head3);
        headPic.add(R.drawable.head4);

        groupName.add("Agroup");
        creator.add("A");


        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(headPic, groupName, creator);
        recyclerView.setAdapter(adapter);


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
        if (creator.size() >= 4 || groupName.size() >= 4) {
            Toast.makeText(this, "群聊数量上限", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 0);
    }

    private void clear() {
        creator.clear();
        groupName.clear();
        adapter.notifyDataSetChanged();
    }

    ////////////////////////////onActivityResult///////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null || groupName.contains(data.getExtras().getString("GroupName"))) {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        } else {
            groupName.add(data.getExtras().getString("GroupName"));
            creator.add(data.getExtras().getString("Creator"));
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "创建成功", Toast.LENGTH_SHORT).show();
        }

    }

    /////////////////////// //////Adapter/////////////////////////////////////
    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        List<Integer> headPic;//头像
        List<String> groupName;//群名
        List<String> creator;//群聊创造者



        public Adapter(List<Integer> headPic, List<String> groupName, List<String> creator) {
            this.headPic = headPic;
            this.groupName = groupName;
            this.creator = creator;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.recycler_, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.headPic.setImageResource(headPic.get(groupName.size() - position - 1));
            holder.groupName.setText(groupName.get(groupName.size() - position - 1));
            holder.creator.setText(creator.get(creator.size() - position - 1));

        }

        @Override
        public int getItemCount() {
            return groupName.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView headPic;
            TextView creator;
            TextView groupName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                headPic = itemView.findViewById(R.id.headPic);
                creator = itemView.findViewById(R.id.creator);
                groupName = itemView.findViewById(R.id.groupName);

            }
        }
    }

}
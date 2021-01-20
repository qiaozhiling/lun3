package com.qzl.lun3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Button button;
    ImageView headPicDetail;
    TextView groupNameDetail, creatorNameDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = this.getIntent();
        headPicDetail = findViewById(R.id.headPicDetail);
        groupNameDetail = findViewById(R.id.groupNameDetail);
        creatorNameDetail = findViewById(R.id.creatorNameDetail);

        headPicDetail.setImageResource( /*头像图片的id*/ intent.getExtras().getInt("picId"));
        groupNameDetail.setText(intent.getExtras().getString("groupName"));
        creatorNameDetail.setText(intent.getExtras().getString("creator"));

        button = findViewById(R.id.sure);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
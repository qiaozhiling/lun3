package com.qzl.lun3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    Button buttonCreate;
    EditText editGroupName, editCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        buttonCreate = findViewById(R.id.buttonCreate);
        editGroupName = findViewById(R.id.editGroupName);
        editCreator = findViewById(R.id.editCreator);

        buttonCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editGroupName.getText().toString().equals("") | editCreator.getText().toString().equals("")) {
                    Toast.makeText(AddActivity.this, "创建失败,群聊名或创建者为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = getIntent();
                Bundle bundle = new Bundle();

                bundle.putString("GroupName", editGroupName.getText().toString());
                bundle.putString("Creator", editCreator.getText().toString());

                intent.putExtras(bundle);
                setResult(1, intent);
                finish();

            }
        });

    }
}
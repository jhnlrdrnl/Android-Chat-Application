package com.example.sockets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final String TEMP_PATH = "http://192.168.1.2:3000";
    private final String SERVER_PATH = "wss://roundtable-euzn2srv2.temarotech.net:6050";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, BackgroundService.class);
        startService(serviceIntent);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
        }

        EditText username = findViewById(R.id.editText);

        findViewById(R.id.enterButton).setOnClickListener(v -> {
            if (!username.getText().toString().equals("")) {
                Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra("name", username.getText().toString());
                intent.putExtra("server", TEMP_PATH);
                startActivity(intent);
            }
        });
    }
}
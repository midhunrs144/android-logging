package com.midhun.androidloggingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.midhun.logging_util.LogAndWrite;
import com.midhun.logging_util.LogClass;

public class MainActivity extends AppCompatActivity {

    TextView click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogAndWrite.newInstance().execute("test");

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogAndWrite.newInstance().execute("clicked");
            }
        });
    }
}

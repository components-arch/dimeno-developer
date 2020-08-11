package com.dimeno.developer.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wangzhen.router.Router;
import com.wangzhen.statusbar.DarkStatusBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DarkStatusBar.get().fitDark(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        SharedPreferences preferences = getSharedPreferences("developer", Context.MODE_PRIVATE);
        ((TextView) findViewById(R.id.textView)).setText(String.format("当前环境：%s", preferences.getBoolean("env", true) ? "测试" : "正式"));
    }

    public void onClick(View view) {
        Router.with(this).toPath("/developer");
    }
}
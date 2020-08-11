package com.dimeno.developer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.wangzhen.router.Router;
import com.wangzhen.statusbar.DarkStatusBar;

/**
 * DeveloperActivity
 * Created by wangzhen on 2020/8/11.
 */
public class DeveloperActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private SharedPreferences mPreferences;
    private boolean mEnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DarkStatusBar.get().fitDark(this);
        setContentView(R.layout.activity_developer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener((v) -> finish());
        View inflate = View.inflate(this, R.layout.toolbar_layout, toolbar);
        ((TextView) inflate.findViewById(R.id.toolbar_title)).setText("开发者选项");

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mPreferences = getSharedPreferences("developer", Context.MODE_PRIVATE);
        mEnv = mPreferences.getBoolean("env", true);
        mRadioGroup.check(mEnv ? R.id.radio_test : R.id.radio_formal);
    }

    @Override
    public void finish() {
        boolean env = mRadioGroup.getCheckedRadioButtonId() == R.id.radio_test;
        if (mEnv != env) {
            new AlertDialog.Builder(this).setTitle("FBI Warning").setMessage("切换环境需要重启").setNegativeButton("放弃", null).setPositiveButton("立即重启", (dialog, which) -> {
                mPreferences.edit().putBoolean("env", env).commit();
                Bundle bundle = new Bundle();
                bundle.putBoolean("init", true);
                Router.with(DeveloperActivity.this).setExtras(bundle).toPath("/login");
                DeveloperActivity.super.finish();
            }).create().show();
        } else {
            super.finish();
        }
    }
}
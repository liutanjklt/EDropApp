package net.edrop.edrop_user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.edrop.edrop_user.R;

import java.io.File;
import java.io.RandomAccessFile;

import static com.mob.MobSDK.getContext;

/**
 * Created by 李诗凡.
 * User: sifannnn
 * Date: 2019/12/10
 * Time: 20:29
 * TODO：设置的主页面
 */
public class SettingActivity extends AppCompatActivity {
    private ImageView imageView;
    private RelativeLayout aboutlayout;
    private RelativeLayout updatelayout;
    private RelativeLayout cachelayout;
    private RelativeLayout feedbacklayout;
    private Button button;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉顶部标题
        setContentView(R.layout.setting_main);
        findViews();
        initEvent();
    }


    /**
     * Created: sifannnn
     * TODO：获取控件对象
     */
    private void findViews() {
        imageView = findViewById(R.id.iv_setting_back);
        aboutlayout = findViewById(R.id.rl_setting_about);
        updatelayout = findViewById(R.id.rl_setting_update);
        feedbacklayout = findViewById(R.id.rl_setting_feedback);
        cachelayout = findViewById(R.id.rl_setting_cache);
        button = findViewById(R.id.btn_setting_quit);
    }

    /**
     * Created: sifannnn
     * TODO：绑定监听器
     */
    private void initEvent() {
        imageView.setOnClickListener(new MyListener());
        aboutlayout.setOnClickListener(new MyListener());
        updatelayout.setOnClickListener(new MyListener());
        feedbacklayout.setOnClickListener(new MyListener());
        cachelayout.setOnClickListener(new MyListener());
        button.setOnClickListener(new MyListener());
    }

    /**
     * Created: sifannnn
     * TODO：设置监听处理方法
     */
    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_setting_back://返回
                    finish();
                    break;
                case R.id.rl_setting_about://关于
                    intent = new Intent(SettingActivity.this, AboutEDropActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.rl_setting_update://更新信息
                    intent = new Intent(SettingActivity.this, VersionActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.rl_setting_feedback://反馈消息
                    intent = new Intent(SettingActivity.this, FeedBackActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.rl_setting_cache://清除缓存
                    intent = new Intent(SettingActivity.this, ClearCacheActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case R.id.btn_setting_quit://退出账号
                    Toast.makeText(SettingActivity.this, "退出账号", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
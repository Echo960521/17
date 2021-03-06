package com.example.a17;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";

    //键
    private final static String Key_UserName="UserName";//用户名
    private final static String Key_LoginDate="LoginDate";//登录时间
    private final static String Key_UserType="UserType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获得SharedPreferences实例
        preferences=getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor=preferences.edit();

        Button btnWrite=(Button)findViewById(R.id.ButtonWrite);
        Button btnRead=(Button)findViewById(R.id.ButtonRead);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //格式化日期，将日期按照年月日时分秒格式转换为字符串形式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String strLoginDate = simpleDateFormat.format(new Date());

                //写入键值对
                editor.putString(Key_UserName, "张笑佳");
                editor.putString(Key_LoginDate, "软工1401");
                editor.putInt(Key_UserType, 2014012715);

                //提交修改，此处换成commit()也可以
                editor.apply();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserName = preferences.getString(Key_UserName, null);
                String strLoginDate = preferences.getString(Key_LoginDate, null);
                int nUserType=preferences.getInt(Key_UserType,0);
                if (strUserName != null && strLoginDate != null)
                    Toast.makeText(MainActivity.this, "姓名：" + strUserName + "班级：" + strLoginDate+"学号："+nUserType, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "无数据", Toast.LENGTH_LONG).show();
            }
        });
    }
}


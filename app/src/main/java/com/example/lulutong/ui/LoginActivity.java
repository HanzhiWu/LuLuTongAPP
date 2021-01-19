package com.example.lulutong.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.lulutong.R;
import com.example.lulutong.widget.dialog.DialogMaker;
import com.example.lulutong.utils.okhttp.CallBackUtil;
import com.example.lulutong.utils.okhttp.OkhttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editText_Name, editText_PassWord;               //初始化布局文件一些控件的对象
    Button regesiter_button, forget_password, button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setView();
    }

    public void initView() {
        editText_Name = findViewById(R.id.editText_name);
        editText_PassWord = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.button_login);
        regesiter_button = findViewById(R.id.rigester_button);
        forget_password = findViewById(R.id.forget_password);
    }

    public void setView() {
        regesiter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, RigesterActivity.class);
                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password;
                String name;
                String url;
                name=editText_Name.getText().toString();
                password=editText_PassWord.getText().toString();
                if(password.length()<6){
                    Toast.makeText(LoginActivity.this, "请检查密码位数", Toast.LENGTH_SHORT).show();
                    return;
                }
                url = "http://www.lizerun.com:8081/android/login_data";
                HashMap<String, String> params = new HashMap<>();
                params.put("password", password);
                params.put("name", name);
                Gson gson = new Gson();
                String jsonStr = gson.toJson(params);
                OkhttpUtil.okHttpPostJson(url, jsonStr, new CallBackUtil() {
                    @Override
                    public Object onParseResponse(Call call, Response response) {
                        String str = "";
                        IsLogin res = new IsLogin(0);
                        try {
                            str = response.body().string();
                            res = gson.fromJson(str, IsLogin.class);
                            Log.d("msg", str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return res;
                    }

                    @Override
                    public void onFailure(Call call, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Object response) {
                        int isLogin = ((IsLogin) response).isLogin;
                        switch (isLogin) {
                            case 0:
                                //Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,RiskEvaluationActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case 1:
                                Toast.makeText(LoginActivity.this, "没有该用户", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(LoginActivity.this, "???", Toast.LENGTH_SHORT).show();
                        }
                        DialogMaker.MakeDialog(LoginActivity.this, R.drawable.logo, "信息", "试一试", "try", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                
                            }
                        });
                    }
                });
            }
        });
    }
    class IsLogin{
        int isLogin;
        public IsLogin(int isLogin) {
            this.isLogin = isLogin;
        }
    }
}

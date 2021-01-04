package com.example.lulutong.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.lulutong.R;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
EditText editText_Name,editText_PassWord;               //初始化布局文件一些控件的对象
    Button regesiter_button,forget_password,button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setView();
    }
    public void initView(){
//        editText_Name=findViewById(R.id.editText_name);
//        editText_PassWord=findViewById(R.id.editText_password);
//        button_login=findViewById(R.id.button_login);
//        regesiter_button=findViewById(R.id.regesiter_button);
//        forget_password=findViewById(R.id.forget_password);
    }
    public void setView(){
        regesiter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(com.example.citiclubapp.ui.LoginActivity.LoginActivity.this, RigesterActivity.class);
//                startActivity(intent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int accountID;
//                String password;
//                String name;
//                name=editText_Name.getText().toString();
//                dbUser=new DBUser();
//                CompanyInfo[] companyInfoArray=dbUser.findCompanyByName(com.example.citiclubapp.ui.LoginActivity.LoginActivity.this,name);
//                CompanyInfo companyInfo=new CompanyInfo();
//                List<CompanyInfo>list= Arrays.asList(companyInfoArray);
//                if(!list.isEmpty()){
//                    companyInfo=list.get(0);
//                    password=companyInfo.getPassWord();
//                    if (password.equals(editText_PassWord.getText().toString())){
//                        CompanyInfo.currentAccountID=companyInfo.getAccountID();
//                        Intent intent=new Intent(com.example.citiclubapp.ui.LoginActivity.LoginActivity.this, BusinessHallActivity.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(com.example.citiclubapp.ui.LoginActivity.LoginActivity.this, "企业名或密码填写错误", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(com.example.citiclubapp.ui.LoginActivity.LoginActivity.this, "未查到相关账号", Toast.LENGTH_SHORT).show();
//                }

            }
        });
    }
}

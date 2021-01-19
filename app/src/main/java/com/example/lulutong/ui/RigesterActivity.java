package com.example.lulutong.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lulutong.R;
import com.example.lulutong.utils.okhttp.CallBackUtil;
import com.example.lulutong.utils.okhttp.OkhttpUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Response;

public class RigesterActivity extends AppCompatActivity {
    EditText editText_setPasswrd, editText_checkPass, editText_email, editText_cer_node, editText_usrname;
    Button havAccount, successfulRegisters, send_cernode;
    String cernode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigester);
        initView();
        setView();

    }


    private void initView() {
        editText_setPasswrd = findViewById(R.id.editText_setPasswrd);
        editText_checkPass = findViewById(R.id.editText_checkPass);
        editText_cer_node = findViewById(R.id.editText_cer_node);
        editText_email = findViewById(R.id.editText_email);
        editText_usrname = findViewById(R.id.editText_usrname);
        havAccount = findViewById(R.id.button_havAccount);
        successfulRegisters = findViewById(R.id.button_successfulRegisters);
        send_cernode = findViewById(R.id.send_cerNode);
    }

    private void setView() {
        havAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RigesterActivity.this, LoginActivity.class);
                finish();
            }
        });
        successfulRegisters.setOnClickListener(new View.OnClickListener() {//验证输入框中文本的格式，正确则向
            @Override
            public void onClick(View view) {
                String password, checkPassword, email, username, cer_node;

                password = editText_setPasswrd.getText().toString();
                checkPassword = editText_checkPass.getText().toString();
                email = editText_email.getText().toString();
                username = editText_usrname.getText().toString();
                cer_node = editText_cer_node.getText().toString();

                String res = checkFormat(username, password, checkPassword, email, cer_node);

                if (res.equals("验证通过")) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    params.put("email", email);
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(params);
                    String url = "http://www.lizerun.com:8081/android/register_data";
                    OkhttpUtil.okHttpPostJson(url, jsonStr, new CallBackUtil() {
                        class IsRigester {
                            int isRegister;

                            public IsRigester(int isLogin) {
                                this.isRegister = isLogin;
                            }
                        }

                        @Override
                        public Object onParseResponse(Call call, Response response) {

                            IsRigester isRigester = new IsRigester(-1);

                            try {
                                String res = response.body().string();
                                isRigester = gson.fromJson(res, IsRigester.class);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return isRigester;
                        }

                        @Override
                        public void onFailure(Call call, Exception e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Object response) {
                            int res = ((IsRigester) response).isRegister;
                            if (res == 0) {
                                Toast.makeText(RigesterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (res == 1) {
                                Toast.makeText(RigesterActivity.this, "用户名重复", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(RigesterActivity.this, res, Toast.LENGTH_SHORT).show();
                }
            }
        });
        send_cernode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_email.getText().toString();
                if (email == null || !Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
                    Toast.makeText(RigesterActivity.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("email", email);
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(params);
                    String url = "http://www.lizerun.com:8081/android/verification_code";
                    OkhttpUtil.okHttpPostJson(url, jsonStr, new CallBackUtil() {
                        class Ver_node {
                            String verificationCode;

                            public Ver_node(String verificationCode) {
                                this.verificationCode = verificationCode;
                            }
                        }

                        @Override
                        public Object onParseResponse(Call call, Response response) {
                            Ver_node node = new Ver_node("");
                            try {
                                String res = response.body().string();
                                node = gson.fromJson(res, Ver_node.class);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return node;
                        }

                        @Override
                        public void onFailure(Call call, Exception e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Object response) {
                            cernode = ((Ver_node) response).verificationCode;
                        }
                    });
                }
            }
        });

    }

    private String checkFormat(String username, String password, String checkPassword, String email, String cer_node) {
        if (username == null)
            return "用户名不能为空";
        if (password.length() < 6 || password.length() > 16)
            return "密码位数不正确";
        if (!password.equals(checkPassword))
            return "确认密码错误";
        if (email == null || !Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email))
            return "邮箱格式不正确";
//        if (cernode == null || !cernode.equals(cer_node))
//            return "验证码错误";
        return "验证通过";
    }
}

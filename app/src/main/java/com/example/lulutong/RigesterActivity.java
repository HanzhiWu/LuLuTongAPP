package com.example.lulutong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RigesterActivity extends AppCompatActivity {
    EditText editText_setPasswrd,editText_checkPass;
//    DBUser dbUser;
Button havAccount,successfulRegisters;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigester);
//        havAccount=findViewById(R.id.button_havAccount);
        havAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(com.example.citiclubapp.ui.RigesterActivity.RigesterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                //finish();
            }
        });
//        successfulRegisters = findViewById(R.id.button_successfulRegisters);
//        successfulRegisters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dbUser=new DBUser();
//                String password,checkPassword;
//                editText_setPasswrd=findViewById(R.id.editText_setPasswrd);
//                editText_checkPass=findViewById(R.id.editText_checkPass);
//                password=editText_setPasswrd.getText().toString();
//                checkPassword=editText_checkPass.getText().toString();
//                if(password.equals(checkPassword)){
//                    int accountID=dbUser.returnAllCompanyInfo(com.example.citiclubapp.ui.RigesterActivity.RigesterActivity.this).length;
//                    CompanyInfo companyInfo=new CompanyInfo(accountID, password, "", "", "", "",
//                            "", "", "",
//                    0, "", "",
//                            "", "");
//                    dbUser.insert(companyInfo, com.example.citiclubapp.ui.RigesterActivity.RigesterActivity.this);
//                    CompanyInfo.currentAccountID=accountID;
//                    Intent intent = new Intent(com.example.citiclubapp.ui.RigesterActivity.RigesterActivity.this, SuccessfulRiges.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(com.example.citiclubapp.ui.RigesterActivity.RigesterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });

    }
}

package com.example.lulutong.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lulutong.R;
import com.example.lulutong.widget.EditorBar;
import com.example.lulutong.widget.InsideTitle;

public class RiskEvaluationActivity extends AppCompatActivity {

    InsideTitle title;
    EditorBar goodType, goodNum, orderPrice, costumerName, costumerId,
            logisticName, sendAddress, receiveAddress;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_evaluation);
        setTitle();
        initEditor();
        initSubmit();
    }

    /**
     * 初始化标题
     */
    private void setTitle() {
        title = findViewById(R.id.title);
        title.setTitle("风险评估");
    }

    /**
     * 初始化输入栏
     */
    private void initEditor() {
        goodType = findViewById(R.id.good_type);
        goodType.setText("货物种类");
        goodType.setHint("请输入货物种类");
        goodNum = findViewById(R.id.good_num);
        goodNum.setText("货物数量");
        goodNum.setHint("请输入货物数量");
        orderPrice = findViewById(R.id.order_price);
        orderPrice.setText("订单总额");
        orderPrice.setHint("请输入订单总额");
        costumerName = findViewById(R.id.costumer_name);
        costumerName.setText("买方名称");
        costumerName.setHint("请输入买方名称");
        costumerId = findViewById(R.id.costumer_id);
        costumerId.setText("买方企业\n机构代码");
        costumerId.setHint("请输入买方企业机构代码");
        logisticName = findViewById(R.id.logistic_name);
        logisticName.setText("物流公司名称");
        logisticName.setHint("请输入物流公司名称");
        sendAddress = findViewById(R.id.send_address);
        sendAddress.setText("发货地址");
        sendAddress.setHint("请输入发货地址");
        receiveAddress = findViewById(R.id.receive_address);
        receiveAddress.setText("收货地址");
        receiveAddress.setHint("请输入收货地址");
    }

    /**
     * 初始化提交功能
     */
    private void initSubmit() {
        submit = findViewById(R.id.submit);
        submit.setText("提 交");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入栏内容
                String type, num, price, cosName, cosId, logName, sAdd, rAdd;
                type = goodType.getText();
                num = goodNum.getText();
                price = orderPrice.getText();
                cosName = costumerId.getText();
                cosId = costumerId.getText();
                logName = logisticName.getText();
                sAdd = sendAddress.getText();
                rAdd = receiveAddress.getText();
                //检测是否有输入栏为空，若有空，则通知并结束点击事件
                if (type.isEmpty() || num.isEmpty() || price.isEmpty() || cosName.isEmpty() ||
                        cosId.isEmpty() || logName.isEmpty() || sAdd.isEmpty() || rAdd.isEmpty()) {
                    Toast.makeText(RiskEvaluationActivity.this, "输入不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

}
package com.example.lulutong.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.lulutong.R;

public class CustomDialog extends DialogFragment implements View.OnClickListener {

    //对话框类型常量
    public final static int SINGLE_BUTTON = 1;
    public final static int DOUBLE_BUTTON = 2;

    int type;

    /**
     * @param type 确定该对话框的类型SINGLE_BUTTON/DOUBLE_BUTTON
     */
    public CustomDialog(int type) {
        super();
        this.type = type;
    }

    //接收自定义标题和内容，在创建对话框时使用
    String title = null, content = null;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String singleButtonText = null, positiveButtonText = null, negativeButtonText = null;

    public void setSingleButtonText(String singleButtonText) {
        this.singleButtonText = singleButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    //需要自定义按钮功能时，让Activity实现Listener接口即可
    SingleListener singleListener = null;
    DoubleListener doubleListener = null;

    //自定义只有一个按钮的对话框时需要重写的接口
    public interface SingleListener {
        public void clickDialogButton();
    }

    //自定义有两个按钮的对话框时需要重写的接口
    public interface DoubleListener {
        public void clickPositiveButton();

        public void clickNegativeButton();
    }

    //再绑定Activity时获取接口的实现
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {//根据不同的类型接取不同的接口
            switch (type) {
                case SINGLE_BUTTON:
                    singleListener = (SingleListener) context;
                    break;
                case DOUBLE_BUTTON:
                    doubleListener = (DoubleListener) context;
                    break;
                default:
            }
        } catch (ClassCastException e) {
            Log.i("In custom dialog", "don't implement click event interface");
        }
    }

    //创建视图，并将自定义视图与功能进行设置
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        switch (type) {
            case SINGLE_BUTTON:
                view = inflater.inflate(R.layout.widget_singlebutton_dialog, container, false);
                Button button = view.findViewById(R.id.button);
                if (singleButtonText != null)
                    button.setText(singleButtonText);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (singleListener != null)
                            singleListener.clickDialogButton();
                        else
                            dismiss();
                    }
                });
                break;
            case DOUBLE_BUTTON:
                view = inflater.inflate(R.layout.widget_doublebutton_dialog, container, false);
                Button positiveButton = view.findViewById(R.id.positive_button),
                        negativeButton = view.findViewById(R.id.negative_button);
                if (positiveButtonText != null)
                    positiveButton.setText(positiveButtonText);
                if (negativeButtonText != null)
                    negativeButton.setText(negativeButtonText);
                if (doubleListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            doubleListener.clickPositiveButton();
                        }
                    });
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            doubleListener.clickNegativeButton();
                        }
                    });
                } else {
                    positiveButton.setOnClickListener(this::onClick);
                    negativeButton.setOnClickListener(this::onClick);
                }
                break;
        }
        if (title != null) {
            TextView titleView = view.findViewById(R.id.title);
            titleView.setText(title);
        }
        if (content != null) {
            TextView contentView = view.findViewById(R.id.content);
            contentView.setText(content);
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }

}

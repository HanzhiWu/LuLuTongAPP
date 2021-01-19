package com.example.lulutong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lulutong.R;

public class EditorBar extends LinearLayout {
    public TextView text;
    public EditText edit;

    public EditorBar(Context context, AttributeSet attrs){
        super(context, attrs );
        LayoutInflater.from( context ).inflate( R.layout.widget_editor_layout, this );
        text=findViewById( R.id.text_editor );
        edit=findViewById( R.id.edit_editor );
    }

    public void setText(String str){
        text.setText(str);
    }

    public void setHint(String hint){
        edit.setHint(hint);
    }
    public String getText(){
        return edit.getText().toString();
    }
}

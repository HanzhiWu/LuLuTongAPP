package com.example.lulutong.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.example.lulutong.R;

public class DialogMaker {
    public static final void MakeDialog(Context context, int iconId, String title, String message, String choice, DialogInterface.OnClickListener click){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setIcon(iconId);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(choice,click);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}

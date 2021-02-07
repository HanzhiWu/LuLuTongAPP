package com.example.lulutong.ui.PDFRelated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lulutong.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 在启动的Activity中重写onActivityResult，
 * 可在intent中取出名为"PDF File"的File类型变量进行操作
 * resultCode为10
 */
public class PDFSearchActivity extends AppCompatActivity {

    ImageView back;
    TextView submit;
    ListView list;
    List<File> fileList = new ArrayList<>();
    int returnId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_search);
        checkPermission();
        bindWidget();
        setTitle();
        initList();
    }

    private void checkPermission() {
        if (permissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE) ||
                permissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            ActivityCompat.requestPermissions(PDFSearchActivity.this, new String[]
                    {Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    private boolean permissionDenied(String permission) {
        return ContextCompat.checkSelfPermission(PDFSearchActivity.this, permission)
                != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(PDFSearchActivity.this, "Permission denied",
                        Toast.LENGTH_SHORT).show();
                finish();
            }else
                initList();
        }
    }

    private void bindWidget() {
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        list = findViewById(R.id.list_view);
    }

    private void setTitle() {
        back.setOnClickListener(v -> finish());
        submit.setOnClickListener(v -> {
            if (returnId == -1) {
                Toast.makeText(PDFSearchActivity.this, "请选择要提交的PDF文件",
                        Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                //Toast.makeText(PDFSearchActivity.this, "before " + fileList.get(returnId),
                //Toast.LENGTH_SHORT).show();
                intent.putExtra("PDF File", fileList.get(returnId));
                setResult(10, intent);
                finish();
            }
        });
    }

    private void initList() {
        ContentResolver contentResolver = getContentResolver();
        String[] columns = new String[]{MediaStore.Files.FileColumns.DATA};
        String select = "mime_type = 'application/pdf'";
        Uri uri = MediaStore.Files.getContentUri("external");
        Cursor cursor = contentResolver.query(uri, columns, select, null, null);
        if (cursor != null) {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
            while (cursor.moveToNext()) {
                String path = cursor.getString(index);
                File file = new File(path);
                fileList.add(file);
            }
            //Template implementation
            ArrayList<String> fileNameList=new ArrayList<>();
            for(File file:fileList)
                fileNameList.add(file.getName());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(PDFSearchActivity.this,
                    android.R.layout.simple_list_item_single_choice, fileNameList);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    returnId = position;
                }
            });
            cursor.close();
        }

    }
}
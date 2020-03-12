package com.example.quanlysach.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.quanlysach.R;
import com.example.quanlysach.model.LoaiSach;
import com.example.quanlysach.model.Sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThemSachActivity extends AppCompatActivity {
private Spinner spMaLS;
private EditText edtMaSach,edtTenSach,edtNgayXb;
private Button bntThemmoi,bntNhapLai;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
private ArrayList<LoaiSach> ls = new ArrayList<>();
public static ArrayList<Sach> sach = new ArrayList<>();  // sử dụng static thì các class có thể nhìn thấy vào gọi nhau được

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        initView();
        createSpiner();
        initEvent();
    }

    private void initEvent() {
        bntThemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String ma = edtMaSach.getText().toString();
                    String ten = edtTenSach.getText().toString();
                    Date ngay = dt.parse(edtNgayXb.getText().toString());

                    LoaiSach loaiSach = (LoaiSach) spMaLS.getSelectedItem(); // lấy object trong spinner ra .
                    String loai = loaiSach.getMaLoai();  // lấy ra loại sách từ  mã loại sách ép về kiểu String

                    Sach sach = new Sach(ma,ten,ngay,loai); // contructor bên kia
                  //  QLSachActivity.arraySach.add(sach); // cách này cùi , không hay , gửi hẳn object cho hay

                    Intent intent = new Intent();
                    intent.putExtra("them",sach);
                    setResult(RESULT_OK,intent); // set kết quả

                    finish();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void createSpiner() {
        ls.add(new LoaiSach("Văn Học","Sách Văn Học Việt Nam"));
        ls.add(new LoaiSach("Công Nghệ Thông Tin","Sách Công Nghệ Thông Tin"));
        ls.add(new LoaiSach("Thể Thao","Các Loại Sách Thể Thao"));
        ArrayAdapter<LoaiSach> adapter = new ArrayAdapter<>(ThemSachActivity.this,android.R.layout.simple_spinner_dropdown_item,ls);
         spMaLS.setAdapter(adapter);

    }

    private void initView() {
        spMaLS = findViewById(R.id.spMaLS);
        edtMaSach = findViewById(R.id.edtMaSach);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtNgayXb = findViewById(R.id.edtNgayXb);
        bntThemmoi = findViewById(R.id.bntThemmoi);
        bntNhapLai = findViewById(R.id.bntNhapLai);

    }
}

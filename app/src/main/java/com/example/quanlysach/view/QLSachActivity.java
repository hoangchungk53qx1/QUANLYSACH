package com.example.quanlysach.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanlysach.R;
import com.example.quanlysach.adapter.SachAdapter;
import com.example.quanlysach.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class QLSachActivity extends AppCompatActivity {
 private EditText searchBook;
private ListView listViewListBook;
 private FloatingActionButton fbInsert;
 SachAdapter adapter;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
public static ArrayList<Sach> arraySach = new ArrayList<>();
public static  ArrayList<Sach> arraySachSave = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_sach);
        initView();
        creatDemo();
        initEventChange();
        initInsert();
    }

    private void initInsert() {
        fbInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QLSachActivity.this,ThemSachActivity.class);
                startActivityForResult(intent,999); // gửi bằng setResult thì bên Kia nhận bằng overide onActivityResult
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // intent data là dữ liệu bên Themsach gửi sang, nhận phải nhận thông qua key
        // resultcode là cái bên THemSach RESULT_OK
        // request coe là code được yêu cầu  =999

        if(resultCode == RESULT_OK && requestCode==999){
            if(data !=null ) {
                Sach s = (Sach) data.getSerializableExtra("them");
                arraySach.add(s);
            //    adapter.notifyDataSetChanged();
            }
        }
    }

    private void initEventChange() {
        searchBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(s)){
                    arraySach.clear();
                    arraySach.addAll(arraySachSave);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void creatDemo(){
        try {

            Sach sach = new Sach();
            sach.setMaSach("0001");
            sach.setTenSach(" JAVA CORE ");
            sach.setNgayxb(dt.parse("2020-10-03"));
            sach.setMaLS("Văn Học");


            Sach sach1 = new Sach();
            sach1.setMaSach("0002");
            sach1.setTenSach(" PYTHON CORE ");
            sach1.setNgayxb(dt.parse("2020-10-03"));
            sach1.setMaLS("Công Nghệ Thông Tin");


            Sach sach2 = new Sach();
            sach2.setMaSach("0002");
            sach2.setTenSach("HELLO ");
            sach2.setNgayxb(dt.parse("2020-10-03"));
            sach2.setMaLS("Thể Thao");



            Sach sach3 = new Sach();
            sach3.setMaSach("0002");
            sach3.setTenSach(" KOTLIN  ");
            sach3.setNgayxb(dt.parse("2020-10-03"));
            sach3.setMaLS("Công Nghệ Thông Tin");


            Sach sach4 = new Sach();
            sach4.setMaSach("0002");
            sach4.setTenSach(" CUỐN THEO CHIỀU GIÓ");
            sach4.setNgayxb(dt.parse("2020-10-03"));
            sach4.setMaLS("VĂN HỌC");

            arraySach.add(sach);
            arraySach.add(sach1);
            arraySach.add(sach2);
            arraySach.add(sach3);
            arraySach.add(sach4);


            arraySachSave.addAll(arraySach);


           adapter = new SachAdapter(QLSachActivity.this,arraySach);
           listViewListBook.setAdapter(adapter);
        }
      catch (Exception e){

      }
    }

    private void initView() {
        searchBook = findViewById(R.id.searchBook);
        listViewListBook = findViewById(R.id.listViewListBook);
        fbInsert = findViewById(R.id.fbInsert);
    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        adapter.notifyDataSetChanged();
//    }

    public void onClickSearch(View view) {
        String sreach = searchBook.getText().toString();

        if(!TextUtils.isEmpty(sreach)){
            arraySach.clear();
            for (int i = 0 ; i < arraySachSave.size();i++){
                if(arraySachSave.get(i).getTenSach().toLowerCase().contains(sreach.toLowerCase()))
                {
                    arraySach.add(arraySachSave.get(i));
                }
            }
            adapter.notifyDataSetChanged();

        }




    }
}

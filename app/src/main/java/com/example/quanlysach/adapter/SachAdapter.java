package com.example.quanlysach.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.quanlysach.R;
import com.example.quanlysach.model.LoaiSach;
import com.example.quanlysach.model.Sach;
import com.example.quanlysach.view.ThemSachActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SachAdapter extends BaseAdapter {
    private  Context context;
    private ArrayList<Sach> ds; // getView trả về danh sách tại với trí position
    private ArrayList<LoaiSach> ls = new ArrayList<>(); // spinner
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
    public SachAdapter(Context context, ArrayList<Sach> ds) {
        this.context = context;
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size() ;
    }

    @Override
    public Object getItem(int position) {
        return ds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    //
    @SuppressLint({"InflateParams", "SetTextI18n"})
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {  // chuyển thành final để cập nhập lại đúng positon
        // đổ dữ liệu lên

       final Sach s = ds.get(position); // mỗi phần tử sẽ lấy theo position  getView trả về danh sách tại với trí position
        ViewHolder holder; // của class bên dưới
        //covertView nạp từ row_book lên
        if (convertView ==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView= inflater.inflate(R.layout.row_book,null);
            holder = new ViewHolder();
            holder.tvMaSach = convertView.findViewById(R.id.tvMaSach);
            holder.tvTenSach = convertView.findViewById(R.id.tvTenSach);
            holder.tvNgayXb = convertView.findViewById(R.id.tvNgayXb);
            holder.tvMALS = convertView.findViewById(R.id.tvMALS);

            holder.imgEdit = convertView.findViewById(R.id.imgEdit);
            holder.imgDel = convertView.findViewById(R.id.imgDel);
            convertView.setTag(holder);

         }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final AlertDialog alert = builder.create();
                builder.setMessage("Thông Báo !");
                builder.setTitle("Bạn có muốn xoá !");

//                final ProgressDialog myLoadingDialog = new ProgressDialog(context);
//                myLoadingDialog.setMessage("Loading...");
//                myLoadingDialog.setIndeterminate(true);
//                myLoadingDialog.setCancelable(false);
//                myLoadingDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

// Set up the buttons
                builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ds.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        alert.dismiss();
                        alert.cancel();


                    }
                });
                builder.setNegativeButton("Huỷ Bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        alert.dismiss();
                    }
                });

                //myLoadingDialog.show();

                alert.show();
                builder.show();


            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.ic_sua_sach, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                final EditText edtMaSachEdit = view.findViewById(R.id.edtMaSachEdit);
                final EditText edtTenSachEdit = view.findViewById(R.id.edtTenSachEdit);
                final EditText edtNgayXbEdit = view.findViewById(R.id.edtNgayXbEdit);
                final Spinner  spMaLSEdit = view.findViewById(R.id.spMaLSEdit);
                Button bntSuamoiEdit = view.findViewById(R.id.bntThemmoiEdit);
                Button bntXoaEdit = view.findViewById(R.id.bntNhapLaiEdit);
                ///
                ls.add(new LoaiSach("Văn Học","Sách Văn Học Việt Nam"));
                ls.add(new LoaiSach("Công Nghệ Thông Tin","Sách Công Nghệ Thông Tin"));
                ls.add(new LoaiSach("Thể Thao","Các Loại Sách Thể Thao"));
                ls.add(new LoaiSach("Điện tử","Điện tử"));
                ls.add(new LoaiSach("Giáo Án","Giáo án"));
                final ArrayAdapter<LoaiSach> adapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,ls);
                spMaLSEdit.setAdapter(adapter);

                // đổ lên form
                edtMaSachEdit.setText(s.getMaSach());
                edtTenSachEdit.setText(s.getTenSach());
                edtNgayXbEdit.setText(dt.format(s.getNgayxb()));
                int idex = -1; // để bằng 0 nó cũng duyệt từ 0 // nhầm thấy bà cố luôn. cay
                for (int i = 0 ; i<ls.size();i++){
                    if(ls.get(i).toString().equalsIgnoreCase(s.getMaLS())){
                        // nếu bằng thì gán idex = i
                        //và beark luôn
                        idex = i;
                        break;
                    }
                }
                spMaLSEdit.setSelection(idex); // đổ spiner lên form thôi/

                // lấy dữ liệu từ form gán cho các biến
               bntSuamoiEdit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try {
                           String ma = edtMaSachEdit.getText().toString().trim();
                           String ten = edtTenSachEdit.getText().toString();
                           Date ngay = dt.parse(edtNgayXbEdit.getText().toString()); // từ Date chuyển qua String
                           LoaiSach ls = (LoaiSach) spMaLSEdit.getSelectedItem(); // lấy thằng đối tượng loại sách từ spinner
                           // Log.d(String.valueOf(ls),"hahahahahaha");
                           String loai = ls.getMaLoai();
                           final Sach sach = new Sach(ma,ten,ngay,loai);
                           ds.set(position,sach);
                          notifyDataSetChanged(); // không có này nó ko cập nhập. // bản thân nó là adapter rồi thì notify thôi

                           adapter.notifyDataSetChanged();
                           alertDialog.dismiss();
                       }

                       catch (Exception e){

                       }

                   }
               });
             bntXoaEdit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     ds.remove(position);
                     notifyDataSetChanged(); // không có này nó ko cập nhập. // bản thân nó là adapter rồi thì notify thôi

                     adapter.notifyDataSetChanged();
                     alertDialog.dismiss();
                 }
             });

              alertDialog.show();

            }
        });


        // đổ ra
       // Sach s = ds.get(position);
        holder.tvMaSach.setText("Mã :"+s.getMaSach());
        holder.tvTenSach.setText("Tên : "+ s.getTenSach());
        holder.tvNgayXb.setText("Ngày xuất bản :"+ dt.format(s.getNgayxb())); // fomat String chuyển sang Date để hiển thị lên
        holder.tvMALS.setText("Loại :"+s.getMaLS());


        return convertView;
    }
    class ViewHolder{
        TextView tvMaSach,tvTenSach,tvNgayXb,tvMALS;
        ImageView imgEdit;
        ImageView imgDel;


    }
}

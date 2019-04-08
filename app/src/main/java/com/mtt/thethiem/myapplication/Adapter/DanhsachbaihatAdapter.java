package com.mtt.thethiem.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtt.thethiem.myapplication.Activity.PlayNhacActivity;
import com.mtt.thethiem.myapplication.Model.Baihat;
import com.mtt.thethiem.myapplication.R;
import com.mtt.thethiem.myapplication.Service.APIService;
import com.mtt.thethiem.myapplication.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangBaiHat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Baihat baihat = mangBaiHat.get(position);
        viewHolder.txtCasi.setText(baihat.getCaSi());
        viewHolder.txtTenBaiHat.setText(baihat.getTenBaiHat());
        viewHolder.txtIndex.setText(position+1+"");

    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtIndex, txtTenBaiHat, txtCasi;
        ImageView imgLuotThich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCasi = itemView.findViewById(R.id.textviewtencasi);
            txtTenBaiHat = itemView.findViewById(R.id.textviewtenbaihat);
            txtIndex = itemView.findViewById(R.id.textviewdanhsachindex);
            imgLuotThich = itemView.findViewById(R.id.imageviewluotthichdanhsachbaihat);
            imgLuotThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLuotThich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1",mangBaiHat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketQua = response.body();
                            if (ketQua.equals("Success")){
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Lỗi!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgLuotThich.setEnabled(false);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangBaiHat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

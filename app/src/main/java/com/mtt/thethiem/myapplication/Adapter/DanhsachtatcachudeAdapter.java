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

import com.mtt.thethiem.myapplication.Activity.DanhsachtheloaitheochudeActivity;
import com.mtt.thethiem.myapplication.Model.ChuDe;
import com.mtt.thethiem.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtatcachudeAdapter extends RecyclerView.Adapter<DanhsachtatcachudeAdapter.ViewHolder>{
    Context context;
    ArrayList<ChuDe> mangChuDe;

    public DanhsachtatcachudeAdapter(Context context, ArrayList<ChuDe> mangChuDe) {
        this.context = context;
        this.mangChuDe = mangChuDe;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_cac_chu_de,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ChuDe chuDe = mangChuDe.get(position);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(viewHolder.imgChuDe);
        viewHolder.txtTenChuDe.setText(mangChuDe.get(position).getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return mangChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgChuDe;
        TextView txtTenChuDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChuDe = itemView.findViewById(R.id.imageviewdongcacchude);
            txtTenChuDe = itemView.findViewById(R.id.textviewtenchude);
            imgChuDe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachtheloaitheochudeActivity.class);
                    intent.putExtra("chude",mangChuDe.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

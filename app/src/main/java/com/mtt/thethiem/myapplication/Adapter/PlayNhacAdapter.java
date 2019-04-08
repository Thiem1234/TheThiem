package com.mtt.thethiem.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mtt.thethiem.myapplication.Model.Baihat;
import com.mtt.thethiem.myapplication.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends  RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangBaiHat;

    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangBaiHat) {
        this.context = context;
        this.mangBaiHat = mangBaiHat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Baihat baihat = mangBaiHat.get(position);
        viewHolder.txtCasi.setText(baihat.getCaSi());
        viewHolder.txtIndex.setText(position + 1 + "");
        viewHolder.txtTenBaiHat.setText(baihat.getTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mangBaiHat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtIndex, txtTenBaiHat, txtCasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCasi = itemView.findViewById(R.id.textviewPlayNhacTenCaSi);
            txtIndex = itemView.findViewById(R.id.textviewPlayNhacIndex);
            txtTenBaiHat = itemView.findViewById(R.id.textviewPlayNhacTenBaiHat);
        }
    }
}

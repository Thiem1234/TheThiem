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

import com.mtt.thethiem.myapplication.Activity.DanhsachbaihatActivity;
import com.mtt.thethiem.myapplication.Model.Playlist;
import com.mtt.thethiem.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCacPlayListAdapter extends RecyclerView.Adapter<DanhSachCacPlayListAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> mangPlayList;

    public DanhSachCacPlayListAdapter(Context context, ArrayList<Playlist> mangPlayList) {
        this.context = context;
        this.mangPlayList = mangPlayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Playlist playlist = mangPlayList.get(position);
        Picasso.with(context).load(playlist.getHinhPlayList()).into(viewHolder.imgHinhNen);
        viewHolder.txtTenPlayList.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return mangPlayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhNen;
        TextView txtTenPlayList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txtTenPlayList = itemView.findViewById(R.id.textviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangPlayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

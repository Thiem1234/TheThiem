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
import com.mtt.thethiem.myapplication.Model.Album;
import com.mtt.thethiem.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHoler> {
    Context context;
    ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,viewGroup,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int position) {
        Album album = mangAlbum.get(position);
        viewHoler.txtCaSiAlbum.setText(album.getTenCasiAlbum());
        viewHoler.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHoler.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtCaSiAlbum;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imageviewalbum);
            txtTenAlbum = itemView.findViewById(R.id.textviewtenalbum);
            txtCaSiAlbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

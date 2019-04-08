package com.mtt.thethiem.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mtt.thethiem.myapplication.Activity.DanhsachbaihatActivity;
import com.mtt.thethiem.myapplication.Activity.DanhsachtatcachudeActivity;
import com.mtt.thethiem.myapplication.Activity.DanhsachtheloaitheochudeActivity;
import com.mtt.thethiem.myapplication.Model.ChuDe;
import com.mtt.thethiem.myapplication.Model.ChudeTheloai;
import com.mtt.thethiem.myapplication.Model.TheLoai;
import com.mtt.thethiem.myapplication.R;
import com.mtt.thethiem.myapplication.Service.APIService;
import com.mtt.thethiem.myapplication.Service.Dataservice;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDeTheLoai extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThemChuDeTheLoai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chudetheloai,container,false);
        //Anh xa
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        txtXemThemChuDeTheLoai = view.findViewById(R.id.textviewxemthemalbumhot);
        txtXemThemChuDeTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChudeTheloai> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<ChudeTheloai>() {
            @Override
            public void onResponse(Call<ChudeTheloai> call, Response<ChudeTheloai> response) {
                ChudeTheloai chudeTheloai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chudeTheloai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList= new ArrayList<>();
                theLoaiArrayList.addAll(chudeTheloai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(800,500);
                layout.setMargins(10,20,10,30);
                for (int i = 0;i < (chuDeArrayList.size());i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }

                for (int j = 0;j < (chuDeArrayList.size());j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<ChudeTheloai> call, Throwable t) {

            }
        });
    }
}

package com.mtt.thethiem.myapplication.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtt.thethiem.myapplication.Activity.PlayNhacActivity;
import com.mtt.thethiem.myapplication.Adapter.PlayNhacAdapter;
import com.mtt.thethiem.myapplication.R;

public class Fragment_Play_Danh_Sach_Cac_Bai_Hat extends Fragment {
    View view;
    RecyclerView recyclerViewPlayNhac;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat,container,false);
        recyclerViewPlayNhac = view.findViewById(R.id.recyclerviewPlayBaiHat);

        if (PlayNhacActivity.mangBaiHat.size() > 0){
            playNhacAdapter = new PlayNhacAdapter(getContext(), PlayNhacActivity.mangBaiHat);
            recyclerViewPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayNhac.setAdapter(playNhacAdapter);
        }
        return view;
    }
}

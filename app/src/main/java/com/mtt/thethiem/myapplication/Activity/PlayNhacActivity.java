package com.mtt.thethiem.myapplication.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mtt.thethiem.myapplication.Adapter.ViewPagerPlayListNhac;
import com.mtt.thethiem.myapplication.Fragment.Fragment_Dia_Nhac;
import com.mtt.thethiem.myapplication.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.mtt.thethiem.myapplication.Model.Baihat;
import com.mtt.thethiem.myapplication.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarPlayNhac;
    TextView txtTimeSong, txtTotalTimeSong;
    SeekBar skTime;
    ImageButton imgPlay, imgRepeat, imgNext, imgPre, imgRandom;
    ViewPager viewPagerPlayNhac;
    public static ArrayList<Baihat> mangBaiHat = new ArrayList<>();
    public static ViewPagerPlayListNhac adapterNhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkRandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();

        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterNhac.getItem(1) != null){
                    if (mangBaiHat.size() > 0){
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                    if(checkRandom == true){
                        checkRandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkRandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    checkRandom = true;
                }else {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkRandom = false;
                }
            }
        });
        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size()-1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                        //upDateTime();
                    }
                }
                imgPre.setEnabled(false);
                imgNext.setEnabled(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setEnabled(true);
                        imgNext.setEnabled(true);
                    }
                },5000);
            }
        });
        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangBaiHat.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0){
                            position = mangBaiHat.size() -1;
                        }
                        if (repeat == true){
                            if (position == 0){
                                position = mangBaiHat.size();
                            }
                            position += 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                        upDateTime();
                    }
                }
                imgPre.setEnabled(false);
                imgNext.setEnabled(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setEnabled(true);
                        imgNext.setEnabled(true);
                    }
                },5000);
            }
        });
    }


    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if (intent != null){
            if (intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baihat);

            }
            if (intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangBaiHat = baihatArrayList;
            }
        }

    }

    private void init() {
        toolbarPlayNhac = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong = findViewById(R.id.textviewtimesong);
        txtTotalTimeSong = findViewById(R.id.textviewTotalTimeSong);
        skTime = findViewById(R.id.seekbarSong);
        imgPlay = findViewById(R.id.imageviewbuttonPlay);
        imgRepeat = findViewById(R.id.imageviewbuttonRepeat);
        imgNext = findViewById(R.id.imageviewbuttonNext);
        imgPre = findViewById(R.id.imageviewbuttonPre);
        imgRandom = findViewById(R.id.imageviewbuttonSuffle);
        viewPagerPlayNhac = findViewById(R.id.viewpagerPlayNhac);
        setSupportActionBar(toolbarPlayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mangBaiHat.clear();
            }
        });
        toolbarPlayNhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapterNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapterNhac.addFragment(fragment_play_danh_sach_cac_bai_hat);
        adapterNhac.addFragment(fragment_dia_nhac);
        viewPagerPlayNhac.setAdapter(adapterNhac);

        fragment_dia_nhac = (Fragment_Dia_Nhac) adapterNhac.getItem(1);
        if (mangBaiHat.size() > 0){
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangBaiHat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }

    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baiHat) {
            super.onPostExecute(baiHat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baiHat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            upDateTime();
        }

    }

    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());
    }
    private void upDateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (mangBaiHat.size())){
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkRandom == true){
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size()-1)){
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaiHat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenBaiHat());
                    }
                imgPre.setEnabled(false);
                imgNext.setEnabled(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setEnabled(true);
                        imgNext.setEnabled(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}

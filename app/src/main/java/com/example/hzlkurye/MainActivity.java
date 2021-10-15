package com.example.hzlkurye;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.hzlkurye.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    int skor;
    Runnable runnable;
    Handler handler;

    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        imageArray = new ImageView[]{binding.imageView, binding.imageView2, binding.imageView3, binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8, binding.imageView9};
        hideImages();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                binding.scoreText2.setText("KALAN ZAMAN : " + l / 1000);
            }

            @Override
            public void onFinish() {
                for (ImageView imageView : imageArray) {
                    imageView.setVisibility(View.INVISIBLE);
                }
                handler.removeCallbacks(runnable);
            }
        }.start();


    }

    public void increaseScore(View view){
            skor++;
            binding.scoreText.setText("SKOR : "+skor);

    }

    public void hideImages(){


        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView:imageArray){
                    imageView.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,500);
            }
        };
        handler.post(runnable);
    }
}
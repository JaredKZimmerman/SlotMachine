package com.example.slotmachine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable happyricImage;
    private Drawable sadricImage;
    private Drawable wooricImage;
    private ImageView[] imageViews;
    private Drawable currentleft;
    private Drawable currentmiddle;
    private Drawable currentright;
    public int score;
    public Update change;
    public Handler handler;
    public Button startButton;
    public TextView scoreText;
    public int speedleft;
    public int speedmiddle;
    public int speedright;
    public boolean on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreText = findViewById(R.id.scoreText);
        grid = findViewById(R.id.gridLayout);
        happyricImage = getDrawable(R.drawable.happyric);
        sadricImage = getDrawable(R.drawable.sadric);
        wooricImage = getDrawable(R.drawable.wooric);
        imageViews = new ImageView[3];
        handler = new Handler();
        startButton = findViewById(R.id.startButton);
        on = false;
        change = new Update();
        for(int i=0; i<3; i++){
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.slot_view, null);
            imageViews[i].setMinimumHeight(400);
            imageViews[i].setMinimumWidth(400);
        }
    }

    public void startPressed(View v){
        if(on){
            int happycount = 0;
            on = false;
            handler.removeCallbacks(change);
            scoreText.setText(score+"");
            for(int i=0; i<3; i++) {
                if (imageViews[i] == happyricImage) {
                    happycount = happycount + 1;
                }
            }
            if (happycount == 1){
                score = score + 10;
            }else if (happycount == 2){
                score = score + 20;
            } else if (happycount == 3){
                score = score + 50;
            }
            if (imageViews[0].getDrawable(R.drawable.wooric) & (imageViews[1].getDrawable(R.drawable.wooric)) & (imageViews[2].getDrawable(R.drawable.wooric))
            {
                score = score + 100;
            }startButton.setText("Start")
        }else{
                on = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int image = data.getIntExtra("IMAGE", 1);

    }
}

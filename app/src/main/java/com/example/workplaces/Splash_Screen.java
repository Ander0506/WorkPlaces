package com.example.workplaces;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends Activity {

    private ImageView imgLogo;
    private ImageView imgLogo1;
    private ImageView imgLogo2;
    private ImageView imgLogo3;
    private Animation animLogo;
    private Animation animLogo1;
    private Animation animLogo2;
    private Animation animLogo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        imgLogo = (ImageView) findViewById(R.id.imgViewLogo);
        animLogo = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        imgLogo.setAnimation(animLogo);

        imgLogo1 = (ImageView) findViewById(R.id.imgViewLogo1);
        imgLogo2 = (ImageView) findViewById(R.id.imgViewLogo2);
        imgLogo3 = (ImageView) findViewById(R.id.imgViewLogo3);
        animLogo1 = AnimationUtils.loadAnimation(this, R.anim.anim_logo1);
        animLogo2 = AnimationUtils.loadAnimation(this, R.anim.anim_logo2);
        animLogo3 = AnimationUtils.loadAnimation(this, R.anim.anim_logo3);
        imgLogo1.setAnimation(animLogo3);
        imgLogo2.setAnimation(animLogo2);
        imgLogo3.setAnimation(animLogo1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                finish();
            }
        },5000);
    }
}

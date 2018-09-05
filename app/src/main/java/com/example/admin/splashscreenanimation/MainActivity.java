package com.example.admin.splashscreenanimation;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    View mView, startView, viewContainer;
    FrameLayout textContainer;
    public static final long SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.layout);
        startView = findViewById(R.id.layout1);
        viewContainer = findViewById(R.id.viewContainer);
        textContainer = findViewById(R.id.textContainer);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    startAnimation();
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            // close this activity
                            finish();
                        }
                    }, SPLASH_TIME_OUT);
                }
            }
        }, 100);

        viewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void startAnimation() {
        int centerInX = (int) (mView.getMeasuredWidth() / 2f);
        int centerInY = (int) (mView.getMeasuredHeight() / 2f);

        float endRadius = (float) Math.sqrt(centerInX * centerInX + centerInY * centerInY);

        // starts the effect at centerX, center Y and covers final radius
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(startView,
                centerInX, centerInY, 0, endRadius);
        revealAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                textContainer.setVisibility(View.VISIBLE);
                textContainer.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.alphaanimation));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        // close this activity
                        finish();
                    }
                }, SPLASH_TIME_OUT);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        revealAnimator.setDuration(500);
        startView.setVisibility(View.VISIBLE);
        revealAnimator.start();
    }
}

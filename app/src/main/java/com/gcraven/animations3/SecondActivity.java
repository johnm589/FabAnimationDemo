package com.gcraven.animations3;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;

public class SecondActivity extends AppCompatActivity {

    View revealView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        revealView = findViewById(R.id.revealView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                revealView();
            }
            @Override
            public void onTransitionStart(Transition transition) {}
            @Override
            public void onTransitionCancel(Transition transition) {}
            @Override
            public void onTransitionPause(Transition transition) {}
            @Override
            public void onTransitionResume(Transition transition) {}
        });
    }

    private void revealView(){

        int startX = revealView.getWidth() / 2;
        int fabPosition = ((CoordinatorLayout.LayoutParams)fab.getLayoutParams()).bottomMargin + (fab.getMeasuredHeight() / 4);
        int startY = revealView.getHeight() - fabPosition;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(startX, startY);

        // create the animator for this view (the start radius is zero)
        Animator animator = ViewAnimationUtils.createCircularReveal(revealView, startX, startY, 0, finalRadius);

        animator.setDuration(400);

        // make the view visible and start the animation
        revealView.setVisibility(View.VISIBLE);

        animator.start();
    }

}

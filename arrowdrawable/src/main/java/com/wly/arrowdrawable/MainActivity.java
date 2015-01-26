package com.wly.arrowdrawable;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

import static android.view.Gravity.START;


public class MainActivity extends Activity {

    private ArrowDrawable arrowDrawable;
    private boolean flipped = false;
    private ImageView imageView;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        imageView = (ImageView) findViewById(R.id.drawer_indicator);

//        imageView.measure(0, 0);
//        int height=imageView.getMeasuredHeight();
//        int width=imageView.getMeasuredWidth();
//        Log.i("width;height:", "[" + width + ";" + height + "]");

        arrowDrawable = new ArrowDrawable();
        imageView.setImageDrawable(arrowDrawable);

        drawer.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset >= .995) {
                    flipped = true;
                    arrowDrawable.setFlip(flipped);
                } else if (slideOffset <= .005) {
                    flipped = false;
                    arrowDrawable.setFlip(flipped);
                }

                arrowDrawable.setParameter(slideOffset);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(START)) {
                    drawer.closeDrawer(START);
                } else {
                    drawer.openDrawer(START);
                }
            }
        });
    }
}

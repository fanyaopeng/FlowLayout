package com.fan.flowlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowLayout flowLayout = findViewById(R.id.flow_layout);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            TextView item = new TextView(this);
            item.setGravity(Gravity.CENTER);
            item.setText("你好啊");
            item.setBackgroundDrawable(getResources().getDrawable(R.drawable.item));
            int width = (int) (300 * random.nextFloat());
            if (width < 60) width = 60;
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(new ViewGroup.LayoutParams(width, 38));
            params.leftMargin = (int) (20 * random.nextFloat());
            params.topMargin = (int) (20 * random.nextFloat());
            params.bottomMargin = (int) (20 * random.nextFloat());
            params.rightMargin = (int) (20 * random.nextFloat());
            flowLayout.addView(item, params);
        }
    }
}

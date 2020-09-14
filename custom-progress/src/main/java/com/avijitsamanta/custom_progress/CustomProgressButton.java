package com.avijitsamanta.custom_progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomProgressButton extends RelativeLayout {
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;
    private TextView textView;
    private ProgressBar progressBar;
    private ConstraintLayout container;
    private static String title;
    private static int textColor, progressColor, backgroundColor;

    public CustomProgressButton(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CustomProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();
    }

    public CustomProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }


    private void initView() {
        this.view = this;
        inflate(mContext, R.layout.custom_progress, this);

        @SuppressLint("Recycle") TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.CustomProgressButton,
                styleAttr, 0);

        title = arr.getString(R.styleable.CustomProgressButton_text);
        textColor = arr.getColor(R.styleable.CustomProgressButton_textColor,
                Color.parseColor("#FFFFFF"));
        /* Parent layout background color */
        backgroundColor = arr.getColor(R.styleable.CustomProgressButton_backgroundColor,
                Color.parseColor("#36e0e3"));
        progressColor = arr.getColor(R.styleable.CustomProgressButton_progressBarColor,
                Color.parseColor("#FFFFFF"));


        container = findViewById(R.id.container);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.title);


        setText(title);

        setTextColor(textColor);
        setBackgroundColor(backgroundColor);
        setProgressBarColor(progressColor);

        arr.recycle();
    }

    public void setText(String msg) {
        title = msg;
        textView.setText(msg);
    }

    public void setTextColor(int color) {
        textColor = color;
        textView.setTextColor(color);
    }

    public void setBackgroundColor(final int color) {
        backgroundColor = color;
        container.setBackgroundColor(color);
    }

    @SuppressLint("SetTextI18n")
    public void show() {
        progressBar.setVisibility(VISIBLE);
        textView.setText("Please Wait..");
    }

    public void show(String msg) {
        progressBar.setVisibility(VISIBLE);
        textView.setText(msg);
    }

    public void hide() {
        progressBar.setVisibility(INVISIBLE);
        textView.setText(title);
    }

    public void setProgressBarColor(int color) {
        progressColor = color;
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(color));
    }

    @SuppressLint("SetTextI18n")
    public void error() {
        progressBar.setVisibility(INVISIBLE);
        textView.setText("Error");
        setBackgroundColor(Color.RED);  // Container color set to RED
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(title);
                setBackgroundColor(backgroundColor);
                setTextColor(textColor);
            }
        }, 3000); // delay 3 seconds
    }


}
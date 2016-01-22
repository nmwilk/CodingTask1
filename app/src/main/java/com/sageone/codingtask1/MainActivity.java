package com.sageone.codingtask1;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button redButton;
    private Button greenButton;
    private Button blueButton;

    private ProgressBar redProgress;
    private ProgressBar greenProgress;
    private ProgressBar blueProgress;
    private View space;

    private int red;
    private int green;
    private int blue;
    private int currentColour = 0xFF000000;

    private int animStartColour;
    private int animEndColour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        redButton = (Button) findViewById(R.id.red);
        greenButton = (Button) findViewById(R.id.green);
        blueButton = (Button) findViewById(R.id.blue);

        redProgress = (ProgressBar) findViewById(R.id.redProgress);
        greenProgress = (ProgressBar) findViewById(R.id.greenProgress);
        blueProgress = (ProgressBar) findViewById(R.id.blueProgress);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                red = (red + 25) % 255;
                redProgress.setProgress(red);

                int colour = currentColour;
                colour &= 0xFF00FFFF;
                colour |= red << 16;

                changeColour(colour);
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                green = (green + 25) % 255;
                greenProgress.setProgress(green);

                int colour = currentColour;
                colour &= 0xFFFF00FF;
                colour |= green << 8;

                changeColour(colour);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                blue = (blue + 25) % 255;
                blueProgress.setProgress(blue);

                int colour = currentColour;
                colour &= 0xFFFFFF00;
                colour |= blue;

                changeColour(colour);
            }
        });

        space = findViewById(R.id.space);
    }

    private void changeColour(final int newColour) {
        animStartColour = currentColour;
        animEndColour = newColour;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "spaceColour", 0, 1);
        animator.setDuration(200);
        animator.start();
    }

    public void setSpaceColour(float progress) {
        currentColour = (int) new ArgbEvaluator().evaluate(progress, animStartColour, animEndColour);
        space.setBackgroundColor(currentColour);
    }
}

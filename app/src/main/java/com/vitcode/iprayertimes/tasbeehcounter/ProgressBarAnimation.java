package com.vitcode.iprayertimes.tasbeehcounter;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation {
    private float from;
    private ProgressBar progressBar;
    private float to;

    public ProgressBarAnimation(ProgressBar progressBar2, float f, float f2) {
        this.progressBar = progressBar2;
        this.from = f;
        this.to = f2;
    }

    public void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        float f2 = this.from;
        this.progressBar.setProgress((int) (f2 + ((this.to - f2) * f)));
    }
}

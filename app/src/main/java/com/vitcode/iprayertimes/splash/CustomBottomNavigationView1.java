package com.vitcode.iprayertimes.splash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vitcode.iprayertimes.R;

public class CustomBottomNavigationView1 extends BottomNavigationView {

    private Path mPath;
    private Paint mPaint;

    /** the CURVE_CIRCLE_RADIUS represent the radius of the fab button */
    private final int CURVE_CIRCLE_RADIUS = 128 / 2 ;
    // the coordinates of the first curve
    private Point mFirstCurveStartPoint = new Point();
    private Point mFirstCurveEndPoint = new Point();
    private Point mFirstCurveControlPoint1 = new Point();
    private Point mFirstCurveControlPoint2 = new Point();

    //the coordinates of the second curve
    @SuppressWarnings("FieldCanBeLocal")
    private Point mSecondCurveStartPoint = new Point();
    private Point mSecondCurveEndPoint = new Point();
    private Point mSecondCurveControlPoint1 = new Point();
    private Point mSecondCurveControlPoint2 = new Point();
    private int mNavigationBarWidth;
    private int mNavigationBarHeight;

    public CustomBottomNavigationView1(Context context) {
        super(context);
        init();
    }

    public CustomBottomNavigationView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomBottomNavigationView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.white));
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);}

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();
        mFirstCurveStartPoint.set((mNavigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
        mFirstCurveEndPoint.set(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);

        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4), mFirstCurveStartPoint.y);
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);

        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)), mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y);

        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y);

        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y);

        mPath.lineTo(mNavigationBarWidth, 0);
        mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight);
        mPath.lineTo(0, mNavigationBarHeight);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
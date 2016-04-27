package com.cjy.jianghw.app.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * <b>@Description:</b>TODO<br/>
 * <b>@Author:</b>jianghw<br/>
 * <b>@Since:</b>2016/4/26<br/>
 */
public class RoundAndCircle extends View {
    private TextPaint mTextPaint;

    public RoundAndCircle(Context context) {
        this(context, null);
    }

    public RoundAndCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundAndCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        Log.i("TAG-1",widthSpecMode+"/"+widthSpecSize);
        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        Log.i("TAG-2",heightSpecMode+"/"+heightSpecSize);

        if(widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(getMeasuredWidth()/5,getMeasuredWidth()/5);
        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(getMeasuredWidth()/5,heightSpecSize);
        }else if(heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,getMeasuredHeight()/8);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int leftPadding = getPaddingLeft();
        int topPadding = getPaddingTop();
        int rightPadding = getPaddingRight();
        int bottomPadding = getPaddingBottom();
        int width = getMeasuredWidth() - leftPadding - rightPadding;
        int height = getMeasuredHeight() - topPadding - bottomPadding;
        Log.i("TAG-3",width+"/"+height);
        canvas.drawCircle(width/2,height/2,width/2,mTextPaint);
    }
}

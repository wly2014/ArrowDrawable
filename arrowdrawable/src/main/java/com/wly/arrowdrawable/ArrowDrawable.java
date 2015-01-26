package com.wly.arrowdrawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

import static android.graphics.PixelFormat.TRANSLUCENT;

/**
 * Created by Administrator on 2015/1/26.
 */
public class ArrowDrawable extends Drawable {

    private float parameter;
    private float lineLength = 50;
    private float strokeWidth = 5;
    private float gap = (float) (25 - 1.6 * strokeWidth);

    private boolean flip;
    private int strokeColor;
    private int strokeAlpha;

    private float rotation;
    private float rotation2;

    public ArrowDrawable() {

        this.strokeColor = Color.WHITE;
        this.strokeAlpha = 255;
    }

    @Override
    public void draw(Canvas canvas) {
//        Log.i("width;height:", "[" + width + ";" + height + "]");

        rotation = parameter * 180;
        if (flip) {
            rotation = 360 - rotation;
        }
        rotation2 = parameter * 45;

        float seniline = (float) ((lineLength / 2.0f) * Math.cos(rotation2 * Math.PI / 180));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(strokeColor);
        paint.setAlpha(strokeAlpha);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        canvas.save();
        canvas.translate(40, 40);
        canvas.rotate(rotation);
        canvas.drawLine(-25, 0, 25, 0, paint);

        canvas.save();
        canvas.rotate(rotation2);
        canvas.drawLine(-seniline, -gap, seniline, -gap, paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(-rotation2);
        canvas.drawLine(-seniline, gap, seniline, gap, paint);
        canvas.restore();

        canvas.restore();

    }

    public void setParameter(float parameter) {
        if (parameter > 1 || parameter < 0) {
            throw new IllegalArgumentException("Value must be between 1 and zero inclusive!");
        }
        this.parameter = parameter;
        Log.i("parameter =", "" + parameter);
        invalidateSelf();
    }

    public void setFlip(boolean flip) {
        this.flip = flip;
        invalidateSelf();
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        invalidateSelf();
    }

    public void setStrokeAlpha(int strokeAlpha) {
        this.strokeAlpha = strokeAlpha;
        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return TRANSLUCENT;
    }
}

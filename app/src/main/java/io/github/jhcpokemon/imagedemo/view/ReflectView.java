package io.github.jhcpokemon.imagedemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import io.github.jhcpokemon.imagedemo.R;

public class ReflectView extends View {
    private Bitmap bitmap, reflectBmp;
    private Paint paint;

    public ReflectView(Context context) {
        super(context);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ReflectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        reflectBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        paint.setShader(new LinearGradient(0, bitmap.getWidth(), 0, bitmap.getHeight()  * 1.4F,
                0xdd000000, 0x10000000, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(reflectBmp, 0, bitmap.getHeight(), null);
        canvas.drawRect(0, bitmap.getHeight(), bitmap.getWidth(), bitmap.getHeight() * 2, paint);
    }
}

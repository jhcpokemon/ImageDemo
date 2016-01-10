package io.github.jhcpokemon.imagedemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import io.github.jhcpokemon.imagedemo.R;

public class ShaderView extends View {
    private Bitmap bitmap;
    private Paint paint;
    private BitmapShader shader;

    public ShaderView(Context context) {
        super(context);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        shader = new BitmapShader(resized, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(300, 300, 300, paint);
    }
}

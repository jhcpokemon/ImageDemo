package io.github.jhcpokemon.imagedemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;

import io.github.jhcpokemon.imagedemo.R;

public class MeshView extends View {
    private Bitmap bitmap;
    private float[] origin = new float[COUNT * 2];
    private float[] vert = new float[COUNT * 2];
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);

    public MeshView(Context context) {
        super(context);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        int index = 0;
        float bmpWidth = bitmap.getWidth();
        float bmpHeight = bitmap.getHeight();
        for (int i = 0; i < HEIGHT + 1; i++) {
            float fy = bmpHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float fx = bmpWidth * j / WIDTH;
                origin[index * 2] = vert[index * 2] = fx + 40;
                origin[index * 2 + 1] = vert[index * 2 + 1] = fy + 60;
                index++;
            }
        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < HEIGHT + 1; i++) {
            for (int j = 0; j < WIDTH + 1; j++) {
                float offSet = (float) Math.sin((float) j / WIDTH * 2 * Math.PI);
                vert[(i * (WIDTH + 1) + j) * 2 + 1] = origin[(i * (WIDTH + 1) + j) * 2 + 1] + offSet * 50;
            }
            invalidate();
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, vert, 0, null, 0, null);
        }
    }
}

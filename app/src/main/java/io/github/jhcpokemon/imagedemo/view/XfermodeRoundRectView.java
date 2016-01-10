package io.github.jhcpokemon.imagedemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import io.github.jhcpokemon.imagedemo.R;

public class XFermodeRoundRectView extends View {
    private Bitmap bitmap, mOut;
    private Paint paint;
    private int width,height;

    public XFermodeRoundRectView(Context context) {
        super(context);
        init();
    }

    public XFermodeRoundRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XFermodeRoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,width / 3,height / 3,false);

        mOut = Bitmap.createBitmap(resizedBitmap.getWidth(), resizedBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mOut);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRoundRect(0, 0, resizedBitmap.getWidth(), resizedBitmap.getHeight(), 50, 50, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resizedBitmap, 0, 0, paint);
        paint.setXfermode(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mOut, 0, 0, null);
    }
}

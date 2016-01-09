package io.github.jhcpokemon.imagedemo.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

import io.github.jhcpokemon.imagedemo.activity.PrimaryColorActivity;

public class ImageTool {
    public static Bitmap handleBitmapEffect(Bitmap bmp, float red, float green, float blue, float saturation, float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrix imageMatrix = new ColorMatrix();

        ColorMatrix matrix = new ColorMatrix();
        matrix.setRotate(0, red); //Change Red
        imageMatrix.postConcat(matrix);

        matrix.reset();
        matrix.setRotate(1, green); //Change Green
        imageMatrix.postConcat(matrix);

        matrix.reset();
        matrix.setRotate(2, blue); //Change Blue
        imageMatrix.postConcat(matrix);

        matrix.reset();
        matrix.setSaturation(saturation);
        imageMatrix.postConcat(matrix);

        matrix.reset();
        matrix.setScale(lum, lum, lum, 1); //lum of RGBA  alpha = 1 --> no translation
        imageMatrix.postConcat(matrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bitmap;
    }

    public static Bitmap reverseBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] oldPixels = new int[width * height];
        int[] newPixels = new int[width * height];
        int color, red, green, blue, alpha;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPixels[i];
            red = Color.red(color);
            green = Color.green(color);
            blue = Color.blue(color);
            alpha = Color.alpha(color);
            red = check(255 - red);
            green = check(255 - green);
            blue = check(255 - blue);
            newPixels[i] = Color.argb(alpha, red, green, blue);
        }
        bmp.setPixels(newPixels, 0, width, 0, 0, width, height);
        return bmp;
    }

    public static Bitmap oldEffectBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] oldPixels = new int[width * height];
        int[] newPixels = new int[width * height];
        int color, red, green, blue, alpha;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPixels[i];
            red = Color.red(color);
            green = Color.green(color);
            blue = Color.blue(color);
            alpha = Color.alpha(color);
            red = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
            green = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
            blue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);
            red = check(red);
            green = check(green);
            blue = check(blue);
            newPixels[i] = Color.argb(alpha, red, green, blue);
        }
        bmp.setPixels(newPixels, 0, width, 0, 0, width, height);
        return bmp;
    }

    public static Bitmap reliefEffectBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] oldPixels = new int[width * height];
        int[] newPixels = new int[width * height];
        int ahead, color, red, green, blue, alpha, aheadR, aheadG, aheadB;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.getPixels(oldPixels, 0, width, 0, 0, width, height);
        for (int i = 1; i < width * height; i++) {
            ahead = oldPixels[i - 1];
            aheadR = Color.red(ahead);
            aheadG = Color.green(ahead);
            aheadB = Color.blue(ahead);

            color = oldPixels[i];
            red = Color.red(color);
            green = Color.green(color);
            blue = Color.blue(color);
            alpha = Color.alpha(color);

            aheadR = aheadR - red + 127;
            aheadG = aheadG - green + 127;
            aheadB = aheadB - blue + 127;

            aheadR = check(aheadR);
            aheadG = check(aheadG);
            aheadB = check(aheadB);
            newPixels[i] = Color.argb(alpha, aheadR, aheadG, aheadB);
        }
        bmp.setPixels(newPixels, 0, width, 0, 0, width, height);
        return bmp;
    }

    private static int check(int i) {
        if (i > 255) {
            return 255;
        } else if (i < 0) {
            return 0;
        } else {
            return i;
        }
    }

    public static float formatDegree(int progress) {
        return (progress - PrimaryColorActivity.MID_PROGRESS) * 1.0f / PrimaryColorActivity.MID_PROGRESS * 180;
    }
}

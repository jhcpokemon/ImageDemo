package io.github.jhcpokemon.imagedemo.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.imagedemo.R;

public class ColorMatrixActivity extends AppCompatActivity {
    @Bind(R.id.image_view)
    ImageView imageView;
    @Bind(R.id.my_grid)
    GridLayout gridLayout;
    private Bitmap bitmap;
    private int width, height;
    private EditText[] editTexts;
    private float[] matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_v);
        imageView.setImageBitmap(bitmap);
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                width = gridLayout.getWidth() / 5;
                height = gridLayout.getHeight() / 4;
                init();
            }
        });
    }

    private void init() {
        editTexts = new EditText[20];
        matrix = new float[20];
        for (int i = 0; i < 20; i++) {
            editTexts[i] = new EditText(ColorMatrixActivity.this);
            editTexts[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            gridLayout.addView(editTexts[i], width, height);
        }
        initMatrix();
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                editTexts[i].setText(String.valueOf(1));
            } else {
                editTexts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            if (!editTexts[i].getText().toString().equals("")) {
                matrix[i] = Float.valueOf(editTexts[i].getText().toString());
            } else {
                matrix[i] = 0;
            }
        }
    }

    private void setMatrix() {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix(matrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        imageView.setImageBitmap(bmp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.change_matrix:
                getMatrix();
                setMatrix();
                break;
            case R.id.reset_matrix:
                initMatrix();
                getMatrix();
                setMatrix();
                break;
            default:
                break;
        }
    }

}

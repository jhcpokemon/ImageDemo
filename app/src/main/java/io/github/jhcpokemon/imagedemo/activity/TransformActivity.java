package io.github.jhcpokemon.imagedemo.activity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.imagedemo.R;
import io.github.jhcpokemon.imagedemo.view.MyImageView;

public class TransformActivity extends AppCompatActivity {
    @Bind(R.id.my_grid)
    GridLayout gridLayout;
    @Bind(R.id.image_view)
    MyImageView imageView;
    private EditText[] editTexts;
    private float[] matrix;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        ButterKnife.bind(this);
        editTexts = new EditText[9];
        matrix = new float[9];
        gridLayout.post(new Runnable() {
            @Override
            public void run() {
                width = gridLayout.getWidth() / 3;
                height = gridLayout.getHeight() / 3;
                initView();
                initMatrix();
            }
        });
    }

    public void doClick(View view) {
        switch (view.getId()) {
            case R.id.change_matrix:
                change();
                break;
            case R.id.reset_matrix:
                reset();
                break;
            default:
                break;
        }
    }

    private void initView() {
        for (int i = 0; i < 9; i++) {
            EditText editText = new EditText(TransformActivity.this);
            editText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
            gridLayout.addView(editText, width, height);
            editTexts[i] = editText;
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                matrix[i] = 1;
            } else {
                matrix[i] = 0;
            }
        }
        initEditTexts();
    }

    private void initEditTexts() {
        for (int i = 0; i < 9; i++) {
            if (i % 4 == 0) {
                editTexts[i].setText(String.valueOf(1));
            } else {
                editTexts[i].setText(String.valueOf(0));
            }
        }
    }

    public void getMatrix() {
        for (int i = 0; i < 9; i++) {
            matrix[i] = Float.parseFloat(editTexts[i].getText().toString());
        }
    }

    private void change() {
        getMatrix();
        Matrix imageMatrix = new Matrix();
        imageMatrix.setValues(matrix);
        imageView.setImageMatrix(imageMatrix);
        imageView.invalidate();
    }

    private void reset() {
        initMatrix();
        Matrix imageMatrix = new Matrix();
        imageMatrix.setValues(matrix);
        imageView.setImageMatrix(imageMatrix);
        imageView.invalidate();
    }
}

package io.github.jhcpokemon.imagedemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.imagedemo.R;
import io.github.jhcpokemon.imagedemo.util.ImageTool;

public class PrimaryColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    @Bind(R.id.image_view)
    ImageView imageView;
    @Bind(R.id.change_red)
    SeekBar changeRed;
    @Bind(R.id.change_green)
    SeekBar changeGreen;
    @Bind(R.id.change_blue)
    SeekBar changeBlue;
    @Bind(R.id.change_saturation)
    SeekBar changeSaturation;
    @Bind(R.id.change_lum)
    SeekBar changeLum;
    private float red, green, blue, saturation, lum;
    private Bitmap bitmap;
    public static final int MID_PROGRESS = 127;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_color);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        imageView.setImageBitmap(bitmap);
        red = 0;
        green = 0;
        blue = 0;
        saturation = 1;
        lum = 1;
        changeRed.setOnSeekBarChangeListener(this);
        changeGreen.setOnSeekBarChangeListener(this);
        changeBlue.setOnSeekBarChangeListener(this);
        changeSaturation.setOnSeekBarChangeListener(this);
        changeLum.setOnSeekBarChangeListener(this);
        changeRed.setProgress(MID_PROGRESS);
        changeGreen.setProgress(MID_PROGRESS);
        changeBlue.setProgress(MID_PROGRESS);
        changeSaturation.setProgress(MID_PROGRESS);
        changeLum.setProgress(MID_PROGRESS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.change_red:
                red = ImageTool.formatDegree(progress);      //-1 ~ +1
                break;
            case R.id.change_green:
                green = ImageTool.formatDegree(progress);
                break;
            case R.id.change_blue:
                blue = ImageTool.formatDegree(progress);
                break;
            case R.id.change_saturation:
                saturation = progress * 1.0f / MID_PROGRESS; //0 - 2
                break;
            case R.id.change_lum:
                lum = progress * 1.0f / MID_PROGRESS;        //0 - 2
                break;
            default:
                break;
        }
        imageView.setImageBitmap(ImageTool.handleBitmapEffect(bitmap, red, green, blue, saturation, lum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}

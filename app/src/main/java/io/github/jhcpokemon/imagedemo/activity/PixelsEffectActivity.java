package io.github.jhcpokemon.imagedemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.imagedemo.R;
import io.github.jhcpokemon.imagedemo.util.ImageTool;

public class PixelsEffectActivity extends AppCompatActivity {
    @Bind(R.id.view1)
    ImageView view1;
    @Bind(R.id.view2)
    ImageView view2;
    @Bind(R.id.view3)
    ImageView view3;
    @Bind(R.id.view4)
    ImageView view4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pixels_effect_acticity);
        ButterKnife.bind(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        view1.setImageBitmap(bitmap);
        view2.setImageBitmap(ImageTool.reverseBitmap(bitmap));
        view3.setImageBitmap(ImageTool.oldEffectBitmap(bitmap));
        view4.setImageBitmap(ImageTool.reliefEffectBitmap(bitmap));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

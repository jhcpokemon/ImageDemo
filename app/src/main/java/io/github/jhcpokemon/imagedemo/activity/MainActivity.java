package io.github.jhcpokemon.imagedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.github.jhcpokemon.imagedemo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.change_rgba:
                intent = new Intent(this,PrimaryColorActivity.class);
                startActivity(intent);
                break;
            case R.id.color_matrix:
                intent = new Intent(this,ColorMatrixActivity.class);
                startActivity(intent);
                break;
            case R.id.pixel:
                intent = new Intent(this,PixelsEffectActivity.class);
                startActivity(intent);
                break;
            case R.id.transform:
                intent = new Intent(this,TransformActivity.class);
                startActivity(intent);
                break;
            case R.id.xfermode:
                intent = new Intent(this,XFermodeActivity.class);
                startActivity(intent);
                break;
            case R.id.shader:
                intent = new Intent(this,ShaderActivity.class);
                startActivity(intent);
                break;
            case R.id.reflect:
                intent = new Intent(this,ReflectActivity.class);
                startActivity(intent);
                break;
            case R.id.mesh:
                intent = new Intent(this,MeshActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

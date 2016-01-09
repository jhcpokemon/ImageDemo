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
            default:
                break;
        }
    }
}

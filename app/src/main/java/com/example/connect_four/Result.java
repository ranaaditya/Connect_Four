package com.example.connect_four;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        player=findViewById(R.id.textView);
        Intent intent=getIntent();
        boolean playerstate=intent.getBooleanExtra("playerstate",false);
        if (playerstate) {
            player.setTextColor(Color.RED);
            player.setText("RED PLAYER HAS WON THE MATCH");
        }else{
            player.setTextColor(Color.YELLOW);
            player.setText("YELLOW PLAYER HAS WON THE MATCH");


        }
    }

    public void restart(View view) {
        Intent intent=new Intent(Result.this,game.class);
        startActivity(intent);
    }
}

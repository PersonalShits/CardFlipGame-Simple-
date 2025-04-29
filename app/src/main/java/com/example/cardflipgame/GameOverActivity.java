package com.example.cardflipgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        TextView gameOverText = findViewById(R.id.game_over_text);
        Button retryButton = findViewById(R.id.retry_button);

        // ✨ Animate the "Game Over" text
        Animation popAnimation = AnimationUtils.loadAnimation(this, R.anim.game_over_pop);
        gameOverText.startAnimation(popAnimation);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        Toast.makeText(this, "Game Over! Try Again!", Toast.LENGTH_SHORT).show();
    }
}

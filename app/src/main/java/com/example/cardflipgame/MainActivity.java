package com.example.cardflipgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private CardAdapter cardAdapter;
    private List<CardModel> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        prepareCards();
        cardAdapter = new CardAdapter(this, cardList);
        gridView.setAdapter(cardAdapter);
    }

    private void prepareCards() {
        int[] images = {
                R.drawable.front_velkhana,
                R.drawable.front_nargacuga,
                R.drawable.front_zinogre,
                R.drawable.front_rathalos,
                R.drawable.front_deviljho,
                R.drawable.front_tigrex
        };

        cardList = new ArrayList<>();
        for (int image : images) {
            cardList.add(new CardModel(image));
            cardList.add(new CardModel(image)); // Add a pair
        }

        Collections.shuffle(cardList);
    }
}

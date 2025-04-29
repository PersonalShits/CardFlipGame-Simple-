package com.example.cardflipgame;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<CardModel> cardList;
    private int flippedCardPosition = -1;

    public CardAdapter(Context context, List<CardModel> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int position) {
        return cardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
            holder = new CardViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CardViewHolder) convertView.getTag();
        }

        CardModel card = cardList.get(position);

        if (card.isFlipped() || card.isMatched()) {
            holder.imageView.setImageResource(card.getImageResId());
        } else {
            holder.imageView.setImageResource(R.drawable.card_back);
        }

        holder.imageView.setOnClickListener(v -> {
            if (card.isFlipped() || card.isMatched()) return;

            flipCard(holder.imageView, card, position);
        });

        return convertView;
    }

    private void flipCard(ImageView imageView, CardModel card, int position) {
        imageView.animate()
                .rotationY(90f)
                .setDuration(150)
                .withEndAction(() -> {
                    // Flip the image
                    card.setFlipped(true);
                    notifyDataSetChanged();

                    imageView.setRotationY(-90f);
                    imageView.animate()
                            .rotationY(0f)
                            .setDuration(150)
                            .start();

                    if (flippedCardPosition == -1) {
                        flippedCardPosition = position;
                    } else {
                        checkForMatch(flippedCardPosition, position);
                        flippedCardPosition = -1;
                    }
                }).start();
    }

    private void checkForMatch(int firstPos, int secondPos) {
        CardModel firstCard = cardList.get(firstPos);
        CardModel secondCard = cardList.get(secondPos);

        if (firstCard.getImageResId() == secondCard.getImageResId()) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);

            if (isGameOver()) {
                goToGameOver();
            }
        } else {
            new Handler().postDelayed(() -> {
                firstCard.setFlipped(false);
                secondCard.setFlipped(false);
                notifyDataSetChanged();
            }, 1000);
        }
    }

    private boolean isGameOver() {
        for (CardModel card : cardList) {
            if (!card.isMatched()) {
                return false;
            }
        }
        return true;
    }

    private void goToGameOver() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(context, GameOverActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }, 500);
    }


    private static class CardViewHolder {
        ImageView imageView;

        CardViewHolder(View view) {
            imageView = view.findViewById(R.id.card_image);
        }
    }
}

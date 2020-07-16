package edu.miracosta.cs134.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import edu.miracosta.cs134.gamersdelight.model.Game;


public class GameDetailsActivity extends AppCompatActivity {

    private static final String TAG = GameDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        ImageView gameDetailsImageView = findViewById(R.id.gameDetailsImageView);
        TextView gameDetailsNameTextView = findViewById(R.id.gameDetailsNameTextView);
        TextView gameDetailsDescriptionTextView = findViewById(R.id.gameDetailsDescriptionTextView);
        RatingBar gameDetailsRatingBar = findViewById(R.id.gameDetailsRatingBar);

        Intent detailsIntent = getIntent();

        // Extract the selectedGame object from the Intent
        Game selectedGame = detailsIntent.getParcelableExtra("SelectedGame");

        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(selectedGame.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedGame.getName());
            gameDetailsImageView.setImageDrawable(image);
        }
        catch (IOException ex)
        {
            Log.e(TAG, "Error loading: " + selectedGame.getImageName(), ex);
        }

        gameDetailsNameTextView.setText(selectedGame.getName());
        gameDetailsDescriptionTextView.setText(selectedGame.getDescription());
        gameDetailsRatingBar.setRating(selectedGame.getRating());
    }
}

package com.example.sails.footballsimulator.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sails.footballsimulator.App;
import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.controllers.DataBaseController;
import com.example.sails.footballsimulator.entity.Player;
import com.example.sails.footballsimulator.entity.Team;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PlayerActivity extends AppCompatActivity {

    private int playerId;

    Player player;
    Team team;

    Context context;

    private TextView textViewPlayersName, textViewPlayersSkill, textViewPlayersPosition;
    private TextView textViewPlayersCountry, textViewPlayersTeam, textViewPlayersAge, textViewPlayersHeight;
    TextView textViewPlayersWeight, textViewPlayersLeadingFoot, textViewPlayersAttack, textViewPlayersDefence;
    private ImageView imageViewPlayersPhoto, imageViewEmblem, imageViewPlayersCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPlayerAndTeam();
        setBackgroundsColors();

        setContentView(R.layout.activity_player);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initComponents();
    }

    private void initPlayerAndTeam() {
        playerId = getIntent().getIntExtra("player_id", 1);
        player = DataBaseController.getPlayerById(playerId);
        team = DataBaseController.getTeamFromDBById(player.getTeamId());
    }

    private void setBackgroundsColors(){
        int color = Color.parseColor(team.getTeamColor());
        context = this;


        LayerDrawable layerDrawable = (LayerDrawable) ContextCompat.getDrawable(context, R.drawable.layer_list_players_background);
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.background_team_color);
        gradientDrawable.setColor(color);




        Log.d("debug", "setBackgroundsColors() called");
    }

    private void initComponents() {

        textViewPlayersName = (TextView) findViewById(R.id.textViewPlayersName);
        textViewPlayersSkill = (TextView) findViewById(R.id.textViewPlayersSkill);
        textViewPlayersPosition = (TextView) findViewById(R.id.textViewPlayersPosition);
        textViewPlayersCountry = (TextView) findViewById(R.id.tvPlayersNationality);
        textViewPlayersTeam = (TextView) findViewById(R.id.tvPlayersTeam);
        textViewPlayersAge = (TextView) findViewById(R.id.tvPlayersAge);
        textViewPlayersHeight = (TextView) findViewById(R.id.tvPlayersHeight);
        textViewPlayersWeight = (TextView) findViewById(R.id.tvPlayersWeight);
        textViewPlayersLeadingFoot = (TextView) findViewById(R.id.tvPlayersFoot);
        textViewPlayersAttack = (TextView) findViewById(R.id.tvPlayersAttack);
        textViewPlayersDefence = (TextView) findViewById(R.id.tvPlayersDefence);

        imageViewPlayersPhoto = (ImageView) findViewById(R.id.imageViewPlayersPhoto);
        imageViewEmblem = (ImageView) findViewById(R.id.ivPlayersTeamEmblem);
        imageViewPlayersCountry = (ImageView) findViewById(R.id.imageViewPlayersCountry);



        setPlayersInfoIntoLayout();

    }

    private void setPlayersInfoIntoLayout() {
        textViewPlayersName.setText(player.getName());
        textViewPlayersSkill.setText(String.valueOf(player.getTotalSkill()));
        textViewPlayersPosition.setText(player.getPosition());

        textViewPlayersCountry.setText(player.getCountry());
        textViewPlayersTeam.setText(player.getTeamName());
        textViewPlayersAge.setText(String.valueOf(player.getAge()));
        textViewPlayersHeight.setText(String.valueOf(player.getHeight()));
        textViewPlayersWeight.setText(String.valueOf(player.getWeight()));
        textViewPlayersLeadingFoot.setText(player.getLeadingFoot());
        textViewPlayersAttack.setText(String.valueOf(player.getAttackSkill()));
        textViewPlayersDefence.setText(String.valueOf(player.getDefenceSkill()));

        imageViewEmblem.setImageResource(team.getEmblemResourse());

        Picasso.with(context).load("http://stbarvinok.in.ua/android-apps/football-simulator/flags/32/" + player.getCountry() + ".png").
                placeholder(R.drawable.flag_unknown).
                fit().
                into(imageViewPlayersCountry);

        imageViewEmblem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imageViewEmblem.setSelected(motionEvent.getAction() == MotionEvent.ACTION_DOWN ||
                        motionEvent.getAction() == MotionEvent.ACTION_MOVE);


                return true;
            }
        });

        Picasso.with(getApplicationContext())
                .load("http://stbarvinok.in.ua/android-apps/football-simulator/players/" + player.getPhotoName())
                .fit()
                .placeholder(R.drawable.no_player_photo)
                .error(R.drawable.no_player_photo)
                .into(imageViewPlayersPhoto);
    }
}

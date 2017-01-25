package com.example.sails.footballsimulator.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.entity.Player;
import com.example.sails.footballsimulator.entity.Team;
import com.example.sails.footballsimulator.listeners.OnRecyclerViewSelectTeamInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sails on 09.01.2017.
 */


public class SelectTeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_HEADER = 0;
    private final int VIEW_TYPE_ITEM_0 = 1;
    private final int VIEW_TYPE_ITEM_1 = 2;
    private final int VIEW_TYPE_ITEM_2 = 3;
    private final int VIEW_TYPE_FOOTER = 4;

    private final int COUNT_OF_EXTRA_VIEWS = 3; //headers - 2, footer

    private List<Player> players;
    private Context context;
    private String managersName;
    private List<Team> teams;
    private int currentTeam;
    private OnRecyclerViewSelectTeamInteractionListener onRecyclerViewSelectTeamInteractionListener;

    public SelectTeamAdapter(Context context, String managersName, List<Team> teams,
                             OnRecyclerViewSelectTeamInteractionListener onRecyclerViewSelectTeamInteractionListener) {
        this.teams = teams;
        this.context = context;
        this.managersName = managersName;
        this.onRecyclerViewSelectTeamInteractionListener = onRecyclerViewSelectTeamInteractionListener;

        currentTeam = 0;
        this.players = teams.get(currentTeam).getPlayers();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_select_team, parent, false);
                viewHolder = new HeaderHolder(v);
                break;
            case VIEW_TYPE_ITEM_0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_header, parent, false);
                viewHolder = new OnTopHolder(v);
                break;
            case VIEW_TYPE_ITEM_1:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
                v.setBackgroundResource(R.drawable.selector_item_light);
                viewHolder = new PlayersHolder(v);
                break;
            case VIEW_TYPE_ITEM_2:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player, parent, false);
                v.setBackgroundResource(R.drawable.selector_item_dark);
                viewHolder = new PlayersHolder(v);
                break;
            case VIEW_TYPE_FOOTER:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_go_main_menu, parent, false);
                viewHolder = new FooterHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PlayersHolder) {
            createItemView((PlayersHolder) holder, position);
        } else if (holder instanceof HeaderHolder) {
            createHeaderView((HeaderHolder) holder);
        } else if (holder instanceof OnTopHolder) {
            createTopView((OnTopHolder) holder);
        } else if (holder instanceof FooterHolder) {
            createFooterView((FooterHolder) holder);
        }
    }

    private void createFooterView(FooterHolder holder) {
        holder.buttonGoMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onRecyclerViewSelectTeamInteractionListener.onConfirmButtonClick();


            }
        });
    }

    private void createTopView(OnTopHolder holder) {
        holder.tvCurrentManagerName.setText(teams.get(currentTeam).getManagerName());
    }

    private void createHeaderView(final HeaderHolder holder) {
        holder.textViewManagerName.setText(managersName);

        setHeaderListeners(holder);
        setSelectedTeamInfo(holder);
    }

    private void setSelectedTeamInfo(final HeaderHolder holder) {

        holder.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (++currentTeam >= teams.size())
                    currentTeam = 0;

                setSelectedTeamInfo(holder);
                notifyDataSetChanged();
            }
        });
        holder.buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (--currentTeam < 0)
                    currentTeam = teams.size() - 1;
                setSelectedTeamInfo(holder);
                notifyDataSetChanged();
            }
        });

        System.out.println("teams size: " + teams.size() + " curr: " + currentTeam);
        Team team = teams.get(currentTeam);


        holder.imageSwitcherTeamLogo.setInAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in));
        holder.imageSwitcherTeamLogo.setOutAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right));
        holder.imageSwitcherTeamLogo.setImageResource(team.getEmblemResourse());


        players = team.getPlayers();

        holder.textViewNewTeamName.setText(team.getName());
        holder.textViewTeamAttackLevel.setText(String.valueOf(team.getAttackSkill()));
        holder.textViewTeamDefenceLevel.setText(String.valueOf(team.getDefenceSkill()));
        holder.textViewTeamHalfBackLevel.setText(String.valueOf(team.getHalfBackSkill()));
        holder.ratingBarTeam.setRating(team.getRating());


    }

    private void setHeaderListeners(final HeaderHolder holder) {
        holder.editTextNewCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.toString().length() < 1) {
                    return;
                }
                String ss = s.toString().trim();
                String country = ss.substring(0, 1).toUpperCase() + ss.substring(1);
                Picasso.with(holder.context).load("http://stbarvinok.in.ua/android-apps/football-simulator/flags/32/" + country + ".png")
                        .placeholder(R.drawable.flag_unknown)
                        .fit()
                        .into(holder.imageViewCountryFlag);

            }
        });
        holder.editTextNewYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.toString().length() < 1) {
                    holder.imageViewYearCorrectness.setImageDrawable(null);
                    return;
                }
                if (s.toString().length() > 4) {
                    return;
                }
                if (checkYear(s.toString()))
                    holder.imageViewYearCorrectness.setImageResource(R.mipmap.ic_success);
                else
                    holder.imageViewYearCorrectness.setImageResource(R.mipmap.ic_unsuccess);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void createItemView(PlayersHolder holder, int position) {
        position -= 2;       //because there are 2 headers.

        final Player player = players.get(position);
        holder.tvName.setText(player.getName());
        holder.tvNumber.setText(String.valueOf(player.getNumber()));
        holder.tvPosition.setText(player.getPosition());
        holder.tvSkill.setText(String.valueOf(player.getAttackSkill()));

        System.out.println(player.getPositionId());
        switch (player.getPositionId()) {
            case 4:
                holder.imageViewPlayerPosition.setColorFilter(Color.parseColor("#eb610d"));
                break;
            case 3:
                holder.imageViewPlayerPosition.setColorFilter(Color.parseColor("#ddbc06"));
                break;
            case 2:
                holder.imageViewPlayerPosition.setColorFilter(Color.parseColor("#2b8d04"));
                break;
            case 1:
                holder.imageViewPlayerPosition.setColorFilter(Color.parseColor("#2774f2"));
                break;
        }


        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.setSelected(motionEvent.getAction() == MotionEvent.ACTION_DOWN ||
                        motionEvent.getAction() == MotionEvent.ACTION_MOVE);

                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    onRecyclerViewSelectTeamInteractionListener.onPlayerClick(player.getId());
                }
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onRecyclerViewSelectTeamInteractionListener.onPlayerClick(player.getId());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println("AAAAAAAAAAAAAAAAAAA  " + position);
        if (position == 0)
            return VIEW_TYPE_HEADER;
        if (position == 1)
            return VIEW_TYPE_ITEM_0;
        if (position == getItemCount() - 1)
            return VIEW_TYPE_FOOTER;
        if (position % 2 == 0)
            return VIEW_TYPE_ITEM_1;
        return VIEW_TYPE_ITEM_2;

    }

    @Override
    public int getItemCount() {
        return players.size() + 3;//because there are 2 header layouts, 1 footer
    }

    private boolean checkYear(String year) {
        int yearInt = Integer.parseInt(year);
        return (yearInt > 1930) && (yearInt < 2015);
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        TextView textViewManagerName, textViewNewTeamName, textViewTeamAttackLevel, textViewTeamHalfBackLevel, textViewTeamDefenceLevel;
        EditText editTextNewCountry, editTextNewYear;
        ImageView imageViewCountryFlag, imageViewYearCorrectness;
        RatingBar ratingBarTeam;
        ImageSwitcher imageSwitcherTeamLogo;

        ImageButton buttonPrev, buttonNext;

        Context context;

        HeaderHolder(View itemView) {
            super(itemView);
            this.context = itemView.getContext();

            textViewManagerName = (TextView) itemView.findViewById(R.id.textViewManagerName);
            textViewNewTeamName = (TextView) itemView.findViewById(R.id.textViewNewTeamName);
            textViewTeamAttackLevel = (TextView) itemView.findViewById(R.id.textViewTeamAttackLevel);
            textViewTeamHalfBackLevel = (TextView) itemView.findViewById(R.id.textViewTeamHalfBackLevel);
            textViewTeamDefenceLevel = (TextView) itemView.findViewById(R.id.textViewTeamDefenceLevel);

            editTextNewCountry = (EditText) itemView.findViewById(R.id.editTextNewCountry);
            editTextNewYear = (EditText) itemView.findViewById(R.id.editTextNewYear);

            imageViewCountryFlag = (ImageView) itemView.findViewById(R.id.imageViewCountryFlag);
            imageViewYearCorrectness = (ImageView) itemView.findViewById(R.id.imageViewYearCorrectness);

            imageSwitcherTeamLogo = (ImageSwitcher) itemView.findViewById(R.id.imageSwitcherTeamLogo);

            ratingBarTeam = (RatingBar) itemView.findViewById(R.id.ratingBarTeam);
            buttonPrev = (ImageButton) itemView.findViewById(R.id.buttonPrev);
            buttonNext = (ImageButton) itemView.findViewById(R.id.buttonNext);

            imageSwitcherTeamLogo.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ImageSwitcher.LayoutParams params = new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.gravity = Gravity.CENTER_HORIZONTAL;
                    imageView.setLayoutParams(params);
                    return imageView;
                }
            });
        }
    }

    private class PlayersHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPosition, tvNumber, tvSkill;
        ImageView imageViewPlayerPosition;
        View itemView;

        PlayersHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.textViewPlayersName);
            tvNumber = (TextView) itemView.findViewById(R.id.textViewPlayersNumber);
            tvPosition = (TextView) itemView.findViewById(R.id.textViewPlayersPosition);
            tvSkill = (TextView) itemView.findViewById(R.id.textViewPlayersSkill);

            imageViewPlayerPosition = (ImageView) itemView.findViewById(R.id.imageViewPlayerPosition);


        }
    }

    private class OnTopHolder extends RecyclerView.ViewHolder {
        TextView tvCurrentManagerName;


        OnTopHolder(View itemView) {
            super(itemView);
            tvCurrentManagerName = (TextView) itemView.findViewById(R.id.tvCurrentManagerName);

        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {

        Button buttonGoMainMenu;

        FooterHolder(View itemView) {
            super(itemView);
            buttonGoMainMenu = (Button) itemView.findViewById(R.id.buttonGoMainMenu);
        }
    }
}
package com.example.sails.footballsimulator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.listeners.OnRecycleViewInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sails on 06.01.2017.
 */

public class ManagersAdapter extends RecyclerView.Adapter<ManagersAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Manager> managers;
    private List<Manager> filteredManagers;
    private OnRecycleViewInteractionListener myListener;

    public ManagersAdapter(Context context, List<Manager> managers, OnRecycleViewInteractionListener listener) {
        this.context = context;
        this.managers = managers;
        this.filteredManagers = managers;
        this.myListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewManagerPhoto;
        ImageView imageViewManagerCountryFlag;
        TextView tvManagerName;
        TextView tvManagerNationality;
        TextView tvManagerAge;
        TextView tvManagerYear;
        TextView tvManagerTeam;
        View itemView;
        Manager manager;

        ViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;

            imageViewManagerPhoto = (ImageView) itemView.findViewById(R.id.imageViewManagerPhoto);
            imageViewManagerCountryFlag = (ImageView) itemView.findViewById(R.id.imageViewManagerCountryFlag);
            tvManagerName = (TextView) itemView.findViewById(R.id.tvManagerName);
            tvManagerNationality = (TextView) itemView.findViewById(R.id.tvManagerNationality);
            tvManagerAge = (TextView) itemView.findViewById(R.id.tvManagerAge);
            tvManagerYear = (TextView) itemView.findViewById(R.id.tvManagerYear);
            tvManagerTeam = (TextView) itemView.findViewById(R.id.tvManagerTeam);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manager, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Manager manager = filteredManagers.get(position);
        holder.manager = manager;

        String name = manager.getName();
        String photoUri = manager.getPhotoUri();
        final String country = manager.getCountry();
        int age = manager.getAge();
        int year = manager.getBirthdayYear();
        String team = manager.getTeam();

        System.out.println(manager);
        holder.tvManagerName.setText(name);
        holder.tvManagerNationality.setText(country);
        holder.tvManagerAge.setText(String.valueOf(age));
        holder.tvManagerYear.setText(String.valueOf(year));
        holder.tvManagerTeam.setText(team);

        Picasso.with(context).load(photoUri).
                placeholder(R.drawable.no_photo).
                fit().
                error(R.drawable.no_photo).
                into(holder.imageViewManagerPhoto);

        Picasso.with(context).load("http://stbarvinok.in.ua/android-apps/football-simulator/flags/32/" + country + ".png").
                placeholder(R.drawable.flag_unknown).
                fit().
                into(holder.imageViewManagerCountryFlag);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != myListener) {
                    myListener.onItemClick(holder.manager);
                }
            }
        });

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                holder.itemView.setSelected(event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return filteredManagers.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    filterResults.values = managers;
                    filterResults.count = managers.size();
                } else {

                    ArrayList<Manager> filteredManagersData = new ArrayList<>();
                    String lowerConstraint = constraint.toString().toLowerCase();


                    for (Manager m : managers) {
                        String name = m.getName().toLowerCase();
                        String team = m.getTeam().toLowerCase();
                        if (name.contains(lowerConstraint)
                                || team.contains((lowerConstraint))) {
                            filteredManagersData.add(m);
                        }
                    }
                    filterResults.values = filteredManagersData;
                    filterResults.count = filteredManagersData.size();
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredManagers = (ArrayList<Manager>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}

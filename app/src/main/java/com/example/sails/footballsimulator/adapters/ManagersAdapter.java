package com.example.sails.footballsimulator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sails.footballsimulator.R;
import com.example.sails.footballsimulator.entity.Manager;
import com.example.sails.footballsimulator.listeners.OnRecycleViewInteractionListener;

import java.util.List;

/**
 * Created by sails on 06.01.2017.
 */

public class ManagersAdapter extends RecyclerView.Adapter<ManagersAdapter.ViewHolder> {

    Context context;
    List<Manager> managers;
    OnRecycleViewInteractionListener myListener;

    public ManagersAdapter(Context context, List<Manager> managers, OnRecycleViewInteractionListener listener) {
        this.context = context;
        this.managers = managers;
        this.myListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewManagerPhoto;
        TextView tvManagerName;
        TextView tvManagerNationality;
        TextView tvManagerAge;
        TextView tvManagerYear;
        TextView tvManagerTeam;
        View itemView;
        Manager manager;

        public ViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;

            imageViewManagerPhoto = (ImageView) itemView.findViewById(R.id.imageViewManagerPhoto);
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

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Manager manager = managers.get(position);
        holder.manager = manager;

        String name = manager.getName();
        String photoName = manager.getPhotoName();
        final String country = manager.getContry();
        int age = manager.getAge();
        int year = manager.getBirthdayYear();
        String team = manager.getTeam();

        System.out.println(manager);
        holder.tvManagerName.setText(name);
        holder.tvManagerNationality.setText(country);
        holder.tvManagerAge.setText(String.valueOf(age));
        holder.tvManagerYear.setText(String.valueOf(year));
        holder.tvManagerTeam.setText(team);
        holder.imageViewManagerPhoto.setImageResource(R.drawable.img_manager);

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
        return managers.size();
    }


}

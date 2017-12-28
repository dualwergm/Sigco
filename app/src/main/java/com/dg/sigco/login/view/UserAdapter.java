package com.dg.sigco.login.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dg.sigco.R;
import com.dg.sigco.card.data.Card;
import com.dg.sigco.card.data.Detail;
import com.dg.sigco.common.Constants;
import com.dg.sigco.common.UtilDate;
import com.dg.sigco.detail.view.NewDetailActivity;
import com.dg.sigco.login.data.Userc;
import com.dg.sigco.login.repository.UserRepository;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<Userc> users;
    private Activity activity;
    private int resource;
    public UserAdapter(List<Userc> users, Activity activity, int resource){
        this.users = users;
        this.activity = activity;
        this.resource = resource;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new UserViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final Userc userc = users.get(position);
        holder.userName.setText(userc.getName());
        holder.user.setText(userc.getUserc());
        holder.containerItemUser.setTag(userc);
        holder.activeUser.setText(userc.isActiveUser() ? "Inactivar" : "Activar");
        holder.activeUser.setTag(userc);
        if(userc.isAdmin()){
            holder.userName.setTypeface(holder.userName.getTypeface(), Typeface.BOLD);
        }
        initializeCreate(holder);
        initializeActive(holder);
    }

    private void initializeActive(final UserViewHolder holder) {
        holder.activeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userc user = (Userc)v.getTag();
                UserRepository userRepository = new UserRepository();
                userRepository.activeUser(user.get_id(), user.newStatus());
                activity.finish();
                activity.startActivity(activity.getIntent());
            }
        });
    }

    private void initializeCreate(UserViewHolder holder){
        holder.containerItemUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userc user = (Userc)v.getTag();
                Intent intent = new Intent(activity, CreateUserActivity.class);
                intent.putExtra(Constants.USER, user);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout containerItemUser;
        private TextView userName, user;
        private Button activeUser;
        public UserViewHolder(View itemView) {
            super(itemView);
            userName = (TextView)itemView.findViewById(R.id.userName);
            user = (TextView)itemView.findViewById(R.id.user);
            containerItemUser = (LinearLayout)itemView.findViewById(R.id.containerItemUser);
            activeUser = (Button)itemView.findViewById(R.id.activeUser);
        }
    }
}

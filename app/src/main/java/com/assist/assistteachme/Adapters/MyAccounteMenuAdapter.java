package com.assist.assistteachme.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assist.assistteachme.Models.CourseDetails;
import com.assist.assistteachme.Models.MyAccountMenuModel;
import com.assist.assistteachme.R;

import java.util.ArrayList;

public class MyAccounteMenuAdapter extends RecyclerView.Adapter<MyAccounteMenuAdapter.ViewHolder>   {

    private static final String TAG = "RecycleViewAdapterS";

    private ArrayList<MyAccountMenuModel> myAccountMenuList = new ArrayList<>();
    Context mContext;

    public MyAccounteMenuAdapter() {
    }

    public MyAccounteMenuAdapter(ArrayList<MyAccountMenuModel> myAccountMenuList, Context mContext) {
        this.myAccountMenuList = myAccountMenuList;
        this.mContext = mContext;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_accounte_menu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAccounteMenuAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "on Bind ViewHolder");

        MyAccountMenuModel listM = myAccountMenuList.get(position);

        holder.Text1.setText(listM.getText1());
        holder.Text2.setText(listM.getText2());
        holder.CategoryM.setText(listM.getPoints());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "asda",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {

        return myAccountMenuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView Text1;
        TextView Text2;
        TextView Text3;
        TextView CategoryM;

        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.my_account_menu_itemphoto);
            Text1 = itemView.findViewById(R.id.my_account_menu_bannertext);
            Text2 = itemView.findViewById(R.id.my_account_menu_subbannertext);
            Text3 = itemView.findViewById(R.id.my_account_menu_pointstext);
            CategoryM = itemView.findViewById(R.id.my_account_menu_pointstext);
            parentLayout = itemView.findViewById(R.id.my_account_menu_recycle_layout);
        }
    }


}

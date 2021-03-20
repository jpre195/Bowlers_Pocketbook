package com.example.bowlerspocketbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FindABallAdapter extends RecyclerView.Adapter<FindABallAdapter.ViewHolder> {

    Context context;
    ArrayList<String> brandList;
    ArrayList<Integer> ballImages;
    ArrayList<Integer> ballNameList;
    ArrayList<String> coreList;
    ArrayList<Integer> rgList;
    ArrayList<Integer> diffList;
    ArrayList<Integer> coverList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView brandTextView;
        TextView ballNameTextView;
        TextView coreTextView;
        TextView rgTextView;
        TextView diffTextView;
        TextView coverTextView;
        ImageView ballImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            brandTextView = itemView.findViewById(R.id.brandExampleTextView);
            ballNameTextView = itemView.findViewById(R.id.ballExampleTextView);
            coreTextView = itemView.findViewById(R.id.coreExampleTextView);
            rgTextView = itemView.findViewById(R.id.rgExampleTextView);
            diffTextView = itemView.findViewById(R.id.differentialExampleTextView);
            coverTextView = itemView.findViewById(R.id.coverstockExampleTextView);
            ballImageView = itemView.findViewById(R.id.ballImageView);
        }
    }

    public FindABallAdapter(Context context, ArrayList<String> brandList, ArrayList<Integer> ballImages, ArrayList<Integer> ballNameList, ArrayList<String> coreList, ArrayList<Integer> rgList, ArrayList<Integer> diffList, ArrayList<Integer> coverList) {
        this.context = context;
        this.brandList = brandList;
        this.ballNameList = ballNameList;
        this.ballImages = ballImages;
        this.coreList = coreList;
        this.rgList = rgList;
        this.rgList = diffList;
        this.rgList = coverList;
    }

    @NonNull
    @Override
    public FindABallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_ball, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ballImageView.setImageResource(ballImages.get(position));
        holder.brandTextView.setText(brandList.get(position));
        holder.ballNameTextView.setText(ballNameList.get(position));
        holder.coreTextView.setText(coreList.get(position));
        holder.rgTextView.setText(coreList.get(position));
        holder.diffTextView.setText(diffList.get(position));
        holder.coverTextView.setText(coverList.get(position));

    }

    @Override
    public int getItemCount() {
        return ballNameList.size();
    }

}

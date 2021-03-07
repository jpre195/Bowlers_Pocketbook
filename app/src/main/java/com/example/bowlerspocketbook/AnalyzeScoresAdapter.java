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

public class AnalyzeScoresAdapter extends RecyclerView.Adapter<AnalyzeScoresAdapter.ViewHolder> {

    Context context;
    ArrayList<String> eventTypeList;
    ArrayList<Integer> ballImages;
    ArrayList<Integer> gameList;
    ArrayList<String> datesList;
    ArrayList<Integer> scoresList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView scoreTextView;
        TextView eventTextView;
        TextView gameTextView;
        TextView dateTextView;
        ImageView ballImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scoreTextView = itemView.findViewById(R.id.scoreExampleTextView);
            eventTextView = itemView.findViewById(R.id.eventExampleTextView);
            gameTextView = itemView.findViewById(R.id.gameExampleTextView);
            dateTextView = itemView.findViewById(R.id.dateExampleTextView);
            ballImageView = itemView.findViewById(R.id.ballImageView);
        }
    }

    public AnalyzeScoresAdapter(Context context, ArrayList<String> eventTypeList, ArrayList<Integer> ballImages, ArrayList<Integer> gameList, ArrayList<String> datesList, ArrayList<Integer> scoresList) {
        this.context = context;
        this.eventTypeList = eventTypeList;
        this.datesList = datesList;
        this.ballImages = ballImages;
        this.gameList = gameList;
        this.scoresList = scoresList;
    }

    @NonNull
    @Override
    public AnalyzeScoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_score, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ballImageView.setImageResource(ballImages.get(position));
        holder.eventTextView.setText("Event: " + eventTypeList.get(position));
        holder.gameTextView.setText("Game: " + gameList.get(position));
        holder.dateTextView.setText(datesList.get(position));
        holder.scoreTextView.setText("" + scoresList.get(position));

    }

    @Override
    public int getItemCount() {
        return scoresList.size();
    }

}

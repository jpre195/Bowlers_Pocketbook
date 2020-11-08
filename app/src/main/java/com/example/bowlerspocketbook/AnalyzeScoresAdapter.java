package com.example.bowlerspocketbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnalyzeScoresAdapter extends RecyclerView.Adapter<AnalyzeScoresAdapter.ViewHolder> {

    Context context;
    String[] eventTypeList;
    int[] ballImages;
    int[] gameList;
    int[] scoresList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView scoreTextView;
        TextView eventTextView;
        TextView gameTextView;
        ImageView ballImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scoreTextView = itemView.findViewById(R.id.scoreExampleTextView);
            eventTextView = itemView.findViewById(R.id.eventExampleTextView);
            gameTextView = itemView.findViewById(R.id.gameExampleTextView);
            ballImageView = itemView.findViewById(R.id.ballImageView);
        }
    }

    public AnalyzeScoresAdapter(Context context, String[] eventTypeList, int[] ballImages, int[] gameList, int[] scoresList) {
        this.context = context;
        this.eventTypeList = eventTypeList;
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
    public void onBindViewHolder(@NonNull AnalyzeScoresAdapter.ViewHolder holder, int position) {
        holder.ballImageView.setImageResource(ballImages[position]);
        holder.eventTextView.setText(eventTypeList[position]);
        holder.gameTextView.setText(gameList[position]);
        holder.scoreTextView.setText(scoresList[position]);

    }

    @Override
    public int getItemCount() {
        return scoresList.length;
    }

}

package com.example.bowlerspocketbook;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnalyzeScoresAdapter extends RecyclerView.Adapter<AnalyzeScoresAdapter.ViewHolder> {

    Context context;
    String[] eventTypeList;
    int[] ballImages;
    int[] gameList;
    int[] scoresList;

    @NonNull
    @Override
    public AnalyzeScoresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyzeScoresAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

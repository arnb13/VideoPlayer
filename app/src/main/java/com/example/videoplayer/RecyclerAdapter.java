package com.example.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<String> arrayList_file;
    Context context;
    private OnVideoListener onVideoListener;

    public RecyclerAdapter(ArrayList<String> arrayList_file, OnVideoListener onVideoListener, Context context) {
        this.arrayList_file = arrayList_file;
        this.context = context;
        this.onVideoListener = onVideoListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        ViewHolder holder = new ViewHolder(view, onVideoListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_file_name.setText(new File(arrayList_file.get(position)).getName());

    }

    @Override
    public int getItemCount() {
        return arrayList_file.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_file_name;
        LinearLayout parent_layout;
        OnVideoListener onVideoListener;

        public ViewHolder(@NonNull View itemView, OnVideoListener onVideoListener) {
            super(itemView);
            textView_file_name = itemView.findViewById(R.id.textView_file_name);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            this.onVideoListener = onVideoListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onVideoListener.OnVideoClick(getAdapterPosition());

        }
    }

    public interface OnVideoListener {
        void OnVideoClick(int position);

    }
}

package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.Holder> {

    private Context context;
    private List<Comic> list;

    public ComicAdapter(Context context, List<Comic> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Comic c = list.get(position);
        holder.img.setImageResource(c.getImageResId());
        holder.title.setText(c.getTitle());
        holder.chapter.setText(c.getChapter());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, chapter;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgComic);
            title = itemView.findViewById(R.id.txtComicTitle);
            chapter = itemView.findViewById(R.id.txtComicAuthor);
        }
    }
}

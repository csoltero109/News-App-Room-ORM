package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private final LayoutInflater mInflater;
    private List<NewsItem> mAllNewsItems;
    private NewsItemViewModel newsItemViewModel;

    public NewsRecyclerViewAdapter(Context context, NewsItemViewModel newsItemViewModel) {
        this.newsItemViewModel = newsItemViewModel;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int view) {
        View viewer = mInflater.inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(viewer);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        if(mAllNewsItems != null) {
            NewsItem currentNewsItem = mAllNewsItems.get(position);
            holder.title.setText("Title: " + currentNewsItem.title);
            holder.description.setText("Description: " + currentNewsItem.description);
            holder.date.setText("Date: " + currentNewsItem.publishedAt);
        }
        else{
            holder.title.setText("Title: No title");
            holder.description.setText("Description: No description");
            holder.date.setText("Date: No date");
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private TextView date;

        private NewsViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.title_view);
            description = itemView.findViewById(R.id.desc_view);
            date = itemView.findViewById(R.id.date_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String link = mAllNewsItems.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            view.getContext().startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        if (mAllNewsItems != null)
            return mAllNewsItems.size();
        return  0;
    }

    public void setUpNewsItemList(List<NewsItem> inputNewsItems) {
        mAllNewsItems = inputNewsItems;
        notifyDataSetChanged();
    }
}
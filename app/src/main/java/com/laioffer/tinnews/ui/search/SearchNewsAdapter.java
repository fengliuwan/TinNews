package com.laioffer.tinnews.ui.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder> {
    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();

    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    interface ItemCallback {
        void onOpenDetails(Article article);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_news_item, parent, false);
        SearchNewsViewHolder searchNewsViewHolder = new SearchNewsViewHolder(view);
        Log.d("tim", searchNewsViewHolder.toString() + "onCreate");
        return searchNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        // bind article with viewholder
        Article article = articles.get(position);
        holder.itemTitleTextView.setText(article.title);
        if (article.urlToImage != null) {
            Picasso.get()
                    .load(article.urlToImage)
                    .resize(200, 200)
                    .into(holder.itemImageView);
        }
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    // 2. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }


}

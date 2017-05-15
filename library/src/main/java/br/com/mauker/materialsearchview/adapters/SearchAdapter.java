package br.com.mauker.materialsearchview.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.mauker.materialsearchview.R;
import br.com.mauker.materialsearchview.models.SearchItem;

/**
 * Adapter that displays the user's search history.
 *
 * Created by adam.mcneilly on 5/11/17.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<SearchItem> history = new ArrayList<>();
    private OnSearchItemClickListener onSearchItemClickListener;
    private OnSearchItemLongClickListener onSearchItemLongClickListener;

    public SearchAdapter(List<SearchItem> history) {
        this.history = history;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bindItem(history.get(position));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public SearchItem getItem(int position) {
        return history.get(position);
    }

    public void swapItems(List<SearchItem> history) {
        this.history = history;
        notifyDataSetChanged();
    }

    public void filter(String filter) {
        //TODO: WAT
        //history.removeIf(x -> !x.getQuery().contains(filter));
        notifyDataSetChanged();
    }

    public void setOnSearchItemClickListener(OnSearchItemClickListener listener) {
        this.onSearchItemClickListener = listener;
    }

    public void setOnSearchItemLongClickListener(OnSearchItemLongClickListener listener) {
        this.onSearchItemLongClickListener = listener;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView icon;
        private TextView content;

        public SearchViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            icon = (ImageView) view.findViewById(R.id.iv_icon);
            content = (TextView) view.findViewById(R.id.tv_str);
        }

        public void bindItem(SearchItem item) {
            this.icon.setImageResource(item.getImageResource());
            this.content.setText(item.getQuery());
        }

        @Override
        public void onClick(View v) {
            if (onSearchItemClickListener != null) {
                onSearchItemClickListener.onSearchItemClick(history.get(getAdapterPosition()));
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onSearchItemLongClickListener != null) {
                onSearchItemLongClickListener.onSearchItemLongClick(history.get(getAdapterPosition()));
                return true;
            } else {
                return false;
            }
        }
    }

    public interface OnSearchItemClickListener {
        void onSearchItemClick(SearchItem item);
    }

    public interface OnSearchItemLongClickListener {
        void onSearchItemLongClick(SearchItem searchItem);
    }
}

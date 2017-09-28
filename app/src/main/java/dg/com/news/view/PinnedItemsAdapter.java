package dg.com.news.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dg.com.news.R;
import dg.com.news.adapter.OnFeedItemClickListener;
import dg.com.news.models.Feed;

/**
 * The type Pinned items adapter.
 */
public class PinnedItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Feed> feedItems = new ArrayList<>();

    private Context context;
    private OnFeedItemClickListener onFeedItemClickListener;

    /**
     * Instantiates a new Pinned items adapter.
     *
     * @param context the context
     */
    public PinnedItemsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pinned_items, parent, false);
        PinnedItemsAdapter.PinnedFeedViewHolder cellFeedViewHolder = new PinnedItemsAdapter.PinnedFeedViewHolder(view);
        setupClickableViews(view, cellFeedViewHolder);
        return cellFeedViewHolder;
    }

    private void setupClickableViews(final View view, final PinnedItemsAdapter.PinnedFeedViewHolder pinnedFeedViewHolder) {
        pinnedFeedViewHolder.ivNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = pinnedFeedViewHolder.getAdapterPosition();
                Feed feed = feedItems.get(adapterPosition);
                onFeedItemClickListener.onFeedItemClick(feed, pinnedFeedViewHolder.ivNews);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((PinnedItemsAdapter.PinnedFeedViewHolder) viewHolder).bindView(feedItems.get(position));
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    /**
     * Add all.
     *
     * @param feedList the list
     */
    public void addAll(List<Feed> feedList) {
        feedItems.clear();
        feedItems.addAll(feedList);
        notifyDataSetChanged();
    }


    /**
     * Remove item.
     *
     * @param feed the feed
     */
    public void removeItem(Feed feed){
        feedItems.remove(feed);
        notifyDataSetChanged();
    }

    /**
     * Add item.
     *
     * @param feed the feed
     */
    public void addItem(Feed feed){
        feedItems.add(feed);
        notifyDataSetChanged();
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size(){
        return feedItems.size();
    }

    /**
     * Clear.
     */
    public void clear() {
        feedItems.clear();
        notifyDataSetChanged();
    }

    /**
     * Sets on feed item click listener.
     *
     * @param onFeedItemClickListener the on feed item click listener
     */
    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    /**
     * The type Pinned feed view holder.
     */
    public class PinnedFeedViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Tv title.
         */
        @BindView(R.id.tv_title)
        TextView tvTitle;
        /**
         * The Tv subtitle.
         */
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;
        /**
         * The Iv news.
         */
        @BindView(R.id.iv_news)
        ImageView ivNews;

        /**
         * Instantiates a new Pinned feed view holder.
         *
         * @param view the view
         */
        public PinnedFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * Bind view.
         *
         * @param feedItem the feed item
         */
        public void bindView(Feed feedItem) {
            if(feedItem.isValid()) {
                Picasso.with(context).load(feedItem.getFeedInfo().getThumbnail()).fit().centerCrop().placeholder(R.drawable.scrim).into(ivNews);
                tvTitle.setText(feedItem.getWebTitle());
                tvSubtitle.setText(feedItem.getSectionName());
            }

        }
    }
}
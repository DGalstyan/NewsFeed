package dg.com.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dg.com.news.R;
import dg.com.news.models.Feed;

/**
 * Created by dgalstya on 26.09.2017.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * The constant ACTION_LIKE_BUTTON_CLICKED.
     */
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    /**
     * The constant ACTION_LIKE_IMAGE_CLICKED.
     */
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    private final List<Feed> feedItems = new ArrayList<>();

    private Context context;
    private OnFeedItemClickListener onFeedItemClickListener;
    private OnLikeButtonClickListener onLikeButtonClickListener;

    /**
     * Instantiates a new Feed adapter.
     *
     * @param context the context
     */
    public FeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);
        setupClickableViews(view, cellFeedViewHolder);
        return cellFeedViewHolder;
    }

    private void setupClickableViews(final View view, final CellFeedViewHolder cellFeedViewHolder) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Feed feed = feedItems.get(adapterPosition);
                onFeedItemClickListener.onFeedItemClick(feed, cellFeedViewHolder.ivFeedCenter);
            }
        });
        cellFeedViewHolder.ivFeedCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Feed feed = feedItems.get(adapterPosition);
                onLikeButtonClickListener.onLikeButtonClick(feed);
                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);

            }
        });
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                Feed feed = feedItems.get(adapterPosition);
                onLikeButtonClickListener.onLikeButtonClick(feed);
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CellFeedViewHolder) viewHolder).bindView(feedItems.get(position));
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
     * Sets on feed item click listener.
     *
     * @param onFeedItemClickListener the on feed item click listener
     */
    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    /**
     * Sets on like button click listener.
     *
     * @param onLikeButtonClickListener the on like button click listener
     */
    public void setOnLikeButtonClickListener(OnLikeButtonClickListener onLikeButtonClickListener) {
        this.onLikeButtonClickListener = onLikeButtonClickListener;
    }

    /**
     * The type Cell feed view holder.
     */
    public class CellFeedViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Iv feed center.
         */
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        /**
         * The Tv title.
         */
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        /**
         * The Tv category.
         */
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        /**
         * The Btn like.
         */
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        /**
         * The V bg like.
         */
        @BindView(R.id.vBgLike)
        View vBgLike;
        /**
         * The Iv like.
         */
        @BindView(R.id.ivLike)
        ImageView ivLike;
        /**
         * The V image root.
         */
        @BindView(R.id.vImageRoot)
        FrameLayout vImageRoot;

        /**
         * Instantiates a new Cell feed view holder.
         *
         * @param view the view
         */
        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        /**
         * Bind view.
         *
         * @param feedItem the feed item
         */
        public void bindView(Feed feedItem) {
            Picasso.with(context).load(feedItem.getFeedInfo().getThumbnail()).fit().centerCrop().placeholder(R.drawable.scrim).into(ivFeedCenter);
            tvTitle.setText(feedItem.getWebTitle());
            tvCategory.setText(feedItem.getSectionName());
            btnLike.setImageResource(feedItem.isLiked() ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
        }
    }

    /**
     * The interface On like button click listener.
     */
    public interface OnLikeButtonClickListener {
        /**
         * On like button click.
         *
         * @param feed the feed
         */
        void onLikeButtonClick(Feed feed);
    }
}

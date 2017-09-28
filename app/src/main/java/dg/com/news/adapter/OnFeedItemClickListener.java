package dg.com.news.adapter;

import android.widget.ImageView;

import dg.com.news.models.Feed;

/**
 * Created by dgalstya on 27.09.2017.
 */
public interface OnFeedItemClickListener {
    /**
     * On feed item click.
     *
     * @param feed      the feed
     * @param thumbnail the thumbnail
     */
    void onFeedItemClick(Feed feed, ImageView thumbnail);
}

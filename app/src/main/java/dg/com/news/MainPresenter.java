package dg.com.news;

/**
 * Created by dgalstya on 27.09.2017.
 */
public interface MainPresenter {
    /**
     * Load data.
     */
    void loadData();

    /**
     * Load more items.
     *
     * @param page the page
     */
    void loadMoreItems(final int page);

    /**
     * Update feeds.
     */
    void updateFeeds();
}

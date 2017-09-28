package dg.com.news;

/**
 * Created by dgalstya on 27.09.2017.
 */
public interface FindItemsInteractor {

    /**
     * The interface On finished listener.
     */
    interface OnFinishedListener {
        /**
         * On finished.
         */
        void onFinished();

        /**
         * Sets total items count.
         *
         * @param total the total
         */
        void setTotalItemsCount(int total);
    }

    /**
     * The interface On more items finished listener.
     */
    interface OnMoreItemsFinishedListener {
        /**
         * On success.
         */
        void onSuccess();

        /**
         * On error.
         */
        void onError();
    }

    /**
     * Gets feeds.
     *
     * @param listener the listener
     */
    void getFeeds(final OnFinishedListener listener);

    /**
     * Gets more feeds.
     *
     * @param listener the listener
     * @param page     the page
     */
    void getMoreFeeds(final OnMoreItemsFinishedListener listener, final int page);

}

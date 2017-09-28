package dg.com.news;

/**
 * Created by dgalstya on 27.09.2017.
 */
public interface MainView {
    /**
     * Update containers.
     */
    void updateContainers();

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    boolean isOnline();

    /**
     * Sets update.
     *
     * @param isUpdated the is updated
     */
    void setUpdate(boolean isUpdated);

    /**
     * Sets max items.
     *
     * @param totalItems the total items
     */
    void setMaxItems(int totalItems);

    /**
     * Hide footer.
     */
    void hideFooter();

    /**
     * Notify feed data set changed.
     */
    void notifyFeedDataSetChanged();
}

package dg.com.news.helpers;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by dgalstya on 27.09.2017.
 */
public abstract class LazyLoader extends RecyclerView.OnScrollListener implements AbsListView.OnScrollListener {

    private static final int DEFAULT_THRESHOLD = 10;

    private boolean loading = true;
    private int threshold = DEFAULT_THRESHOLD;


    private int maxItems = 0;
    private boolean isRecyclerView = false;


    /**
     * Instantiates a new Lazy loader.
     *
     * @param threshold      the threshold
     * @param isRecyclerView the is recycler view
     */
    public LazyLoader (int threshold, boolean isRecyclerView) {
        this.threshold = threshold;
        this.isRecyclerView = isRecyclerView;
    }

    @Override
    public void onScrollStateChanged (AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll (AbsListView view, int firstVisibleItem,
                          int visibleItemCount, int totalItemCount) {
        load( view,  firstVisibleItem,
                visibleItemCount,  totalItemCount);
    }



    @Override
    public void onScrolled (RecyclerView recyclerView, int dx, int dy) {

        if(isRecyclerView && dy > 0){
            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            load(recyclerView,  firstVisibleItem, visibleItemCount,  totalItemCount);
        }
    }


    /**
     * Load more.
     *
     * @param view             the view
     * @param firstVisibleItem the first visible item
     * @param visibleItemCount the visible item count
     * @param totalItemCount   the total item count
     */
// Called when the user is nearing the end of the ListView
    // and the ListView is ready to add more items.
    public abstract void loadMore (ViewGroup view, int firstVisibleItem,
                                   int visibleItemCount, int totalItemCount);

    /**
     * Reset all.
     */
    public void resetAll(){
        loading = false;
    }

    /**
     * Sets max items.
     *
     * @param maxItems the max items
     */
    public void setMaxItems (int maxItems) {
        this.maxItems = maxItems;
    }

    private void load(ViewGroup view, int firstVisibleItem,
                      int visibleItemCount, int totalItemCount){
        if (maxItems < 10)
        {
            return;
        }
        if (loading) {
            if (totalItemCount < maxItems) {
                // the loading has finished
                loading = false;
            }
        }

        // check if the List needs more data
        if (!loading && totalItemCount - threshold <= firstVisibleItem + visibleItemCount) {
            loading = true;

            // List needs more data. Go fetch !!
            loadMore(view, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }
    }

}

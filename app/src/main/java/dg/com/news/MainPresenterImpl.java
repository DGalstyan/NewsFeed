package dg.com.news;

/**
 * Created by dgalstya on 27.09.2017.
 */
public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener, FindItemsInteractor.OnMoreItemsFinishedListener {
    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    /**
     * Instantiates a new Main presenter.
     *
     * @param mainView            the main view
     * @param findItemsInteractor the find items interactor
     */
    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }


    @Override
    public void onFinished() {
        if (mainView != null) {
            mainView.updateContainers();
            mainView.notifyFeedDataSetChanged();
        }
    }

    @Override
    public void setTotalItemsCount(int total) {
        if (mainView != null) {
            mainView.setMaxItems(total);
        }
    }

    @Override
    public void loadData() {
        if (mainView.isOnline()) {
            mainView.setUpdate(false);
            findItemsInteractor.getFeeds(this);
        }
        mainView.notifyFeedDataSetChanged();
    }

    @Override
    public void loadMoreItems(int page) {
        if (mainView != null && mainView.isOnline()) {
            mainView.setUpdate(false);
            findItemsInteractor.getMoreFeeds(this, page);
        }
    }

    @Override
    public void updateFeeds() {
        if (mainView != null && mainView.isOnline()) {
            mainView.setUpdate(false);
            findItemsInteractor.getFeeds(this);
        }
    }

    @Override
    public void onSuccess() {
        if (mainView != null) {
            mainView.notifyFeedDataSetChanged();
            mainView.setUpdate(true);
            mainView.hideFooter();
        }
    }

    @Override
    public void onError() {
        if (mainView != null) {
            mainView.setUpdate(true);
            mainView.hideFooter();
        }
    }
}

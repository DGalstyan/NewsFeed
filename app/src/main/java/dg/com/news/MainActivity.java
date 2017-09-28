package dg.com.news;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import dg.com.news.adapter.FeedAdapter;
import dg.com.news.adapter.FeedItemAnimator;
import dg.com.news.adapter.OnFeedItemClickListener;
import dg.com.news.helpers.LazyLoader;
import dg.com.news.helpers.RealmController;
import dg.com.news.helpers.Utils;
import dg.com.news.models.Feed;
import dg.com.news.view.PinnedItemsAdapter;

/**
 * The type Main activity.
 */
public class MainActivity extends BaseActivity implements MainView, OnFeedItemClickListener, FeedAdapter.OnLikeButtonClickListener {


    private static final int ANIM_DURATION_TOOLBAR = 600;

    /**
     * The Rv feed.
     */
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    private FeedAdapter feedAdapter;
    private PinnedItemsAdapter pinnedItemsAdapter;

    /**
     * The Tv no news.
     */
    @BindView(R.id.tv_no_news)
    TextView tvNoNews;

    /**
     * The Cl content.
     */
    @BindView(R.id.content)
    RelativeLayout clContent;

    /**
     * The Rv pinned.
     */
    @BindView(R.id.rvPinned)
    RecyclerView rvPinned;

    /**
     * The M footer view.
     */
    @BindView(R.id.footer_list)
    LinearLayout mFooterView;

    private boolean pendingIntroAnimation;
    private boolean isUpdated = true;

    /**
     * The Swipe container.
     */
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    /**
     * The No news.
     */
    @BindView(R.id.noNews)
    RelativeLayout noNews;

    /**
     * The Main content.
     */
    @BindView(R.id.mainContent)
    LinearLayout mainContent;
    private int page = 2;


    private MainPresenter presenter;


    /**
     * The Lazy loader.
     */
    LazyLoader lazyLoader = new LazyLoader(3, true) {
        @Override
        public void loadMore(ViewGroup view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if(isUpdated){
                mFooterView.setVisibility(View.VISIBLE);
                presenter.loadMoreItems(page++);
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());
        initUi();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initUi() {
        setupFeed();
        final AnimatedVectorDrawable avd2 = (AnimatedVectorDrawable) tvNoNews.getBackground();

        tvNoNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvNoNews.setText("Loading...");
                avd2.start();
                presenter.loadData();
            }
        });

        avd2.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                tvNoNews.setText("Try Again");
            }
        });
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(this);
        feedAdapter.setOnFeedItemClickListener(this);
        feedAdapter.setOnLikeButtonClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        rvFeed.setItemAnimator(new FeedItemAnimator());
        updateFeeds();
        setupPinnedItems();
        rvFeed.addOnScrollListener(lazyLoader);
        swipeContainer.setEnabled(false);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                presenter.loadData();
                page = 2;
                lazyLoader.resetAll();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        getToolbar().setTranslationY(-actionbarSize);
        getToolbarTitle().setTranslationY(-actionbarSize);

        getToolbar().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);

    }

    /**
     * Show liked snackbar.
     */
    public void showLikedSnackbar() {
        Snackbar.make(clContent, "Pined!", Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Show removed snackbar.
     */
    public void showRemovedSnackbar() {
        Snackbar.make(clContent, "Pined feed is removed!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onFeedItemClick(Feed feed, ImageView thumbnail) {
        Intent intent = new Intent(this, NewsInfoActivity.class);
        intent.putExtra("id", feed.getId());
        intent.putExtra("title", feed.getWebTitle());
        intent.putExtra("imageURL", feed.getFeedInfo().getThumbnail());
        intent.putExtra("description", feed.getFeedInfo().getBodyText());

        if (Build.VERSION.SDK_INT >= 23) {
            ActivityOptions options=ActivityOptions.makeClipRevealAnimation(thumbnail, 0, 0, thumbnail.getWidth(), thumbnail.getHeight());
            startActivity(intent, options.toBundle());
        }else{
            ActivityOptions options = ActivityOptions.makeScaleUpAnimation(thumbnail, 0, 0, thumbnail.getWidth(), thumbnail.getHeight());
            startActivity(intent, options.toBundle());
        }
    }

    private void updateFeeds(){
        presenter.loadData();

        Timer timerObj = new Timer();
        TimerTask timerTaskObj = new TimerTask() {
            public void run() {
            //perform your action here
                if(isUpdated){
                  presenter.updateFeeds();
                }
            }
        };
        timerObj.schedule(timerTaskObj, 0, 30000);


    }

    private void loadDataFromRealm(){
        feedAdapter.addAll(RealmController.with().getFeeds());
    }

    @Override
    public void onLikeButtonClick(Feed feed) {
        if(feed.isLiked()){
            showRemovedSnackbar();
            pinnedItemsAdapter.removeItem(feed);
        }else{
            showLikedSnackbar();
            pinnedItemsAdapter.addItem(feed);
        }

        RealmController.with().updatePin(feed);
        pinnedItemsAdapter.notifyDataSetChanged();
        checkPinnedItems();
    }

    private void emptyItems(){
        if(feedAdapter.size() > 0){
            mainContent.setVisibility(View.VISIBLE);
            noNews.setVisibility(View.GONE);
        }else{
            mainContent.setVisibility(View.GONE);
            noNews.setVisibility(View.VISIBLE);
        }
    }

    private void checkPinnedItems(){
        if(pinnedItemsAdapter.size() > 0){
            rvPinned.setVisibility(View.VISIBLE);
        }else{
            rvPinned.setVisibility(View.GONE);
        }
    }

    private void setupPinnedItems() {
        rvPinned.setHasFixedSize(true);
        rvPinned.setNestedScrollingEnabled(false);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(
                this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvPinned.setLayoutManager(mLayoutManager);

        List<Feed> pinnedFeadList =  RealmController.with().getPinedItems();

        pinnedItemsAdapter = new PinnedItemsAdapter(this);
        pinnedItemsAdapter.setOnFeedItemClickListener(this);
        pinnedItemsAdapter.addAll(pinnedFeadList);
        rvPinned.setAdapter(pinnedItemsAdapter);
        checkPinnedItems();
    }

    @Override
    public void updateContainers() {
        swipeContainer.setEnabled(true);
        swipeContainer.setRefreshing(false);
        isUpdated = true;
    }

    @Override
    public boolean isOnline() {
        return Utils.isOnline(this);
    }

    @Override
    public void setUpdate(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    @Override
    public void setMaxItems(int totalItems) {
        lazyLoader.setMaxItems(totalItems);
    }

    @Override
    public void hideFooter() {
        mFooterView.setVisibility(View.GONE);
    }

    @Override
    public void notifyFeedDataSetChanged() {
        loadDataFromRealm();
        feedAdapter.notifyDataSetChanged();
        emptyItems();
    }
}

package dg.com.news;

import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dg.com.news.helpers.Utils;
import dg.com.news.network.InternetReceiver;
import dg.com.news.network.NetworkReceiverListener;
import io.realm.Realm;

/**
 * Created by dgalstya on 26.09.2017.
 */
public class BaseActivity extends AppCompatActivity implements NetworkReceiverListener {
    private final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    /**
     * The Toolbar.
     */
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * The Toolbar title.
     */
    @Nullable
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    /**
     * The Error bar.
     */
    @Nullable
    @BindView(R.id.toolbar_sub_error_bar)
    View errorBar;

    /**
     * The Toolbar error text.
     */
    @Nullable
    @BindView(R.id.toolbarErrorText)
    TextView toolbarErrorText;

    private InternetReceiver networkReceiver;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
        networkReceiver = new InternetReceiver();
        networkReceiver.addListener(this);
        this.registerReceiver(networkReceiver, new IntentFilter(CONNECTIVITY_CHANGE_ACTION));
    }

    /**
     * Bind views.
     */
    protected void bindViews() {
        ButterKnife.bind(this);
        setupToolbar();
    }

    /**
     * Sets toolbar.
     */
    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    @Override
    protected void onDestroy() {
        if (networkReceiver != null) {
            this.unregisterReceiver(networkReceiver);
        }
        super.onDestroy();
    }

    /**
     * Gets toolbar.
     *
     * @return the toolbar
     */
    public Toolbar getToolbar() {
        return toolbar;
    }


    /**
     * Gets toolbar title.
     *
     * @return the toolbar title
     */
    public TextView getToolbarTitle() {
        return toolbarTitle;
    }

    private void hideErrorsBar(boolean hide) {
        try {
            if (errorBar != null) {
                //TODO some animation here?
                if (hide && Utils.isOnline(this)) {
                    new CountDownTimer(5000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            errorBar.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.green));
                            toolbarErrorText.setText(getResources().getString(R.string.connected));
                        }

                        @Override
                        public void onFinish() {
                            // do something end times 5s
                            toolbarErrorText.setText(getResources().getString(R.string.waiting_for_network));
                            errorBar.setBackgroundColor(ContextCompat.getColor(BaseActivity.this, R.color.network));
                            errorBar.setVisibility(View.GONE);
                        }
                    }.start();

                } else errorBar.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void networkAvailable() {
        hideErrorsBar(true);
    }

    @Override
    public void networkUnavailable() {
        hideErrorsBar(false);
    }
}

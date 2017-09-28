package dg.com.news;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type News info activity.
 */
public class NewsInfoActivity extends AppCompatActivity {
    /**
     * The M banner.
     */
    @BindView(R.id.banner)
    ImageView mBanner;

    /**
     * The Tv description.
     */
    @BindView(R.id.tvDescription)
    TextView tvDescription;

    /**
     * The Toolbar.
     */
    @BindView(R.id.app_bar)
    Toolbar toolbar;

    /**
     * The Collapsing toolbar layout.
     */
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    /**
     * The Id.
     */
    String id, /**
     * The Title.
     */
    title, /**
     * The Image url.
     */
    imageURL, /**
     * The Description.
     */
    description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        ButterKnife.bind(this);
        setToolBar();

        if (getIntent().getExtras() != null) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            imageURL = getIntent().getStringExtra("imageURL");
            description = getIntent().getStringExtra("description");
        }

        Picasso.with(this).load(imageURL).fit().centerCrop().placeholder(R.drawable.scrim).into(mBanner);
        tvDescription.setText(description);
        collapsingToolbarLayout.setTitle(title);
     }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void setToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

package dg.com.news.helpers;

import dg.com.news.models.Feed;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * The type Realm controller.
 */
public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    /**
     * Instantiates a new Realm controller.
     */
    public RealmController() {
        realm = Realm.getDefaultInstance();
    }

    /**
     * With realm controller.
     *
     * @return the realm controller
     */
    public static RealmController with() {
        if (instance == null) {
            instance = new RealmController();
        }
        return instance;
    }


    /**
     * Gets realm.
     *
     * @return the realm
     */
    public Realm getRealm() {
        return realm;
    }


    /**
     * Gets pined items.
     *
     * @return the pined items
     */
    public RealmResults<Feed> getPinedItems() {
        return realm.where(Feed.class).equalTo("isLiked", true).findAll();
    }

    /**
     * Update pin.
     *
     * @param feed the feed
     */
    public void updatePin(final Feed feed){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                feed.setLiked(!feed.isLiked());
            }
        });
    }

    /**
     * Save feed.
     *
     * @param feed the feed
     */
    public void saveFeed(final Feed feed){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
            if (realm.where(Feed.class).equalTo("id", feed.getId()).count() == 0) {
                realm.copyToRealm(feed);
            }
            }
        });
    }

    /**
     * Get feeds realm results.
     *
     * @return the realm results
     */
    public RealmResults<Feed> getFeeds(){
         return realm.where(Feed.class).findAll();
    }

}

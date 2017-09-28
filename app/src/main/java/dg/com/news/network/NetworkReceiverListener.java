package dg.com.news.network;

/**
 * Created by dgalstya on 26.09.2017.
 */

public interface NetworkReceiverListener {
    /**
     * Network available.
     */
    void networkAvailable ();

    /**
     * Network unavailable.
     */
    void networkUnavailable ();
}

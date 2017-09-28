package dg.com.news.network;

/**
 * Created by dgalstya on 26.09.2017.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;

import dg.com.news.helpers.Utils;

/**
 * The Class NetworkStateReceiver.
 */
public class InternetReceiver extends BroadcastReceiver {

    /**
     * The listeners.
     */
    protected List<NetworkReceiverListener> listeners;

    /**
     * The is connected.
     */
    protected Boolean isConnected;

    /**
     * Instantiates a new network state receiver.
     */
    public InternetReceiver () {
        listeners = new ArrayList<NetworkReceiverListener>();
        isConnected = null;
    }

    /**
     * Notify to all.
     */
    private void notifyToAll () {
        for (NetworkReceiverListener listener : listeners)
            notifyListener(listener);
    }

    /**
     * Notify listener.
     *
     * @param listener the listener
     */
    private void notifyListener (final NetworkReceiverListener listener) {
        if (isConnected == null || listener == null) {
            return;
        }

        if (isConnected == true) {
            listener.networkAvailable();
        } else {
            listener.networkUnavailable();
        }
    }

    /**
     * Adds the listener.
     *
     * @param listener the listener
     */
    public void addListener (final NetworkReceiverListener listener) {
        listeners.add(listener);
        notifyListener(listener);
    }

    /**
     * Removes the listener.
     *
     * @param listener the listener
     */
    public void removeListener (final NetworkReceiverListener listener) {
        listeners.remove(listener);
    }


    /*
     * (non-Javadoc)
     *
     * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
     * android.content.Intent)
     */
    @Override
    public void onReceive (final Context context, final Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        if (Utils.isOnline(context)) {
            isConnected = true;
        } else if (intent.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            isConnected = false;
        }

        notifyToAll();
    }
}


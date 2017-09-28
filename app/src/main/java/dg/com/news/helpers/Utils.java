package dg.com.news.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by dgalstya on 26.09.2017.
 */
public class Utils {
    /**
     * The constant LIVE_URL.
     */
    public static final String LIVE_URL = "https://content.guardianapis.com";

    private static int screenHeight = 0;


    /**
     * Gets screen height.
     *
     * @param c the c
     * @return the screen height
     */
    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    /**
     * Dp to px int.
     *
     * @param dp the dp
     * @return the int
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Is online boolean.
     *
     * @param ctx the ctx
     * @return the boolean
     */
    public static boolean isOnline (Context ctx) {
        final ConnectivityManager connectivity = (ConnectivityManager) ctx.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            final NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

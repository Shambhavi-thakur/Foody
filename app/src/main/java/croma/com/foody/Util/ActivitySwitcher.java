package croma.com.foody.Util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;


import croma.com.foody.Constants.AppConstants;


// Activity Switcher
public final class ActivitySwitcher {


    /**
     *
     * @param activity
     * @param className
     * @param intent
     */
    public static void switchActivityWithIntent(final Activity activity, final Class className,Intent intent) {
        intent.setClass(activity, className);
        activity.startActivity(intent);
    }

    /**
     *
     * @param activity
     * @param className
     * @param intent
     * @param finishCurrent
     */
    public static void switchActivityWithIntent(final Activity activity, final Class className,Intent intent, final boolean finishCurrent){
            intent.setClass(activity, className);
            activity.startActivity(intent);
            if(finishCurrent)
                activity.finish();
    }

    /**
     *
     * @param activity
     * @param className
     */
    public static void switchActivity(final Activity activity, final Class className) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, className);
                activity.startActivity(intent);
            }
        }, AppConstants.SPLASH_HANDLER_TIMER);
    }

    /**
     *
     * @param activity
     * @param className
     * @param finishCurrent
     */
    public static void switchActivity(final Activity activity, final Class className, final boolean finishCurrent) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, className);
                activity.startActivity(intent);
                if (finishCurrent)
                    activity.finish();
            }
        }, AppConstants.SPLASH_HANDLER_TIMER);
    }

    /**
     *
     * @param activity
     * @param className
     * @param finishCurrent
     */
    @TargetApi(16)
    public static void switchActivityWithFinishAffinity(final Activity activity, final Class className, final boolean finishCurrent) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, className);
                activity.startActivity(intent);
                if (finishCurrent)
                    activity.finishAffinity();
            }
        }, AppConstants.SPLASH_HANDLER_TIMER);
    }

    /**
     *
     * @param activity
     * @param className
     */
    public static void switchActivityWithoutHandler(final Activity activity, final Class className) {
        Intent intent = new Intent(activity, className);
        activity.startActivity(intent);
    }

    /**
     *
     * @param activity
     * @param className
     * @param finishCurrent
     */
    public static void switchActivityWithoutHandler(final Activity activity, final Class className, final boolean finishCurrent) {
        Intent intent = new Intent(activity, className);
        activity.startActivity(intent);
        if (finishCurrent)
            activity.finish();

    }


}

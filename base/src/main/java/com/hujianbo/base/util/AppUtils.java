package com.hujianbo.base.util;

import android.app.Application;

/**
 * @Author pengfei.zhang
 * @Date 2018/8/14
 * @Des
 */

public class AppUtils {


    private static final Application sApp;


    static {
        Application app = null;
        try {
            app = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            if (app == null)
                throw new IllegalStateException("Static initialization of Applications must be on main thread.");
        } catch (final Exception e) {
           // Log.e(DimentAndResUtil.class.getSimpleName(), "Failed to get current application from AppGlobals." + e.getMessage());
            try {
                app = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (final Exception ex) {
                //Log.e(DimentAndResUtil.class.getSimpleName(), "Failed to get current application from ActivityThread." + e.getMessage());
            }
        } finally {
            sApp = app;
        }
    }

    public static Application getApp() {
        return sApp;
    }
}

package cu.innovat.psigplus.util;

import android.util.Log;

/**
 * @author Luis Andrés Valido Fajardo +53 53694742  luis.valido1989@gmail.com
 * @date 26/12/23
 */
public class LOG {
    private static boolean DEBUG = true;

    public static int v(String tag, String msg){
        return (DEBUG ? Log.v(tag,msg) : 0);
    }

    public static int d(String tag, String msg){
        return (DEBUG ? Log.d(tag,msg) : 0);
    }

    public static int i(String tag, String msg){
        return (DEBUG ? Log.i(tag,msg) : 0);
    }

    public static int w(String tag, String msg){
        return (DEBUG ? Log.w(tag,msg) : 0);
    }

    public static int e(String tag, String msg){
        return (DEBUG ? Log.e(tag,msg) : 0);
    }
}

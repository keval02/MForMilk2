package nivida.com.mformilk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Request;


/**
 * Created by prince on 9/5/2016.
 */
public class GlobalFile {

    public static final int POST= Request.Method.POST;

    public static String server_link ="http://192.168.1.119/mformilk/";
    public static String image_link =server_link+"files/";
    //public static String link_document="http://app.nivida.in/";

    //public static String server_link1 ="http://www.teamnautilus.com/greeto/";
    //public static String share ="https://play.google.com/store/apps/details?id=&hl=en";
    public static String share ="https://play.google.com/store/apps/details?id=nivida.com.mformilk&hl=en";

    public static String link_document="http://www.ecofms.com/ecofms/files/upload_document/";

    public static boolean isOnline(Context con) {
        ConnectivityManager connectivity = (ConnectivityManager) con
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }

        return false;
    }

    public static void noInternet(Context context){
        Toast.makeText(context, "No Internet Connection Found\nPlease Check your Connection First!", Toast.LENGTH_SHORT).show();
    }

    public static void serverError(Context context){
        Toast.makeText(context, "Server Error Occured\nPlease Try After Sometime!", Toast.LENGTH_SHORT).show();
    }

}

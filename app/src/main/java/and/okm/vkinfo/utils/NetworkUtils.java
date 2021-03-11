package and.okm.vkinfo.utils;

import android.net.Uri;
import android.os.StrictMode;

import org.apache.commons.io.IOUtils;

import and.okm.vkinfo.constants.DefaultParamsConstants;
import and.okm.vkinfo.constants.ParamsConstants;
import and.okm.vkinfo.constants.VkConstants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static URL generateUrl(String userIds) throws MalformedURLException {
        Uri builtUri = Uri.parse(VkConstants.API__BASE_URL + VkConstants.USERS_GET)
                .buildUpon()
                .appendQueryParameter(ParamsConstants.PARAM_USER_IDS, userIds)
                .appendQueryParameter(ParamsConstants.PARAM_VERSION, DefaultParamsConstants.VERSION)
                .appendQueryParameter(ParamsConstants.PARAM_FIELDS, DefaultParamsConstants.PHOTO_MAX_ORIG)
                .appendQueryParameter(ParamsConstants.PARAM_ACCESS_TOKEN, DefaultParamsConstants.ACCESS_TOKEN)
                .build();

        return new URL(builtUri.toString());
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = urlConnection.getInputStream();

        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        urlConnection.disconnect();

        if (scanner.hasNext())
            return scanner.next();
        else throw new NullPointerException("scanner.hasNext() = false");
    }

    public static byte[] downloadImage(String url) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        InputStream in = new URL(url).openStream();
        return IOUtils.toByteArray(in);
    }

}

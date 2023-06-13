package atom.mining.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringJoiner;


/**
 * This class encapsulates the logic of working with the network.
 * It is built on a native implementation java.net, without using libraries and implements the simplest logic (at the moment only HTTP GET).
 * @author all-creator
 * @version 1.0
 */
public class NetworkProcessing {

    private NetworkProcessing() {}

    /**
     * Performs a simple GET connection on the specified {@code sUrl} path.
     * Supports parameters in the format application/x-www-form-urlencoded.
     * @param url String of url to connection
     * @return String of the response
     * @throws IOException if an I/O error occurs during processing request or if the specified
     * text of URL
     */
    public static String get(String url) throws IOException {
        final var urlObj = new URL(url);

        var conn = (HttpURLConnection) urlObj.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(url.split("\\?")[1].length()));

        final var content = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = input.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } finally {
            conn.disconnect();
        }

        return content.toString();
    }


    /**
     * Not implemented.
     * @param url String of url to connection
     * @param data Post data to send
     * @return String of the response
     * @throws IllegalStateException if it is called
     */
    public String post(String url, String data) {
        // TODO Implementation of a post request to Minerstat. (Why?). Or removing this method.
        throw new IllegalStateException("NetworkProcessing post method not implemented");
    }

    /**
     * {@link #getURL(boolean, String, String, String[])} without checking http/https.
     * @param domain this is the full address of the website on the Internet, with an explicit indication of the protocol
     * @param prefix this is the name of the first argument of the request
     * @param params The value of the first argument of the request, separated by commas
     * @return ready-to-use URL
     */
    public static String getURL(String domain, String prefix, String[] params) {
        final var res = new StringBuilder();
        final var pes = new StringJoiner(",");
        res.append(domain).append("?").append(prefix).append("=");
        Arrays.stream(params).map(String::toUpperCase).map(e -> URLEncoder.encode(e, StandardCharsets.UTF_8)).forEach(pes::add);
        res.append(pes);
        return res.toString();
    }


    /**
     * This method collects the URL by the passed component parts.
     * Enables support for only one parameter and a list value for it.
     * @param isSecure this value indicates which protocol (https-true/http-false) the connection will take place.
     * @param domain this is the full address of the website on the Internet, without an explicit indication of the protocol
     * @param prefix this is the name of the first argument of the request
     * @param params The value of the first argument of the request, separated by commas
     * @return ready-to-use URL
     */
    public static String getURL(boolean isSecure, String domain, String prefix, String[] params) {
        return isSecure ? "https://" + getURL(domain, prefix, params) : "http://" + getURL(domain, prefix, params);
    }
}

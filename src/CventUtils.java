import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CventUtils {
    public static int statusCode = -1;

    public static StringBuffer WebServiceCall(String url) throws IOException {
        HttpURLConnection con = null;
        StringBuffer response = null;
        URL obj = null;

        try {
            obj = new URL(url);

            // Open connection
            if (url.startsWith("https")) {
                con = (HttpsURLConnection) obj.openConnection();
                // Bypass DNS name checking
                ((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        // Always return true to bypass DNS name checking
                        return true;
                    }
                });
            } else {
                con = (HttpURLConnection) obj.openConnection();
            }
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            // con.setRequestProperty("Authorization", "Basic " + encoding);
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            statusCode = con.getResponseCode();

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return response;
    }

}

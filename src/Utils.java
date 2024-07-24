import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public static int statusCode = -1;
    public static String username = "admin";
    public static String password = "A123456789a$";
    public static String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());


    public StringBuffer WebServiceCallold(String url) throws IOException {
        HttpURLConnection con = null;
        StringBuffer response = null;
        URL obj = null;
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setConnectTimeout(5000);
            Authentication(con);
            statusCode = con.getResponseCode();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            in = null;
            con.disconnect();
            con = null;
            obj = null;
            inputLine = null;
        } catch (MalformedURLException e) {
            Logger.getLogger(Utils.class.getName()).log(Level.WARNING, "URL Format Failure", e);
        } catch (IOException e) {
            Logger.getLogger(Utils.class.getName()).log(Level.WARNING, "I/O Exception", e);
            throw e;

        }
        return response;
    }

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
            Authentication(con);
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

    public static void Authentication(HttpURLConnection con) throws ProtocolException {
        if (RFID_Starter.isBsicAuth == 1) {
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", "Basic " + encoding);
        } else {
            con.setRequestMethod("PUT");
            con.setDoOutput(true);
            System.out.println("Bearer Auth :" + RFID_Starter.token);
            con.setRequestProperty("Authorization", "Bearer " + RFID_Starter.token);
        }


        con.setUseCaches(false);
    }

    public static void Authenticationphp(HttpURLConnection con) throws ProtocolException {
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Authorization", "Basic " + encoding);

            // If you need to send data with the POST request
            String postData = "flag=1";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
                con.setUseCaches(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
}

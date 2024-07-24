import org.json.JSONObject;
import renderer.RoundTextAreaField;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RFID_Starter {

    public static String token = "";
    public static int isBsicAuth = 0;

    public static void main(String[] args) {
        // Create a new JFrame container for IP and Port entry
        JFrame ipPortFrame = new JFrame("RFID CVENT");
        ipPortFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ipPortFrame.setSize(600, 400); // Increased size
        ipPortFrame.setLocationRelativeTo(null); // Center the frame on the screen

        // Load the image
        ImageIcon icon = loadIcon("img/NECO_logo.png");
        if (icon != null) {
            Image image = icon.getImage();
            ipPortFrame.setIconImage(image);
        } else {
            System.err.println("Icon image not found!");
        }

        // Create a JPanel for the first frame with GridBagLayout
        JPanel ipPortPanel = new JPanel(new GridBagLayout());
        ipPortPanel.setBackground(Color.BLACK); // Set background color to black
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10); // Set padding

        Font font = new Font("Arial", Font.PLAIN, 22); // Create a font with increased size

        // IP Address label
        JLabel ipLabel = new JLabel("IP Address:");
        ipLabel.setForeground(Color.WHITE); // Set text color to white
        ipLabel.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        ipPortPanel.add(ipLabel, gbc);

        // IP Address text field
        JTextField ipText = new JTextField(20);
        ipText.setBackground(Color.DARK_GRAY); // Set text field background color
        ipText.setForeground(Color.WHITE); // Set text color
        ipText.setFont(font); // Set font
        ipText.setText("192.168.1.88");
        ipText.setHorizontalAlignment(JTextField.CENTER);
        ipText.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        ipPortPanel.add(ipText, gbc);

        // Port label
        JLabel portLabel = new JLabel("Port:");
        portLabel.setForeground(Color.WHITE); // Set text color to white
        portLabel.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        ipPortPanel.add(portLabel, gbc);

        // Port text field
        JTextField portText = new JTextField(20);
        portText.setBackground(Color.DARK_GRAY); // Set text field background color
        portText.setForeground(Color.WHITE); // Set text color
        portText.setFont(font); // Set font
        portText.setText("443");
        portText.setEnabled(false);
        portText.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        ipPortPanel.add(portText, gbc);

        // Connect button
        JButton connectButton = new JButton("Connect");
        connectButton.setBackground(Color.GRAY); // Set button background color
        connectButton.setForeground(Color.WHITE); // Set button text color
        connectButton.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        ipPortPanel.add(connectButton, gbc);

        // Add action listener to the connect button to open the new frame
        connectButton.addActionListener(e -> {
            String ip = ipText.getText();
            String port = portText.getText();

            // Print the IP and port to the console (for demonstration purposes)
            System.out.println("IP Address: " + ip);
            System.out.println("Port: " + port);

            // Open the new frame with buttons
            openButtonFrame();

            // Close the current frame
            ipPortFrame.dispose();
        });

        // Add the panel to the frame and set it visible
        ipPortFrame.add(ipPortPanel);
        ipPortFrame.setVisible(true);
    }

    private static void openButtonFrame() {
        // Create a new JFrame container for buttons
        JFrame buttonFrame = new JFrame("RFID_CVENT");
        buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonFrame.setResizable(false);
        buttonFrame.setSize(400, 300); // Set frame size
        buttonFrame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create a JPanel to hold the buttons with GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK); // Set background color to black
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set padding

        // Load the image
        ImageIcon icon = loadIcon("img/NECO_logo.png");
        if (icon != null) {
            Image image = icon.getImage();
            buttonFrame.setIconImage(image);
        } else {
            System.err.println("Icon image not found!");
        }

        Color buttonColor = Color.decode("#04AA6D"); // Set button background color to #04AA6D
        Font font = new Font("Arial", Font.PLAIN, 22); // Create a font with increased size

        // Login label
        JLabel loginLabel = new JLabel("Login ->");
        loginLabel.setForeground(Color.WHITE); // Set label text color
        loginLabel.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(loginLabel, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(buttonColor); // Set button background color
        loginButton.setForeground(Color.WHITE); // Set button text color
        loginButton.setFont(font); // Set font
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        buttonPanel.add(loginButton, gbc);

        // Start label
        JLabel startLabel = new JLabel("Start ->");
        startLabel.setForeground(Color.WHITE); // Set label text color
        startLabel.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(startLabel, gbc);

        // Start button
        JButton startButton = new JButton("Start");
        startButton.setBackground(buttonColor); // Set button background color
        startButton.setForeground(Color.WHITE); // Set button text color
        startButton.setFont(font); // Set font
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.setEnabled(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        buttonPanel.add(startButton, gbc);

        // Stop label
        JLabel stopLabel = new JLabel("Stop ->");
        stopLabel.setForeground(Color.WHITE); // Set label text color
        stopLabel.setFont(font); // Set font
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        buttonPanel.add(stopLabel, gbc);

        // Stop button
        JButton stopButton = new JButton("Stop");
        stopButton.setBackground(buttonColor); // Set button background color
        stopButton.setForeground(Color.WHITE); // Set button text color
        stopButton.setFont(font);
        stopButton.setOpaque(true);
        stopButton.setBorderPainted(false);
        stopButton.setEnabled(false); // Set font
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        buttonPanel.add(stopButton, gbc);

        // Add the panel to the frame and set it visible
        buttonFrame.add(buttonPanel);
        buttonFrame.setVisible(true);

        // Optional: Add action listeners to the buttons
        loginButton.addActionListener(e -> {
            isBsicAuth = 1;
            System.out.println("Login button clicked");
            String url = "https://192.168.1.88/cloud/localRestLogin";
            String message = "Login Failed";
            try {
                String response = String.valueOf(new Utils().WebServiceCall(url));
                JSONObject myResponse = new JSONObject(response);
                String code = myResponse.optString("code");
                if (code.equals("0")) {
                    message = "Login Successfully";
                    token = myResponse.optString("message");
                    System.out.println("Token is :" + token);
                    startButton.setEnabled(true);
                } else {
                    message = "Login Failed";
                }
                System.out.println("Response is :" + response);
            } catch (Exception ex) {
                message = "Internal Error occurred " + ex;
            }
            JOptionPane.showMessageDialog(
                    buttonPanel,
                    message,
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        startButton.addActionListener(e -> {
            isBsicAuth = 0;
            System.out.println("Start button clicked");
            String message = "";
            String url = "https://192.168.1.88/cloud/start";
            try {
                String response = String.valueOf(new Utils().WebServiceCall(url));
                if (Utils.statusCode == 200) {
                    System.out.println("Reader Started Successfully");
                    message = "Reader Started Successfully";
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                } else {
                    System.out.println("Something went wrong");
                    message = "Reader Failed to start";
                }
                System.out.println("Response is :" + response);
            } catch (Exception ex) {
                System.out.println("Status code :" + Utils.statusCode);
                message = "Internal Error Occurred " + ex;
            }
            if(Utils.statusCode==200){

                url= "http://192.168.1.27:84/RFIDStartCheck.php?Flag=1";
                try {
                    String response = String.valueOf(new CventUtils().WebServiceCall(url));
                } catch (Exception ex) {
                    System.out.println("Internal Error Occured "+ex);
                }
            }
            JOptionPane.showMessageDialog(
                    buttonPanel,
                    message,
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        stopButton.addActionListener(e -> {
            isBsicAuth = 0;
            System.out.println("Stop button clicked");
            String url = "https://192.168.1.88/cloud/stop";
            String message = "Reader Failed to Stop";
            try {
                String response = String.valueOf(new Utils().WebServiceCall(url));
                if (Utils.statusCode == 200) {
                    System.out.println("Reader Stopped Successfully");
                    message = "Reader Stopped Successfully";
                    startButton.setEnabled(false);
                    stopButton.setEnabled(false);
                } else {
                    System.out.println("Something went wrong");
                }
                System.out.println("Response is :" + response);
            } catch (Exception ex) {
                message = "Internal Error Occurred " + ex;
            }
            if(Utils.statusCode==200){
                url = "http://192.168.1.27:84/RFIDStartCheck.php?Flag=0";
                try {
                    String response = String.valueOf(new CventUtils().WebServiceCall(url));
                } catch (Exception ex) {
                    System.out.println("Internal Error Occured "+ex);
                }
            }
            JOptionPane.showMessageDialog(
                    buttonPanel,
                    message,
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // Add the panel to the frame and set it visible
        buttonFrame.add(buttonPanel);
        buttonFrame.setVisible(true);
    }

    private static ImageIcon loadIcon(String path) {
        // Try loading the image from the file system
        ImageIcon icon = new ImageIcon(path);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return icon;
        }

        // If not found, try loading the image as a resource from the classpath
        java.net.URL imgURL = RFID_Starter.class.getResource("/" + path);
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                return icon;
            }
        }

        // Return null if the image cannot be loaded
        return null;
    }
}

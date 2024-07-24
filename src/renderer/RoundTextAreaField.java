package renderer;//package renderer;

import javax.swing.*;
import java.awt.*;

public class RoundTextAreaField extends JScrollPane {
    private final JTextArea textArea;
    private final Color lineColor;
    private final int arcWidth;
    private final int arcHeight;

    public void setDescription(String description) {
        textArea.setToolTipText(description);
    }

    public RoundTextAreaField() {
        lineColor = new Color(52, 152, 219);
        arcWidth = 10;
        arcHeight = 10;

        textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEditable(true);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        setViewportView(textArea);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());

        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(lineColor);
        int s = 2;
        int w = getWidth() - (2 * s) - getVerticalScrollBar().getWidth(); // Adjusted width
        int h = getHeight() - (2 * s);
        g2d.setStroke(new BasicStroke(s));
        g2d.drawRoundRect(s, s, w, h, arcWidth, arcHeight);
        g2d.dispose();
        super.paintComponent(g);
    }


    public JTextArea getTextArea() {
        return textArea;
    }
}

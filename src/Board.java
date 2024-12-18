import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Board {
    JFrame frame;
    VisualizationPanel panel;
    public Board(DijkstraGraph graph) throws IOException {
        frame = new JFrame("Search Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300, 1000));
        sidePanel.setBackground(Color.GRAY);
        frame.add(sidePanel, BorderLayout.EAST);

        panel = new VisualizationPanel(graph);
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }
    public void startAnimation(String startNode) {
        panel.animation(startNode);
    }
}

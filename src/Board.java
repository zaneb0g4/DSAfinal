import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Board {
    JFrame frame;
    public Board() throws IOException {
        frame = new JFrame("Search Algorithm Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300, 1000));
        sidePanel.setBackground(Color.GRAY);
        frame.add(sidePanel, BorderLayout.EAST);
        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        frame.add(board);

        frame.pack();
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }
}

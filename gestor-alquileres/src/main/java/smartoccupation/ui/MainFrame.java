package smartoccupation.ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        super("Gestor de Alquileres");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Aquí añadimos tu panel principal
        add(new AlquilerPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}

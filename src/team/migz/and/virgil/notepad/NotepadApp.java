package team.migz.and.virgil.notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NotepadApp extends JFrame {

    private static final String WINDOW_TITLE = "Notepad";

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private static final String MENU_SAVE_AS = "Save As";
    private static final String MENU_NEW_FILE = "New";
    private static final String MENU_OPEN_FILE = "Open";
    private static final String MENU_SAVE_FILE = "Save";

    private JTextArea txtWorkspace;
    private JButton btnNewFile;
    private JButton btnOpenFile;
    private JButton btnSaveFile;
    private JButton btnSaveAsFile;

    private File mCurrentlyOpenedFile;

    public NotepadApp() {
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        initializeComponents();
    }

    private void initializeComponents() {
        /**
         * TextEditor Panel
         */
        txtWorkspace = new JTextArea();
        txtWorkspace.setWrapStyleWord(false);
        txtWorkspace.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtWorkspace);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        /**
         * Toolbar Panel
         */
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnNewFile = new JButton(MENU_NEW_FILE);
        btnOpenFile = new JButton(MENU_OPEN_FILE);
        btnSaveFile = new JButton(MENU_SAVE_FILE);
        btnSaveAsFile = new JButton(MENU_SAVE_AS);

        addToolbarItemListeners();

        toolbar.add(btnNewFile);
        toolbar.add(btnOpenFile);
        toolbar.add(btnSaveFile);
        toolbar.add(btnSaveAsFile);

        stylizeToolbarComponents(toolbar, btnNewFile, btnOpenFile, btnSaveFile, btnSaveAsFile);

        /**
         * Add toolbar and textarea to main window
         */
        Container contentPane = getContentPane();
        contentPane.add(toolbar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void addToolbarItemListeners() {
        btnNewFile.addActionListener(createNewFileListener());
        btnOpenFile.addActionListener(createOpenFileListener());
        btnSaveFile.addActionListener(createSaveFileListener());
        btnSaveAsFile.addActionListener(createSaveAsFileListener());
    }

    private ActionListener createSaveAsFileListener() {
        return e -> {
            System.out.println("Click Trigger: Save As");
        };
    }

    private ActionListener createSaveFileListener() {
        return e -> {
            System.out.println("Click Trigger: Save");

            String desiredFileName = JOptionPane.showInputDialog(this, "Enter filename: ", "Input", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Desired Filename: " + desiredFileName);

            // TODO save the file
            if (mCurrentlyOpenedFile == null) {

            } else {

            }
        };
    }

    private ActionListener createOpenFileListener() {
        return e -> {
            System.out.println("Click Trigger: Open File");
        };
    }

    private ActionListener createNewFileListener() {
        return e -> {
            System.out.println("Click Trigger: New File");

            if (mCurrentlyOpenedFile == null) {
                if (!txtWorkspace.getText().isEmpty()) {
                    int choice = JOptionPane.showConfirmDialog(this, "You have unsaved changes, are you sure you want to create a new file?", "Prompt", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        txtWorkspace.setText("");
                    }
                }
            } else {
                try {
                    if (Utils.readAsString(mCurrentlyOpenedFile).equals(txtWorkspace.getText())) {
                        mCurrentlyOpenedFile = null;
                        txtWorkspace.setText("");
                    } else {
                        int choice = JOptionPane.showConfirmDialog(this, "You have unsaved changes, are you sure you want to close this file and create a new file?", "Prompt", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            mCurrentlyOpenedFile = null;
                            txtWorkspace.setText("");
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void stylizeToolbarComponents(JComponent... components) {
        Color background = new Color(0x3c5eb9);
        Color foreground = Color.WHITE;

        for (JComponent component : components) {
            component.setBackground(background);

            if (component instanceof JButton) {
                component.setForeground(foreground);
                component.setFocusable(false);
                ((JButton) component).setBorderPainted(false);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new NotepadApp().setVisible(true));
    }

}

package br.com.staroski.profanator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

final class ProfanatorFrame extends JFrame {

    private static final long serialVersionUID = 1;

    private static final String ICON = "/devil_256x256.png";

    private final JTabbedPane tabbedPane;

    private JFileChooser chooser;

    ProfanatorFrame() {
        super("Staroski's Java Code Profanator");
        setIconImage(createIcon());
        setJMenuBar(createMenuBar());
        tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    private Image createIcon() {
        try {
            InputStream input = getClass().getResourceAsStream(ICON);
            return ImageIO.read(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createMenuFile());
        return menuBar;
    }

    private JMenu createMenuFile() {
        JMenu menu = new JMenu("File");
        menu.add(createMenuItemOpen());
        menu.add(createMenuItemSaveAs());
        return menu;
    }

    private JMenuItem createMenuItemOpen() {
        JMenuItem item = new JMenuItem("Open");
        item.addActionListener(e -> open());
        return item;
    }

    private JMenuItem createMenuItemSaveAs() {
        JMenuItem item = new JMenuItem("Save as...");
        item.addActionListener(e -> saveAs());
        return item;
    }

    private JFileChooser getFileChooser() {
        if (chooser == null) {
            chooser = new JFileChooser(System.getProperty("user.dir"));
            chooser.setToolTipText("Open Java file to be profanated");
            chooser.setFileFilter(new FileFilter() {

                private static final String JAVA_FILE_EXTENSION = ".java";

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(JAVA_FILE_EXTENSION);
                }

                @Override
                public String getDescription() {
                    return "Java Files (" + JAVA_FILE_EXTENSION + ")";
                }
            });
        }
        return chooser;
    }

    private void open() {
        JFileChooser chooser = getFileChooser();
        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        String title = file.getName();
        Icon icon = null;
        Component component = new ProfanatorPanel(file);
        String tip = file.getAbsolutePath();
        int index = 0;
        tabbedPane.insertTab(title, icon, component, tip, index);
        tabbedPane.setSelectedIndex(index);
    }

    private void saveAs() {
        ProfanatorPanel profanatorPanel = (ProfanatorPanel) tabbedPane.getSelectedComponent();
        if (profanatorPanel == null) {
            return;
        }
        JFileChooser chooser = getFileChooser();
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = chooser.getSelectedFile();
        profanatorPanel.saveAs(file);
    }
}

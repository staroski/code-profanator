package br.com.staroski.profanator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

final class ProfanatorPanel extends JPanel {

    private static final long serialVersionUID = 1;

    private final String originalText;
    private JTextArea textArea;
    private JButton profanateButton;

    private Selection selection;

    ProfanatorPanel(File file) {
        selection = new Selection();
        originalText = readContents(file);
        setLayout(new BorderLayout());
        add(createOptionPanel(), BorderLayout.NORTH);
        add(createScrollPane(), BorderLayout.CENTER);
    }

    public void saveAs(File file) {
        if (file.exists()) {
            int option = JOptionPane.showConfirmDialog(this, "File already exists, overwrite?", "Confirm", JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.QUESTION_MESSAGE);
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        writeContents(file);
    }

    private JButton createButtonProfanate() {
        profanateButton = new JButton("Profanate");
        profanateButton.addActionListener(e -> profanate());
        updateState();
        return profanateButton;
    }

    private JButton createButtonReset() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> reset());
        return resetButton;
    }

    private JScrollPane createEditor() {
        textArea = new JTextArea(originalText);
        textArea.setEditable(false);
        textArea.addCaretListener(e -> selectionChanged(e.getDot(), e.getMark()));
        TextLineNumber textLineNumber = new TextLineNumber(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setRowHeaderView(textLineNumber);
        try {
            Class<? extends ProfanatorPanel> type = getClass();
            String fontPath = "/" + type.getPackage().getName().replace('.', '/') + "/cour.ttf";
            InputStream input = type.getResourceAsStream(fontPath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, input).deriveFont(Font.PLAIN, 14);
            textArea.setFont(font);
            textLineNumber.setFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scrollPane;
    }

    private JPanel createOptionPanel() {
        JPanel options = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        options.add(createButtonProfanate());
        options.add(createButtonReset());
        return options;
    }

    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createEditor());
        return scrollPane;
    }

    private void profanate() {
        String text = textArea.getText();
        String prefix = text.substring(0, selection.start);
        String toProfanate = text.substring(selection.start, selection.end);
        String sufix = text.substring(selection.end);

        LGBT_Fucker lgbt_fucker = new LGBT_Fucker();
        String profanated = lgbt_fucker.fuck(toProfanate);
        textArea.setText(prefix + profanated + sufix);
        textArea.setCaretPosition(prefix.length());
    }

    private String readContents(File file) {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            String text = new String(bytes).replaceAll("\r\n", "\n");
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void reset() {
        int position = textArea.getCaretPosition();
        int length = originalText.length();
        textArea.setText(originalText);
        textArea.setCaretPosition(position < length ? position : length);
    }

    private void selectionChanged(int start, int end) {
        selection = new Selection(start, end);
        updateState();
    }

    private void updateState() {
        profanateButton.setEnabled(!selection.empty);
    }

    private void writeContents(File file) {
        JOptionPane.showMessageDialog(this, "This function is not yet implemented, sorry", "Not yet implemented", JOptionPane.QUESTION_MESSAGE);
        // TODO
    }

}

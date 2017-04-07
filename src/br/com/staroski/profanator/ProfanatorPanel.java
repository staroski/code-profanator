package br.com.staroski.profanator;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

final class ProfanatorPanel extends JPanel {

	private static final long serialVersionUID = 1;

	private final String originalText;
	private JEditorPane editorPane;
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
			int option = JOptionPane.showConfirmDialog(this, "File already exists, overwrite?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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

	private JEditorPane createEditor() {
		editorPane = new JEditorPane("text", originalText);
		editorPane.setEditable(false);
		editorPane.addCaretListener(e -> selectionChanged(e.getDot(), e.getMark()));
		return editorPane;
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
		String text = editorPane.getText();
		String prefix = text.substring(0, selection.start);
		String toProfanate = text.substring(selection.start, selection.end);
		String sufix = text.substring(selection.end);

		LGBT_Fucker lgbt_fucker = new LGBT_Fucker();
		String profanated = lgbt_fucker.fuck(toProfanate);
		editorPane.setText(prefix + profanated + sufix);
		editorPane.setCaretPosition(prefix.length());
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
		int position = editorPane.getCaretPosition();
		int length = originalText.length();
		editorPane.setText(originalText);
		editorPane.setCaretPosition(position < length ? position : length);
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
		//TODO
	}

}

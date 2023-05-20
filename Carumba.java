import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Carumba {

    private JFrame frame;
    private JTextField inputField;
    private JTextArea resultArea;

    public Carumba() {
        frame = new JFrame("Calculator for Convertion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setFont(new Font("Tahoma", Font.PLAIN, 30));
        inputField.setEditable(true); // Set to editable
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume(); // Consume the key event to prevent input duplication
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                String text = inputField.getText();
                String inputString = text;

                if (e.getModifiersEx() == 0 && e.getExtendedKeyCode() != KeyEvent.VK_UNDEFINED) {
                    char keyChar = e.getKeyChar();
                    if (Character.isDigit(keyChar)) {
                        inputString += keyChar;
                    }
                } else if (keyCode == KeyEvent.VK_BACK_SPACE && text.length() > 0) {
                    inputString = text.substring(0, text.length() - 1);
                }

                inputField.setText(inputString);
            }

            @Override
            public void keyReleased(KeyEvent e) {}

        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertInput();
            }
        });

        inputField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputField.requestFocus();
            }
        });
        inputPanel.add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = createNumberButton(String.valueOf(i));
            buttonPanel.add(numberButtons[i]);
        }

        JButton clearButton = createSpecialButton("C", Color.RED);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
            }
        });
        buttonPanel.add(clearButton);

        JButton convertButton = createSpecialButton("Convert", Color.GREEN);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertInput();
            }
        });
        buttonPanel.add(convertButton);

        inputPanel.add(buttonPanel, BorderLayout.CENTER);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight() / 3));

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        inputField.requestFocus();
    }

    private JButton createNumberButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setPreferredSize(new Dimension(50, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText(inputField.getText() + label);
                inputField.requestFocus(); // Ensure inputField retains focus after button click
            }
        });
        return button;
    }

    private JButton createSpecialButton(String label, Color color) {
        JButton button = new JButton(label);
        button.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setPreferredSize(new Dimension(50, 50));
        return button;
    }

    private void convertInput() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            try {
                int number = Integer.parseInt(input);

                String result = "Decimal: \t" + number + "\n"
                        + "Hexadecimal: \t" + Integer.toHexString(number) + "\n"
                        + "Octal: \t" + Integer.toOctalString(number) + "\n"
                        + "Binary: \t" + Integer.toBinaryString(number);

                resultArea.setText(result);
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Carumba();
            }
        });
    }
}

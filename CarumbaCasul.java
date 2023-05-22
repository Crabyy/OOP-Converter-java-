import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarumbaCasul {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JLabel label = new JLabel("ENTER A NUMBER");
        label.setBounds(10, 0, 100, 100);

        JLabel HexaDecimal = new JLabel("Hexa-decimal: ");
        JLabel Decimal = new JLabel("Decimal: ");
        JLabel Octal = new JLabel("Octal: ");
        JLabel Binary = new JLabel("Binary: ");

        HexaDecimal.setBounds(10, 100, 100, 100);
        Decimal.setBounds(250, 100, 100, 100);
        Octal.setBounds(10, 50, 100, 300);
        Binary.setBounds(250, 50, 100, 300);

        JButton button = new JButton("CONVERT");
        button.setBounds(230, 80, 200, 30);

        JTextField inputField = new JTextField();
        inputField.setBounds(230, 40, 200, 30);

        // Frame settings
        frame.setTitle("CALCULATOR CONVERTER!");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Add components to the frame
        frame.add(label);
        frame.add(HexaDecimal);
        frame.add(Decimal);
        frame.add(Octal);
        frame.add(Binary);

        frame.add(button);
        frame.add(inputField);

        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                if (!input.isEmpty()) {
                    try {
                        int number = Integer.parseInt(input);

                        String hex = Integer.toHexString(number);
                        String octal = Integer.toOctalString(number);
                        String binary = Integer.toBinaryString(number);

                        HexaDecimal.setText("Hexa-decimal: " + hex);
                        Decimal.setText("Decimal: " + number);
                        Octal.setText("Octal: " + octal);
                        Binary.setText("Binary: " + binary);
                    } catch (NumberFormatException ex) {
                        // Handle invalid input
                        JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid integer.");
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int numberToGuess;
    private int attempts;
    private JTextField guessField;
    private JLabel feedbackLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel instructionLabel = new JLabel("Guess the number (1-100):");
        guessField = new JTextField();
        JButton guessButton = new JButton("Guess");
        feedbackLabel = new JLabel();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        add(panel, BorderLayout.CENTER);
        add(feedbackLabel, BorderLayout.SOUTH);

        initializeGame();
        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        feedbackLabel.setText("");
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < 1 || userGuess > 100) {
                feedbackLabel.setText("Please enter a number between 1 and 100.");
            } else if (userGuess < numberToGuess) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Congratulations! You've guessed the number in " + attempts + " attempts.");
                int option = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    initializeGame();
                    guessField.setText("");
                } else {
                    System.exit(0);
                }
            }

            guessField.setText("");
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGameGUI();
            }
        });
    }
}

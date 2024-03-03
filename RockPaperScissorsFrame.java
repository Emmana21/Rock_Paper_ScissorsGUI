import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton quitButton;
    private JPanel statsPanel;
    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private JTextArea resultsTextArea;

    private int playerWins;
    private int computerWins;
    private int ties;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Title label
        titleLabel = new JLabel("Rock Paper Scissors Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Button panel
        buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose Your Move"));

        rockButton = createButton("Rock", "rock.png");
        paperButton = createButton("Paper", "paper.png");
        scissorsButton = createButton("Scissors", "scissors.png");
        quitButton = createButton("Quit", null);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Stats panel
        statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));

        playerWinsLabel = new JLabel("Player Wins: 0", SwingConstants.CENTER);
        computerWinsLabel = new JLabel("Computer Wins: 0", SwingConstants.CENTER);
        tiesLabel = new JLabel("Ties: 0", SwingConstants.CENTER);

        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        add(statsPanel, BorderLayout.SOUTH);

        // Results text area
        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        add(scrollPane, BorderLayout.EAST);

        // Initialize stats
        playerWins = 0;
        computerWins = 0;
        ties = 0;

        // Button actions
        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text, String imageName) {
        JButton button = new JButton(text);
        if (imageName != null) {
            ImageIcon icon = new ImageIcon(imageName);
            button.setIcon(icon);
        }
        return button;
    }

    private void playGame(String playerMove) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        String computerMove = moves[random.nextInt(moves.length)];
        String result = determineWinner(playerMove, computerMove);
        updateStats(result);
        displayResult(playerMove, computerMove, result);
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Tie";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player";
        } else {
            return "Computer";
        }
    }

    private void updateStats(String result) {
        switch (result) {
            case "Player":
                playerWins++;
                break;
            case "Computer":
                computerWins++;
                break;
            case "Tie":
                ties++;
                break;
        }
        updateStatsLabels();
    }

    private void updateStatsLabels() {
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    private void displayResult(String playerMove, String computerMove, String result) {
        String resultText = String.format("Player chose %s. Computer chose %s. %s wins!", playerMove, computerMove, result);
        resultsTextArea.append(resultText + "\n");
    }
}
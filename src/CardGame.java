import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

public class CardGame {
    ArrayList<Card> deck;
    ArrayList<Card> playerDeck;
    ArrayList<Card> computerDeck;
    ArrayList<Card> validComputerDeck;
    static int playerPoint;
    static int computerPoint;
    JFrame frame = new JFrame("Card Game");
    JButton[] buttonArr = new JButton[5];
    JButton playAgainButton;
    Random random = new Random();
    JLabel scoreLabel = new JLabel();
    JPanel playAgainButtonPanel = new JPanel();
    JPanel playButtonPanel = new JPanel();
    JPanel backgroundPanel = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                int cardWidth = 100;
                for (int i = 0; i<playerDeck.size(); i++){
                    Card card = playerDeck.get(i);
                    if (!buttonArr[i].isEnabled()){
                        Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder-X/" + card.toString() + ".png")).getImage();
                        g.drawImage(cardImage, (cardWidth + 25 ) * i + 45,465, cardWidth,140,null);
                    }
                    else{
                        Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder/" + card.toString() + ".png")).getImage();
                        g.drawImage(cardImage, (cardWidth + 25 ) * i + 45,465, cardWidth,140,null);
                    }
                }
                for (int i = 0; i<computerDeck.size(); i++){
                    Card card = computerDeck.get(i);
                    if (validComputerDeck.contains(card)){
                        /*
                        Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder/" + card.toString() + ".png")).getImage();
                        g.drawImage(cardImage, (cardWidth + 25 ) * i + 45,45, cardWidth,140,null);

                         */
                        Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder/back.png")).getImage();
                        g.drawImage(cardImage, (cardWidth + 25 ) * i + 45,45, cardWidth,140,null);
                    }
                    else{
                        Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder/" + card.toString() + ".png")).getImage();
                        g.drawImage(cardImage, (cardWidth + 25 ) * i + 45,45, cardWidth,140,null);
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    };

    CardGame(){
        startGame();
        //GUI
        frame.setSize(700,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting panel + text + buttons
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(34,145,34));
        playAgainButton = new JButton("Play again");
        buttonArr[0] = new JButton("Play card 1");
        buttonArr[1] = new JButton("Play card 2");
        buttonArr[2] = new JButton("Play card 3");
        buttonArr[3] = new JButton("Play card 4");
        buttonArr[4] = new JButton("Play card 5");
        playAgainButtonPanel.add(playAgainButton);
        validComputerDeck = new ArrayList<>(computerDeck);
        for (int i = 0; i < buttonArr.length; i++){
            int j = i;
            playButtonPanel.add(buttonArr[i]);
            buttonArr[i].setFocusable(false);
            buttonArr[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Card playerCard = playerDeck.get(j);
                    int index = random.nextInt(validComputerDeck.size());
                    Card compCard = validComputerDeck.remove(index);
                    buttonArr[j].setEnabled(false);
                    compareCards(playerCard, compCard);
                    updateScoreLabel();
                    frame.repaint();
                }
            });
        }

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playAgain();
            }
        });
        updateScoreLabel();
        backgroundPanel.add(scoreLabel, BorderLayout.EAST);
        updateScoreLabel();
        backgroundPanel.add(playButtonPanel, BorderLayout.SOUTH);
        backgroundPanel.add(playAgainButtonPanel, BorderLayout.NORTH);
        frame.getContentPane().add(backgroundPanel);
        frame.repaint();
        frame.setVisible(true);
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Player: " + playerPoint + " Computer: " + computerPoint + "   ");
    }

    public void startGame(){
        playerPoint = 0;
        computerPoint = 0;
        buildDeck();
        shuffleDeck();
        distributeDeck();
    }

    public void playAgain(){
        playerPoint = 0;
        computerPoint = 0;
        buildDeck();
        shuffleDeck();
        distributeDeck();
        for (JButton button: buttonArr){
            button.setEnabled(true);
        }

        updateScoreLabel();
        validComputerDeck = new ArrayList<>(computerDeck);
        frame.repaint();
    }

    public static void main(String[] args){
        new CardGame();
    }

    private class Card {
        private final String suit;
        private final String rank;

        public Card(String suit, String rank) {
            this.suit = suit;
            this.rank = rank;
        }

        public String getSuit() {
            return suit;
        }

        public String getRank() {
            return rank;
        }

        public int getCardValue(){
            String faceCards = "AKQJ";
            if (faceCards.contains(rank)){
                switch(rank){
                    case "A":
                        return 14;
                    case "K":
                        return 13;
                    case "Q":
                        return 12;
                    case "J":
                        return 11;
                }
            }
            return Integer.parseInt(rank);
        }

        public String toString() {
            return suit + "-" + rank;
        }

    }

    public static void compareCards(Card playerCard, Card computerCard){
        int playerCardValue = playerCard.getCardValue();
        int computerCardValue = computerCard.getCardValue();
        if (playerCardValue > computerCardValue){
            playerPoint = playerPoint + (playerCardValue - computerCardValue);
        }
        else if (playerCardValue < computerCardValue){
            computerPoint = computerPoint + (computerCardValue - playerCardValue);
        }
    }

    /**
     * Method will build a deck of 52 cards using a combination of 13 values and of 4 suits.
     */
    private void buildDeck(){
        this.deck = new ArrayList<>(52);
        String[] suitArr = {"Heart", "Diamond", "Club", "Spade"};
        String[] rankArr = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String suit: suitArr){
            for (String rank: rankArr){
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Method shuffles deck by randomly swapping cards multiple times.
     * Will iterate 260 times and will swap two random cards everytime.
     */
    private void shuffleDeck(){
        for (int i = 0; i < 260; i++){
            int index1 = random.nextInt(52);
            int index2;
            do{
                index2 = random.nextInt(52);
            }while(index1 == index2);
            Card temp = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, temp);
        }
    }

    /**
     * Method will distribute cards from deck to player's and computer's hands.
     * Each deck receives 5 random cards
     */
    private void distributeDeck(){
        playerDeck = new ArrayList<>(5);
        computerDeck = new ArrayList<>(5);
        while(playerDeck.size() < 5){
            playerDeck.add(deck.remove(deck.size() - 1));
            computerDeck.add(deck.remove(deck.size() - 1));
        }
    }
}



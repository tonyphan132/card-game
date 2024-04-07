import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

//Points will be earned depending on difference of ranks played.
public class CardGame {
    ArrayList<Card> deck;
    ArrayList<Card> playerDeck;
    ArrayList<Card> computerDeck;
    static int playerPoint;
    static int computerPoint;
    JFrame frame = new JFrame("Card Game");
    JPanel backgroundPanel = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            for (int i = 0; i<playerDeck.size(); i++){
                Card card = playerDeck.get(i);
                int cardWidth = 100;
                Image cardImage = new ImageIcon(getClass().getResource("./Card-Folder/" + card.toString() + ".png")).getImage();
                g.drawImage(cardImage, (cardWidth + 10 ) * i + 15,10, cardWidth,140,null);
                //+ 15 is offset from the left.
                //
            }
        }
    };

    CardGame(){
        playerPoint = 0;
        computerPoint = 0;
        buildDeck();
        shuffleDeck();
        distributeDeck();
        //GUI
        frame.setSize(700,700);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Setting panel + text + buttons
        backgroundPanel.add(new JLabel("Welcome to The Dueling Card Game"));
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(34,145,34));
        frame.getContentPane().add(backgroundPanel);
    }

    public static void main(String[] args){
        CardGame game = new CardGame();
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
    }

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

    private void shuffleDeck(){
        Random random = new Random();
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

    private void distributeDeck(){
        playerDeck = new ArrayList<>(5);
        computerDeck = new ArrayList<>(5);
        while(playerDeck.size() < 5){
            playerDeck.add(deck.remove(deck.size() - 1));
            computerDeck.add(deck.remove(deck.size() - 1));
        }
    }
}



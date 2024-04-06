import java.util.ArrayList;
import java.util.Random;

public class CardGame {
    ArrayList<Card> deck;
    CardGame(){
        buildDeck();
        shuffleDeck();
    }

    public static void main(String[] args){
        CardGame game = new CardGame();
        ArrayList<Card> deck = game.deck;
        for (Card element: deck){
            System.out.println(element);
        }
    }

    private class Card{
        private String suit;
        private String rank;
        public Card(String suit, String rank){
            this.suit = suit;
            this.rank = rank;
        }
        public String toString(){
            return rank + "-" + suit;
        }

    }

    public void buildDeck(){
        this.deck = new ArrayList<>(52);
        String[] suitArr = {"Heart", "Diamond", "Club", "Spade"};
        String[] rankArr = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String suit: suitArr){
            for (String rank: rankArr){
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void shuffleDeck(){
        Random random = new Random();
        for (int i = 0; i < 260; i++){
            int index1 = random.nextInt(52);
            int index2 = 0;
            do{
                index2 = random.nextInt(52);
            }while(index1 == index2);
            Card temp = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, temp);
        }
    }
}



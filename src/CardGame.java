import java.util.ArrayList;

public class CardGame {
    ArrayList<Card> deck;
    CardGame(){
        buildDeck();
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


}



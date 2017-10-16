package charlie.card;

/**
 *
 * @author blossom
 */
public class SplitShoe00 extends Shoe{
    
    @Override
    public void init() {
        cards.clear();
        
        // My first card
        cards.add(new Card(8, Card.Suit.HEARTS));
        
        // Dealers first card
        cards.add(new Card(6, Card.Suit.CLUBS));
        
        // My second card
        cards.add(new Card(8, Card.Suit.SPADES));
        
        // Dealers second card
        cards.add(new Card(6, Card.Suit.SPADES));
        
        // My first card for one of the split hands
        cards.add(new Card(10, Card.Suit.SPADES));
        
        // My first card for the second hand
        cards.add(new Card(4, Card.Suit.DIAMONDS));
        
        // dealers bust card
        cards.add(new Card(6, Card.Suit.HEARTS));
        
        // an extra card
        cards.add(new Card(5, Card.Suit.CLUBS)); 
    }
    
    @Override
    public boolean shuffleNeeded() {
        return false;
    }
    
}

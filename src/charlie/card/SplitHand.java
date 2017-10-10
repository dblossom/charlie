package charlie.card;

import charlie.dealer.Seat;

/**
 * A class that will extend hand to add split functionality
 * 
 * @author Dan Blossom
 */
public class SplitHand extends Hand{
    
    private SplitHand(){
        super();
    }
    
    private SplitHand(Hid hid){
        super(hid);
    }
    
    /**
     * A method that will split the hand
     * 
     * TODO: consider returning array
     * 
     * @param originalHand the original hand to split
     * @return the new hand
     */
    public static Hand split(Hand originalHand){
        
        // Create the new hand with defaults
        // = new Hand(new Hid(Seat.YOU)); ?
        Hand newHand = new Hand(new Hid(Seat.YOU));
        
        // The bet amnount needs to equal the original bet
        // TODO: Make sure it's destroyed IE: not retained after this game.
        newHand.hid.setAmt(originalHand.hid.getAmt());
        
        // WARNING::REMOVING THE UN-NEEDED CARD WHILE RETURNING IT.
        // Should use setter and getter here?
        Card card = originalHand.cards.remove(1);
        
        // Update "this" hand's values
        originalHand.revalue();
        // Give the new hand a card, the card we removed from this hand
        newHand.hit(card);
        
        // return the new hand
        return newHand;
    }   
}
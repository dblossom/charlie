package charlie.card;

/**
 * A class that will extend hand to add split functionality
 * 
 * @author Dan Blossom
 */
public class SplitHand extends Hand{
    
    private SplitHand(){
        super();
    }
    
    /**
     * Given a HID & Original Hand, it will construct a new single card hand
     * It will also ALTER the original Hand passed into it, so use with caution
     * 
     * TODO: consider returning deep copy to leave Original Hand unaltered.
     * 
     * @param hid
     * @param hand 
     */
    public SplitHand(Hid hid, Hand hand){
        super(hid);
        split(hand);
    }
    
    /**
     * A helper method that splits the original hand
     * @param originalHand the original hand to split
     */
    private void split(Hand originalHand){
                
        // WARNING::REMOVING THE UN-NEEDED CARD WHILE RETURNING IT.
        // Should use setter and getter here?
        // altering original hand from constructor - deep copy instead?
        Card card = originalHand.cards.remove(1);
        
        // Update "this" hand's values
        originalHand.revalue();
        
        // Give the new hand a card, the card we removed from this hand
        hit(card);
    }   
}
package charlie.dealer;

import charlie.actor.House;
import charlie.card.Card;
import charlie.card.Hand;
import charlie.card.Hid;
import charlie.card.SplitHand;
import charlie.plugin.IPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that adds split functionality to dealer
 * 
 * TODO: Consider refactor to "AdvancedDealer" for other functions?
 * 
 * @author Dan Blossom
 */
public class SplitDealer extends Dealer {
    
    private final Logger LOG = LoggerFactory.getLogger(SplitDealer.class);
    private final int DEAL_DELAY = 1500;
    
    
    public SplitDealer(House house){
        super(house);
    }
    
    /**
     * Override the 'goNextHand()' but still, regardless will call the
     * 'super.goNextHand()' method to preform tasks there. This override
     * is to ensure, after a split, the first card is automatically dealt.
     */
    @Override
    protected void goNextHand(){
        if(handSeqIndex < handSequence.size()){
            Hid hid = handSequence.get(handSeqIndex);
            Hand hand = super.hands.get(hid);
            if(hid.getSplit() && hand.size() == 1){
                
                try{
                    Thread.sleep(DEAL_DELAY);
                    
                    Card card = deal();
                    
                    hand.hit(card);
                    
                    for (IPlayer _player : playerSequence){
                        _player.deal(hid, card, hand.getValues());
                    }
                } catch (InterruptedException ex) {
                    LOG.error(ex.getMessage());
                }
            }
        }
        super.goNextHand();
    }

    public void split(IPlayer player, Hid hid){
        
        // First we need to validate original hand
        Hand origHand = validate(hid);
        
        // Log any errors
        if(origHand == null) {
            LOG.error("got invalid SPLIT player = "+player);
            return;
        }
        
        // Create a new Hand ID from original.
        // Same seat, same bet amount, but no sidebet as player
        // does side bet and did or did not already.
        Hid newHid = new Hid(hid.getSeat(), hid.getAmt(), 0);
        
        newHid.setSplit(true);
        
        // Let us split the original hand.
        SplitHand newHand = new SplitHand(newHid, origHand);
        
        // Log that we are doing a split action
        // Guess we will log what cards we are splitting and the "new hand amount"
        LOG.info("Player requested to split " 
                + origHand.getCard(0).getName() 
                + "'s."); 
        LOG.info("HID: " + newHid + " created for hand: " + newHand );

        // Add this hand to this player
        players.put(newHand.getHid(), player);
        
        // Now that we have two hands we need to manipulate the handSeqIndex
        // Think it will be easier to add it AFTER the current hand since that
        // hand is actually "in play" ... 
        int i = handSequence.indexOf(hid);
        handSequence.add((i+1),newHand.getHid());
        
        hands.put(newHid, newHand);
        
        // Send back to the ATable what has just occured.
        player.split(newHid, hid);
        
        // Need to hit the one of the hands, might as well make it the 
        // original.
        super.hit(player, hid);
    }
}

package charlie.actor;

import charlie.card.Hid;
import charlie.dealer.SplitDealer;
import charlie.message.view.from.SplitFromView;
import charlie.message.view.to.SplitToView;
import charlie.plugin.IPlayer;
import com.googlecode.actorom.Address;
import com.googlecode.actorom.annotation.OnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author blossom
 */
public class SplitRealPlayer extends RealPlayer implements IPlayer{
    
    private final SplitDealer splitDealer;
    private final Logger LOG = LoggerFactory.getLogger(SplitRealPlayer.class);
    
    SplitRealPlayer(SplitDealer dealer, Address courierAddress){
        super(dealer,courierAddress);
        this.splitDealer = dealer;
    }
    
    /**
     * Sends the split request back to the ATable
     * @param newHid the HID for the newly created hand
     * @param hid the HID for the hand that was just split
     */
    @Override
    public void split(Hid newHid, Hid hid){
        courier.send(new SplitToView(newHid, hid));
    }
    
    /**
     * Receives a request from the courier that player split
     * @param split split Request
     */
    @OnMessage(type = SplitFromView.class)
    public void onReceive(SplitFromView split) {
        LOG.info("received split notification from player = "+split);
                
        splitDealer.split(this, split.getHid());
    }   
}
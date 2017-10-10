package charlie.actor;

import charlie.card.Hid;
import charlie.dealer.SplitDealer;
import charlie.message.view.from.DoubleDown;
import charlie.message.view.from.Hit;
import charlie.message.view.from.Request;
import charlie.message.view.from.Split;
import charlie.message.view.from.Stay;
import com.googlecode.actorom.Address;
import com.googlecode.actorom.annotation.OnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author blossom
 */
public class SplitRealPlayer extends RealPlayer{
    
    private final SplitDealer splitDealer;
    private final Logger LOG = LoggerFactory.getLogger(SplitRealPlayer.class);
    
    SplitRealPlayer(SplitDealer dealer, Address courierAddress){
        super(dealer,courierAddress);
        this.splitDealer = dealer;
    }
    
    /**
     * Receives a request from the courier.
     * @param request Request
     */
    @OnMessage(type = Request.class)
    @Override
    public void onReceive(Request request) {
        LOG.info("received request = "+request);
        Hid hand = request.getHid();
        
        if(request instanceof Hit)
            dealer.hit(this, hand);
        
        else if(request instanceof Stay)
            dealer.stay(this, hand);
        
        else if(request instanceof DoubleDown)
            dealer.doubleDown(this, hand);
        
        else if(request instanceof Split)
            splitDealer.split(this, hand);
        
        else
            LOG.error("received unknown request: "+request+" for hand = "+hand);
    }

    
}

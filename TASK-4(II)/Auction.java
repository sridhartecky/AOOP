import java.util.ArrayList;
import java.util.List;

public abstract class Auction {
    private List<Observer> bidders = new ArrayList<>();

    public void subscribe(Observer bidder) {
        bidders.add(bidder);
    }

    public void unsubscribe(Observer bidder) {
        bidders.remove(bidder);
    }

    public void notifyBidders(String message) {
        for (Observer bidder : bidders) {
            bidder.update(message);
        }
    }

    public final void conductAuction() {
        startAuction();
        handleBidding();
        endAuction();
    }

    protected abstract void startAuction();
    protected abstract void handleBidding();
    protected abstract void endAuction();
}

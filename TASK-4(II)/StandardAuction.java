public class StandardAuction extends Auction {
    @Override
    protected void startAuction() {
        System.out.println("Starting a standard auction...");
        notifyBidders("Standard auction has started!");
    }

    @Override
    protected void handleBidding() {
        System.out.println("Handling bids for the standard auction...");
        notifyBidders("A new bid has been placed in the standard auction.");
    }

    @Override
    protected void endAuction() {
        System.out.println("Ending the standard auction...");
        notifyBidders("The standard auction has ended!");
    }
}

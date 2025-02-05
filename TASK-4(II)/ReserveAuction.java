public class ReserveAuction extends Auction {
    private double reservePrice;

    public ReserveAuction(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    @Override
    protected void startAuction() {
        System.out.println("Starting a reserve auction with reserve price: " + reservePrice);
        notifyBidders("Reserve auction has started! Reserve price is " + reservePrice);
    }

    @Override
    protected void handleBidding() {
        System.out.println("Handling bids for the reserve auction...");
        notifyBidders("A new bid has been placed in the reserve auction.");
    }

    @Override
    protected void endAuction() {
        System.out.println("Ending the reserve auction...");
        notifyBidders("The reserve auction has ended!");
    }
}

package something.modal;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Position {
    private long userId;
    private long averageBuyPrice;
    private long averageSellPrice;
    private long totalBuyQuantity;
    private long totalFilledBuyQuantity;
    private long totalSellQuantity;
    private long totalFilledSellQuantity;

    public Position( Order order ) {
        userId = order.getUserId();
        switch ( order.getType() ) {
            case Buy:
                averageBuyPrice = order.getPrice();
                totalBuyQuantity = order.getQuantity();
                totalFilledBuyQuantity = order.getFilled();
                break;
            case Sell:
                averageSellPrice = order.getPrice();
                totalSellQuantity = order.getQuantity();
                totalFilledSellQuantity = order.getFilled();
                break;
        }
    }

}

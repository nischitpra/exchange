package exchange.modal;

import exchange.util.Logger;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Position {
    private long userId;
    private long averageBuyPrice;
    private long averageSellPrice;
    private long totalBuyQuantity;
    private long totalSellQuantity;

    public void fillByOrder( Order order ) {
        userId = order.getUserId();
        switch ( order.getType() ) {
            case Buy:
                averageBuyPrice = getAveragePrice( averageBuyPrice, totalBuyQuantity, order.getPrice(), order.getQuantity() );
                totalBuyQuantity += order.getQuantity();
                break;
            case Sell:
                averageSellPrice = getAveragePrice( averageSellPrice, totalSellQuantity, order.getPrice(), order.getQuantity() );
                totalSellQuantity += order.getQuantity();
                break;
        }
    }

    private long getAveragePrice( long averagePrice, long totalQuantity, long fillPrice, long fillQuantity ) {
        return ( averagePrice * totalQuantity + fillPrice * fillQuantity ) / ( totalQuantity + fillQuantity );
    }

    public String toString() {
        return String.format( "userId: %d, abp: %d, bq: %d, asp: %d, sq: %d", userId, averageBuyPrice, totalBuyQuantity, averageSellPrice, totalSellQuantity );
    }
}

package exchange.core;

import exchange.modal.Order;
import exchange.util.Logger;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class MatchingEngine {

    OrderBook orderBook;
    PositionManager positionManager;

    public MatchingEngine( final OrderBook orderBook, final PositionManager positionManager ) {
        this.orderBook = orderBook;
        this.positionManager = positionManager;
    }

    public void run() {
        orderBook.getBuyOrderTable().forEach( ( price, buyOrderList ) -> {
            ListIterator<Order> buyOrderItr = buyOrderList.listIterator();
            while ( buyOrderItr.hasNext() ) {
                fillOrder( buyOrderItr, buyOrderItr.next() );
            }
            Logger.log( "orderbook price: %d completed", price );
        } );
    }

    /**
     * worst complexity is n^2.
     *
     * @param buyOrderItr
     * @param buyOrder
     */
    private void fillOrder( final ListIterator<Order> buyOrderItr, final Order buyOrder ) {
        List<Order> sellOrderList = orderBook.getSellOrderTable().get( buyOrder.getPrice() );
        if ( sellOrderList == null ) return;
        ListIterator<Order> sellOrderItr = sellOrderList.listIterator();
        while ( sellOrderItr.hasNext() ) {
            Order sellOrder = sellOrderItr.next();
            if ( sellOrder.getUserId() == buyOrder.getUserId() ) continue;
            final long pendingBuyFillQuantity = buyOrder.getQuantity() - buyOrder.getFilledQuantity();
            final long pendingSellFillQuantity = sellOrder.getQuantity() - sellOrder.getFilledQuantity();
            if ( pendingBuyFillQuantity < pendingSellFillQuantity ) {
                transformOrderToPosition( buyOrderItr, buyOrder, sellOrder, pendingBuyFillQuantity );
                break;
            }
            else if ( pendingBuyFillQuantity == pendingSellFillQuantity ) {
                transformOrderToPosition( buyOrderItr, buyOrder, sellOrder, pendingBuyFillQuantity );
                transformOrderToPosition( sellOrderItr, sellOrder, buyOrder, pendingSellFillQuantity );
                break;
            }
            else {
                // dont break here because buy order is not completely filled
                transformOrderToPosition( sellOrderItr, sellOrder, buyOrder, pendingSellFillQuantity );
            }
        }
    }

    private void transformOrderToPosition( final ListIterator<Order> itr, final Order filledOrder, final Order partiallyFilledOrder, final long partialFillQuantity ) {
        positionManager.updatePosition( filledOrder );
        partiallyFilledOrder.setFilledQuantity( partialFillQuantity );
        itr.remove();
    }

}

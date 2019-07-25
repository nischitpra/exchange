package something.engine;

import something.enums.OrderType;
import something.modal.Order;
import something.modal.OrderRequest;
import something.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class OrderBook {

    private HashMap<Long, List<Order>> buyOrderTable;
    private HashMap<Long, List<Order>> sellOrderTable;

    public OrderBook() {
        buyOrderTable = new HashMap<>();
        sellOrderTable = new HashMap<>();
    }

    public void placeOrder( OrderRequest orderRequest ) {
        if ( orderRequest.getType() == OrderType.Buy ) {
            addToOrderTable( orderRequest, buyOrderTable );
        }
        else {
            addToOrderTable( orderRequest, sellOrderTable );
        }
    }

    public void cancelOrder( Order cancelOrder ) {
        if ( cancelOrder.getType() == OrderType.Buy ) {
            cancelOrder( cancelOrder, buyOrderTable );
        }
        else {
            cancelOrder( cancelOrder, sellOrderTable );
        }
    }

    private long generateId( OrderType type, long price, int size ) {
        StringBuilder id = new StringBuilder();
        id.append( type.getValue() );
        id.append( price );
        id.append( size );
        return Long.parseLong( id.toString() );
    }

    private void addToOrderTable( OrderRequest orderRequest, HashMap<Long, List<Order>> orderTable ) {
        List<Order> orderList = orderTable.get( orderRequest.getPrice() );
        if ( orderList == null ) orderList = new ArrayList<>();
        orderTable.put( orderRequest.getPrice(), orderList );
        Order newOrder = new Order( generateId( orderRequest.getType(), orderRequest.getPrice(), orderList.size() ), orderRequest );
        orderTable.get( newOrder.getPrice() ).add( newOrder );
    }

    private void cancelOrder( Order cancelOrder, HashMap<Long, List<Order>> orderTable ) {
        List<Order> orderList = orderTable.get( cancelOrder.getPrice() );
        for ( Order order : orderList ) {
            if ( order.getId() == cancelOrder.getId() ) {
                order.cancelPendingOrder();
            }
        }
    }

    private void moveToActiveOrder( ) {
//TODO:
    }

    public HashMap<Long, List<Order>> getBuyOrderTable() {
        return buyOrderTable;
    }

    public HashMap<Long, List<Order>> getSellOrderTable() {
        return sellOrderTable;
    }

    public void log() {
        Set<Long> buyKeyList = buyOrderTable.keySet();
        for ( Long key : buyKeyList ) {
            Logger.log( "key: %d, buy size: %d", key, buyOrderTable.get( key ).size() );
        }
        Set<Long> sellKeyList = sellOrderTable.keySet();
        for ( Long key : sellKeyList ) {
            Logger.log( "key: %d, sell size: %d", key, sellOrderTable.get( key ).size() );
        }
    }

}

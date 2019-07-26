package exchange.core;

import exchange.enums.OrderType;
import exchange.modal.Order;
import exchange.modal.OrderRequest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nischitpradhan on 2019-07-26
 */
public interface OrderBook {

    HashMap<Long, List<Order>> buyOrderTable = new HashMap<>();
    HashMap<Long, List<Order>> sellOrderTable = new HashMap<>();
    void placeOrder( final OrderRequest orderRequest );
    void cancelOrder( Order cancelOrder );
    String generateId( final OrderType type, final long price, final int size );
    HashMap<Long, List<Order>> getBuyOrderTable();
    HashMap<Long, List<Order>> getSellOrderTable();

}

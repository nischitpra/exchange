package exchange;

import exchange.core.MatchingEngine;
import exchange.core.OrderBook;
import exchange.core.OrderBookImpl;
import exchange.core.PositionManager;
import exchange.enums.OrderType;
import exchange.modal.OrderRequest;
import exchange.util.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

class SystemInit {

    private OrderBook orderBook;
    private PositionManager positionManager;
    private MatchingEngine matchingEngine;

    public SystemInit() {
        orderBook = new OrderBookImpl();
        positionManager = new PositionManager();
        matchingEngine = new MatchingEngine( orderBook, positionManager );
    }

    public void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
// test to debug matching engine
//        OrderRequest orderRequest = new OrderRequest( 1, 101, 20, OrderType.Buy );
//        orderBook.placeOrder( orderRequest );
//        orderRequest = new OrderRequest( 2, 101, 15, OrderType.Sell );
//        orderBook.placeOrder( orderRequest );
//        ((OrderBookImpl)orderBook).log();
//        matchingEngine.run();
//        ((OrderBookImpl)orderBook).log();
//        positionManager.log();
// actual code to test matching engine
        run( this.getClass().getMethod( "createOrders", OrderBookImpl.class ), this, orderBook );
        run( MatchingEngine.class.getMethod( "run" ), matchingEngine );
        run( OrderBookImpl.class.getMethod( "log" ), orderBook );
        run( PositionManager.class.getMethod( "log" ), positionManager );
    }

    public void run( Method method, Object object, Object... params ) throws InvocationTargetException, IllegalAccessException {
        Logger.log( "\n------%s::%s------\n", method.getDeclaringClass().getName(), method.getName() );
        long start = getCurrentMillis();
        method.invoke( object, params );
        long end = getCurrentMillis();
        Logger.log( "run time: %fs", ( end - start ) / 1000.0 );
    }

    public void createOrders( OrderBookImpl orderBook ) {
        Random randomId = new Random();
        for ( int i = 0; i < 2000000; i++ ) {
            OrderRequest orderRequest = new OrderRequest( randomId.nextInt( 2 ) + 1, getRandomPrice(), getRandomQuantity(), getRandomOrderType() );
            orderBook.placeOrder( orderRequest );
        }
        orderBook.log();
    }

    public long getCurrentMillis() {
        return new Date().getTime();
    }

    public long getRandomPrice() {
        Random rand = new Random();
        return rand.nextInt( 20 ) * Constants.PRICE_PRECISION + 40 * Constants.PRICE_PRECISION;
    }

    public long getRandomQuantity() {
        Random rand = new Random();
        return rand.nextInt( 20 ) + 10;
    }

    public OrderType getRandomOrderType() {
        Random rand = new Random();
        if ( ( rand.nextInt( 2 ) + 1 ) == OrderType.Buy.getValue() ) {
            return OrderType.Buy;
        }
        return OrderType.Sell;
    }

}

package exchange;

import exchange.engine.MatchingEngine;
import exchange.engine.OrderBook;
import exchange.engine.PositionManager;
import exchange.enums.OrderType;
import exchange.modal.OrderRequest;
import exchange.modal.User;
import exchange.util.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Starter {
    public static void main( String[] args ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SystemInit systemInit = new SystemInit();
        systemInit.init();
    }
}

class SystemInit {
    public SystemInit() {}

    public void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        OrderBook orderBook = new OrderBook();
        PositionManager positionManager = new PositionManager();
        MatchingEngine matchingEngine = new MatchingEngine( orderBook, positionManager );
        run( this.getClass().getMethod( "createOrders", OrderBook.class ), this, orderBook );
        run( MatchingEngine.class.getMethod( "run" ), matchingEngine );
        run( OrderBook.class.getMethod( "log" ), orderBook );
        run( PositionManager.class.getMethod( "log" ), positionManager );
    }

    public void run( Method method, Object object, Object... params ) throws InvocationTargetException, IllegalAccessException {
        Logger.log( "\n------%s::%s------\n", method.getDeclaringClass().getName(), method.getName() );
        long start = getCurrentMillis();
        method.invoke( object, params );
        long end = getCurrentMillis();
        Logger.log( "run time: %fs", ( end - start ) / 1000.0 );
    }

    public void createOrders( OrderBook orderBook ) {
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

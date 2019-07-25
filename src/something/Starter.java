package something;

import something.engine.OrderBook;
import something.enums.OrderType;
import something.modal.OrderRequest;
import something.modal.User;
import something.util.Logger;

import java.util.Date;
import java.util.Random;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Starter {

    public static void main( String[] args ) {
        OrderBook orderBook = new OrderBook();
        long start = getCurrentMillis();
        User user = new User( 1 );
        for ( int i = 0; i < 1000000; i++ ) {
            OrderRequest orderRequest = new OrderRequest( user.getId(), getRandomPrice(), getRandomQuantity(), getRandomOrderType() );
            orderBook.placeOrder( orderRequest );
        }
        long end = getCurrentMillis();
        Logger.log( "run time: %fs", ( end - start ) / 1000.0 );
        orderBook.log();
    }

    public static long getCurrentMillis() {
        return new Date().getTime();
    }

    public static long getRandomPrice() {
        Random rand = new Random();
        return rand.nextInt( 4 ) + 1;
    }

    public static long getRandomQuantity() {
        Random rand = new Random();
        return rand.nextInt( 200000 ) + 10000;
    }

    public static OrderType getRandomOrderType() {
        Random rand = new Random();
        if ( ( rand.nextInt( 2 ) + 1 ) == OrderType.Buy.getValue() ) {
            return OrderType.Buy;
        }
        return OrderType.Sell;
    }

}

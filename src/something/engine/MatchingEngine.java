package something.engine;

import java.util.Set;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class MatchingEngine {

    OrderBook orderBook;

    public MatchingEngine( OrderBook orderBook ) {
        this.orderBook = orderBook;
    }

    public void run() {
        while ( true ) {
            Set<Long> buyPrices = orderBook.getBuyOrderTable().keySet();
            Set<Long> sellPrices = orderBook.getSellOrderTable().keySet();

        }
    }

    private void fillOrder() {
//TODO:
    }

}

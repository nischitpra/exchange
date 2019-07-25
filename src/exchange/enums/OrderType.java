package exchange.enums;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public enum OrderType {
    Buy( 1 ),
    Sell( 2 );
    int value;

    OrderType( int value ) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

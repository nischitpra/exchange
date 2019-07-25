package exchange.modal;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Order extends OrderRequest {

    private String id;
    private long filledQuantity;

    public Order( String newId, OrderRequest orderRequest ) {
        super( orderRequest );
        id = newId;
    }

    public String getId() {
        return id;
    }

    public long getFilledQuantity() {
        return filledQuantity;
    }

    public void setFilledQuantity( long filledQuantity ) {
        this.filledQuantity = filledQuantity;
    }

    public boolean isFilled() {
        return this.getQuantity() == this.getFilledQuantity();
    }

    public void cancelPendingOrder() {
        if( !isFilled() ) {
            this.setQuantity( this.getFilledQuantity() ); // reduce pending quantity to filled quantity
        }
    }

}

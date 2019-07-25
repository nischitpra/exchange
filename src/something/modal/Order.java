package something.modal;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Order extends OrderRequest {

    private long id;
    private long filled;

    public Order( long newId, OrderRequest orderRequest ) {
        super( orderRequest );
        id = newId;
    }

    public long getId() {
        return id;
    }

    public long getFilled() {
        return filled;
    }

    public void setFilled( long filled ) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return this.getQuantity() == this.getFilled();
    }

    public void cancelPendingOrder() {
        if( !isFilled() ) {
            this.setQuantity( this.getFilled() ); // reduce pending quantity to filled quantity
        }
    }

}

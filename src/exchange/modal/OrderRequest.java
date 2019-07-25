package exchange.modal;

import exchange.enums.OrderType;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class OrderRequest {

    private long userId;
    private long price;
    private long quantity;
    private OrderType type;

    public OrderRequest( long userId, long price, long quantity, OrderType type ) {
        this.userId = userId;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public OrderRequest( OrderRequest orderRequest ) {
        userId = orderRequest.userId;
        price = orderRequest.price;
        quantity = orderRequest.quantity;
        type = orderRequest.type;
    }

    public long getUserId() {
        return userId;
    }

    public long getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public OrderType getType() {
        return type;
    }

    protected void setUserId( long userId ) {
        this.userId = userId;
    }

    protected void setPrice( long price ) {
        this.price = price;
    }

    protected void setQuantity( long quantity ) {
        this.quantity = quantity;
    }

    protected void setType( OrderType type ) {
        this.type = type;
    }

}

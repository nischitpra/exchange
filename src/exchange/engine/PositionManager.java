package exchange.engine;

import exchange.modal.Order;
import exchange.modal.Position;
import exchange.util.Logger;

import java.util.HashMap;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class PositionManager {
    private HashMap<Long, Position> positionTable;

    public PositionManager() {
        positionTable = new HashMap();
    }

    public void updatePosition( final Order order ) {
        final long userId = order.getUserId();
        Position position = positionTable.get( userId );
        if ( position == null ) {
            position = new Position();
            positionTable.put( userId, position );
        }
        position.fillByOrder( order );
    }

    public void log() {
        positionTable.forEach( ( k, v ) -> {
            Logger.log( "position: %s", v.toString() );
        } );
    }
}

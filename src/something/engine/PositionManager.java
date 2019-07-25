package something.engine;

import something.modal.Order;
import something.modal.Position;

import java.util.HashMap;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class PositionManager {

    private HashMap<Long, Position> positionTable;

    public PositionManager() {
        positionTable = new HashMap();
    }

    public void createPosition( Order order ) {
        final long userId = order.getUserId();
        Position position = positionTable.get( userId );
        if( position == null ) {
            position = new Position( order );
            positionTable.put( userId, position );
        }

        position.setUserId(  );

    }

}

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
package exchange;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Starter {
    public static void main( String[] args ) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SystemInit systemInit = new SystemInit();
        systemInit.init();
    }
}

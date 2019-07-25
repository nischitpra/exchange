package exchange.util;

/**
 * Created by nischitpradhan on 2019-07-25
 */
public class Logger {
    public static void log( String format, Object... args ) {
        System.out.println( String.format( format, args ) );
    }
}

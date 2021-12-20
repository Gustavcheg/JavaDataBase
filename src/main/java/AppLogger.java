import org.slf4j.*;

public class AppLogger {
    static Logger log = LoggerFactory.getLogger(AppLogger.class);

    public static void infoOut(String info){
        log.info(info);
    }
}

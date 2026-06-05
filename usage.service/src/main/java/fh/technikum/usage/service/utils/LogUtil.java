package fh.technikum.usage.service.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LogUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    public static void printInfo(String message){
        LOG.info("________________________________________________");
        LOG.info(message);
        LOG.info("________________________________________________");
    }

}

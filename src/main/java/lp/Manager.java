package lp;

import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import org.apache.logging.log4j.Logger;

public class Manager {

    private static final LoggerService LOGGER_SERVICE = LoggerServiceImpl.getInstance(Manager.class);
    private static final Logger LOG = LOGGER_SERVICE.getLog();

    public static void main(String[] args) {
        LOG.info("test");
    }
}

package lp;

import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.Texts;
import lp.fe.App;
import org.apache.logging.log4j.Logger;

public class Manager {

    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();

    public Manager() {
        log.info(Texts.APPLICATION_STARTED.getText());
        javafx.application.Application.launch(App.class);
    }

    public static void main(String[] args) {
        new Manager();
    }
}

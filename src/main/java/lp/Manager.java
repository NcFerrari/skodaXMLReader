package lp;

import lp.be.service.FileService;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.FileServiceImpl;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.Texts;
import lp.fe.App;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class Manager {

    private static Manager manager;

    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();
    private final FileService fileService = FileServiceImpl.getInstance();
    private final Properties properties = fileService.loadConfigFile();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(App.class);
        }
        return manager;
    }

    public Manager() {
        log.info(Texts.APPLICATION_STARTED.getText());
    }

    public static void main(String[] args) {
        getInstance();
    }

    public int getWindowWidth() {
        return Integer.parseInt(properties.getProperty(Texts.WINDOW_WIDTH.getText()));
    }

    public int getWindowHeight() {
        return Integer.parseInt(properties.getProperty(Texts.WINDOW_HEIGHT.getText()));
    }
}

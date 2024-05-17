package lp;

import lp.be.service.FileService;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.FileServiceImpl;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.Texts;
import lp.fe.App;
import lp.fe.DragAndDropTest;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class Manager {

    private static Manager manager;

    private final FileService fileService = FileServiceImpl.getInstance();
    private final Properties properties = fileService.loadConfigFile();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(App.class);
//            javafx.application.Application.launch(DragAndDropTest.class);
        }
        return manager;
    }

    public Manager() {
        LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
        Logger log = loggerService.getLog();
        log.debug(Texts.APPLICATION_STARTED.getText());
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

    public InputStream getIconImage() {
        return fileService.loadImage(Texts.ICON_IMAGE.getText());
    }
}

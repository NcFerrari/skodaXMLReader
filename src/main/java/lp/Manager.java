package lp;

import lombok.Getter;
import lp.be.service.FileService;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.FileServiceImpl;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.enums.ReportStatus;
import lp.enums.Suffix;
import lp.enums.Texts;
import lp.fe.App;
import lp.validations.FileValidator;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@Getter
public class Manager {

    private static Manager manager;

    private final FileService fileService = FileServiceImpl.getInstance();
    private final Properties properties = fileService.loadConfigFile();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(Manager.class);
    private final Logger log = loggerService.getLog();
    private final Set<File> listOfFiles = new HashSet<>();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(App.class);
        }
        return manager;
    }

    private Manager() {

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

    public boolean validateFiles() {
        boolean[] result = {true};
        StringBuilder reports = new StringBuilder();
        listOfFiles.forEach(file -> {
            String validationResult = FileValidator.getInstance().validateFileSuffix(file, Suffix.XML);
            if (!validationResult.equals(ReportStatus.OK.name().toUpperCase())) {
                result[0] = false;
                reports.append(validationResult);
            }
        });
        log.debug(reports);
        return result[0];
    }

    public void addToList(List<File> files) {
        listOfFiles.addAll(files);
    }

    public void removeItemFromListOfFiles(String fileName) {
        Optional<File> optional = listOfFiles.stream().filter(file -> file.getName().equals(fileName)).findFirst();
        optional.ifPresent(listOfFiles::remove);
    }
}

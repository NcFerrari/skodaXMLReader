package lp.be.serviceimpl;

import lp.be.service.FileService;
import lp.be.service.LoggerService;
import lp.enums.Texts;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileServiceImpl implements FileService {

    private static FileServiceImpl fileServiceImpl;
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(FileServiceImpl.class);
    private final Logger log = loggerService.getLog();

    public static FileService getInstance() {
        if (fileServiceImpl == null) {
            fileServiceImpl = new FileServiceImpl();
        }
        return fileServiceImpl;
    }

    @Override
    public Properties loadConfigFile() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(Texts.CONFIG_FILE_NAME.getText()));
        } catch (IOException e) {
            log.error(e);
            for (StackTraceElement ste : e.getStackTrace()) {
                log.error(ste);
            }
        }
        return properties;
    }

    @Override
    public InputStream loadImage(String imageName) {
        return getClass().getClassLoader().getResourceAsStream(imageName);
    }
}

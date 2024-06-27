package lp.be.service;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public interface FileService {

    Properties loadConfigFile();

    InputStream loadImage(String imageName);

    void loadXMLFile(File xmlFile);
}

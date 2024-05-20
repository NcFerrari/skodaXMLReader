package lp.validations;

import lp.enums.ReportStatus;
import lp.enums.Suffix;
import lp.enums.Texts;

import java.io.File;

public class FileValidator {

    private static FileValidator fileValidator;

    public static FileValidator getInstance() {
        if (fileValidator == null) {
            fileValidator = new FileValidator();
        }
        return fileValidator;
    }

    private FileValidator() {

    }

    public String validateFileSuffix(File file, Suffix suffix) {
        StringBuilder report = new StringBuilder();
        if (fileNotExists(file)) {
            return Texts.FILE_NOT_EXISTS.getText();
        }
        String[] splitFileName = file.getName().split("\\.");
        if (splitFileName.length < 2) {
            report.append(Texts.FILE_TEXT.getText());
            report.append(file.getName());
            report.append(Texts.FILE_WITHOUT_SUFFIX.getText());
        }
        if (suffix.name().equals(splitFileName[splitFileName.length - 1].toUpperCase())) {
            report.append(ReportStatus.OK);
        } else {
            report.append(Texts.FILE_SUFFIX.getText());
            report.append(file.getName());
            report.append(Texts.NOT_XML.getText());
        }
        return report.toString();
    }

    private boolean fileNotExists(File file) {
        return !file.exists();
    }

}

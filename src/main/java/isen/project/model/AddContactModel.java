package isen.project.model;

import isen.project.util.Constant;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class AddContactModel {

    /**
     * Function which save file in the directory profilIcon
     * @param fileProfilIcon
     * @return the name of the file
     * @throws IOException
     */

    public String SaveFile(File fileProfilIcon) throws IOException {
        //We load our file into Image
        Image imageProfilIcon = new Image(fileProfilIcon.toURI().toString());
        String fileName = fileProfilIcon.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileProfilIcon.getName().length());

        //We transform our image to saveable file
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageProfilIcon, null);
        String nameOfSaveFile = java.util.UUID.randomUUID().toString() +"."+fileExtension;
        //We generate random id
        File fileToSave = new File(Constant.URL_TO_IMAGE +  nameOfSaveFile);
        //We save our file into fileIcon
        ImageIO.write(bufferedImage, "png", fileToSave);

        return nameOfSaveFile;

    }

}

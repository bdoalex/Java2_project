package isen.project.util;

import java.nio.file.Paths;

public  class Constant {

    public static  String URL_TO_IMAGE = Paths.get(".").toAbsolutePath().normalize().toAbsolutePath() + "/src/main/resources/isen/project/image/profilIcon/";
    public static  String DEFAULT_IMAGE = "defaultImage.jpg";

    public static  int MIN_WIDTH = 500;
    public static  int MIN_HEIGHT = 500;

    public static  int WIDTH_DRAWER_CLOSE= 50;
    public static  int WIDTH_DRAWER_OPEN= 150;
}

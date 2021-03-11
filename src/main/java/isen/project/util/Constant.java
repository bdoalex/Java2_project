package isen.project.util;

import java.nio.file.Paths;

public  class Constant {

    public static final  String URL_TO_IMAGE = Paths.get(".").toAbsolutePath().normalize().toAbsolutePath() + "/src/main/resources/isen/project/image/profilIcon/";

    public static  final String DEFAULT_IMAGE = "defaultImage.jpg";


    public static final  int MIN_WIDTH = 800;
    public static final int MIN_HEIGHT = 500;

    public static final int WIDTH_DRAWER_CLOSE= 50;
    public static final int WIDTH_DRAWER_OPEN= 150;

    private Constant() { }
}

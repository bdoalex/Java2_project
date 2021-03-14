package isen.project.util;

import java.nio.file.Paths;

/**
 * All constant of the project.
 */
public  class Constant {

    /**
     * The constant URL_TO_IMAGE.
     * It works only in development , you have to change for release mode
     */
    public static final  String URL_TO_IMAGE = Paths.get(".").toAbsolutePath().normalize().toAbsolutePath() + "/src/main/resources/isen/project/image/profilIcon/";

    /**
     * The constant DEFAULT_IMAGE.
     */
    public static  final String DEFAULT_IMAGE = "defaultImage.jpg";


    /**
     * The constant MIN_WIDTH.
     */
    public static final  int MIN_WIDTH = 800;
    /**
     * The constant MIN_HEIGHT.
     */
    public static final int MIN_HEIGHT = 500;

    /**
     * The constant WIDTH_DRAWER_CLOSE.
     */
    public static final int WIDTH_DRAWER_CLOSE= 50;
    /**
     * The constant WIDTH_DRAWER_OPEN.
     */
    public static final int WIDTH_DRAWER_OPEN= 150;

    private Constant() { }
}

package com.nta.utils;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class ImageUtils {



    public static boolean isImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    public  static boolean isJpgOrJpegImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    public  static boolean isPngOrJpegImage(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpeg");
    }

}

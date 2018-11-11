package com.yg.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class Ocr {
    public String ChangeToString(BufferedImage bufferedImage){
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Program Files (x86)\\Tesseract-OCR\\tessdata");
        String result = "";
        try {
            result = instance.doOCR(bufferedImage);
            System.out.println(result);
        } catch (TesseractException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }
}

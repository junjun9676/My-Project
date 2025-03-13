package com.jun.springbootimagetool.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    public void resizeImage(File inputFile, File outputFile, int width, int height) throws IOException{
        Thumbnails.of(inputFile).size(width, height).toFile(outputFile);
    }
}

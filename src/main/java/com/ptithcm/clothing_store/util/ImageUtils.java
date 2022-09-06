package com.ptithcm.clothing_store.util;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.dto.Image;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Objects;

public class ImageUtils {
    public static String downloadImage(Image image,String URL_IMG) {
        if (!Objects.isNull(image.getImageByte())) {
            try {
                String[] strings = image.getImageByte().split(",");
                byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
                File file = new File(URL_IMG + image.getImageName());
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                outputStream.write(data);
                return CSConstant.SUCCESS;
            } catch (IOException e) {
                throw new ResourceNotFoundException("Image can't found");
            }
        }
        return CSConstant.FAIL;
    }
}

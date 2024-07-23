package com.webspringboot.create_qrcode.untils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Objects;

public class AppUtils {
    //tạo đối tướngj jsson từ dto
   public static String prettyObject(Object object){
       try{
           ObjectMapper mapper = new ObjectMapper();
           return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
       }catch (JsonProcessingException e)
       {
           e.printStackTrace();
       }
       return "";
   }


//tạo max base64 để tạo qrcode

    public static String generateQrCode(String data,int wid,int hei)
    {
        StringBuilder result = new StringBuilder();
        if(!data.isEmpty()){
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {

                QRCodeWriter writer = new QRCodeWriter();

                BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE,wid,hei);

                BufferedImage oufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(oufferedImage,"png",os);
                result.append("data:image/png;base64,");
                result.append(new String(Base64.getEncoder().encode(os.toByteArray())));
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

            return result.toString();
    }
//giải mã base64 thành ảnh
    public static BufferedImage decodeBase64ToImage(String base64String) throws Exception {
        // Remove the "data:image/png;base64," prefix (if present)
        String pureBase64 = base64String.replace("data:image/png;base64,", "");

        // Decode base64 string to byte array
        byte[] decodedBytes = Base64.getDecoder().decode(pureBase64);

        // Convert byte array to BufferedImage
        ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
        return ImageIO.read(bis);
    }

//    public static void main(String[] args) {
//        String input =" anh vũ đẹp trai 10d";
//        System.out.println(generateQrCode(input,300,300));
//    }
}

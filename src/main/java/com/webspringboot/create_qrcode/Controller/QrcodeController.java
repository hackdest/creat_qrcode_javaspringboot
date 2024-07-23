package com.webspringboot.create_qrcode.Controller;

import com.webspringboot.create_qrcode.DTO.UserLoginSdi;
import com.webspringboot.create_qrcode.Serrvice.QrcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import static com.webspringboot.create_qrcode.untils.AppUtils.decodeBase64ToImage;


@RestController
public class QrcodeController {

    @Autowired
    QrcodeService qrcodeService;

    @PostMapping(value = "generateQRCode")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody UserLoginSdi sdi) throws Exception {
        String basecode = qrcodeService.generateQRCode(sdi);
        BufferedImage qrCodeImage = decodeBase64ToImage(basecode);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    // ... other methods ...
}
package com.webspringboot.create_qrcode.Serrvice;

import com.webspringboot.create_qrcode.DTO.UserLoginSdi;

public interface QrcodeService {
     String generateQRCode(UserLoginSdi sdi);
}

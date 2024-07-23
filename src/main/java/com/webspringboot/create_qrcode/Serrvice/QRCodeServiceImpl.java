package com.webspringboot.create_qrcode.Serrvice;

import com.webspringboot.create_qrcode.DTO.UserLoginSdi;
import com.webspringboot.create_qrcode.untils.AppUtils;
import org.springframework.stereotype.Service;

@Service
public class QRCodeServiceImpl implements QrcodeService{
    private static final int ORDER_QR_CODE_WID=300;
    private static final int ORDER_QR_CODE_HEI=300;
    @Override
    public String generateQRCode(UserLoginSdi sdi) {
        String prettyData = AppUtils.prettyObject(sdi);
       String qrCode = AppUtils.generateQrCode(prettyData,ORDER_QR_CODE_WID,ORDER_QR_CODE_HEI);
        return qrCode;
    }
}

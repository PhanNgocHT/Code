package com.zuckerteam;

/**
 * Created by Dung Ali on 8/31/2017.
 */

public class Utilitis {

    public static int soNgayTrongMotThang(int thang, int nam) {
        int soNgayTrongThang = 0;
        boolean isNhuan = (nam % 400 == 0) || ((nam % 4 == 0) && (nam % 100 != 0));
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                soNgayTrongThang = 31;
                break;
            case 2:
                if (isNhuan) {
                    soNgayTrongThang = 29;
                } else soNgayTrongThang = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                soNgayTrongThang = 30;
                break;
            default:
                break;
        }
        return soNgayTrongThang;
    }
}

package com.zuckerteam;

import android.os.Environment;

import java.util.Calendar;

/**
 * Created by Dung Ali on 8/4/2017.
 */

public interface Constant {
    String chuyenMucDinhDuongBaBau = "DINH_DUONG_BA_BAU";
    String chuyenMucChamSocTre = "CHAM_SOC_TRE";
    String chuyenMucDayCon = "DAY_CON";
    String chuyenMucGocHaiHuoc = "GOC_HAI_HUOC";
    String chuyenMucTenHayCuaBe = "TEN_HAY_CUA_BE";
    String duongDanTrangChu = "http://afamily.vn";
    String keyDuongDanTinTuc = "DUONG_DAN_TIN_TUC";
    String keyTieuDeChuyenMuc = "TIEU_DE_CHUYEN_MUC";
    String chuyenMucKinhNghiemDiDe = "KINH_NGHIEM_SINH_NO";
    String COLUMN_ID = "Id";

    String COLUMN_TEN_DAI_DIEN = "TenDaiDien";
    String COLUMN_DANH_SACH_TEN = "DanhSachTen";
    String COLUMN_Y_NGHIA_TEN = "YNghiaTen";

    String COLUMN_THOI_GIAN_TIEM_PHONG = "ThoiGianTiemPhong";
    String COLUMN_TEN_BENH_TIEM_PHONG = "TenBenhTiemPhong";
    String COLUMN_MO_TA_BENH_TIEM_PHONG = "MoTaBenhTiemPhong";


    String COLUMN_TAM_CA_NGUYET = "TamCaNguyet";
    String COLUMN_TUAN_THAI_KY = "TuanThaiKy";
    String COLUMN_THE_TRANG_THAI_PHU = "TheTrangThaiPhu";
    String COLUMN_TAM_LY_THAI_PHU = "TamLyThaiPhu";
    String COLUMN_TINH_TRANG_THAI_NHI = "TinhTrangThaiNhi";
    String COLUMN_LOI_KHUYEN_TRONG_TUAN = "LoiKhuyenTrongTuan";
    String COLUMN_ANH_THAI_NHI = "AnhThaiNhi";
    String KEY_BIEU_DO_THAI_KY = "bieuDoThaiKy";
    String chuyenMucBieuDoThaiKy = "bieuDo";
    String TABLE_VIEC_CAN_LAM = "ViecCanLam";
    String COLUMN_TEN_BIEN_CHUNG = "TenBienChung";
    String COLUMN_NOI_DUNG_BIEN_CHUNG = "NoiDungBienChung";
    String COLUMN_TEN_VIEC_CAN_LAM = "TenViecCanLam";
    String COLUMN_NOI_DUNG_VIEC_CAN_LAM = "NoiDungViecCanLam";
    String TABLE_BIEN_TRONG_CHUNG_THAI_KY = "BienChungTrongThaiKy";
    String COLUMN_TEN_GIAI_DOAN = "TenGiaiDoan";
    String TABLE_GIAI_DOAN_BIEN_CHUNG_TRONG_THAI_KY = "GiaiDoanBienChungTrongThaiKy";
    int WHAT_DOWNLOAD_WEPPAGE = 1;
    String KEY_DUONG_DAN_TIN_TUC = "DuongDanTinTuc";
    String KEY_ANH_TIN_TUC = "anhTinTuc";
    String KEY_TIEU_DE_TIN_TUC = "tieuDeTinTuc";
    String KEY_MO_TA_TIN_TUC = "moTaTinTuc";
    String COLUMN_MO_TA_TIN_TUC = "MoTaTinTuc";
    String COLUMN_DUONG_DAN_TIN_TUC = "DuongDanTinTuc";
    String COLUMN_ANH_TIN_TUC = "AnhTinTuc";
    String COLUMN_TIEU_DE_TIN_TUC = "TieuDeTinTuc";
    String KEY_TIN_TUC_ME_VA_BE = "tinTucMeVaBe";
    String COLUMN_THAN_TIN_TUC_HTML = "ThanTinTucHtml";
    String KEY_THAN_TIN_TUC_HTML = "thanTinTucHtml";
    int WHAT_DOWNLOAD_HTML = 1;
    int WHAT_LUU_OFFLINE = 1;
    String KEY_TIN_TUC_OFFLINE = "key_tin_tuc_offline";
    String chuyenMucSoTayTiemPhong = "chuyenMucSoTayTiemPhong";
    String COLUMN_GIOI_TINH_TRE = "GioiTinhTre";
    String COLUMN_NAM_SINH_TRE = "NamSinhTre";
    String COLUMN_THANG_SINH_TRE = "ThangSinhTre";
    String COLUMN_NGAY_SINH_TRE = "NgaySinhTre";
    String COLUMN_ANH_DAI_DIEN_TRE = "AnhDaiDienTre";
    String COLUMN_TEN_TRE = "TenTre";
    String KEY_TEN_TRE = "tenTre";
    String KEY_ANH_DAI_DIEN_TRE = "anhDaiDienTre";
    String KEY_NGAY_SINH_TRE = "ngaySinhTre";
    String KEY_THANG_SINH_TRE = "thangSinhTre";
    String KEY_NAM_SINH_TRE = "namSinhTre";
    String KEY_GIOI_TINH_TRE = "gioiTinhTre";
    int NGAY_HIEN_TAI = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    int THANG_HIEN_TAI = Calendar.getInstance().get(Calendar.MONTH) + 1;
    int NAM_HIEN_TAI = Calendar.getInstance().get(Calendar.YEAR);
    String COLUMN_LAN_KHAM = "LanKham";
    String COLUMN_TUAN_KHAM = "TuanKham";
    String COLUMN_NOI_DUNG_KHAM = "NoiDungKham";
    String chuyenMucMuaSam = "MuaSam";
    String COLUMN_TEN_DO_DUNG = "TenDoDung";
    String COLUMN_MO_TA_DO_DUNG = "MoTaDoDung";
    String COLUMN_SO_LUONG_DE_NGHI = "SoLuongDeNghi";
    String COLUMN_CONG_DUNG = "CongDung";
    String TABLE_DO_DUNG_CHO_BA_BAU = "DoDungChoBaBau";
    String TABLE_DO_DUNG_SAU_SINH = "DoDungSauKhiSinh";
    String COLUMN_TEN_CHUYEN = "TenChuyen";
    String COLUMN_NOI_DUNG_CUA_CHUYEN = "NoiDungChuyen";
    String COLUMN_BAI_HOC_CUA_CHUYEN = "BaiHocCuaChuyen";
    String TABLE_TEN_BE_GAI = "TenBeGai";
    String TABLE_LICH_TIEM_PHONG = "LichTiemPhong";
    String TABLE_BENH_TIEM_PHONG = "BenhTiemPhong";
    String TABLE_BIEU_DO_THAI_KY = "BieuDoThaiKy";
    String TABLE_TIN_TUC_YEU_THICH = "TinTucYeuThich";
    String TABLe_TRE_TIEM_PHONG="TreTiemPhong";
    String TABLE_LICH_KHAM_THAI = "LichKhamThai";
    String TABLE_KE_CHUYEN_CHO_BE = "KeChuyenChobe";
    String pathFileAudio = Environment.getExternalStorageDirectory().getPath()+"/NhacKhongLoi/";
    int WHAT_GET_LINK_AUDIO = 1;
    int WHAT_DOWNLOAD_FILE_AUDIO = 2;
    int STATE_IDLE = 1;
    int STATE_PLAYING = 2;
    int STATE_PAUSED = 3;
    int NOTIFICATION_ID = 100;
    String STATE_ON_STOP ="on_stop";

    String PLAY = "play";
    String PAUSE = "pause";
    String NEXT = "next";
    String PREVIOUS = "previous";
    String SONG_NAME = "song_name";
    String URI = "uri";
    String IS_PLAYING = "is_playing";
    String SERVICE_ACTION_STOP = "service_action_stop";
    String ARRAYLIST_FILE = "ARRAYLIST_FILE";
    String POSITION_SONG = "position_song";
    String POSITON_PLAY = "position_play" ;
    String ACTION_PLAY = "action_play";
    String BROADCAST_ACTION_PREVIOUS = "action_previous";
    String BROADCAST_ACTION_PLAY_PAUSE = "action_pause";
    String BROADCAST_ACTION_NEXT = "action_next";
}

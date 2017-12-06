package com.zuckerteam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zuckerteam.Constant;
import com.zuckerteam.model.KeChuyenChoBe;
import com.zuckerteam.model.TinTucYeuThich;
import com.zuckerteam.model.lich_kham_thai.LichKhamThai;
import com.zuckerteam.model.muasam.DoDungChoBaBau;
import com.zuckerteam.model.muasam.DoDungSauSinh;
import com.zuckerteam.model.sotaytiemphong.BenhTiemPhong;
import com.zuckerteam.model.sotaytiemphong.LichTiemPhong;
import com.zuckerteam.model.TenHayCuaBe;
import com.zuckerteam.model.sotaytiemphong.TreTiemPhong;
import com.zuckerteam.model.thaiky.BienChungTrongThaiKy;
import com.zuckerteam.model.thaiky.BieuDoThaiKy;
import com.zuckerteam.model.thaiky.GiaiDoanBienChungTrongThaiKy;
import com.zuckerteam.model.thaiky.ViecCanLam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 8/5/2017.
 */

public class DatabaseClass implements DatabaseInterface<Object> {
    private static final String DATABASE_NAME = "database.sqlite";
    private static final String TAG = "DATABASE";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseClass(Context context) {
        this.context = context;
        checkDatabase();
    }

    @Override
    public boolean openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
            return true;
        }
        return false;
    }

    @Override
    public boolean closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean insertData(Object object, String tableName) {
        openDatabase();
        ContentValues values = new ContentValues();
        if (object instanceof TinTucYeuThich && tableName.equals("TinTucYeuThich")) {
            values.put(Constant.KEY_TIEU_DE_TIN_TUC, ((TinTucYeuThich) object).getTieuDeTinTuc());
            values.put(Constant.KEY_ANH_TIN_TUC, ((TinTucYeuThich) object).getAnhTinTuc());
            values.put(Constant.KEY_MO_TA_TIN_TUC, ((TinTucYeuThich) object).getMoTaTinTuc());
            values.put(Constant.KEY_THAN_TIN_TUC_HTML, ((TinTucYeuThich) object).getThanTinTucHtml());
            values.put(Constant.KEY_DUONG_DAN_TIN_TUC, ((TinTucYeuThich) object).getDuongDanTinTuc());
            long id = sqLiteDatabase.insert(tableName, null, values);
            Log.d(TAG, "insertData: "+id);
            return id != -1;
        } else if (object instanceof TreTiemPhong && tableName.equals("TreTiemPhong")) {
            values.put(Constant.KEY_TEN_TRE, ((TreTiemPhong) object).getTenTre());
            values.put(Constant.KEY_ANH_DAI_DIEN_TRE, ((TreTiemPhong) object).getAnhDaiDienTre());
            values.put(Constant.KEY_NGAY_SINH_TRE, ((TreTiemPhong) object).getNgaySinhTre());
            values.put(Constant.KEY_THANG_SINH_TRE, ((TreTiemPhong) object).getThangSinhTre());
            values.put(Constant.KEY_NAM_SINH_TRE, ((TreTiemPhong) object).getNamSinhTre());
            values.put(Constant.KEY_GIOI_TINH_TRE, ((TreTiemPhong) object).getGioiTinhTre());
            long id = sqLiteDatabase.insert(tableName, null, values);
            Log.d(TAG, "insertData: "+id);
            return id != -1;
        }
        closeDatabase();
        return false;
    }

    @Override
    public boolean updateData(Object object, String tableName) {
        return false;
    }

    @Override
    public boolean deleteData(Object object, String tableName) {
        openDatabase();
        if (object instanceof TinTucYeuThich && tableName.equals(Constant.TABLE_TIN_TUC_YEU_THICH)) {
            long id = sqLiteDatabase.delete(tableName, "Id=?", new String[]{((TinTucYeuThich) object).getId() + ""});
            return id != -1;
        } else if (object instanceof TreTiemPhong && tableName.equals(Constant.TABLe_TRE_TIEM_PHONG)) {
            long id = sqLiteDatabase.delete(tableName, "Id=?", new String[]{((TreTiemPhong) object).getId() + ""});
            return id != -1;
        }
        closeDatabase();
        return false;
    }

    @Override
    public void checkDatabase() {
        File f = context.getDatabasePath(DATABASE_NAME);
        if (!f.exists()) {
            handleCoppyDatabaseFromAssets();
        }
    }

    @Override
    public void handleCoppyDatabaseFromAssets() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            String path = layDuongDanDatabase();
            File f = new File(path);
            f.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(f);

            byte[] b = new byte[1024];
            int lenght = inputStream.read(b);
            while (lenght != -1) {
                outputStream.write(b, 0, lenght);
                lenght = inputStream.read(b);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList getData(String tableName) {
        openDatabase();
        if (tableName.equals(Constant.TABLE_TEN_BE_GAI) || tableName.equals("TenBeTrai")) {
            return getDataDatTenChoBe(tableName);
        } else if (tableName.equals(Constant.TABLE_LICH_TIEM_PHONG)) {
            return getDataLichTiemPhong(tableName);
        } else if (tableName.equals(Constant.TABLE_BENH_TIEM_PHONG)) {
            return getDataBenhTiemPhong(tableName);
        } else if (tableName.equals(Constant.TABLE_BIEU_DO_THAI_KY)) {
            return getDataBieuDoThaiKy(tableName);
        } else if (tableName.equals(Constant.TABLE_VIEC_CAN_LAM)) {
            return getDataViecCanLam(tableName);
        } else if (tableName.equals(Constant.TABLE_BIEN_TRONG_CHUNG_THAI_KY)) {
            return getDataBienChungTrongThaiKy(tableName);
        } else if (tableName.equals(Constant.TABLE_GIAI_DOAN_BIEN_CHUNG_TRONG_THAI_KY)) {
            return getDataGiaiDoanBienChung(tableName);
        } else if (tableName.equals(Constant.TABLE_TIN_TUC_YEU_THICH)) {
            return getDataTinTucYeuThich(tableName);
        } else if (tableName.equals(Constant.TABLe_TRE_TIEM_PHONG)) {
            return getDataTreTiemPhong(tableName);
        } else if(tableName.equals(Constant.TABLE_LICH_KHAM_THAI)){
            return getDataLichKhamThai(tableName);
        }else if(tableName.equals(Constant.TABLE_DO_DUNG_CHO_BA_BAU)){
            return getDataDoDungChoBaBau(tableName);
        }else if(tableName.equals(Constant.TABLE_DO_DUNG_SAU_SINH)){
            return getDataDoDungSauKhiSinh(tableName);
        }else if(tableName.equals(Constant.TABLE_KE_CHUYEN_CHO_BE)){
            return getDataKeChuyenChoBe(tableName);
        }
        return new ArrayList();
    }

    private ArrayList getDataKeChuyenChoBe(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName,null,null,null,null,null,null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenChuyen_index = cursor.getColumnIndex(Constant.COLUMN_TEN_CHUYEN);
        int columnNoiDungCuaChuyen_index = cursor.getColumnIndex(Constant.COLUMN_NOI_DUNG_CUA_CHUYEN);
        int columnBaiHocCuaChuyen_index = cursor.getColumnIndex(Constant.COLUMN_BAI_HOC_CUA_CHUYEN);
        cursor.moveToFirst();
        ArrayList<KeChuyenChoBe> keChuyenChoBes = new ArrayList<>();
        while (!cursor.isAfterLast()){
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_chuyen = cursor.getString(columnTenChuyen_index);
            String noi_dung_cua_chuyen = cursor.getString(columnNoiDungCuaChuyen_index);
            String bai_hoc_cua_chuyen = cursor.getString(columnBaiHocCuaChuyen_index);

            KeChuyenChoBe keChuyenChoBe = new KeChuyenChoBe(id,ten_chuyen,noi_dung_cua_chuyen,bai_hoc_cua_chuyen);
            keChuyenChoBes.add(keChuyenChoBe);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return keChuyenChoBes;
    }

    private ArrayList getDataDoDungChoBaBau(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName,null,null,null,null,null,null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenDoDung_index = cursor.getColumnIndex(Constant.COLUMN_TEN_DO_DUNG);
        int columnMoTaDoDung_index = cursor.getColumnIndex(Constant.COLUMN_MO_TA_DO_DUNG);
        cursor.moveToFirst();
        ArrayList<DoDungChoBaBau> doDungChoBaBaus = new ArrayList<>();
        while (!cursor.isAfterLast()){
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_do_dung = cursor.getString(columnTenDoDung_index);
            String mo_ta_do_dung = cursor.getString(columnMoTaDoDung_index);

            DoDungChoBaBau doDungChoBaBau = new DoDungChoBaBau(id,ten_do_dung,mo_ta_do_dung);
            doDungChoBaBaus.add(doDungChoBaBau);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return doDungChoBaBaus;
    }

    private ArrayList getDataDoDungSauKhiSinh(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName,null,null,null,null,null,null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenDoDung_index = cursor.getColumnIndex(Constant.COLUMN_TEN_DO_DUNG);
        int columnSoLuongDeNghi_index = cursor.getColumnIndex(Constant.COLUMN_SO_LUONG_DE_NGHI);
        int columnCongDung_index = cursor.getColumnIndex(Constant.COLUMN_CONG_DUNG);
        cursor.moveToFirst();
        ArrayList<DoDungSauSinh> doDungSauSinhs = new ArrayList<>();
        while (!cursor.isAfterLast()){
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_do_dung = cursor.getString(columnTenDoDung_index);
            String so_luong_de_nghi = cursor.getString(columnSoLuongDeNghi_index);
            String cong_dung = cursor.getString(columnCongDung_index);

            DoDungSauSinh doDungSauSinh = new DoDungSauSinh(id,ten_do_dung,so_luong_de_nghi,cong_dung);
            doDungSauSinhs.add(doDungSauSinh);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return doDungSauSinhs;
    }

    private ArrayList getDataLichKhamThai(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName,null,null,null,null,null,null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnLanKham_index = cursor.getColumnIndex(Constant.COLUMN_LAN_KHAM);
        int columnTuanKham_index = cursor.getColumnIndex(Constant.COLUMN_TUAN_KHAM);
        int columnNoiDungKham_index = cursor.getColumnIndex(Constant.COLUMN_NOI_DUNG_KHAM);
        cursor.moveToFirst();
        ArrayList<LichKhamThai> lichKhamThais = new ArrayList<>();
        while (!cursor.isAfterLast()){
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String lan_kham = cursor.getString(columnLanKham_index);
            String tuan_kham = cursor.getString(columnTuanKham_index);
            String noi_dung_kham = cursor.getString(columnNoiDungKham_index);

            LichKhamThai lichKhamThai = new LichKhamThai(id,lan_kham,tuan_kham,noi_dung_kham);
            lichKhamThais.add(lichKhamThai);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lichKhamThais;
    }


    private ArrayList getDataDatTenChoBe(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenDaiDien_index = cursor.getColumnIndex(Constant.COLUMN_TEN_DAI_DIEN);
        int columnDanhSachTen_index = cursor.getColumnIndex(Constant.COLUMN_DANH_SACH_TEN);
        int columnYNghiaTen_index = cursor.getColumnIndex(Constant.COLUMN_Y_NGHIA_TEN);
        cursor.moveToFirst();
        ArrayList<TenHayCuaBe> tenHayCuaBes = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_dai_dien = cursor.getString(columnTenDaiDien_index);
            String danh_sach_ten = cursor.getString(columnDanhSachTen_index);
            String y_nghia_ten = cursor.getString(columnYNghiaTen_index);

            TenHayCuaBe tenHayCuaBe = new TenHayCuaBe(id, ten_dai_dien, danh_sach_ten, y_nghia_ten);
            tenHayCuaBes.add(tenHayCuaBe);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return tenHayCuaBes;
    }

    private ArrayList getDataBenhTiemPhong(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenBenhTiemPhong_index = cursor.getColumnIndex(Constant.COLUMN_TEN_BENH_TIEM_PHONG);
        int columnMoTaBenhTiemPhong = cursor.getColumnIndex(Constant.COLUMN_MO_TA_BENH_TIEM_PHONG);
        cursor.moveToFirst();
        ArrayList<BenhTiemPhong> benhTiemPhongs = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_benh_tiem_phong = cursor.getString(columnTenBenhTiemPhong_index);
            String mo_ta_benh_tiem_phong = cursor.getString(columnMoTaBenhTiemPhong);

            BenhTiemPhong benhTiemPhong = new BenhTiemPhong(id, ten_benh_tiem_phong, mo_ta_benh_tiem_phong);
            Log.d(TAG, "getDataBenhTiemPhong: " +
                    benhTiemPhong.getTenBenhTiemPhong() + "\t" +
                    benhTiemPhong.getMoTaBenhTiemPhong());
            benhTiemPhongs.add(benhTiemPhong);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return benhTiemPhongs;
    }

    private ArrayList getDataLichTiemPhong(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnThoiGianTiemPhong_index = cursor.getColumnIndex(Constant.COLUMN_THOI_GIAN_TIEM_PHONG);
        int columnTenBenhTiemPhong_index = cursor.getColumnIndex(Constant.COLUMN_TEN_BENH_TIEM_PHONG);
        cursor.moveToFirst();
        ArrayList<LichTiemPhong> lichTiemPhongs = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String thoi_gian_tiem_phong = cursor.getString(columnThoiGianTiemPhong_index);
            String ten_benh_tiem_phong = cursor.getString(columnTenBenhTiemPhong_index);

            LichTiemPhong lichTiemPhong = new LichTiemPhong(id, thoi_gian_tiem_phong, ten_benh_tiem_phong);
            lichTiemPhongs.add(lichTiemPhong);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lichTiemPhongs;
    }

    private ArrayList getDataTreTiemPhong(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenTre_index = cursor.getColumnIndex(Constant.COLUMN_TEN_TRE);
        int columnAnhDaiDienTre_index = cursor.getColumnIndex(Constant.COLUMN_ANH_DAI_DIEN_TRE);
        int columnNgaySinhTre_index = cursor.getColumnIndex(Constant.COLUMN_NGAY_SINH_TRE);
        int columnThangSinhTre_index = cursor.getColumnIndex(Constant.COLUMN_THANG_SINH_TRE);
        int columnNamSinhTre_index = cursor.getColumnIndex(Constant.COLUMN_NAM_SINH_TRE);
        int columnGioiTinhTre_index = cursor.getColumnIndex(Constant.COLUMN_GIOI_TINH_TRE);
        cursor.moveToFirst();
        ArrayList<TreTiemPhong> treTiemPhongs = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_tre = cursor.getString(columnTenTre_index);
            int anh_dai_dien_tre = Integer.parseInt(cursor.getString(columnAnhDaiDienTre_index));
            int ngay_sinh_tre = Integer.parseInt(cursor.getString(columnNgaySinhTre_index));
            int thang_sinh_tre = Integer.parseInt(cursor.getString(columnThangSinhTre_index));
            int nam_sinh_tre = Integer.parseInt(cursor.getString(columnNamSinhTre_index));
            String gioi_tinh_tre = cursor.getString(columnGioiTinhTre_index);

            TreTiemPhong treTiemPhong = new TreTiemPhong(id, ten_tre, anh_dai_dien_tre,
                    ngay_sinh_tre, thang_sinh_tre, nam_sinh_tre,
                    gioi_tinh_tre);
            treTiemPhongs.add(treTiemPhong);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return treTiemPhongs;
    }

    private ArrayList getDataBieuDoThaiKy(String tableName) {
        openDatabase();
        ArrayList<BieuDoThaiKy> bieuDoThaiKies = new ArrayList<>();
        String sqlStatementSelect = "Select *from " + tableName + " order by TuanThaiKy ASC";
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTamCaNguyet_index = cursor.getColumnIndex(Constant.COLUMN_TAM_CA_NGUYET);
        int columnTuanThaiKy_index = cursor.getColumnIndex(Constant.COLUMN_TUAN_THAI_KY);
        int columnTheTrangThaiPhu_index = cursor.getColumnIndex(Constant.COLUMN_THE_TRANG_THAI_PHU);
        int columnTamLyThaiPhu_index = cursor.getColumnIndex(Constant.COLUMN_TAM_LY_THAI_PHU);
        int columnTinhTrangThaiNhi_index = cursor.getColumnIndex(Constant.COLUMN_TINH_TRANG_THAI_NHI);
        int columnLoiKhuyenTrongTuan_index = cursor.getColumnIndex(Constant.COLUMN_LOI_KHUYEN_TRONG_TUAN);
        int columnAnhThaiNhi_index = cursor.getColumnIndex(Constant.COLUMN_ANH_THAI_NHI);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String tamCaNguyet = cursor.getString(columnTamCaNguyet_index);
            String tuanThaiKy = cursor.getString(columnTuanThaiKy_index);
            String theTrangThaiPhu = cursor.getString(columnTheTrangThaiPhu_index);
            String tamLyThaiPhu = cursor.getString(columnTamLyThaiPhu_index);
            String tinhTrangThaiNhi = cursor.getString(columnTinhTrangThaiNhi_index);
            String loiKhuyenTrongTuan = cursor.getString(columnLoiKhuyenTrongTuan_index);
            byte[] anhThaiNhi = cursor.getBlob(columnAnhThaiNhi_index);

            BieuDoThaiKy bieuDoThaiKy = new BieuDoThaiKy(id, tamCaNguyet,
                    tuanThaiKy, theTrangThaiPhu,
                    tamLyThaiPhu, tinhTrangThaiNhi,
                    loiKhuyenTrongTuan, anhThaiNhi);
            bieuDoThaiKies.add(bieuDoThaiKy);

            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return bieuDoThaiKies;
    }

    private ArrayList getDataViecCanLam(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenViecCanLam_index = cursor.getColumnIndex(Constant.COLUMN_TEN_VIEC_CAN_LAM);
        int columnNoiDungViecCanLam_index = cursor.getColumnIndex(Constant.COLUMN_NOI_DUNG_VIEC_CAN_LAM);
        cursor.moveToFirst();
        ArrayList<ViecCanLam> viecCanLams = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_viec_can_lam = cursor.getString(columnTenViecCanLam_index);
            String noi_dung_viec_can_lam = cursor.getString(columnNoiDungViecCanLam_index);

            ViecCanLam viecCanLam = new ViecCanLam(id, ten_viec_can_lam, noi_dung_viec_can_lam);
            viecCanLams.add(viecCanLam);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return viecCanLams;
    }

    public ArrayList getDataBienChungTrongThaiKy(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenBienChung_index = cursor.getColumnIndex(Constant.COLUMN_TEN_BIEN_CHUNG);
        int columnNoiDungBienChung_index = cursor.getColumnIndex(Constant.COLUMN_NOI_DUNG_BIEN_CHUNG);
        cursor.moveToFirst();
        ArrayList<BienChungTrongThaiKy> bienChungThaiKies = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_bien_chung = cursor.getString(columnTenBienChung_index);
            String noi_dung_bien_chung = cursor.getString(columnNoiDungBienChung_index);

            BienChungTrongThaiKy bienChungThaiKy = new BienChungTrongThaiKy(id, ten_bien_chung, noi_dung_bien_chung);
            bienChungThaiKies.add(bienChungThaiKy);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return bienChungThaiKies;
    }

    private ArrayList getDataGiaiDoanBienChung(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTenGiaiDoan_index = cursor.getColumnIndex(Constant.COLUMN_TEN_GIAI_DOAN);
        int columnTenBienChung_index = cursor.getColumnIndex(Constant.COLUMN_TEN_BIEN_CHUNG);
        cursor.moveToFirst();
        ArrayList<GiaiDoanBienChungTrongThaiKy> giaiDoanBienChungs = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String ten_giai_doan = cursor.getString(columnTenGiaiDoan_index);
            String ten_bien_chung = cursor.getString(columnTenBienChung_index);

            GiaiDoanBienChungTrongThaiKy giaiDoanBienChung = new GiaiDoanBienChungTrongThaiKy(id, ten_giai_doan, ten_bien_chung);
            giaiDoanBienChungs.add(giaiDoanBienChung);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return giaiDoanBienChungs;
    }

    private ArrayList getDataTinTucYeuThich(String tableName) {
        openDatabase();
        Cursor cursor = sqLiteDatabase.query(tableName, null, null, null, null, null, null);
        int columnId_index = cursor.getColumnIndex(Constant.COLUMN_ID);
        int columnTieuDeTinTuc_index = cursor.getColumnIndex(Constant.COLUMN_TIEU_DE_TIN_TUC);
        int columnAnhTinTuc_index = cursor.getColumnIndex(Constant.COLUMN_ANH_TIN_TUC);
        int columnMoTaTinTuc_index = cursor.getColumnIndex(Constant.COLUMN_MO_TA_TIN_TUC);
        int columnThanTinTucHtml_index = cursor.getColumnIndex(Constant.COLUMN_THAN_TIN_TUC_HTML);
        int columnDuongDanTinTuc_index = cursor.getColumnIndex(Constant.COLUMN_DUONG_DAN_TIN_TUC);
        cursor.moveToFirst();
        ArrayList<TinTucYeuThich> tinTucYeuThiches = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = Integer.parseInt(cursor.getString(columnId_index));
            String tieu_de_tin_tuc = cursor.getString(columnTieuDeTinTuc_index);
            String anh_tin_tuc = cursor.getString(columnAnhTinTuc_index);
            String mo_ta_tin_tuc = cursor.getString(columnMoTaTinTuc_index);
            String than_tin_tuc_html = cursor.getString(columnThanTinTucHtml_index);
            String duong_dan_tin_tuc = cursor.getString(columnDuongDanTinTuc_index);

            TinTucYeuThich yeuThich = new TinTucYeuThich(id, tieu_de_tin_tuc, anh_tin_tuc, mo_ta_tin_tuc, than_tin_tuc_html, duong_dan_tin_tuc);
            tinTucYeuThiches.add(yeuThich);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return tinTucYeuThiches;
    }


    private String layDuongDanDatabase() {
        return context.getApplicationInfo().dataDir + "/databases/" + DATABASE_NAME;
    }


}

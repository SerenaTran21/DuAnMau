/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlydaotao.entity;

import java.util.List;
import quanlydaotao.dao.KhoaHocDAO;
import quanlydaotao.utils.Auth;

/**
 *
 * @author Anh Thu
 */
public class KhoaHoc {

    private int maKH;
    private String maCD;
    private Double hocPhi;
    private int thoiLuong;
    private String ngayKG;
    private String ghiChu;
    private String maNV;
    private String ngayTao;

    public KhoaHoc() {
    }

    public KhoaHoc(String maCD, int thoiLuong, String maNV, Double hocPhi) {
        this();
        this.maCD = maCD;
        this.thoiLuong = thoiLuong;
        this.maNV = maNV;
        this.hocPhi = hocPhi;
    }

    public KhoaHoc(int maKH, String maCD, Double hocPhi, int thoiLuong, String ngayKG, String ghiChu, String maNV, String ngayTao) {
        this.maKH = maKH;
        this.maCD = maCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.ngayKG = ngayKG;
        this.ghiChu = ghiChu;
        this.maNV = maNV;
        this.ngayTao = ngayTao;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getNgayKG() {
        return ngayKG;
    }

    public void setNgayKG(String ngayKG) {
        this.ngayKG = ngayKG;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public String toString() {
        return maCD + maKH + " (" + ngayTao + ")";
    }
    
    


}

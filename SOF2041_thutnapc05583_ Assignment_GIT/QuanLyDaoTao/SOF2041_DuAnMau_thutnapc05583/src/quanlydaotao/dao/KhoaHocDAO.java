/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlydaotao.dao;

import JDBCHelper.JDBCHelper;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import quanlydaotao.entity.HocVien;
import quanlydaotao.entity.KhoaHoc;
import quanlydaotao.utils.XDate;

/**
 *
 * @author Anh Thu
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    String INSERT_SQL = "SET IDENTITY_INSERT KHOAHOC ON INSERT INTO KHOAHOC(MaKH,MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao)Values(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG= ?,GhiChu = ?,MaNV=?, NgayTao =? WHERE MaKH=?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";

    @Override
    public void insert(KhoaHoc enity) {
        System.out.println(enity.getMaCD() + "---" + enity.getHocPhi() + "\n" + enity.getThoiLuong() + "\n" + enity.getNgayKG()
                + "\n" + enity.getGhiChu() + "\n" + enity.getMaNV() + "\n" + enity.getNgayTao() + "\n" + enity.getMaKH());
        JDBCHelper.update(INSERT_SQL, enity.getMaKH(), enity.getMaCD(), enity.getHocPhi(), enity.getThoiLuong(), enity.getNgayKG(), enity.getGhiChu(), enity.getMaNV(), enity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc enity) {
        System.out.println(enity.getMaCD() + "---" + enity.getHocPhi() + "\n" + enity.getThoiLuong() + "\n" + enity.getNgayKG()
                + "\n" + enity.getGhiChu() + "\n" + enity.getMaNV() + "\n" + enity.getNgayTao() + "\n" + enity.getMaKH());
        JDBCHelper.update(UPDATE_SQL, enity.getMaCD(), enity.getHocPhi(), enity.getThoiLuong(), enity.getNgayKG(), enity.getGhiChu(), enity.getMaNV(), enity.getNgayTao(), enity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc enity = new KhoaHoc();
                enity.setMaKH(rs.getInt("MaKH"));
                enity.setMaCD(rs.getString("MaCD"));
                enity.setHocPhi(rs.getDouble("HocPhi"));
                enity.setThoiLuong(rs.getInt("ThoiLuong"));
                enity.setNgayKG(rs.getString("NgayKG"));
                enity.setGhiChu(rs.getString("GhiChu"));
                enity.setMaNV(rs.getString("MaNV"));
                enity.setNgayTao(rs.getString("NgayTao"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getLastID() {
        String sql = " select max(MaKH) from KHOAHOC";
        int lastID = 999;
        try {
            ResultSet rs = JDBCHelper.query(sql);
            if (rs.next()) {
                lastID = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
        }
        return lastID;
    }

    public boolean contains(int IDKH) {
        String sql = "select * from KHOAHOC where MaKH like " + IDKH;
        int ID = -1;

        try {
            ResultSet rs = JDBCHelper.query(sql);
            if (rs.next()) {
                ID = rs.getInt(1);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
        }
        return ID != -1;
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<KhoaHoc> selectByChuyenDe(String maCD) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
        return this.selectBySql(sql, maCD);
    }

    public List<Integer> selectYear() {
        String SQL = "SELECT DISTINCT year(NgayKG) FROM KhoaHoc ORDER BY year(NgayKG) DESC";
        List<Integer> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

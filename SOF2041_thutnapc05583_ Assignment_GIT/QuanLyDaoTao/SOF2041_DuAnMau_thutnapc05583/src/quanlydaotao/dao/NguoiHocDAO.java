/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlydaotao.dao;

import JDBCHelper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import quanlydaotao.entity.HocVien;
import quanlydaotao.entity.NguoiHoc;
import quanlydaotao.utils.Auth;
import quanlydaotao.utils.XDate;

/**
 *
 * @author Anh Thu
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    String INSERT_SQL = "INSERT INTO NguoiHoc (MaNH,HoTen,GioiTinh,NgaySinh,DienThoai,EMail,GhiChu,NgayDK,MaNV) VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen=?,NgaySinh=?,GioiTinh=?,DienThoai=?,EMail=?,GhiChu=?,NgayDK=? WHERE MaNH=?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH=?";

    @Override
    public void insert(NguoiHoc enity) {
//        System.out.println("insert\n"+enity.getMaNH()+"\n"+ enity.getHoTen()+"\n"+ enity.getGioiTinh()+"\n"+ enity.getNgaySinh()+"\n"+ enity.getDienThoai()+"\n"+ enity.getEmail()+"\n"+ enity.getGhiChu()+"\n"+Auth.user.getMaNV());

        JDBCHelper.update(INSERT_SQL, enity.getMaNH(), enity.getHoTen(), enity.getGioiTinh(), XDate.toDate(enity.getNgaySinh(), "yyyy-mm-dd"), enity.getDienThoai(), enity.getEmail(), enity.getGhiChu(), enity.getNgayDK(),Auth.user.getMaNV());
    }

    @Override
    public void update(NguoiHoc enity) {
        JDBCHelper.update(UPDATE_SQL, enity.getHoTen(), enity.getNgaySinh(), enity.getGioiTinh(), enity.getDienThoai(), enity.getEmail(), enity.getGhiChu(), enity.getNgayDK(), enity.getMaNH());
    }

    @Override
    public void delete(String id) {
                JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc enity = new NguoiHoc();
                enity.setMaNH(rs.getString("MaNH"));
                enity.setHoTen(rs.getString("HoTen"));
                enity.setGioiTinh(rs.getBoolean("GioiTinh"));
                enity.setDienThoai(rs.getString("DienThoai"));
                enity.setNgaySinh(rs.getString("NgaySinh"));
                enity.setEmail(rs.getString("EMail"));
                enity.setGhiChu(rs.getString("GhiChu"));
                enity.setNgayDK(rs.getString("NgayDK"));
                enity.setMaNV(rs.getString("MaNV"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = "SELECT * FROM NguoiHoc "
                + "WHERE HoTen LIKE ? AND "
                + " MANH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }


    
    
}

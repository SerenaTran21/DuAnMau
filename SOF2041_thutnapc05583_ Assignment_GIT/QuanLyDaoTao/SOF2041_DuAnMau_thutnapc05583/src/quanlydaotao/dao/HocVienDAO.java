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
import quanlydaotao.entity.NhanVien;

/**
 *
 * @author Anh Thu
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    String INSERT_SQL = "INSERT INTO HOCVIEN (MaKH,MaNH,DIEM) VALUES(?,?,?)";
    String UPDATE_SQL = "UPDATE HOCVIEN SET DIEM=? WHERE MaHV=? ";
    String DELETE_SQL = "DELETE FROM HOCVIEN WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HOCVIEN WHERE MaHV=?";

    @Override
    public void insert(HocVien enity) {
        JDBCHelper.update(INSERT_SQL, enity.getMaKH(), enity.getMaNH(), enity.getDiem());
    }

    @Override
    public void update(HocVien enity) {
//        System.out.println(enity.getDiem()+"   _"+enity.getMaHV());

        JDBCHelper.update(UPDATE_SQL, enity.getDiem(), enity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HocVien enity = new HocVien();
                enity.setMaHV(rs.getInt("MaHV"));
                enity.setMaKH(rs.getInt("MaKH"));
                enity.setMaNH(rs.getString("MaNH"));
                enity.setDiem(rs.getFloat("Diem"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<HocVien> seletedByKhoaHoc(int maKH) {
        String sql = "SELECT * FROM HocVien WHERE MaKH = ?";
        System.out.println( this.selectBySql(sql, maKH));

        return this.selectBySql(sql, maKH);
    }
}

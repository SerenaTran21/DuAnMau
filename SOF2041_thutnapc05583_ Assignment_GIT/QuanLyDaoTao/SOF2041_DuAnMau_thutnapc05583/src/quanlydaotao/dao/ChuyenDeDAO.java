/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlydaotao.dao;

import JDBCHelper.JDBCHelper;
import java.util.*;
import java.sql.*;
import quanlydaotao.entity.ChuyenDe;
import quanlydaotao.giaodien.GiaoDienQuanLyChuyenDeJDialog;
import quanlydaotao.utils.MsgBox;

/**
 *
 * @author Anh Thu
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    String INSERT_SQL = "INSERT INTO CHUYENDE(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa)VALUES (?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE CHUYENDE SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh =?, MoTa = ? WHERE MaCD=?";
    String DELETE_SQL = "DELETE FROM CHUYENDE WHERE MaCD = ?";
    String SELECT_ALL_SQL = "SELECT * FROM CHUYENDE";
    String SELECT_BY_ID_SQL = "SELECT * FROM CHUYENDE WHERE MaCD = ?";

    @Override
    public void insert(ChuyenDe entity) {
        JDBCHelper.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);

    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

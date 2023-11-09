/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBCHelper;

import java.sql.*;

/**
 *
 * @author Anh Thu
 */
public class JDBCHelper {

    public static Connection getDBConnection() {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=EduSys;user=sa;password=123;encrypt=true;trustServerCertificate=true;";
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connection success...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = getDBConnection();
        PreparedStatement stmt;
        if (sql.trim().startsWith("{")) {
            stmt = conn.prepareCall(sql);
        } else {
            stmt = conn.prepareCall(sql);
        }
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement stmt = JDBCHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement stmt = JDBCHelper.getStmt(sql, args);

            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

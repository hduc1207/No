package com.pethotel.dao;

import com.pethotel.dto.CageDTO;
import com.pethotel.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CageDAO {

    // 1. Lấy tất cả chuồng
    public List<CageDTO> getAllCages() {
        List<CageDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Cages";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                CageDTO cage = new CageDTO();
                cage.setCageId(rs.getInt("CageID"));
                cage.setCageName(rs.getString("CageName"));
                cage.setType(rs.getString("Type"));
                cage.setPricePerDay(rs.getDouble("PricePerDay"));
                cage.setStatus(rs.getString("Status"));

                list.add(cage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Lấy chuồng theo ID
    public CageDTO getCageById(int cageId) {
        String sql = "SELECT * FROM Cages WHERE CageID = ?";
        CageDTO cage = null;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, cageId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cage = new CageDTO();
                cage.setCageId(rs.getInt("CageID"));
                cage.setCageName(rs.getString("CageName"));
                cage.setType(rs.getString("Type"));
                cage.setPricePerDay(rs.getDouble("PricePerDay"));
                cage.setStatus(rs.getString("Status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cage;
    }

    // 3. Thêm chuồng mới
    public boolean insertCage(CageDTO cage) {
        String sql = """
            INSERT INTO Cages (CageName, Type, PricePerDay, Status)
            VALUES (?, ?, ?, ?)
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, cage.getCageName());
            ps.setString(2, cage.getType());
            ps.setDouble(3, cage.getPricePerDay());
            ps.setString(4, cage.getStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Cập nhật chuồng
    public boolean updateCage(CageDTO cage) {
        String sql = """
            UPDATE Cages SET
                CageName = ?,
                Type = ?,
                PricePerDay = ?,
                Status = ?
            WHERE CageID = ?
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, cage.getCageName());
            ps.setString(2, cage.getType());
            ps.setDouble(3, cage.getPricePerDay());
            ps.setString(4, cage.getStatus());
            ps.setInt(5, cage.getCageId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Xóa chuồng
    public boolean deleteCage(int cageId) {
        String sql = "DELETE FROM Cages WHERE CageID = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, cageId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. Lấy danh sách chuồng trống (rất hay dùng)
    public List<CageDTO> getAvailableCages() {
        List<CageDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Cages WHERE Status = 'Trong'";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                CageDTO cage = new CageDTO();
                cage.setCageId(rs.getInt("CageID"));
                cage.setCageName(rs.getString("CageName"));
                cage.setType(rs.getString("Type"));
                cage.setPricePerDay(rs.getDouble("PricePerDay"));
                cage.setStatus(rs.getString("Status"));

                list.add(cage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

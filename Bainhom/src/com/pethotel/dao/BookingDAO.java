package com.pethotel.dao;

import com.pethotel.dto.BookingDTO;
import com.pethotel.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // 1. Lấy tất cả Booking
    public List<BookingDTO> getAllBookings() {
        List<BookingDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM Bookings";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setCustomerId(rs.getInt("CustomerID"));
                booking.setPetId(rs.getInt("PetID"));
                booking.setCageId(rs.getInt("CageID"));
                booking.setCheckInDate(rs.getTimestamp("CheckInDate"));
                booking.setCheckOutDate(rs.getTimestamp("CheckOutDate"));
                booking.setStatus(rs.getString("Status"));
                booking.setPaymentStatus(rs.getString("PaymentStatus"));
                booking.setTotalPrice(rs.getDouble("TotalPrice"));
                booking.setCreatedDate(rs.getTimestamp("CreatedDate"));

                list.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Thêm Booking
    public boolean insertBooking(BookingDTO b) {
        String sql = """
            INSERT INTO Bookings(
                CustomerID, PetID, CageID,
                CheckInDate, CheckOutDate,
                Status, PaymentStatus, TotalPrice
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, b.getCustomerId());
            ps.setInt(2, b.getPetId());
            ps.setInt(3, b.getCageId());
            ps.setTimestamp(4, b.getCheckInDate());

            if (b.getCheckOutDate() != null) {
                ps.setTimestamp(5, b.getCheckOutDate());
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            ps.setString(6, b.getStatus());
            ps.setString(7, b.getPaymentStatus());
            ps.setDouble(8, b.getTotalPrice());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Cập nhật Booking
    public boolean updateBooking(BookingDTO b) {
        String sql = """
            UPDATE Bookings SET
                CustomerID = ?, PetID = ?, CageID = ?,
                CheckInDate = ?, CheckOutDate = ?,
                Status = ?, PaymentStatus = ?, TotalPrice = ?
            WHERE BookingID = ?
        """;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, b.getCustomerId());
            ps.setInt(2, b.getPetId());
            ps.setInt(3, b.getCageId());
            ps.setTimestamp(4, b.getCheckInDate());

            if (b.getCheckOutDate() != null) {
                ps.setTimestamp(5, b.getCheckOutDate());
            } else {
                ps.setNull(5, Types.TIMESTAMP);
            }

            ps.setString(6, b.getStatus());
            ps.setString(7, b.getPaymentStatus());
            ps.setDouble(8, b.getTotalPrice());
            ps.setInt(9, b.getBookingId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Xóa Booking
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM Bookings WHERE BookingID = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, bookingId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5. Lấy Booking theo ID (khuyên nên có)
    public BookingDTO getBookingById(int bookingId) {
        String sql = "SELECT * FROM Bookings WHERE BookingID = ?";
        BookingDTO booking = null;

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                booking = new BookingDTO();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setCustomerId(rs.getInt("CustomerID"));
                booking.setPetId(rs.getInt("PetID"));
                booking.setCageId(rs.getInt("CageID"));
                booking.setCheckInDate(rs.getTimestamp("CheckInDate"));
                booking.setCheckOutDate(rs.getTimestamp("CheckOutDate"));
                booking.setStatus(rs.getString("Status"));
                booking.setPaymentStatus(rs.getString("PaymentStatus"));
                booking.setTotalPrice(rs.getDouble("TotalPrice"));
                booking.setCreatedDate(rs.getTimestamp("CreatedDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
}

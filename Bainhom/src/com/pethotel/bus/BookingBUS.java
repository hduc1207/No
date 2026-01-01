package com.pethotel.bus;

import com.pethotel.dao.BookingDAO;
import com.pethotel.dto.BookingDTO;

import java.util.List;

public class BookingBUS {
    private BookingDAO bookingDAO;

    public BookingBUS() {
        this.bookingDAO = new BookingDAO();
    }

    public List<BookingDTO> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public String addBooking(BookingDTO booking) {
        if (booking.getCheckInDate() == null) {
            return "Ngày Check-in không được để trống!";
        }
        if (booking.getCheckOutDate() != null && booking.getCheckOutDate().before(booking.getCheckInDate())) {
            return "Ngày Check-out phải sau ngày Check-in!";
        }
        if (bookingDAO.insertBooking(booking)) {
            return "Thêm Booking thành công!";
        }
        return "Thêm Booking thất bại!";
    }

    public String updateBooking(BookingDTO booking) {
        if (bookingDAO.updateBooking(booking)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    public String deleteBooking(int bookingId) {
        if (bookingDAO.deleteBooking(bookingId)) {
            return "Xóa thành công!";
        }
        return "Xóa thất bại!";
    }

    // Hàm tìm kiếm hoặc lọc nếu cần sau này
    public BookingDTO getBookingById(int id) {
        return bookingDAO.getBookingById(id);
    }
}
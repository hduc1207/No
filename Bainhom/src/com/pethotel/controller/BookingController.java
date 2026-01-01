package com.pethotel.controller;

import com.pethotel.bus.BookingBUS;
import com.pethotel.dto.BookingDTO;
import com.pethotel.gui.quanlybooking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingController {
    private quanlybooking view;
    private BookingBUS bus;
    private DefaultTableModel tableModel;

    public BookingController(quanlybooking view) {
        this.view = view;
        this.bus = new BookingBUS();

        // Khởi tạo bảng và các sự kiện
        initTable();
        loadDataToTable();
        initEventHandlers();

        // TODO: Bạn cần load dữ liệu cho các ComboBox (Customer, Pet, Cage, Service) từ các BUS tương ứng tại đây.
        // loadComboBoxData();
    }

    private void initTable() {
        String[] headers = {"ID", "Khách hàng", "Thú cưng", "Chuồng", "Check-in", "Check-out", "Trạng thái", "Tổng tiền"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTable1().setModel(tableModel);
    }

    private void loadDataToTable() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        List<BookingDTO> list = bus.getAllBookings();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (BookingDTO b : list) {
            Object[] row = {
                    b.getBookingId(),
                    b.getCustomerId(), // Nên thay bằng tên khách hàng nếu có CustomerBUS
                    b.getPetId(),      // Nên thay bằng tên thú cưng
                    b.getCageId(),
                    b.getCheckInDate() != null ? sdf.format(b.getCheckInDate()) : "",
                    b.getCheckOutDate() != null ? sdf.format(b.getCheckOutDate()) : "",
                    b.getStatus(),
                    b.getTotalPrice()
            };
            tableModel.addRow(row);
        }
    }

    private void initEventHandlers() {
        // Nút Thêm
        view.getThêmButton().addActionListener(e -> {
            BookingDTO dto = getBookingFromForm();
            if (dto != null) {
                String result = bus.addBooking(dto);
                JOptionPane.showMessageDialog(view.getMainPanel(), result);
                loadDataToTable();
            }
        });

        // Nút Sửa
        view.getSửaButton().addActionListener(e -> {
            int selectedRow = view.getTable1().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view.getMainPanel(), "Vui lòng chọn dòng để sửa!");
                return;
            }
            BookingDTO dto = getBookingFromForm();
            if (dto != null) {
                int id = (int) view.getTable1().getValueAt(selectedRow, 0);
                dto.setBookingId(id);
                String result = bus.updateBooking(dto);
                JOptionPane.showMessageDialog(view.getMainPanel(), result);
                loadDataToTable();
            }
        });

        // Nút Xóa
        view.getXóaButton().addActionListener(e -> {
            int selectedRow = view.getTable1().getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) view.getTable1().getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(view.getMainPanel(), "Bạn có chắc muốn xóa?");
                if (confirm == JOptionPane.YES_OPTION) {
                    String result = bus.deleteBooking(id);
                    JOptionPane.showMessageDialog(view.getMainPanel(), result);
                    loadDataToTable();
                }
            } else {
                JOptionPane.showMessageDialog(view.getMainPanel(), "Vui lòng chọn dòng để xóa!");
            }
        });

        // Sự kiện click chuột vào bảng (Mouse Listener)
        view.getTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable1().getSelectedRow();
                if (row >= 0) {
                    // Đổ dữ liệu từ bảng ngược lại form
                    // Lưu ý: ComboBox cần setSelectedItem dựa trên ID
                    view.getCboUserID().setSelectedItem(tableModel.getValueAt(row, 1).toString());
                    view.getCboPetID().setSelectedItem(tableModel.getValueAt(row, 2).toString());
                    view.getCboCageID().setSelectedItem(tableModel.getValueAt(row, 3).toString());
                    view.getTxtCheckinDate().setText(tableModel.getValueAt(row, 4).toString());
                    view.getTxtCheckOutDate().setText(tableModel.getValueAt(row, 5).toString());
                    view.getCboStatus().setSelectedItem(tableModel.getValueAt(row, 6).toString());
                    view.getTotalPrice().setText(tableModel.getValueAt(row, 7).toString());
                }
            }
        });

        // Listener cho nút thoát
        view.getBtnExit().addActionListener(e -> System.exit(0));
    }

    private BookingDTO getBookingFromForm() {
        try {
            BookingDTO dto = new BookingDTO();
            // Giả sử ComboBox chứa item là String ID hoặc Object có override toString
            // Bạn cần ép kiểu cho đúng với dữ liệu thực tế trong ComboBox
            dto.setCustomerId(Integer.parseInt(view.getCboUserID().getSelectedItem().toString()));
            dto.setPetId(Integer.parseInt(view.getCboPetID().getSelectedItem().toString()));
            dto.setCageId(Integer.parseInt(view.getCboCageID().getSelectedItem().toString()));

            dto.setCheckInDate(Timestamp.valueOf(view.getTxtCheckinDate().getText())); // Format: yyyy-MM-dd HH:mm:ss
            if (!view.getTxtCheckOutDate().getText().isEmpty()) {
                dto.setCheckOutDate(Timestamp.valueOf(view.getTxtCheckOutDate().getText()));
            }

            dto.setStatus(view.getCboStatus().getSelectedItem().toString());
            dto.setTotalPrice(Double.parseDouble(view.getTotalPrice().getText()));
            // Mặc định PaymentStatus có thể set là "Pending" hoặc lấy từ 1 cbo khác
            dto.setPaymentStatus("Pending");

            return dto;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Lỗi dữ liệu nhập: " + ex.getMessage());
            return null;
        }
    }
}
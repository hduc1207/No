package com.pethotel.controller;

import com.pethotel.bus.BookingBUS;
// Import các BUS khác để lấy dữ liệu cho ComboBox
// import com.pethotel.bus.CustomerBUS;
// import com.pethotel.bus.PetBUS;
// import com.pethotel.bus.CageBUS;
import com.pethotel.dto.BookingDTO;
import com.pethotel.gui.quanlybooking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class BookingController {
    private quanlybooking view;
    private BookingBUS bus;
    private DefaultTableModel tableModel;

    // Khai báo các BUS phụ
    // private CustomerBUS customerBUS;
    // private PetBUS petBUS;
    // private CageBUS cageBUS;

    public BookingController(quanlybooking view) {
        this.view = view;
        this.bus = new BookingBUS();
        // this.customerBUS = new CustomerBUS();
        // this.petBUS = new PetBUS();
        // this.cageBUS = new CageBUS();

        initTable();
        loadComboBoxData(); // <--- Đã thêm hàm này
        loadDataToTable();
        initEventHandlers();
    }

    private void initTable() {
        // Code cũ của bạn giữ nguyên
        String[] headers = {"ID", "Khách hàng", "Thú cưng", "Chuồng", "Check-in", "Check-out", "Trạng thái", "Tổng tiền"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTable1().setModel(tableModel);
    }

    // --- PHẦN BỔ SUNG QUAN TRỌNG ---
    private void loadComboBoxData() {
        // 1. Load Trạng thái (Hardcode vì ít thay đổi)
        view.getCboStatus().removeAllItems();
        view.getCboStatus().addItem("Pending");
        view.getCboStatus().addItem("Confirmed");
        view.getCboStatus().addItem("Checked-in");
        view.getCboStatus().addItem("Checked-out");
        view.getCboStatus().addItem("Cancelled");

        // 2. Load Customer, Pet, Cage (Ví dụ mẫu, bạn cần bỏ comment và dùng BUS thật)
        /*
        view.getCboUserID().removeAllItems();
        List<CustomerDTO> customers = customerBUS.getAllCustomers();
        for (CustomerDTO c : customers) {
            // Nên tạo class Wrapper hoặc override toString trong DTO để hiển thị tên nhưng lấy được ID
            view.getCboUserID().addItem(c.getCustomerId() + " - " + c.getFullName());
        }

        // Tương tự cho Pet và Cage...
        */
    }

    private void loadDataToTable() {
        // Code cũ của bạn giữ nguyên
        tableModel.setRowCount(0);
        List<BookingDTO> list = bus.getAllBookings();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (BookingDTO b : list) {
            Object[] row = {
                    b.getBookingId(),
                    b.getCustomerId(),
                    b.getPetId(),
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
        // Code Listener cũ của bạn rất tốt, giữ nguyên logic

        // Nút Thêm
        view.getThêmButton().addActionListener(e -> {
            BookingDTO dto = getBookingFromForm();
            if (dto != null) {
                String result = bus.addBooking(dto);
                JOptionPane.showMessageDialog(view.getMainPanel(), result);
                loadDataToTable();
            }
        });

        // Nút Sửa, Xóa... (Giữ nguyên như file cũ)
        // ...

        // Listener click bảng
        view.getTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable1().getSelectedRow();
                if (row >= 0) {
                    // Logic fill ngược lại form
                    // Lưu ý: Nếu ComboBox chứa String "ID - Name", bạn cần xử lý cắt chuỗi để set lại cho đúng
                    view.getTxtCheckinDate().setText(tableModel.getValueAt(row, 4).toString());
                    // ... các trường khác
                }
            }
        });
    }

    private BookingDTO getBookingFromForm() {
        // Cần xử lý cẩn thận việc lấy ID từ ComboBox
        try {
            BookingDTO dto = new BookingDTO();

            // Nếu ComboBox chỉ chứa ID (String):
            // dto.setCustomerId(Integer.parseInt(view.getCboUserID().getSelectedItem().toString()));

            // Nếu ComboBox chứa "ID - Tên", bạn cần tách chuỗi:
            // String selectedCus = view.getCboUserID().getSelectedItem().toString();
            // int cusId = Integer.parseInt(selectedCus.split(" - ")[0]);
            // dto.setCustomerId(cusId);

            // Tạm thời giữ logic cũ của bạn nhưng hãy cẩn thận format dữ liệu:
            if(view.getCboUserID().getSelectedItem() != null)
                dto.setCustomerId(Integer.parseInt(view.getCboUserID().getSelectedItem().toString()));
            if(view.getCboPetID().getSelectedItem() != null)
                dto.setPetId(Integer.parseInt(view.getCboPetID().getSelectedItem().toString()));
            if(view.getCboCageID().getSelectedItem() != null)
                dto.setCageId(Integer.parseInt(view.getCboCageID().getSelectedItem().toString()));

            dto.setCheckInDate(Timestamp.valueOf(view.getTxtCheckinDate().getText()));

            if (!view.getTxtCheckOutDate().getText().isEmpty()) {
                dto.setCheckOutDate(Timestamp.valueOf(view.getTxtCheckOutDate().getText()));
            }

            dto.setStatus(view.getCboStatus().getSelectedItem().toString());
            dto.setTotalPrice(Double.parseDouble(view.getTotalPrice().getText()));
            dto.setPaymentStatus("Pending");

            return dto;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Lỗi dữ liệu nhập: " + ex.getMessage());
            return null;
        }
    }
}
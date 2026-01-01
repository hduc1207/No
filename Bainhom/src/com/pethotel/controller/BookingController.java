package com.pethotel.controller;

import com.pethotel.bus.BookingBUS;
import com.pethotel.bus.CustomerBUS;
import com.pethotel.bus.PetBUS;
import com.pethotel.bus.CageBUS;
import com.pethotel.dto.CustomerDTO;
import com.pethotel.dto.PetDTO;
import com.pethotel.dto.CageDTO;

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
    private CustomerBUS customerBUS;
    private PetBUS petBUS;
    private CageBUS cageBUS;

    public BookingController(quanlybooking view) {
        this.view = view;
        this.bus = new BookingBUS();
        this.customerBUS = new CustomerBUS();
        this.petBUS = new PetBUS();
        this.cageBUS = new CageBUS();

        initTable();
        loadComboBoxData();
        loadDataToTable();
        initEventHandlers();
    }

    private void initTable() {
        String[] headers = {"ID", "Khách hàng", "Thú cưng", "Chuồng", "Check-in", "Check-out", "Trạng thái", "Tổng tiền"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTable1().setModel(tableModel);
    }

    private void loadComboBoxData() {
        // 1. Load Trạng thái
        view.getCboStatus().removeAllItems();
        String[] statuses = {"Pending", "Confirmed", "Checked-in", "Checked-out", "Cancelled"};
        for (String s : statuses) view.getCboStatus().addItem(s);

        // 2. Load Customer (Ví dụ mẫu - Bỏ comment để dùng)
        view.getCboUserID().removeAllItems();
        List<CustomerDTO> customers = customerBUS.getAllCustomers();
        for (CustomerDTO c : customers) {
            // Hiển thị dạng "1 - Nguyễn Văn A" để dễ nhìn
            view.getCboUserID().addItem(c.getCustomerId() + " - " + c.getFullName());
        }

        // 3. Load Pet

        view.getCboPetID().removeAllItems();
        List<PetDTO> pets = petBUS.getAllPets();
        for (PetDTO p : pets) {
            view.getCboPetID().addItem(p.getPetId() + " - " + p.getPetName());
        }


        // 4. Load Cage
        view.getCboCageID().removeAllItems();
        List<CageDTO> cages = cageBUS.getAllCages(); // Hoặc getAvailableCages()
        for (CageDTO c : cages) {
            view.getCboCageID().addItem(c.getCageId() + " - " + c.getCageName());
        }
    }

    private void loadDataToTable() {
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
        // Nút Thêm
        view.getbtnAdd().addActionListener(e -> {
            BookingDTO dto = getBookingFromForm();
            if (dto != null) {
                String result = bus.addBooking(dto);
                JOptionPane.showMessageDialog(view.getMainPanel(), result);
                if (result.contains("thành công")) loadDataToTable();
            }
        });

        // Nút Sửa
        view.getbtnEdit().addActionListener(e -> {
            int selectedRow = view.getTable1().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view.getMainPanel(), "Vui lòng chọn dòng để sửa!");
                return;
            }
            BookingDTO dto = getBookingFromForm();
            if (dto != null) {
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                dto.setBookingId(id);
                String result = bus.updateBooking(dto);
                JOptionPane.showMessageDialog(view.getMainPanel(), result);
                loadDataToTable();
            }
        });

        // Nút Xóa
        view.getbtnDelete().addActionListener(e -> {
            int selectedRow = view.getTable1().getSelectedRow();
            if (selectedRow != -1) {
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                int confirm = JOptionPane.showConfirmDialog(view.getMainPanel(), "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String result = bus.deleteBooking(id);
                    JOptionPane.showMessageDialog(view.getMainPanel(), result);
                    loadDataToTable();
                }
            } else {
                JOptionPane.showMessageDialog(view.getMainPanel(), "Vui lòng chọn dòng để xóa!");
            }
        });

        // Click bảng -> Đổ dữ liệu lên form
        view.getTable1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable1().getSelectedRow();
                if (row >= 0) {
                    // Xử lý set ComboBox thông minh (nếu combo chứa text dạng "ID - Name")
                    setSelectedComboBoxItem(view.getCboUserID(), tableModel.getValueAt(row, 1).toString());
                    setSelectedComboBoxItem(view.getCboPetID(), tableModel.getValueAt(row, 2).toString());
                    setSelectedComboBoxItem(view.getCboCageID(), tableModel.getValueAt(row, 3).toString());

                    view.getTxtCheckinDate().setText(tableModel.getValueAt(row, 4).toString());
                    view.getTxtCheckOutDate().setText(tableModel.getValueAt(row, 5).toString());
                    view.getCboStatus().setSelectedItem(tableModel.getValueAt(row, 6).toString());
                    view.getTotalPrice().setText(tableModel.getValueAt(row, 7).toString());
                }
            }
        });

        // Nút thoát
        view.getBtnExit().addActionListener(e -> System.exit(0));
        com.pethotel.utils.NavigationHelper.attachMenuEvents(
                null, // Nút Booking (đang ở đây rồi thì null hoặc disable)
                view.getBtnMenuPet(),
                view.getbtnMenuCage(),
                view.getBtnMenuService(),
                view.getBtnMenuCustomer(),
                view.getBtnMenuAccount(), // Nếu có
                view.getMainPanel()
        );
    }

    // Hàm hỗ trợ chọn item trong ComboBox dựa trên ID (prefix)
    private void setSelectedComboBoxItem(JComboBox cbo, String idValue) {
        for (int i = 0; i < cbo.getItemCount(); i++) {
            String item = cbo.getItemAt(i).toString();
            if (item.startsWith(idValue + " -") || item.equals(idValue)) {
                cbo.setSelectedIndex(i);
                return;
            }
        }
    }

    private BookingDTO getBookingFromForm() {
        try {
            BookingDTO dto = new BookingDTO();

            // XỬ LÝ ID TỪ COMBOBOX (Dạng "123 - Tên") -> Lấy "123"
            if (view.getCboUserID().getSelectedItem() != null) {
                String s = view.getCboUserID().getSelectedItem().toString();
                dto.setCustomerId(Integer.parseInt(s.split(" - ")[0]));
            }

            if (view.getCboPetID().getSelectedItem() != null) {
                String s = view.getCboPetID().getSelectedItem().toString();
                dto.setPetId(Integer.parseInt(s.split(" - ")[0]));
            }

            if (view.getCboCageID().getSelectedItem() != null) {
                String s = view.getCboCageID().getSelectedItem().toString();
                dto.setCageId(Integer.parseInt(s.split(" - ")[0]));
            }

            // Ngày tháng
            dto.setCheckInDate(Timestamp.valueOf(view.getTxtCheckinDate().getText()));
            if (!view.getTxtCheckOutDate().getText().isEmpty()) {
                dto.setCheckOutDate(Timestamp.valueOf(view.getTxtCheckOutDate().getText()));
            }

            dto.setStatus(view.getCboStatus().getSelectedItem().toString());
            dto.setTotalPrice(Double.parseDouble(view.getTotalPrice().getText()));
            dto.setPaymentStatus("Pending"); // Mặc định

            return dto;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Lỗi nhập liệu (Định dạng ngày yyyy-MM-dd HH:mm:ss): " + ex.getMessage());
            return null;
        }
    }
}
package com.pethotel.gui;

import com.pethotel.controller.BookingController;
import javax.swing.*;

public class quanlybooking {
    private JButton btnMenuPet;
    private JButton btnMenuCage;
    private JButton btnMenuService;
    private JButton btnMenuBooking;
    private JComboBox cboUserID;
    private JComboBox cboPetID;
    private JComboBox cboCageID;
    private JTextField txtCheckinDate;
    private JTextField txtCheckOutDate;
    private JTextArea TotalPrice;
    private JComboBox cboStatus;
    private JComboBox cboServiceID;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnEdit;
    private JTable table1;
    private JButton btnAccount;
    private JButton btnExit;
    private JPanel MainPanel;
    private JButton btnMenuCustomer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Tạo khung cửa sổ
            JFrame frame = new JFrame("Quản Lý Đặt Lịch (Booking)");

            // 2. Tạo giao diện
            quanlybooking view = new quanlybooking();

            // 3. Đưa Panel chính vào khung
            frame.setContentPane(view.getMainPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600); // Set kích thước mặc định
            frame.setLocationRelativeTo(null); // Căn giữa màn hình

            // 4. QUAN TRỌNG: Kích hoạt Controller để xử lý sự kiện
            new BookingController(view);

            // 5. Hiển thị
            frame.setVisible(true);
        });
    }

    public JButton getBtnMenuPet() {
        return btnMenuPet;
    }

    public JButton getbtnMenuCage() {
        return btnMenuCage;
    }

    public JButton getBtnMenuService() {
        return btnMenuService;
    }

    public JButton getBtnMenuBooking() {
        return btnMenuBooking;
    }

    public JComboBox getCboUserID() {
        return cboUserID;
    }

    public JComboBox getCboPetID() {
        return cboPetID;
    }

    public JComboBox getCboCageID() {
        return cboCageID;
    }

    public JTextField getTxtCheckinDate() {
        return txtCheckinDate;
    }

    public JTextField getTxtCheckOutDate() {
        return txtCheckOutDate;
    }

    public JTextArea getTotalPrice() {
        return TotalPrice;
    }

    public JComboBox getCboStatus() {
        return cboStatus;
    }

    public JComboBox getCboServiceID() {
        return cboServiceID;
    }

    public JButton getbtnAdd() {
        return btnAdd;
    }

    public JButton getbtnDelete() {
        return btnDelete;
    }

    public JButton getbtnEdit() {
        return btnEdit;
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getBtnAccount() {
        return btnAccount;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private void createUIComponents() {
    }

    public JButton getBtnMenuCustomer() {
        return btnMenuCustomer;
    }

    public JButton getBtnMenuCage() {
        return btnMenuCage;
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnMenuAccount() {
        return null;
    }
}

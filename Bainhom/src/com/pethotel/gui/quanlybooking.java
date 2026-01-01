package com.pethotel.gui;

import javax.swing.*;

public class quanlybooking {
    private JButton btnMenuPet;
    private JButton quảnLýChuồngButton;
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
    private JButton thêmButton;
    private JButton xóaButton;
    private JButton sửaButton;
    private JTable table1;
    private JButton btnAccount;
    private JButton btnExit;
    private JPanel MainPanel;
    public static void main(String[] args) {
        // Chạy giao diện
        SwingUtilities.invokeLater(() -> new quanlybooking());
    }

    public JButton getBtnMenuPet() {
        return btnMenuPet;
    }

    public JButton getQuảnLýChuồngButton() {
        return quảnLýChuồngButton;
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

    public JButton getThêmButton() {
        return thêmButton;
    }

    public JButton getXóaButton() {
        return xóaButton;
    }

    public JButton getSửaButton() {
        return sửaButton;
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
}

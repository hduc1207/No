package com.pethotel.gui;

import com.pethotel.controller.BookingController;
import javax.swing.*;

public class quanlybooking extends javax.swing.JFrame {
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
    private JButton btnMenuAccount;
    private JButton btnExit;
    private JPanel MainPanel;
    private JButton btnMenuCustomer;

    public JButton getBtnMenuPet() {
        return btnMenuPet;
    }

    public JButton getBtnMenuCage() {
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

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JTable getTable1() {
        return table1;
    }

    public JButton getBtnMenuAccount() {
        return btnMenuAccount;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public JButton getBtnMenuCustomer() {
        return btnMenuCustomer;
    }
}

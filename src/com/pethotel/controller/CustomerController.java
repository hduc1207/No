package com.pethotel.controller;

import com.pethotel.bus.CustomerBUS;
import com.pethotel.dto.CustomerDTO;
import com.pethotel.gui.quanlykhachhang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerController {
    private quanlykhachhang view;
    private CustomerBUS bus;
    private DefaultTableModel tableModel;

    public CustomerController(quanlykhachhang view) {
        this.view = view;
        this.bus = new CustomerBUS();

        initTable();
        loadData();
        initEvents();
    }

    private void initTable() {
        String[] headers = {"ID", "Tên khách hàng", "SĐT", "Email", "Địa chỉ"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTblCustomer().setModel(tableModel); // Nhớ tạo Getter trong GUI
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<CustomerDTO> list = bus.getAllCustomers();
        for (CustomerDTO c : list) {
            tableModel.addRow(new Object[]{
                    c.getCustomerId(), c.getFullName(), c.getPhoneNumber(), c.getEmail(), c.getAddress()
            });
        }
    }

    private void initEvents() {
        // Sự kiện click bảng
        view.getTblCustomer().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblCustomer().getSelectedRow();
                if (row >= 0) {
                    view.getTxtCustomerName().setText(tableModel.getValueAt(row, 1).toString());
                    view.getTxtPhoneName().setText(tableModel.getValueAt(row, 2).toString());
                    view.getTxtEmail().setText(tableModel.getValueAt(row, 3).toString());
                    view.getTxtAddress().setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });
        view.getBtnAdd().addActionListener(e -> {
            CustomerDTO c = new CustomerDTO();
            c.setFullName(view.getTxtCustomerName().getText());
            c.setPhoneNumber(view.getTxtPhoneName().getText());
            c.setEmail(view.getTxtEmail().getText());
            c.setAddress(view.getTxtAddress().getText());
            JOptionPane.showMessageDialog(null, bus.addCustomer(c));
            loadData();
        });

        // Nút Thoát
        view.getBtnExit().addActionListener(e -> System.exit(0));
        // Thêm vào CustomerController.java
        com.pethotel.utils.NavigationHelper.attachMenuEvents(
                view.getBtnMenuBooking(), // 1. Booking
                view.getBtnMenuPet(),     // 2. Pet
                view.getBtnMenuCage(),    // 3. Cage
                view.getBtnMenuService(), // 4. Service
                null,                     // 5. Customer (null vì đang ở đây)
                view.getBtnMenuAccount(), // 6. User
                view.getMainPanel()
        );
    }
}
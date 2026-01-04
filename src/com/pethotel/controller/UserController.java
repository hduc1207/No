package com.pethotel.controller;

import com.pethotel.bus.UserBUS;
import com.pethotel.dto.UserDTO;
import com.pethotel.gui.Taikhoanuser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.pethotel.utils.StyleHelper;

public class UserController {
    private Taikhoanuser view;
    private UserBUS bus;
    private DefaultTableModel tableModel;

    public UserController(Taikhoanuser view) {
        this.view = view;
        this.bus = new UserBUS();

        initTable();
        loadRoleCombo();
        loadData();
        initEvents();
    }

    private void initTable() {
        String[] headers = {"ID", "Username", "Họ tên", "Vai trò"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTblAccount().setModel(tableModel);
    }

    private void loadRoleCombo() {
        view.getCboRole().removeAllItems();
        view.getCboRole().addItem("Admin");
        view.getCboRole().addItem("Staff");
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<UserDTO> list = bus.getAllUsers();
        for (UserDTO u : list) {
            tableModel.addRow(new Object[]{
                    u.getUserId(),
                    u.getUsername(),
                    u.getFullName(),
                    u.getRole()
            });
        }
    }

    private void initEvents() {
        // Nút Thêm
        view.getBtnAdd().addActionListener(e -> {
            UserDTO u = getUserFromForm();
            if (u != null) {
                JOptionPane.showMessageDialog(view.getMainPanel(), bus.addUser(u));
                loadData();
                clearForm();
            }
        });

        // Nút Sửa
        view.getBtnEdit().addActionListener(e -> {
            int row = view.getTblAccount().getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(view.getMainPanel(), "Chọn tài khoản cần sửa!");
                return;
            }
            UserDTO u = getUserFromForm();
            if (u != null) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                u.setUserId(id);
                JOptionPane.showMessageDialog(view.getMainPanel(), bus.updateUser(u));
                loadData();
                clearForm();
            }
        });

        // Nút Xóa
        view.getBtnDelete().addActionListener(e -> {
            int row = view.getTblAccount().getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                if (JOptionPane.showConfirmDialog(view.getMainPanel(), "Xóa tài khoản này?") == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(view.getMainPanel(), bus.deleteUser(id));
                    loadData();
                    clearForm();
                }
            }
        });

        // Click bảng
        view.getTblAccount().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblAccount().getSelectedRow();
                if (row >= 0) {
                    view.getTxtUserName().setText(tableModel.getValueAt(row, 1).toString());
                    // Mapping FullName vào txtPhoneNumber trên GUI
                    view.getTxtPhoneNumber().setText(tableModel.getValueAt(row, 2).toString());
                    view.getCboRole().setSelectedItem(tableModel.getValueAt(row, 3).toString());
                    // Không load password ngược lại field vì lý do bảo mật
                }
            }
        });

        view.getBtnExit().addActionListener(e -> System.exit(0));
        com.pethotel.utils.NavigationHelper.attachMenuEvents(
                view.getBtnMenuBooking(),
                view.getBtnMenuPet(),
                view.getBtnMenuCage(),
                view.getBtnMenuService(),
                view.getBtnMenuCustomer(),
                null,                     // Nút User (Đang ở đây -> null)
                view.getMainPanel()
        );
    }

    private UserDTO getUserFromForm() {
        UserDTO u = new UserDTO();
        u.setUsername(view.getTxtUserName().getText());
        u.setPassword(view.getTxtPassword().getText()); // Trong thực tế nên Hash password này
        u.setRole(view.getCboRole().getSelectedItem().toString());
        // Mapping txtPhoneNumber (GUI) -> FullName (DTO)
        u.setFullName(view.getTxtPhoneNumber().getText());

        return u;
    }

    private void clearForm() {
        view.getTxtUserName().setText("");
        view.getTxtPassword().setText("");
        view.getTxtPhoneNumber().setText("");
        view.getTblAccount().clearSelection();
    }
}
package com.pethotel.utils;

import com.pethotel.controller.*;
import com.pethotel.gui.*;

import javax.swing.*;

public class NavigationHelper {

    // Đã thêm tham số btnCustomer vào vị trí thứ 5
    public static void attachMenuEvents(JButton btnBooking, JButton btnPet, JButton btnCage,
                                        JButton btnService, JButton btnCustomer, JButton btnUser,
                                        JComponent currentPanel) {

        // Lấy JFrame chứa panel hiện tại
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);

        if (btnBooking != null) {
            btnBooking.addActionListener(e -> {
                currentFrame.dispose();
                openBooking();
            });
        }

        if (btnPet != null) {
            btnPet.addActionListener(e -> {
                currentFrame.dispose();
                openPet();
            });
        }

        if (btnCage != null) {
            btnCage.addActionListener(e -> {
                currentFrame.dispose();
                openCage();
            });
        }

        if (btnService != null) {
            btnService.addActionListener(e -> {
                currentFrame.dispose();
                openService();
            });
        }

        // --- MỚI: Xử lý nút Customer ---
        if (btnCustomer != null) {
            btnCustomer.addActionListener(e -> {
                currentFrame.dispose();
                openCustomer();
            });
        }

        if (btnUser != null) {
            btnUser.addActionListener(e -> {
                currentFrame.dispose();
                openUser();
            });
        }
    }

    private static void openBooking() {
        quanlybooking view = new quanlybooking();
        new BookingController(view);
        createAndShowFrame("Quản lý Đặt phòng", view.getMainPanel());
    }

    private static void openPet() {
        quanlythucung view = new quanlythucung();
        new PetController(view);
        createAndShowFrame("Quản lý Thú cưng", view.getMainPanel());
    }

    private static void openCage() {
        quanlychuong view = new quanlychuong();
        new CageController(view);
        createAndShowFrame("Quản lý Chuồng", view.getMainPanel());
    }

    private static void openService() {
        dichvu view = new dichvu();
        new ServiceController(view);
        // Đảm bảo class dichvu có hàm getMainPanel()
        createAndShowFrame("Quản lý Dịch vụ", view.getMainPanel());
    }

    private static void openUser() {
        Taikhoanuser view = new Taikhoanuser();
        new UserController(view);
        createAndShowFrame("Quản lý Tài khoản", view.getMainPanel());
    }

    // --- MỚI: Hàm mở màn hình Customer ---
    private static void openCustomer() {
        quanlykhachhang view = new quanlykhachhang();
        new CustomerController(view);
        createAndShowFrame("Quản lý Khách hàng", view.getMainPanel());
    }

    private static void createAndShowFrame(String title, JComponent content) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
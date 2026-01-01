package com.pethotel.utils;

import com.pethotel.controller.*;
import com.pethotel.gui.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NavigationHelper {

    // Hàm này sẽ gán sự kiện cho các nút menu
    public static void attachMenuEvents(JButton btnBooking, JButton btnPet, JButton btnCage, JButton btnService, JButton btnUser, JComponent currentPanel) {

        // Lấy JFrame chứa panel hiện tại để đóng nó lại khi chuyển
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(currentPanel);

        if (btnBooking != null) {
            btnBooking.addActionListener(e -> {
                currentFrame.dispose(); // Đóng màn hình cũ
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

        if (btnUser != null) {
            btnUser.addActionListener(e -> {
                currentFrame.dispose();
                openUser();
            });
        }
    }

    private static void openBooking() {
        quanlybooking view = new quanlybooking();
        new BookingController(view); // Khởi tạo controller
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
        createAndShowFrame("Quản lý Dịch vụ", view.getTblService()); // Lưu ý: View Dichvu của bạn cần có getPanel chính, tạm thời check lại getter
    }

    private static void openUser() {
        Taikhoanuser view = new Taikhoanuser();
        new UserController(view);
        createAndShowFrame("Quản lý Tài khoản", view.getMainPanel());
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
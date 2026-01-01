package com.pethotel.main;

import com.pethotel.controller.LoginController;
import com.pethotel.gui.Login;
import com.pethotel.gui.Login;
import com.pethotel.utils.DBConnection;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // 1. THIẾT LẬP GIAO DIỆN ĐẸP (NIMBUS)
        // Phần này giúp các nút bấm bo tròn, màu sắc hiện đại hơn giao diện mặc định
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Nếu lỗi thì kệ, dùng giao diện mặc định
            System.out.println("Không thể thiết lập giao diện Nimbus: " + e.getMessage());
        }

        // 2. KIỂM TRA KẾT NỐI CSDL (Optional - để debug cho dễ)
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Kết nối CSDL thành công!");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Không thể kết nối đến CSDL!\nVui lòng kiểm tra lại file DBConnection.",
                        "Lỗi Kết Nối",
                        JOptionPane.ERROR_MESSAGE);
                // Vẫn cho chạy tiếp hoặc return tùy bạn
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. CHẠY MÀN HÌNH ĐĂNG NHẬP
        // Sử dụng invokeLater để đảm bảo an toàn luồng cho Swing
        SwingUtilities.invokeLater(() -> {
            // Tạo view (Giao diện)
            Login loginView = new Login();

            // Tạo controller (Logic)
            LoginController loginController = new LoginController(loginView);

            // Hiển thị lên
            loginController.showLoginView();
        });
    }
}
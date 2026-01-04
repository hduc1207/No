package com.pethotel.utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StyleHelper {

    // 1. BẢNG MÀU CHUYÊN NGHIỆP (Modern Blue Theme)
    public static final Color PRIMARY_COLOR = new Color(52, 152, 219);    // Xanh dương chủ đạo
    public static final Color SECONDARY_COLOR = new Color(41, 128, 185);  // Xanh đậm (khi hover)
    public static final Color BG_COLOR = new Color(236, 240, 241);        // Xám nhạt (Nền app)
    public static final Color TEXT_COLOR = new Color(44, 62, 80);         // Đen xám (Chữ)
    public static final Color WHITE = Color.WHITE;

    public static final Font FONT_BOLD = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_REGULAR = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 24);

    // 2. HÀM TRANG TRÍ NÚT BẤM (BUTTON)
    public static void styleButton(JButton btn) {
        btn.setFont(FONT_BOLD);
        btn.setBackground(PRIMARY_COLOR);
        btn.setForeground(WHITE);
        btn.setFocusPainted(false); // Bỏ viền xanh khi click
        btn.setBorder(new EmptyBorder(10, 20, 10, 20)); // Tạo độ dày cho nút (Padding)
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Đổi chuột thành bàn tay

        // Hiệu ứng Hover đơn giản
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(SECONDARY_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(PRIMARY_COLOR);
            }
        });
    }

    // 3. HÀM TRANG TRÍ NÚT MENU (SIDEBAR)
    public static void styleMenuButton(JButton btn) {
        btn.setFont(FONT_BOLD);
        btn.setBackground(new Color(52, 73, 94)); // Màu menu tối
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT); // Căn lề trái cho đẹp
        btn.setBorder(new EmptyBorder(15, 20, 15, 20)); // Nút to hơn
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // 4. HÀM TRANG TRÍ BẢNG (TABLE) - Phần này làm app đẹp lên 80%
    public static void styleTable(JTable table) {
        table.setFont(FONT_REGULAR);
        table.setRowHeight(30); // Dòng cao thoáng
        table.setSelectionBackground(new Color(220, 230, 240)); // Màu khi chọn dòng
        table.setSelectionForeground(TEXT_COLOR);
        table.setShowVerticalLines(false); // Bỏ kẻ dọc cho hiện đại
        table.setIntercellSpacing(new Dimension(0, 0));

        // Header (Tiêu đề cột)
        table.getTableHeader().setFont(FONT_BOLD);
        table.getTableHeader().setBackground(PRIMARY_COLOR); // Header xanh
        table.getTableHeader().setForeground(WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(0, 40)); // Header cao

        // Căn giữa dữ liệu
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
    }

    // 5. TRANG TRÍ LABEL TIÊU ĐỀ
    public static void styleTitle(JLabel lbl) {
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(PRIMARY_COLOR);
    }
}
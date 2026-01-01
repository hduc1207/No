package com.pethotel.gui;

import com.pethotel.dao.PetDAO;
import com.pethotel.dto.PetDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class quanlythucung extends JFrame {
    private JButton btnMenuPet;
    private JButton btnMenuCage;
    private JButton btnMenuService;
    private JButton btnMenuBooking;
    private JTextField txtPetName;
    private JTextField txtBreed;
    private JComboBox cboSpecies;
    private JTextField txtWeight;
    private JTextField txtOwner;
    private JTextField txtNote;
    private JTable tblPetList;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnMenuAccount;
    private JButton btnMenuExit;
    private JPanel MainPanel;
    private JPanel plnInput;
    private JPanel plncongcu;
    private JPanel plnxuat;

    private PetDAO petDAO = new PetDAO();
    private DefaultTableModel tableModel;

    public quanlythucung() {
        // Cấu hình JFrame
        setContentPane(MainPanel);
        setTitle("Quản lý thú cưng");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. Cấu hình bảng
        initTable();

        // 2. Load dữ liệu lên bảng
        loadDataToTable();

        // 3. Sự kiện nút THÊM
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PetDTO pet = new PetDTO();
                    pet.setPetName(txtPetName.getText());
                    pet.setType(cboSpecies.getSelectedItem().toString());
                    pet.setBreed(txtBreed.getText());

                    // Xử lý Cân nặng
                    double weight = Double.parseDouble(txtWeight.getText());
                    pet.setWeight(weight);

                    pet.setHealthStatus(txtNote.getText());

                    // Xử lý ID Khách (Người dùng phải nhập số ID)
                    int customerId = Integer.parseInt(txtOwner.getText());
                    pet.setCustomerId(customerId);

                    if (petDAO.insertPet(pet)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công!");
                        loadDataToTable(); // Refresh lại bảng
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cân nặng và ID Chủ phải là số!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });

        // Khởi tạo ComboBox loại thú
        cboSpecies.addItem("Chó");
        cboSpecies.addItem("Mèo");
        cboSpecies.addItem("Khác");
    }

    // Hàm tạo cột cho bảng
    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên Thú Cưng");
        tableModel.addColumn("Loại");
        tableModel.addColumn("Giống");
        tableModel.addColumn("Cân nặng");
        tableModel.addColumn("Sức khỏe");
        tableModel.addColumn("ID Chủ");

        tblPetList.setModel(tableModel);
    }

    // Hàm load data từ Database
    private void loadDataToTable() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        List<PetDTO> list = petDAO.getAllPets();
        for (PetDTO p : list) {
            tableModel.addRow(new Object[]{
                    p.getPetId(),
                    p.getPetName(),
                    p.getType(),
                    p.getBreed(),
                    p.getWeight(),
                    p.getHealthStatus(),
                    p.getCustomerId()
            });
        }
    }

    // Hàm main để chạy thử giao diện này
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new quanlythucung().setVisible(true);
        });
    }
}

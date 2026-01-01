package com.pethotel.controller;

import com.pethotel.bus.CustomerBUS;
import com.pethotel.bus.PetBUS;
import com.pethotel.dto.CustomerDTO;
import com.pethotel.dto.PetDTO;
import com.pethotel.gui.quanlythucung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PetController {
    private quanlythucung view;
    private PetBUS bus;
    private CustomerBUS customerBUS; // Cần để lấy danh sách chủ
    private DefaultTableModel tableModel;

    public PetController(quanlythucung view) {
        this.view = view;
        this.bus = new PetBUS();
        this.customerBUS = new CustomerBUS();

        initTable();
        loadComboBoxData();
        loadData();
        initEvents();
    }

    private void initTable() {
        String[] headers = {"ID", "Tên thú cưng", "Giống", "Loài", "Cân nặng", "ID Chủ", "Ghi chú"};
        tableModel = new DefaultTableModel(headers, 0);
        view.getTblPetList().setModel(tableModel);
    }

    private void loadComboBoxData() {
        // Load danh sách chủ nhân
        view.getCboOwner().removeAllItems();
        List<CustomerDTO> customers = customerBUS.getAllCustomers();
        for (CustomerDTO c : customers) {
            view.getCboOwner().addItem(c.getCustomerId() + " - " + c.getFullName());
        }

        // Load loài (nếu chưa set trong Properties)
        view.getCboSpecies().removeAllItems();
        view.getCboSpecies().addItem("Chó");
        view.getCboSpecies().addItem("Mèo");
        view.getCboSpecies().addItem("Khác");
    }

    private void loadData() {
        tableModel.setRowCount(0);
        List<PetDTO> list = bus.getAllPets();
        for (PetDTO p : list) {
            tableModel.addRow(new Object[]{
                    p.getPetId(), p.getPetName(), p.getBreed(), p.getSpecies(),
                    p.getWeight(), p.getCustomerId()
            });
        }
    }

    private void initEvents() {
        // Nút Thêm
        view.getBtnAdd().addActionListener(e -> {
            PetDTO p = getPetFromForm();
            if(p != null) {
                JOptionPane.showMessageDialog(view.getMainPanel(), bus.addPet(p));
                loadData();
            }
        });

        // Nút Sửa
        view.getBtnEdit().addActionListener(e -> {
            int row = view.getTblPetList().getSelectedRow();
            if (row == -1) return;
            PetDTO p = getPetFromForm();
            if(p != null) {
                p.setPetId(Integer.parseInt(tableModel.getValueAt(row, 0).toString()));
                JOptionPane.showMessageDialog(view.getMainPanel(), bus.updatePet(p));
                loadData();
            }
        });

        // Nút Xóa
        view.getBtnDelete().addActionListener(e -> {
            int row = view.getTblPetList().getSelectedRow();
            if (row != -1) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                if(JOptionPane.showConfirmDialog(null, "Xóa thú cưng này?") == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(view.getMainPanel(), bus.deletePet(id));
                    loadData();
                }
            }
        });

        // Click bảng
        view.getTblPetList().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTblPetList().getSelectedRow();
                if (row >= 0) {
                    view.getTxtPetName().setText(tableModel.getValueAt(row, 1).toString());
                    view.getTxtBreed().setText(tableModel.getValueAt(row, 2).toString());
                    view.getCboSpecies().setSelectedItem(tableModel.getValueAt(row, 3).toString());
                    view.getTxtWeight().setText(tableModel.getValueAt(row, 4).toString());
                    // Chọn đúng chủ nhân trong ComboBox
                    String ownerId = tableModel.getValueAt(row, 5).toString();
                    setComboBoxByPrefix(view.getCboOwner(), ownerId);
                    view.getTxtNote().setText(tableModel.getValueAt(row, 6).toString());
                }
            }
        });

        view.getBtnExit().addActionListener(e -> System.exit(0));
    }

    private PetDTO getPetFromForm() {
        try {
            PetDTO p = new PetDTO();
            p.setPetName(view.getTxtPetName().getText());
            p.setBreed(view.getTxtBreed().getText());
            p.setSpecies(view.getCboSpecies().getSelectedItem().toString());
            p.setWeight(Double.parseDouble(view.getTxtWeight().getText()));

            // Lấy ID chủ từ chuỗi "ID - Tên"
            String ownerString = view.getCboOwner().getSelectedItem().toString();
            p.setCustomerId(Integer.parseInt(ownerString.split(" - ")[0]));

            return p;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view.getMainPanel(), "Lỗi nhập liệu: " + ex.getMessage());
            return null;
        }
    }

    private void setComboBoxByPrefix(JComboBox cbo, String prefix) {
        for (int i = 0; i < cbo.getItemCount(); i++) {
            if (cbo.getItemAt(i).toString().startsWith(prefix + " -")) {
                cbo.setSelectedIndex(i);
                return;
            }
        }
    }
}
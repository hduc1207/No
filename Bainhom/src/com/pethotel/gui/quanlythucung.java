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
    private JComboBox cboOwner;
    private JTextField txtNote;
    private JTable tblPetList;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnMenuAccount;
    private JButton btnExit;
    private JPanel MainPanel;
    private JPanel plnInput;
    private JPanel plncongcu;
    private JPanel plnxuat;
}

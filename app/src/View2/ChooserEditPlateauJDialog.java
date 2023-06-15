/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View2;

import Controller.IApp;
import Model.Plateaux.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Léa Berne
 */
public class ChooserEditPlateauJDialog extends javax.swing.JDialog {
    private IApp iApp;
    /**
     * Creates new form PlateauxJDialog
     */
    public ChooserEditPlateauJDialog(java.awt.Frame parent, boolean modal, IApp iApp) {
        super(parent, modal);
        this.iApp = iApp;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPlateauxJPanel = new javax.swing.JPanel();
        creationPlateauJLabel = new javax.swing.JLabel();
        centrePlateauxJPanel = new javax.swing.JPanel();
        nomPlateauJLabel1 = new javax.swing.JLabel();
        choosePlateauToEditJComboBox1 = new javax.swing.JComboBox<PlateauStandard>();
        buttonRetourSaveJPanel = new javax.swing.JPanel();
        annulerJButton = new javax.swing.JButton();
        okJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        creationPlateauJLabel.setText("Edition des plateaux standards");
        mainPlateauxJPanel.add(creationPlateauJLabel);

        getContentPane().add(mainPlateauxJPanel, java.awt.BorderLayout.PAGE_START);

        centrePlateauxJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centrePlateauxJPanel.setLayout(new java.awt.GridBagLayout());

        nomPlateauJLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomPlateauJLabel1.setText("Choisisez le plateau à modifier :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        centrePlateauxJPanel.add(nomPlateauJLabel1, gridBagConstraints);

        DefaultComboBoxModel editPlateauCBBModel = new DefaultComboBoxModel<PlateauStandard>();
        ArrayList<Plateau> plateaux = iApp.toutRecupererPlateauStandard();
        for (Plateau plateau : plateaux){
            editPlateauCBBModel.addElement(plateau);
        }
        choosePlateauToEditJComboBox1.setModel(editPlateauCBBModel);
        centrePlateauxJPanel.add(choosePlateauToEditJComboBox1, new java.awt.GridBagConstraints());

        getContentPane().add(centrePlateauxJPanel, java.awt.BorderLayout.CENTER);

        annulerJButton.setText("Annuler");
        annulerJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerJButtonActionPerformed(evt);
            }
        });
        buttonRetourSaveJPanel.add(annulerJButton);

        okJButton.setText("OK");
        okJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okJButtonActionPerformed(evt);
            }
        });
        buttonRetourSaveJPanel.add(okJButton);

        getContentPane().add(buttonRetourSaveJPanel, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void annulerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerJButtonActionPerformed

    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okJButtonActionPerformed
        EditPlateauJDialog editPlateauJDialog = new EditPlateauJDialog((App)this.getOwner(), true, this.iApp, (PlateauStandard) this.choosePlateauToEditJComboBox1.getSelectedItem());
        this.dispose();
        editPlateauJDialog.setVisible(true);
    }//GEN-LAST:event_okJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerJButton;
    private javax.swing.JPanel buttonRetourSaveJPanel;
    private javax.swing.JPanel centrePlateauxJPanel;
    private javax.swing.JComboBox<PlateauStandard> choosePlateauToEditJComboBox1;
    private javax.swing.JLabel creationPlateauJLabel;
    private javax.swing.JPanel mainPlateauxJPanel;
    private javax.swing.JLabel nomPlateauJLabel1;
    private javax.swing.JButton okJButton;
    // End of variables declaration//GEN-END:variables
}

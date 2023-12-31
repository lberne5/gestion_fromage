/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View2;

import Controller.IApp;
import Model.Commande.Commande;
import Model.Plateaux.Plateau;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Léa Berne
 */
public class ChooserDeleteCommandeJDialog extends javax.swing.JDialog {

    private IApp iApp;

    /**
     * Creates new form PlateauxJDialog
     */
    public ChooserDeleteCommandeJDialog(java.awt.Frame parent, boolean modal, IApp iApp) {
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
        nomCommandeJLabel = new javax.swing.JLabel();
        chooseCommandToEditJComboBox = new javax.swing.JComboBox<Commande>();
        buttonRetourSaveJPanel = new javax.swing.JPanel();
        annulerJButton = new javax.swing.JButton();
        okJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        creationPlateauJLabel.setText("Suppression des commandes");
        mainPlateauxJPanel.add(creationPlateauJLabel);

        getContentPane().add(mainPlateauxJPanel, java.awt.BorderLayout.PAGE_START);

        centrePlateauxJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centrePlateauxJPanel.setLayout(new java.awt.GridBagLayout());

        nomCommandeJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomCommandeJLabel.setText("Choisisez la commande à supprimer :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        centrePlateauxJPanel.add(nomCommandeJLabel, gridBagConstraints);

        DefaultComboBoxModel deleteCommandeCBBModel = new DefaultComboBoxModel<Commande>();
        ArrayList<Commande> commandes = iApp.toutRecupererCommande();
        for (Commande commande : commandes){
            deleteCommandeCBBModel.addElement(commande);
        }
        chooseCommandToEditJComboBox.setModel(deleteCommandeCBBModel);
        centrePlateauxJPanel.add(chooseCommandToEditJComboBox, new java.awt.GridBagConstraints());

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
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annulerJButtonActionPerformed

    private void okJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okJButtonActionPerformed
        Commande commandeSelectionne = (Commande) this.chooseCommandToEditJComboBox.getSelectedItem();
        int result = JOptionPane.showConfirmDialog(rootPane, "Etes-vous sûr de vouloir supprimer la commande de M./Mme " + commandeSelectionne.getNomClient() + " ?", "Confirmez", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            //TODO : try catch
            iApp.supprimer(commandeSelectionne);
            this.dispose();
            App owner = (App) this.getOwner();
            owner.refreshTableCommande();
        }    }//GEN-LAST:event_okJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerJButton;
    private javax.swing.JPanel buttonRetourSaveJPanel;
    private javax.swing.JPanel centrePlateauxJPanel;
    private javax.swing.JComboBox<Commande> chooseCommandToEditJComboBox;
    private javax.swing.JLabel creationPlateauJLabel;
    private javax.swing.JPanel mainPlateauxJPanel;
    private javax.swing.JLabel nomCommandeJLabel;
    private javax.swing.JButton okJButton;
    // End of variables declaration//GEN-END:variables
}

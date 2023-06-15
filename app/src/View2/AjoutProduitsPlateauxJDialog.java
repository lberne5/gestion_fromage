/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View2;

import Controller.IApp;
import Model.Produits.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Léa Berne
 */
public class AjoutProduitsPlateauxJDialog extends javax.swing.JDialog {
    
    private IApp iApp;
    private Produit produitDuPlateau;
    
    /**
     * Creates new form AjoutProduitsPlateauxJDialog
     */
    public AjoutProduitsPlateauxJDialog(java.awt.Frame parent, boolean modal, IApp iApp) {
        super(parent, modal);
        this.iApp = iApp; 
        initComponents();
    }
    
    public Produit showDialog() {
        this.setVisible(true);
        return produitDuPlateau;
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

        ajoutProduitsJPanel = new javax.swing.JPanel();
        nomPlateauJLabel1 = new javax.swing.JLabel();
        choosePlateauToEditJComboBox1 = new javax.swing.JComboBox<Produit>();
        buttonRetourSaveJPanel = new javax.swing.JPanel();
        annulerJButton = new javax.swing.JButton();
        enregistrerJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ajoutProduitsJPanel.setLayout(new java.awt.GridBagLayout());

        nomPlateauJLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomPlateauJLabel1.setText("Choisisez le produit à ajouter au plateau :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(125, 76, 0, 0);
        ajoutProduitsJPanel.add(nomPlateauJLabel1, gridBagConstraints);

        DefaultComboBoxModel ajoutProduitPlateauCBBModel = new DefaultComboBoxModel<Produit>();
        ArrayList<Fromage> fromages = iApp.toutRecupererFromage();
        for (Fromage fromage : fromages){
            ajoutProduitPlateauCBBModel.addElement(fromage);
        }
        ArrayList<Fruit> fruits = iApp.toutRecupererFruit();
        for (Fruit fruit : fruits){
            ajoutProduitPlateauCBBModel.addElement(fruit);
        }
        ArrayList<Charcuterie> charcuteries = iApp.toutRecupererCharcuterie();
        for (Charcuterie charcuterie : charcuteries){
            ajoutProduitPlateauCBBModel.addElement(charcuterie);
        }
        ArrayList<AutreProduit> autres = iApp.toutRecupererAutre();
        for (AutreProduit autre : autres){
            ajoutProduitPlateauCBBModel.addElement(autre);
        }
        choosePlateauToEditJComboBox1.setModel(ajoutProduitPlateauCBBModel);
        choosePlateauToEditJComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePlateauToEditJComboBox1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(122, 0, 123, 76);
        ajoutProduitsJPanel.add(choosePlateauToEditJComboBox1, gridBagConstraints);

        getContentPane().add(ajoutProduitsJPanel, java.awt.BorderLayout.CENTER);

        annulerJButton.setText("Annuler");
        annulerJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerJButtonActionPerformed(evt);
            }
        });
        buttonRetourSaveJPanel.add(annulerJButton);

        enregistrerJButton.setText("Enregistrer");
        enregistrerJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enregistrerJButtonActionPerformed(evt);
            }
        });
        buttonRetourSaveJPanel.add(enregistrerJButton);

        getContentPane().add(buttonRetourSaveJPanel, java.awt.BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void annulerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerJButtonActionPerformed

    private void choosePlateauToEditJComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePlateauToEditJComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_choosePlateauToEditJComboBox1ActionPerformed

    private void enregistrerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerJButtonActionPerformed
        this.produitDuPlateau = (Produit)choosePlateauToEditJComboBox1.getSelectedItem();
        this.dispose();
    }//GEN-LAST:event_enregistrerJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ajoutProduitsJPanel;
    private javax.swing.JButton annulerJButton;
    private javax.swing.JPanel buttonRetourSaveJPanel;
    private javax.swing.JComboBox<Produit> choosePlateauToEditJComboBox1;
    private javax.swing.JButton enregistrerJButton;
    private javax.swing.JLabel nomPlateauJLabel1;
    // End of variables declaration//GEN-END:variables
}

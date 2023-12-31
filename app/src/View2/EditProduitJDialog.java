/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View2;

import Controller.Exceptions.ErreurInconnueException;
import Controller.Exceptions.NomTropLongException;
import Controller.Exceptions.PrixInvalideException;
import Controller.Exceptions.QuantiteParPersonneInvalideException;
import Controller.IApp;
import Model.Produits.*;
import Model.Util.Unite;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author Léa Berne
 */
public class EditProduitJDialog extends javax.swing.JDialog {

    private Produit produit;
    private IApp iApp;

    /**
     * Creates new form ProduitsJDialog
     */
    public EditProduitJDialog(java.awt.Frame parent, boolean modal, IApp iApp, Produit produit) {
        super(parent, modal);
        this.iApp = iApp;
        this.produit = produit;
        initComponents();
        if (produit instanceof Fromage) {
            fromageProduitsJToggleButton.doClick();
        }
        if (produit instanceof Charcuterie) {
            charcuterieProduitsJToggleButton.doClick();
        }
        if (produit instanceof Fruit) {
            fruitProduitsJToggleButton.doClick();
        }
        if (produit instanceof AutreProduit) {
            autreProduitsJToggleButton.doClick();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editProduitButtonGroup = new javax.swing.ButtonGroup();
        mainProduitsJPanel = new javax.swing.JPanel();
        fromageProduitsJToggleButton = new javax.swing.JToggleButton();
        charcuterieProduitsJToggleButton = new javax.swing.JToggleButton();
        fruitProduitsJToggleButton = new javax.swing.JToggleButton();
        autreProduitsJToggleButton = new javax.swing.JToggleButton();
        EditProduitsCentralJPanel = new javax.swing.JPanel();
        editFromageCardLayoutJPanel = new javax.swing.JPanel();
        nomFromageJLabel = new javax.swing.JLabel();
        nomFromageJTextField = new javax.swing.JTextField();
        quantitePersonneFromageJLabel = new javax.swing.JLabel();
        quantitePersonneFromageJTextField = new javax.swing.JTextField();
        uniteFromageJLabel = new javax.swing.JLabel();
        uniteFromageJComboBox = new javax.swing.JComboBox<>();
        prixUnitaireFromageJLabel = new javax.swing.JLabel();
        prixUnitaireFromageJTextField = new javax.swing.JTextField();
        editCharcuterieCardLayoutJPanel = new javax.swing.JPanel();
        nomCharcuterieJLabel = new javax.swing.JLabel();
        nomCharcuterieJTextField = new javax.swing.JTextField();
        quantitePersonneCharcuterieJLabel = new javax.swing.JLabel();
        quantitePersonneCharcuterieJTextField = new javax.swing.JTextField();
        uniteCharcuterieJLabel = new javax.swing.JLabel();
        uniteCharcuterieJComboBox = new javax.swing.JComboBox<>();
        prixUnitaireCharcuterieJLabel = new javax.swing.JLabel();
        prixUnitaireCharcuterieJTextField = new javax.swing.JTextField();
        editFruitCardLayoutJPanel = new javax.swing.JPanel();
        nomFruitJLabel = new javax.swing.JLabel();
        nomFruitJTextField = new javax.swing.JTextField();
        quantitePersonneFruitJLabel = new javax.swing.JLabel();
        quantitePersonneFruitJTextField = new javax.swing.JTextField();
        uniteFruitJLabel = new javax.swing.JLabel();
        uniteFruitJComboBox = new javax.swing.JComboBox<>();
        autreFruitJLabel = new javax.swing.JLabel();
        prixUFruitJTextField = new javax.swing.JTextField();
        editAutreCardLayoutJPanel = new javax.swing.JPanel();
        nomAutreJLabel = new javax.swing.JLabel();
        nomAutreJTextField = new javax.swing.JTextField();
        prixUnitaireAutreJLabel = new javax.swing.JLabel();
        prixUnitaireAutreJTextField = new javax.swing.JTextField();
        buttonRetourSaveJPanel = new javax.swing.JPanel();
        annulerJButton = new javax.swing.JButton();
        enregistrerJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout(0, 20));

        editProduitButtonGroup.add(fromageProduitsJToggleButton);
        if(this.produit instanceof Fromage){
            fromageProduitsJToggleButton.setSelected(true);
            fromageProduitsJToggleButton.setEnabled(true);
        }else{
            fromageProduitsJToggleButton.setEnabled(false);
        }
        fromageProduitsJToggleButton.setText("Fromage");
        fromageProduitsJToggleButton.setMinimumSize(new java.awt.Dimension(100, 30));
        fromageProduitsJToggleButton.setPreferredSize(new java.awt.Dimension(100, 30));
        fromageProduitsJToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                everyProduitsJToggleButtonActionPerformed(evt);
            }
        });
        fromageProduitsJToggleButton.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fromageProduitsJToggleButtonPropertyChange(evt);
            }
        });
        mainProduitsJPanel.add(fromageProduitsJToggleButton);

        editProduitButtonGroup.add(charcuterieProduitsJToggleButton);
        if (this.produit instanceof Charcuterie){
            charcuterieProduitsJToggleButton.setSelected(true);
            charcuterieProduitsJToggleButton.setEnabled(true);
        }
        else{
            charcuterieProduitsJToggleButton.setEnabled(false);
        }
        charcuterieProduitsJToggleButton.setText("Charcuterie");
        charcuterieProduitsJToggleButton.setMinimumSize(new java.awt.Dimension(100, 30));
        charcuterieProduitsJToggleButton.setPreferredSize(new java.awt.Dimension(100, 30));
        charcuterieProduitsJToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                everyProduitsJToggleButtonActionPerformed(evt);
            }
        });
        mainProduitsJPanel.add(charcuterieProduitsJToggleButton);

        editProduitButtonGroup.add(fruitProduitsJToggleButton);
        if(this.produit instanceof Fruit){
            fruitProduitsJToggleButton.setSelected(true);
            fruitProduitsJToggleButton.setEnabled(true);
        }else{
            fruitProduitsJToggleButton.setEnabled(false);
        }
        fruitProduitsJToggleButton.setText("Fruit");
        fruitProduitsJToggleButton.setMinimumSize(new java.awt.Dimension(100, 30));
        fruitProduitsJToggleButton.setPreferredSize(new java.awt.Dimension(100, 30));
        fruitProduitsJToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                everyProduitsJToggleButtonActionPerformed(evt);
            }
        });
        mainProduitsJPanel.add(fruitProduitsJToggleButton);

        editProduitButtonGroup.add(autreProduitsJToggleButton);
        if(this.produit instanceof AutreProduit){
            autreProduitsJToggleButton.setSelected(true);
            autreProduitsJToggleButton.setEnabled(true);
        }else{
            autreProduitsJToggleButton.setEnabled(false);
        }
        autreProduitsJToggleButton.setText("Autre");
        autreProduitsJToggleButton.setMinimumSize(new java.awt.Dimension(100, 30));
        autreProduitsJToggleButton.setPreferredSize(new java.awt.Dimension(100, 30));
        autreProduitsJToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                everyProduitsJToggleButtonActionPerformed(evt);
            }
        });
        mainProduitsJPanel.add(autreProduitsJToggleButton);

        getContentPane().add(mainProduitsJPanel, java.awt.BorderLayout.PAGE_START);

        EditProduitsCentralJPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        EditProduitsCentralJPanel.setLayout(new java.awt.CardLayout());

        editFromageCardLayoutJPanel.setLayout(new java.awt.GridLayout(4, 2, 0, 10));

        nomFromageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFromageJLabel.setText("Nom du fromage");
        editFromageCardLayoutJPanel.add(nomFromageJLabel);

        nomFromageJTextField.setText(this.produit.getNom());
        nomFromageJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFromageCardLayoutJPanel.add(nomFromageJTextField);

        quantitePersonneFromageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantitePersonneFromageJLabel.setText("Quantité par personne");
        editFromageCardLayoutJPanel.add(quantitePersonneFromageJLabel);

        if (this.produit instanceof Fromage){
            Fromage fromageQuantite = (Fromage) this.produit;
            quantitePersonneFromageJTextField.setText(fromageQuantite.getQuantiteParPersonnes()+"");
        }
        quantitePersonneFromageJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFromageCardLayoutJPanel.add(quantitePersonneFromageJTextField);

        uniteFromageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uniteFromageJLabel.setText("Unité");
        editFromageCardLayoutJPanel.add(uniteFromageJLabel);

        uniteFromageJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRAMME", "PIECE", "INDEFINIE" }));
        uniteFromageJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniteFromageJComboBoxActionPerformed(evt);
            }
        });
        editFromageCardLayoutJPanel.add(uniteFromageJComboBox);

        prixUnitaireFromageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prixUnitaireFromageJLabel.setText("Prix unitaire ( Euro )");
        editFromageCardLayoutJPanel.add(prixUnitaireFromageJLabel);

        prixUnitaireFromageJTextField.setText(this.produit.getPrix()+"");
        prixUnitaireFromageJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFromageCardLayoutJPanel.add(prixUnitaireFromageJTextField);

        EditProduitsCentralJPanel.add(editFromageCardLayoutJPanel, "Fromage");

        editCharcuterieCardLayoutJPanel.setLayout(new java.awt.GridLayout(4, 2, 0, 10));

        nomCharcuterieJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomCharcuterieJLabel.setText("Nom de la charcuterie");
        editCharcuterieCardLayoutJPanel.add(nomCharcuterieJLabel);

        nomCharcuterieJTextField.setText(this.produit.getNom());
        nomCharcuterieJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCharcuterieCardLayoutJPanel.add(nomCharcuterieJTextField);

        quantitePersonneCharcuterieJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantitePersonneCharcuterieJLabel.setText("Quantité par personne");
        editCharcuterieCardLayoutJPanel.add(quantitePersonneCharcuterieJLabel);

        if (this.produit instanceof Charcuterie){
            Charcuterie charcuterieQuantite = (Charcuterie) this.produit;
            quantitePersonneCharcuterieJTextField.setText(charcuterieQuantite.getQuantiteParPersonnes()+"");
        }
        quantitePersonneCharcuterieJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCharcuterieCardLayoutJPanel.add(quantitePersonneCharcuterieJTextField);

        uniteCharcuterieJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uniteCharcuterieJLabel.setText("Unité");
        editCharcuterieCardLayoutJPanel.add(uniteCharcuterieJLabel);

        uniteCharcuterieJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRAMME", "PIECE", "INDEFINIE" }));
        uniteCharcuterieJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniteCharcuterieJComboBoxActionPerformed(evt);
            }
        });
        editCharcuterieCardLayoutJPanel.add(uniteCharcuterieJComboBox);

        prixUnitaireCharcuterieJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prixUnitaireCharcuterieJLabel.setText("Prix unitaire ( Euro )");
        editCharcuterieCardLayoutJPanel.add(prixUnitaireCharcuterieJLabel);

        prixUnitaireCharcuterieJTextField.setText(this.produit.getPrix()+"");
        prixUnitaireCharcuterieJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCharcuterieCardLayoutJPanel.add(prixUnitaireCharcuterieJTextField);

        EditProduitsCentralJPanel.add(editCharcuterieCardLayoutJPanel, "Charcuterie");

        editFruitCardLayoutJPanel.setLayout(new java.awt.GridLayout(4, 2, 0, 10));

        nomFruitJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomFruitJLabel.setText("Nom du fruit");
        editFruitCardLayoutJPanel.add(nomFruitJLabel);

        nomFruitJTextField.setText(this.produit.getNom());
        nomFruitJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFruitCardLayoutJPanel.add(nomFruitJTextField);

        quantitePersonneFruitJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quantitePersonneFruitJLabel.setText("Quantité par personne");
        editFruitCardLayoutJPanel.add(quantitePersonneFruitJLabel);

        if (this.produit instanceof Fruit){
            Fruit fruitQuantite = (Fruit) this.produit;
            quantitePersonneFruitJTextField.setText(fruitQuantite.getQuantiteParPersonnes()+"");
        }
        quantitePersonneFruitJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFruitCardLayoutJPanel.add(quantitePersonneFruitJTextField);

        uniteFruitJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uniteFruitJLabel.setText("Unité");
        editFruitCardLayoutJPanel.add(uniteFruitJLabel);

        uniteFruitJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GRAMME", "PIECE", "INDEFINIE" }));
        uniteFruitJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uniteFruitJComboBoxActionPerformed(evt);
            }
        });
        editFruitCardLayoutJPanel.add(uniteFruitJComboBox);

        autreFruitJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        autreFruitJLabel.setText("Prix unitaire ( Euro )");
        editFruitCardLayoutJPanel.add(autreFruitJLabel);

        prixUFruitJTextField.setText(this.produit.getPrix()+"");
        prixUFruitJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFruitCardLayoutJPanel.add(prixUFruitJTextField);

        EditProduitsCentralJPanel.add(editFruitCardLayoutJPanel, "Fruit");

        editAutreCardLayoutJPanel.setLayout(new java.awt.GridLayout(2, 2, 0, 10));

        nomAutreJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomAutreJLabel.setText("Nom du nouvel élément");
        editAutreCardLayoutJPanel.add(nomAutreJLabel);

        nomAutreJTextField.setText(this.produit.getNom());
        nomAutreJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAutreCardLayoutJPanel.add(nomAutreJTextField);

        prixUnitaireAutreJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prixUnitaireAutreJLabel.setText("Prix unitaire ( Euro )");
        editAutreCardLayoutJPanel.add(prixUnitaireAutreJLabel);

        prixUnitaireAutreJTextField.setText(this.produit.getPrix()+"");
        prixUnitaireAutreJTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAutreCardLayoutJPanel.add(prixUnitaireAutreJTextField);

        EditProduitsCentralJPanel.add(editAutreCardLayoutJPanel, "Autre");

        getContentPane().add(EditProduitsCentralJPanel, java.awt.BorderLayout.CENTER);

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

    private void everyProduitsJToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_everyProduitsJToggleButtonActionPerformed
        // TODO add your handling code here:
        JToggleButton button = (JToggleButton) evt.getSource();
        String str = button.getText();

        CardLayout cardLayout = (CardLayout) EditProduitsCentralJPanel.getLayout();
        cardLayout.show(EditProduitsCentralJPanel, str);
    }//GEN-LAST:event_everyProduitsJToggleButtonActionPerformed

    private void annulerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerJButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annulerJButtonActionPerformed

    private void enregistrerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enregistrerJButtonActionPerformed
        App app = (App) this.getOwner();
        if (this.produit instanceof Fromage) {
            try {
                String prixText = prixUnitaireFromageJTextField.getText();
                double prix = Double.parseDouble(prixText);
                String quantitePPText = quantitePersonneFromageJTextField.getText();
                double quantitePP = Double.parseDouble(quantitePPText);
                String uniteText = (String) this.uniteFromageJComboBox.getSelectedItem();
                Unite unite;
                if (uniteText.compareTo("GRAMME") == 0) {
                    unite = Unite.GRAMMES;
                } else if (uniteText.compareTo("PIECE") == 0) {
                    unite = Unite.PIECE;
                } else {
                    unite = Unite.INDEFINIE;
                }
                this.iApp.modifierFromage((Fromage) this.produit, nomFromageJTextField.getText(), prix, quantitePP, unite);
            } catch (NomTropLongException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (QuantiteParPersonneInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (PrixInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ErreurInconnueException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            app.refreshTableFromage();
        } else if (this.produit instanceof Charcuterie) {
            try {
                String prixText = prixUnitaireCharcuterieJTextField.getText();
                double prix = Double.parseDouble(prixText);
                String quantitePPText = quantitePersonneCharcuterieJTextField.getText();
                double quantitePP = Double.parseDouble(quantitePPText);
                String uniteText = (String) this.uniteCharcuterieJComboBox.getSelectedItem();
                Unite unite;
                if (uniteText.compareTo("GRAMME") == 0) {
                    unite = Unite.GRAMMES;
                } else if (uniteText.compareTo("PIECE") == 0) {
                    unite = Unite.PIECE;
                } else {
                    unite = Unite.INDEFINIE;
                }
                this.iApp.modifierCharcuterie((Charcuterie) this.produit, nomCharcuterieJTextField.getText(), prix, quantitePP, unite);
            } catch (NomTropLongException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (QuantiteParPersonneInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (PrixInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ErreurInconnueException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            app.refreshTableCharcuterie();
            fromageProduitsJToggleButton.doClick();
        } else if (this.produit instanceof Fruit) {
            try {
                String prixText = prixUFruitJTextField.getText();
                double prix = Double.parseDouble(prixText);
                String quantitePPText = quantitePersonneFruitJTextField.getText();
                double quantitePP = Double.parseDouble(quantitePPText);
                String uniteText = (String) this.uniteFruitJComboBox.getSelectedItem();
                Unite unite;
                if (uniteText.compareTo("GRAMME") == 0) {
                    unite = Unite.GRAMMES;
                } else if (uniteText.compareTo("PIECE") == 0) {
                    unite = Unite.PIECE;
                } else {
                    unite = Unite.INDEFINIE;
                }
                this.iApp.modifierFruit((Fruit) this.produit, nomFruitJTextField.getText(), prix, quantitePP, unite);
            } catch (NomTropLongException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (QuantiteParPersonneInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (PrixInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ErreurInconnueException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            app.refreshTableFruit();
            fromageProduitsJToggleButton.doClick();
            
        } else if (this.produit instanceof AutreProduit) {
            try {
                String prixText = prixUnitaireAutreJTextField.getText();
                double prix = Double.parseDouble(prixText);
                this.iApp.modifierAutre((AutreProduit) this.produit, nomAutreJTextField.getText(), prix);
            } catch (NomTropLongException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (QuantiteParPersonneInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (PrixInvalideException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ErreurInconnueException ex) {
                JOptionPane.showMessageDialog(this, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            app.refreshTableAutre();
            fromageProduitsJToggleButton.doClick();
        }
        app.refreshTablePlateaux();
        app.refreshTableCommande();
        app.refreshTablePlanifierTotal();
        app.refreshTablePlanifierParCommande();
        this.dispose();

    }//GEN-LAST:event_enregistrerJButtonActionPerformed

    private void fromageProduitsJToggleButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fromageProduitsJToggleButtonPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_fromageProduitsJToggleButtonPropertyChange

    private void uniteFruitJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniteFruitJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uniteFruitJComboBoxActionPerformed

    private void uniteCharcuterieJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniteCharcuterieJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uniteCharcuterieJComboBoxActionPerformed

    private void uniteFromageJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uniteFromageJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uniteFromageJComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EditProduitsCentralJPanel;
    private javax.swing.JButton annulerJButton;
    private javax.swing.JLabel autreFruitJLabel;
    private javax.swing.JToggleButton autreProduitsJToggleButton;
    private javax.swing.JPanel buttonRetourSaveJPanel;
    private javax.swing.JToggleButton charcuterieProduitsJToggleButton;
    private javax.swing.JPanel editAutreCardLayoutJPanel;
    private javax.swing.JPanel editCharcuterieCardLayoutJPanel;
    private javax.swing.JPanel editFromageCardLayoutJPanel;
    private javax.swing.JPanel editFruitCardLayoutJPanel;
    private javax.swing.ButtonGroup editProduitButtonGroup;
    private javax.swing.JButton enregistrerJButton;
    private javax.swing.JToggleButton fromageProduitsJToggleButton;
    private javax.swing.JToggleButton fruitProduitsJToggleButton;
    private javax.swing.JPanel mainProduitsJPanel;
    private javax.swing.JLabel nomAutreJLabel;
    private javax.swing.JTextField nomAutreJTextField;
    private javax.swing.JLabel nomCharcuterieJLabel;
    private javax.swing.JTextField nomCharcuterieJTextField;
    private javax.swing.JLabel nomFromageJLabel;
    private javax.swing.JTextField nomFromageJTextField;
    private javax.swing.JLabel nomFruitJLabel;
    private javax.swing.JTextField nomFruitJTextField;
    private javax.swing.JTextField prixUFruitJTextField;
    private javax.swing.JLabel prixUnitaireAutreJLabel;
    private javax.swing.JTextField prixUnitaireAutreJTextField;
    private javax.swing.JLabel prixUnitaireCharcuterieJLabel;
    private javax.swing.JTextField prixUnitaireCharcuterieJTextField;
    private javax.swing.JLabel prixUnitaireFromageJLabel;
    private javax.swing.JTextField prixUnitaireFromageJTextField;
    private javax.swing.JLabel quantitePersonneCharcuterieJLabel;
    private javax.swing.JTextField quantitePersonneCharcuterieJTextField;
    private javax.swing.JLabel quantitePersonneFromageJLabel;
    private javax.swing.JTextField quantitePersonneFromageJTextField;
    private javax.swing.JLabel quantitePersonneFruitJLabel;
    private javax.swing.JTextField quantitePersonneFruitJTextField;
    private javax.swing.JComboBox<String> uniteCharcuterieJComboBox;
    private javax.swing.JLabel uniteCharcuterieJLabel;
    private javax.swing.JComboBox<String> uniteFromageJComboBox;
    private javax.swing.JLabel uniteFromageJLabel;
    private javax.swing.JComboBox<String> uniteFruitJComboBox;
    private javax.swing.JLabel uniteFruitJLabel;
    // End of variables declaration//GEN-END:variables
}

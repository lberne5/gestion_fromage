/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View2;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Timon Fournier
 */
public class VoirPlateauxCommande extends javax.swing.JDialog {

    ArrayList<ArrayList> idQuantiteNbpersonnesPlateaux;
            
    /**
     * Creates new form VoirPlateauxCommande
     */
    public VoirPlateauxCommande(java.awt.Frame parent, boolean modal, ArrayList<ArrayList> idQuantiteNbpersonnesPlateaux) {
        super(parent, modal);
        initComponents();
        this.idQuantiteNbpersonnesPlateaux = idQuantiteNbpersonnesPlateaux;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (ArrayList element : idQuantiteNbpersonnesPlateaux) {
            listModel.addElement(element.get(0) + " - quantite :  "  + element.get(1) + " - pour " + element.get(2) + " personnes");
        }
        jList2.setModel(listModel);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(800, 300));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));

        jScrollPane1.setViewportView(jList2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

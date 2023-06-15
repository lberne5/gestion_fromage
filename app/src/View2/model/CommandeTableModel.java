/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View2.model;

import Model.Commande.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Timon Fournier
 */
public class CommandeTableModel extends AbstractTableModel {

    private List<Commande> listeCommandes = new ArrayList<Commande>();
    private ArrayList<Commande> listeFiltree = new ArrayList<Commande>();
    private String[] colonnes = {"Nom Client", "Date de création", "Date récupération", "Nombre plateaux", "Prix indicatif", "Montant payé", "Commentaire"};
    private int buttonClicked = 0;

    public void addCommande(Commande commande) {
        this.listeCommandes.add(commande);
        refresh();
    }

    @Override
    public int getRowCount() {
        return this.listeFiltree.size();
    }

    @Override
    public int getColumnCount() {
        return colonnes.length;
    }

    @Override
    public String getColumnName(int column) {
        return colonnes[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Commande commande = listeFiltree.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return commande.getNomClient();
            case 1:
                return commande.getDateEcriture();
            case 2:
                return commande.getDateRecuperation();
            case 3:
                return commande.getListePlateaux().size();
            case 4:
                return commande.getPrix();
            case 5:
                return commande.getMontantPaye();
            case 6:
                return commande.getCommentaires();

            default:
                return null;
        }
    }

    public void ajouterAPreparer() {
        this.buttonClicked = 0;
        refresh();
    }

    public void ajouterPreparee() {
        this.buttonClicked = 1;
        refresh();

    }

    public void ajouterRecuperee() {
        this.buttonClicked = 2;
        refresh();

    }

    public void ajouterArchivee() {
        this.buttonClicked = 3;
        refresh();
    }

    private void refresh() {
        fireTableRowsDeleted(0,listeFiltree.size());
        this.listeFiltree.clear();
        for (Commande commande : listeCommandes) {
            EtatCommande etat = commande.getEtatCommande();
            if (etat instanceof APreparer && this.buttonClicked == 0) {
                listeFiltree.add(commande);
            }
            if (etat instanceof Preparee && this.buttonClicked == 1) {
                listeFiltree.add(commande);

            }
            if (etat instanceof Recuperee && this.buttonClicked == 2) {
                listeFiltree.add(commande);

            }
            if (etat instanceof Archivee && this.buttonClicked == 3) {
                listeFiltree.add(commande);
            }
        }
        fireTableRowsInserted(0,listeFiltree.size());
    }

    public void removeAllCommandes() {
        this.listeCommandes.removeAll(listeCommandes);
    }

}

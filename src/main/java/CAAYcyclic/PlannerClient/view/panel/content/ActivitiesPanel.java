/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.view.panel.content;

import CAAYcyclic.PlannerClient.enumeration.ApplicationColor;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public class ActivitiesPanel extends javax.swing.JPanel {

    /**
     * Creates new form ActivitiesPanel
     */
    public ActivitiesPanel() {
        initComponents();
        weekComboBox.setBackground(Color.white);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JTable getTableView() {
        return tableView;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }
    
    public JComboBox getWeekComboBox(){
        return weekComboBox;
    }
    
    public void setTableHeader(String[] headers) {
        if(headers != null){
            tableView.setModel(new javax.swing.table.DefaultTableModel(null,
                headers
            ));
        }

    }
    

    private void hightlightButton(JButton button){
        button.setForeground(ApplicationColor.accentColor.value);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,3,0,ApplicationColor.accentColor.value),BorderFactory.createEmptyBorder(1,1,1,1)));
        button.repaint();
        button.revalidate();
    }

    private void resetHightlightedButton(JButton button){
        button.setForeground(ApplicationColor.primaryColor.value);
        button.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        button.repaint();
        button.revalidate();

    }
    
     public void refresh() {
        this.revalidate();
        this.repaint();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleTable = new javax.swing.JLabel();
        roundedPanel1 = new CAAYcyclic.PlannerClient.view.panel.component.RoundedPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        weekComboBox = new javax.swing.JComboBox<>();
        updateBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();

        titleTable.setBackground(new java.awt.Color(255, 255, 255));
        titleTable.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        titleTable.setForeground(ApplicationColor.primaryColor.value);
        titleTable.setText("Activities");

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setForeground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setToolTipText("");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableView.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableView);

        weekComboBox.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        weekComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52" }));
        weekComboBox.setToolTipText("");
        weekComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Week", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 1, 14))); // NOI18N
        weekComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weekComboBoxActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(255, 255, 255));
        updateBtn.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        updateBtn.setText("Refresh");
        updateBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        updateBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateBtnMouseExited(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(255, 255, 255));
        addBtn.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        addBtn.setText("Add");
        addBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addBtnMouseExited(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(255, 255, 255));
        editBtn.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        editBtn.setText("Edit");
        editBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        editBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(weekComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(weekComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 448, Short.MAX_VALUE))
                    .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void weekComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weekComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weekComboBoxActionPerformed

    private void updateBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMouseEntered
        // TODO add your handling code here:
         hightlightButton(updateBtn);
    }//GEN-LAST:event_updateBtnMouseEntered

    private void updateBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBtnMouseExited
        // TODO add your handling code here:
         resetHightlightedButton(updateBtn);
    }//GEN-LAST:event_updateBtnMouseExited

    private void addBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseEntered
        // TODO add your handling code here:
        hightlightButton(addBtn);
    }//GEN-LAST:event_addBtnMouseEntered

    private void addBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseExited
        // TODO add your handling code here:
         resetHightlightedButton(addBtn);
    }//GEN-LAST:event_addBtnMouseExited

    private void editBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseExited
        // TODO add your handling code here:
         resetHightlightedButton(editBtn);
    }//GEN-LAST:event_editBtnMouseExited

    private void editBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editBtnMouseEntered
        // TODO add your handling code here:
        hightlightButton(editBtn);
    }//GEN-LAST:event_editBtnMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private CAAYcyclic.PlannerClient.view.panel.component.RoundedPanel roundedPanel1;
    private javax.swing.JTable tableView;
    private javax.swing.JLabel titleTable;
    private javax.swing.JButton updateBtn;
    private javax.swing.JComboBox<String> weekComboBox;
    // End of variables declaration//GEN-END:variables
}

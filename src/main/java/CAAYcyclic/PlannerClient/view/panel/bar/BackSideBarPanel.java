/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.view.panel.bar;

import CAAYcyclic.PlannerClient.enumeration.ApplicationColor;
import javax.swing.JButton;

/**
 *
 * @author Amos
 */
public class BackSideBarPanel extends javax.swing.JPanel {

    /**
     * Creates new form BackSideBarView
     */
    public BackSideBarPanel() {
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

        backBtn = new javax.swing.JButton();

        setBackground(ApplicationColor.primaryColor.value);
        setPreferredSize(new java.awt.Dimension(137, 300));

        backBtn.setBackground(ApplicationColor.primaryColor.value);
        backBtn.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("Back");
        backBtn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10), javax.swing.BorderFactory.createMatteBorder(0, 3, 0, 0, new java.awt.Color(242, 241, 241))));
        backBtn.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        backBtn.setMargin(new java.awt.Insets(0, 10, 0, 10));
        backBtn.setOpaque(true);
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backBtnMouseExited(evt);
            }
        });
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backBtnActionPerformed

    private void backBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseEntered
        backBtn.setBackground(ApplicationColor.accentColor.value);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_backBtnMouseEntered

    private void backBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backBtnMouseExited
        backBtn.setBackground(null);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_backBtnMouseExited

    public JButton getBackBtn() {
        return backBtn;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    // End of variables declaration//GEN-END:variables
}

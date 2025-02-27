/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.view.panel.content;

import CAAYcyclic.PlannerClient.enumeration.ApplicationColor;
import CAAYcyclic.PlannerClient.view.panel.component.RoundedJTextArea;
import CAAYcyclic.PlannerClient.view.panel.component.ToggleSwitch;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
/**
 *
 * @author Amos
 */
public class ActivityFormPanel extends javax.swing.JPanel {

    /**
     * Creates new form ActivityFormPanel
     */
    public ActivityFormPanel() {
        initComponents();
        procComboBox.setBackground(Color.white);
    }

    public JLabel getETAValueLabel() {
        return ETAValueLabel;
    }

    public RoundedJTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

     
    public ToggleSwitch getInterrToggleBtn() {
        return interrToggleBtn;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JLabel getWeekValueLabel() {
        return weekValueLabel;
    }

      
    public void setSavingText(){
        saveButton.setText("Saving...");
        refresh();
    }
    
    public void setSaveText(){
        saveButton.setText("Save");
        refresh();
    }

    public JSlider getETASlider() {
        return ETASlider;
    }

    public JSlider getWeekSlider() {
        return weekSlider;
    }

    public JComboBox<String> getProcComboBox() {
        return procComboBox;
    }
    
    
    
    public void setETASliderFromInput(Integer value){
        
        this.ETASlider.setValue(value);
         ETAValueLabel.setText(Integer.toString(value));
        
    }
    
    public void setWeekSliderFromInput(Integer value){
        
        this.weekSlider.setValue(value);
         weekValueLabel.setText(Integer.toString(value));
        
    }
    

    public void refresh() {
        this.revalidate();
        this.repaint();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPanel1 = new CAAYcyclic.PlannerClient.view.panel.component.RoundedPanel();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTextArea = new CAAYcyclic.PlannerClient.view.panel.component.RoundedJTextArea();
        interruptableLabel = new javax.swing.JLabel();
        weekSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        interrToggleBtn = new CAAYcyclic.PlannerClient.view.panel.component.ToggleSwitch();
        weekLabel = new javax.swing.JLabel();
        ETALabel = new javax.swing.JLabel();
        ETASlider = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        weekValueLabel = new javax.swing.JLabel();
        ETAValueLabel = new javax.swing.JLabel();
        procComboBox = new javax.swing.JComboBox<>();
        saveButton = new javax.swing.JButton();
        titleTable = new javax.swing.JLabel();

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setForeground(new java.awt.Color(255, 255, 255));

        descriptionLabel.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        descriptionLabel.setForeground(ApplicationColor.primaryColor.value);
        descriptionLabel.setText("Description");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        descriptionTextArea.setFont(new java.awt.Font("Lucida Sans", 1, 10)); // NOI18N
        jScrollPane2.setViewportView(descriptionTextArea);

        interruptableLabel.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        interruptableLabel.setForeground(ApplicationColor.primaryColor.value);
        interruptableLabel.setText("Interruptable");

        weekSlider.setBackground(new java.awt.Color(250, 250, 250));
        weekSlider.setForeground(new java.awt.Color(250, 250, 250));
        weekSlider.setMaximum(52);
        weekSlider.setMinimum(1);
        weekSlider.setValue(1);
        weekSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                weekSliderStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N

        interrToggleBtn.setForeground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout interrToggleBtnLayout = new javax.swing.GroupLayout(interrToggleBtn);
        interrToggleBtn.setLayout(interrToggleBtnLayout);
        interrToggleBtnLayout.setHorizontalGroup(
            interrToggleBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );
        interrToggleBtnLayout.setVerticalGroup(
            interrToggleBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        weekLabel.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        weekLabel.setForeground(ApplicationColor.primaryColor.value);
        weekLabel.setText("Week");

        ETALabel.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        ETALabel.setForeground(ApplicationColor.primaryColor.value);
        ETALabel.setText("Estimated Time");

        ETASlider.setBackground(new java.awt.Color(250, 250, 250));
        ETASlider.setForeground(new java.awt.Color(250, 250, 250));
        ETASlider.setMaximum(60);
        ETASlider.setMinimum(1);
        ETASlider.setValue(1);
        ETASlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ETASliderStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel8.setText("1");

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel9.setText("52");

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel10.setText("1");

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 0, 12)); // NOI18N
        jLabel11.setText("60");

        weekValueLabel.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        weekValueLabel.setText("1");

        ETAValueLabel.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        ETAValueLabel.setText("1");

        procComboBox.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        procComboBox.setToolTipText("");
        procComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Procedure", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Sans", 1, 14))); // NOI18N
        procComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procComboBoxActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Lucida Sans", 1, 12)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(roundedPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(154, 154, 154)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(weekSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundedPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(weekValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(weekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(interrToggleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(interruptableLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ETASlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                                .addComponent(ETAValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68))
                            .addComponent(ETALabel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addComponent(procComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(interruptableLabel))
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(interrToggleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundedPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weekLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ETALabel))
                .addGap(29, 29, 29)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(weekValueLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ETAValueLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(6, 6, 6)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(weekSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ETASlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(36, 36, 36)
                .addComponent(procComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        titleTable.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        titleTable.setForeground(ApplicationColor.primaryColor.value);
        titleTable.setText("Activities");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(titleTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(titleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButtonActionPerformed

    private void weekSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_weekSliderStateChanged
        // TODO add your handling code here:
        weekValueLabel.setText(Integer.toString(weekSlider.getValue()));
        
    }//GEN-LAST:event_weekSliderStateChanged

    private void ETASliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ETASliderStateChanged
        // TODO add your handling code here:
        ETAValueLabel.setText(Integer.toString(ETASlider.getValue()));
    }//GEN-LAST:event_ETASliderStateChanged

    private void procComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_procComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ETALabel;
    private javax.swing.JSlider ETASlider;
    private javax.swing.JLabel ETAValueLabel;
    private javax.swing.JLabel descriptionLabel;
    private CAAYcyclic.PlannerClient.view.panel.component.RoundedJTextArea descriptionTextArea;
    private CAAYcyclic.PlannerClient.view.panel.component.ToggleSwitch interrToggleBtn;
    private javax.swing.JLabel interruptableLabel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> procComboBox;
    private CAAYcyclic.PlannerClient.view.panel.component.RoundedPanel roundedPanel1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel titleTable;
    private javax.swing.JLabel weekLabel;
    private javax.swing.JSlider weekSlider;
    private javax.swing.JLabel weekValueLabel;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.builder.AlertDialog.impl;

import CAAYcyclic.PlannerClient.navigation.NavigationController;
import CAAYcyclic.PlannerClient.view.frame.AlertJDialog;
import CAAYcyclic.PlannerClient.view.panel.content.AlertPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import CAAYcyclic.PlannerClient.builder.AlertDialog.IAlertBuilder;

/**
 *
 * @author Amos
 */
public class AlertDialogBuilder implements IAlertBuilder {
    
    private String title;
    private String message;
    private String positiveTitle;
    private String negativeTitle;
    private MouseAdapter positiveAction;
    private MouseAdapter negativeAction;
    private AlertJDialog dialog;
    private JFrame parentFrame;
    
    private static final Logger LOG = Logger.getLogger(AlertDialogBuilder.class.getName());

    public AlertDialogBuilder() {
    }
    
    public AlertDialogBuilder(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    @Override
    public void setParentFrame(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }
    
    /**
     * Set title or will be display the default one.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set message or will be display the default one (Empty string).
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * If not set value and MouseAdapter, it will be removed from panel.
     * If value will not be set, default value will be assigned.
     * If MouseAdapter will not be set, default MouseAdapter will be assigned(Close Dialog).
     */
    @Override
    public void setPositiveButton(String value, MouseAdapter action) {
        this.positiveTitle = value;
        this.positiveAction = action;
    }

    /**
     * If not set value and MouseAdapter, it will be removed from panel.
     * If value will not be set, default value will be assigned.
     * If MouseAdapter will not be set, default MouseAdapter will be assigned(Close Dialog).
     */
    @Override
    public void setNegativeButton(String value, MouseAdapter action) {
        this.negativeTitle = value;
        this.negativeAction = action;
    }
    
     
    @Override
    public void display() {
        if(parentFrame == null){
            LOG.log(java.util.logging.Level.SEVERE, "Dialog parent frame not exist.");
            return;
        }
        dialog = new AlertJDialog(parentFrame, true);
        dialog.setContentPane(getAlertPanel());
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        LOG.log(java.util.logging.Level.WARNING, "Showing Dialog.");
    }
    
    
    public void dispose() {
        if(dialog == null){
            LOG.log(java.util.logging.Level.SEVERE, "The Dialog to dispose not exist.");
            return;
        }
        dialog.dispose();
    }
    
    private JPanel getAlertPanel() {
        AlertPanel alertPanel = new AlertPanel();
        customizeTitle(alertPanel);
        customizeMessage(alertPanel);
        customizeNegativeBtn(alertPanel);
        customizePositiveBtn(alertPanel);
        alertPanel.refresh();
        return alertPanel;
    }
    
    private void customizeTitle(AlertPanel alertPanel){
        if(title != null){
            alertPanel.getTitleDlg().setText(title);
        }
    }
    
    private void customizeMessage(AlertPanel alertPanel){
        if(message != null){
            alertPanel.getMessageDlg().setText(message);
        } else {
            alertPanel.getMessageDlg().setText("");
        }
    }
    
    private void customizePositiveBtn(AlertPanel alertPanel){
        JButton positiveButton = alertPanel.getPositiveBtn();
        if(positiveTitle == null && positiveAction == null){
            positiveButton.setVisible(false);
            return;
        }
        
        if(positiveTitle != null){
            positiveButton.setText(positiveTitle);
        } else {
           positiveButton.setText("Ok");
        }
        
        if(positiveAction != null){
            positiveButton.addMouseListener(closeDialogAction);
            positiveButton.addMouseListener(positiveAction);
        } else {
            positiveButton.addMouseListener(closeDialogAction);
        }
    }
    
    private void customizeNegativeBtn(AlertPanel alertPanel){
        JButton negativeButton = alertPanel.getNegativeBtn();
        if(negativeTitle == null && negativeAction == null){
            negativeButton.setVisible(false);
            return;
        }
        if(negativeTitle!= null){
            negativeButton.setText(negativeTitle);
        } else {
           negativeButton.setText("Cancel");
        }
        
        if(negativeAction != null){
            negativeButton.addMouseListener(closeDialogAction);
            negativeButton.addMouseListener(negativeAction);
        } else {
            negativeButton.addMouseListener(closeDialogAction);
        }
    }
    
    public void setDefaultPositiveAction(){
        positiveAction = closeDialogAction;
    }
    
    public void setDefaultNegativeAction(){
        negativeAction = closeDialogAction;
    }
    
    private final MouseAdapter closeDialogAction = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            dispose();
        }
    };
    
}

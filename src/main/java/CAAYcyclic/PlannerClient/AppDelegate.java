/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient;

import CAAYcyclic.PlannerClient.coordinator.ICoordinator;
import CAAYcyclic.PlannerClient.coordinator.impl.AppCoordinator;
import CAAYcyclic.PlannerClient.navigation.NavigationController;
import CAAYcyclic.PlannerClient.view.frame.MainFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Youssef
 */
public class AppDelegate extends WindowAdapter {

    private static AppDelegate istance = null;
    
    private MainFrame mainFrame;
    
    private ICoordinator appCordinator;

    private AppDelegate() {}

    public static AppDelegate getIstance() {
        if(istance==null)
            istance = new AppDelegate();
        return istance;
    }


    public static void main(String[] args) {
        newMain();
       // oldMain();
    }
    
        
    public static void newMain() {
        AppDelegate.getIstance().mainFrame = new MainFrame();
        AppDelegate.getIstance().mainFrame.setWindowsAdapter(istance);
        AppDelegate.getIstance().appCordinator = new AppCoordinator(new NavigationController(AppDelegate.getIstance().mainFrame));       
        AppDelegate.getIstance().appCordinator.start();
        AppDelegate.getIstance().mainFrame.setVisible(true);
        AppDelegate.getIstance().mainFrame.pack();
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }


    /**
     * Invoked when a window has been opened.
     * */
    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);
    }

    /**
     * Invoked when a window is in the process of being closed.
     * The close operation can be overridden at this point.
     * */
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
    }


    /**
     * Invoked when a window has been closed.
     * */
    @Override
    public void windowClosed(WindowEvent e) {
        super.windowClosed(e);
    }

    /**
     * Invoked when a window is iconified.
     * */
    @Override
    public void windowIconified(WindowEvent e) {
        super.windowIconified(e);
    }

    /**
     * Invoked when a window is de-iconified.
     * */
    @Override
    public void windowDeiconified(WindowEvent e) {
        super.windowDeiconified(e);
    }

    /**
     * Invoked when a window is activated.
     * */
    @Override
    public void windowActivated(WindowEvent e) {
        super.windowActivated(e);
    }

    /**
     * Invoked when a window is de-activated.
     * */
    @Override
    public void windowDeactivated(WindowEvent e) {
        super.windowDeactivated(e);
    }

    /**
     * Invoked when a window state is changed.
     * */
    @Override
    public void windowStateChanged(WindowEvent e) {
        super.windowStateChanged(e);
    }

    /**
     * Invoked when the Window is set to be the focused Window, which means
     * that the Window, or one of its sub components, will receive keyboard
     * events.
     * */
    @Override
    public void windowGainedFocus(WindowEvent e) {
        super.windowGainedFocus(e);
    }

    /**
    * Invoked when the Window is no longer the focused Window,
    * which means that keyboard events will no longer
    * be delivered to the Window or any of its sub components.
    * */
    @Override
    public void windowLostFocus(WindowEvent e) {
        super.windowLostFocus(e);
    }
}
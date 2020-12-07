/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.SystemAdiminClient.controller.frame;

import CAAYcyclic.SystemAdiminClient.controller.container.ContainerController;
import CAAYcyclic.SystemAdiminClient.factory.container.HomeContainerViewFactory;
import CAAYcyclic.SystemAdiminClient.factory.container.IContainerViewAbstractFactory;
import CAAYcyclic.SystemAdiminClient.navigation.NavigationController;
import CAAYcyclic.SystemAdiminClient.view.frame.MainFrame;
import java.awt.event.WindowAdapter;
import javax.swing.SwingUtilities;

/**
 *
 * @author Youssef
 */
public class MainFrameController {

    private final MainFrame mainFrame;

    private ContainerController containerController;

    public MainFrameController() {
        this.mainFrame = new MainFrame();
        NavigationController.getInstance().registerFrameController(this);
        this.containerController = new ContainerController(new HomeContainerViewFactory());
        initView();
    }

    private void initView() {
        SwingUtilities.invokeLater(() -> {
            mainFrame.setFrameContent(containerController.getContainerView());
            mainFrame.setVisible(true);
        });
    }
    
    public void setMainFrameWindowsAdapter(WindowAdapter windowAdapter){
        mainFrame.setWindowsAdapter(windowAdapter);
    }

    public void setFrameTitle(String title) {
        mainFrame.setTitle(title);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setContainerView(ContainerController mainViewController, IContainerViewAbstractFactory panelFrameAbstractFactory) {
        this.containerController = new ContainerController(panelFrameAbstractFactory);
        mainFrame.setFrameContent(mainViewController.getContainerView());
    }
    
    public void setContainerView(ContainerController containerController) {
        this.containerController = containerController;
        SwingUtilities.invokeLater(() -> {
            mainFrame.setFrameContent(containerController.getContainerView());
        });
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }
}
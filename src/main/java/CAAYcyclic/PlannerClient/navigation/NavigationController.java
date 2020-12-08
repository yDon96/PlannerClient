/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.navigation;

import CAAYcyclic.PlannerClient.controller.PanelController;
import CAAYcyclic.PlannerClient.controller.frame.MainFrameController;
import CAAYcyclic.PlannerClient.controller.bar.BarController;
import CAAYcyclic.PlannerClient.controller.container.ContainerController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;
import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;

/**
 *
 * @author Youssef
 * 
 * A navigation controller is a controller that helps container view controller to 
 * manage one or more child view controllers in a navigation interface.
 */
public class NavigationController {
    
    private static NavigationController instance = null;
    
    private static final Logger LOG = Logger.getLogger(NavigationController.class.getName()); 
         
    private MainFrameController frameController;

    private ContainerController containerController;

    private final ArrayList<ContentPanelController> panelControllers;

    private final ArrayList<BarController> barControllers;
    
    private final HashMap<String,ContentPanelController> panelControllerMap;


    private NavigationController() {
        LOG.log(java.util.logging.Level.CONFIG, "Init.");
        panelControllers = new ArrayList<>();
        barControllers = new ArrayList<>();
        panelControllerMap = new HashMap<>();
    }

    public static NavigationController getInstance() {
        if(instance==null)
            instance = new NavigationController();
        return instance;
    }
    
    /**
     * Presents the specified content panel controller in the navigation interface.
     * @param panelControllerName
     */
    public void performPanelNavigationTo(Class<? extends PanelController> panelControllerName) {
            LOG.log(java.util.logging.Level.INFO, "Perform navigation to panel: {0}.", panelControllerName.getName());
            ContentPanelController contentPanelController;
            if(panelControllerMap.containsKey(panelControllerName.getName())){
                LOG.log(java.util.logging.Level.CONFIG, "Found old instance in map.");
                contentPanelController = panelControllerMap.get(panelControllerName.getName());
            }else {
                try{
                    LOG.log(java.util.logging.Level.CONFIG, "Create new instance.");
                    contentPanelController = (ContentPanelController) panelControllerName.newInstance();
                } catch (IllegalAccessException | InstantiationException exception){
                    LOG.log(java.util.logging.Level.WARNING, "Could not create a new instance of: {0}.", panelControllerName.getName());
                    return;
                }
            }
            containerController.getContentPanelController().prepare(new Segue(contentPanelController));
            containerController.setContentPanel(contentPanelController);
            containerController.refreshView();
    }
    
    /**
     * Pops the top view controller from the navigation stack and updates the display.
     */
    public void performBackToStack() {
        LOG.log(java.util.logging.Level.INFO, "Restore last view in stack.");
        if(panelControllers.size() > 0 && barControllers.size() > 0) {
            ContentPanelController contentController = panelControllers.remove(panelControllers.size() - 1);
            BarController barController = barControllers.remove(barControllers.size() - 1);
            ContentPanelController currentContentController = containerController.getContentPanelController();
            BarController currentBarController = containerController.getBarController();
            currentContentController.panelWillDisappear();
            currentBarController.panelWillDisappear();
            containerController.setContentPanel(contentController);
            containerController.setBarPanel(barController);
            containerController.refreshView();
            currentContentController.panelDidDisappear();
            currentBarController.panelDidDisappear();
        } else {
            LOG.log(java.util.logging.Level.WARNING, "No view is present in stack history."); 
        }
    }
    
    /**
     * Add content view controller to HashMap.
     * @param contentPanelController
     */
    public void addPanelToMap(ContentPanelController contentPanelController){
        LOG.log(java.util.logging.Level.CONFIG, "Add panel {0} to map.", contentPanelController.getClass().getName()); 
        panelControllerMap.put(contentPanelController.getClass().getName(),contentPanelController);
    }
    
    /** 
     * Add view controller(BarController, ContentPanelController) to stack.
     * @param barController
     * @param contentPanelController
     */
    private void addViewToStack(BarController barController,ContentPanelController contentPanelController){
        LOG.log(java.util.logging.Level.CONFIG, "Add view [{0}] to stack.",barController.getClass().getName() + "," + contentPanelController.getClass().getName()); 
        panelControllers.add(contentPanelController);
        barControllers.add(barController);
    }
    
    /**
     * Presents the specified view controller defined by the factory in the navigation interface.
     * @param factory ContainerViewAbstractFactory
     */
    public void performViewNavigationTo(IContainerViewAbstractFactory factory){
        LOG.log(java.util.logging.Level.INFO, "Perform navigation to view given by: {0}.", factory.getClass().getName()); 
        ContentPanelController contentController = containerController.getContentPanelController();
        BarController barController = containerController.getBarController();
        if(contentController != null && barController != null){
            addViewToStack(barController,contentController);
            contentController.panelWillDisappear();
            barController.panelWillDisappear();
            panelControllerMap.clear();
        }

        factory.getContentPanelController();
        factory.getBarController();
        containerController.refreshView();
        if(contentController != null && barController != null){
            contentController.panelDidDisappear();
            barController.panelDidDisappear();
        }    
    }
    
    /**
     *  Lock panel navigation
     */
    public void lockNavigation(){
        containerController.getBarController().lockNavigation();
        containerController.getContentPanelController().lockNavigation();
    }
    
    /** 
     * Unlock panel navigation
     */
    public void unlockNavigation(){
        containerController.getBarController().unlockNavigation();
        containerController.getContentPanelController().unlockNavigation();
    }
    
    /**
     * Register the current container associated 
     * with the currently visible panel
     * @param mainViewController
     */
    public void registerContainerController(ContainerController mainViewController){
        LOG.log(java.util.logging.Level.CONFIG, 
                "Set current container controller: {0}.", 
                mainViewController.getClass().getName()); 
        this.containerController = mainViewController;
    }
    
    /**
     * Register the current frame associated 
     * with the currently visible container.
     * @param myFrameController
     */
    public void registerFrameController(MainFrameController myFrameController){
        LOG.log(java.util.logging.Level.CONFIG, 
                "Set current frame controller: {0}.", 
                myFrameController.getClass().getName()); 
        this.frameController = myFrameController;
    }
    
    /**
     * The container controller associated 
     * with the currently visible panel in the navigation interface.
     * @return ContainerController type.
     */
    public ContainerController getCurrentContainer(){
        return containerController;
    }
    
    /**
     * The frame controller associated 
     * with the currently visible container in the navigation interface.
     * @return MainFrameController type.
     */
    public MainFrameController getFrameController(){
        return frameController;
    }
    
}

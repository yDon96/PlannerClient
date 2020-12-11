/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.navigation;

import CAAYcyclic.PlannerClient.controller.IPanelController;
import CAAYcyclic.PlannerClient.controller.PanelController;
import CAAYcyclic.PlannerClient.controller.frame.MainFrameController;
import CAAYcyclic.PlannerClient.controller.bar.BarController;
import CAAYcyclic.PlannerClient.controller.container.ContainerController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;
import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;
import CAAYcyclic.PlannerClient.view.frame.MainFrame;
import javax.swing.JFrame;

/**
 *
 * @author Youssef
 * 
 * A navigation controller is a controller that helps container view controller to 
 * manage one or more child view controllers in a navigation interface.
 */
public class NavigationController {
    
    @Deprecated
    private static NavigationController instance = null;
    
    private static final Logger LOG = Logger.getLogger(NavigationController.class.getName()); 
         
    @Deprecated
    private MainFrameController frameController;

    @Deprecated
    private ContainerController containerController;

    private final ArrayList<IPanelController> panelControllers;

    private final ArrayList<IPanelController> barControllers;
    
    private final HashMap<String,IPanelController> panelControllerMap;
    
    private MainFrame currentFrame;


    @Deprecated
    private NavigationController() {
        LOG.log(java.util.logging.Level.CONFIG, "Init.");
        panelControllers = new ArrayList<>();
        barControllers = new ArrayList<>();
        panelControllerMap = new HashMap<>();
    }
    
    public NavigationController(MainFrame frame) {
        LOG.log(java.util.logging.Level.CONFIG, "Init.");
        panelControllers = new ArrayList<>();
        barControllers = new ArrayList<>();
        panelControllerMap = new HashMap<>();
        currentFrame = frame;
    }

    @Deprecated
    public static NavigationController getInstance() {
        if(instance==null)
            instance = new NavigationController();
        return instance;
    }
    
    /**
     * Presents the specified content panel controller in the navigation interface.
     * @param panelControllerName
     */
    @Deprecated
    public void performPanelNavigationTo(Class<? extends PanelController> panelControllerName) {
            LOG.log(java.util.logging.Level.INFO, "Perform navigation to panel: {0}.", panelControllerName.getName());
            ContentPanelController contentPanelController;
            if(panelControllerMap.containsKey(panelControllerName.getName())){
                LOG.log(java.util.logging.Level.CONFIG, "Found old instance in map.");
                contentPanelController = (ContentPanelController) panelControllerMap.get(panelControllerName.getName());
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
     * Presents the specified content panel controller in the navigation
     * interface.
     *
     * @param panelController panel's controller to visualize in the current view.
     */
    public void performPanelNavigationTo(IPanelController panelController) {
        LOG.log(java.util.logging.Level.INFO, "Perform navigation to panel: {0}.", panelController.getClass().getName());
        IPanelController currentContentController = panelControllers.remove(panelControllers.size() - 1);
        notifyPanelWillDisappear(currentContentController);
        notifyPanelWillAppear(panelController);
        panelControllers.add(panelController);
        addPanelToMap(currentContentController);
        currentFrame.setContentPanel(panelController.getPanel());
        notifyPanelDidAppear(panelController);
        notifyPanelDidDisappear(currentContentController);
    }
    
    /**
     * Pops the top view controller from the navigation stack and updates the display.
     */
    @Deprecated
    public void performBackToStack() {
        LOG.log(java.util.logging.Level.INFO, "Restore last view in stack.");
        if(panelControllers.size() > 0 && barControllers.size() > 0) {
            IPanelController contentController = panelControllers.remove(panelControllers.size() - 1);
            IPanelController barController = barControllers.remove(barControllers.size() - 1);
            ContentPanelController currentContentController = containerController.getContentPanelController();
            BarController currentBarController = containerController.getBarController();
            currentContentController.panelWillDisappear();
            currentBarController.panelWillDisappear();
            containerController.setContentPanel((ContentPanelController) contentController);
            containerController.setBarPanel((BarController) barController);
            containerController.refreshView();
            currentContentController.panelDidDisappear();
            currentBarController.panelDidDisappear();
        } else {
            LOG.log(java.util.logging.Level.WARNING, "No view is present in stack history."); 
        }
    }
    
    /**
     * Pops the top view controller from the navigation stack and updates the
     * display.
     */
    public void performBackStack() {
        LOG.log(java.util.logging.Level.INFO, "Restore last view in stack.");
        if (panelControllers.size() > 0 && barControllers.size() > 0) {
            IPanelController currentContentController = panelControllers.remove(panelControllers.size() - 1);
            IPanelController currentBarController = barControllers.remove(barControllers.size() - 1);
            IPanelController contentController = panelControllers.get(panelControllers.size() - 1);
            IPanelController barController = barControllers.get(barControllers.size() - 1);
            notifyViewWillDisappear(currentBarController, currentContentController);
            notifyViewWillAppear(barController, contentController);
            currentFrame.setContentPanel(contentController.getPanel());
            currentFrame.setBarPanel(barController.getPanel());
            notifyViewDidAppear(barController, contentController);
            notifyViewDidDisappear(currentBarController, currentContentController);
        } else {
            LOG.log(java.util.logging.Level.WARNING, "No view is present in stack history.");
        }
    }
    
    /**
     * Add content's controller to HashMap.
     *
     * @param contentPanelController 
     */
    public void addPanelToMap(IPanelController contentPanelController) {
        LOG.log(java.util.logging.Level.CONFIG, "Add panel {0} to map.", contentPanelController.getClass().getName());
        panelControllerMap.put(contentPanelController.getClass().getName(), contentPanelController);
    }
    
    /**
     * Add content view controller to HashMap.
     * @param contentPanelController
     */
    @Deprecated
    public void addPanelToMap(ContentPanelController contentPanelController){
        LOG.log(java.util.logging.Level.CONFIG, "Add panel {0} to map.", contentPanelController.getClass().getName()); 
        panelControllerMap.put(contentPanelController.getClass().getName(),contentPanelController);
    }
    
    /** 
     * Add view controller(BarController, ContentPanelController) to stack.
     * @param barController
     * @param contentPanelController
     */
    @Deprecated
    private void addViewToStack(BarController barController,ContentPanelController contentPanelController){
        LOG.log(java.util.logging.Level.CONFIG, "Add view [{0}] to stack.",barController.getClass().getName() + "," + contentPanelController.getClass().getName()); 
        panelControllers.add(contentPanelController);
        barControllers.add(barController);
    }
    
    /**
     * Add view controller(bar's controller and content's controller) to stack.
     *
     * @param barController bar's controller to visualize.
     * @param contentPanelController content's controller to visualize.
     */
    private void addViewToStack(IPanelController barController, IPanelController contentPanelController) {
        LOG.log(java.util.logging.Level.CONFIG, "Add view [{0}] to stack.", barController.getClass().getName() + "," + contentPanelController.getClass().getName());
        panelControllers.add(contentPanelController);
        barControllers.add(barController);
    }
    
    /**
     * Presents the specified view controller defined by the factory in the navigation interface.
     * @param factory ContainerViewAbstractFactory
     */
    @Deprecated
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
     * Presents the specified view controller defined by the  bar's controller and content's controller.
     *
     * @param barController bar's controller to visualize.
     * @param contentPanelController content's controller to visualize.
     */
    public void performViewNavigationTo(IPanelController barController, IPanelController contentPanelController) {
        LOG.log(java.util.logging.Level.INFO, "Perform navigation to view given by: {0}.", barController.getClass().getName() + " " + contentPanelController.getClass().getName());
        if (!isCurrentView(barController, contentPanelController)) {
            int panelStackSize = panelControllers.size();
            int barStackSize = barControllers.size();
            if (panelStackSize > 0 && barStackSize > 0) {
                notifyViewWillDisappear(barControllers.get(barStackSize - 1), panelControllers.get(panelStackSize - 1));
                panelControllerMap.clear();
            }
            addViewToStack(barController, contentPanelController);
            notifyViewWillAppear(barController, contentPanelController);
            currentFrame.setBarPanel(barController.getPanel());
            currentFrame.setContentPanel(contentPanelController.getPanel());
            notifyViewDidAppear(barController, contentPanelController);
            if (panelStackSize > 0 && barStackSize > 0) {
                notifyViewDidDisappear(barControllers.get(barStackSize - 1), panelControllers.get(panelStackSize - 1));
            }
        } else {
            LOG.log(java.util.logging.Level.WARNING, "Trying to perform navigation to the same current view.");
        }
    }
    
    /**
     *  Lock panel navigation
     */
    @Deprecated
    public void lockNavigation(){
        containerController.getBarController().lockNavigation();
        containerController.getContentPanelController().lockNavigation();
    }
    
    /** 
     * Unlock panel navigation
     */
    @Deprecated
    public void unlockNavigation(){
        containerController.getBarController().unlockNavigation();
        containerController.getContentPanelController().unlockNavigation();
    }
    
    /**
     * Register the current container associated 
     * with the currently visible panel
     * @param mainViewController
     */
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    public ContainerController getCurrentContainer(){
        return containerController;
    }
    
    /**
     * The frame controller associated 
     * with the currently visible container in the navigation interface.
     * @return MainFrameController type.
     */
    @Deprecated
    public MainFrameController getFrameController(){
        return frameController;
    }
    
    /**
     * Notify both bar and content that panels is going to be no more visible.
     * 
     * @param barController bar's controller to notify.
     * @param contentPanelController content's controller to notify.
     */
    private void notifyViewWillDisappear(IPanelController barController, IPanelController contentPanelController) {
        contentPanelController.panelWillDisappear();
        barController.panelWillDisappear();
    }

    /**
     * Notify both bar and content that panels is no more visible.
     * 
     * @param barController bar's controller to notify.
     * @param contentPanelController content's controller to notify.
     */
    private void notifyViewDidDisappear(IPanelController barController, IPanelController contentPanelController) {
        contentPanelController.panelDidDisappear();
        barController.panelDidDisappear();
    }

    /**
     * Notify both bar and content that panels is going to be visible.
     * 
     * @param barController bar's controller to notify.
     * @param contentPanelController content's controller to notify.
     */
    private void notifyViewWillAppear(IPanelController barController, IPanelController contentPanelController) {
        contentPanelController.panelWillAppear();
        barController.panelWillAppear();
    }

    /**
     * Notify both bar and content controller that panels is visible.
     * 
     * @param barController bar's controller to notify.
     * @param contentPanelController content's controller to notify.
     */
    private void notifyViewDidAppear(IPanelController barController, IPanelController contentPanelController) {
        contentPanelController.panelDidAppear();
        barController.panelDidAppear();
    }

    /**
     * Notify panel's controller that panel is going to be no more visible.
     * 
     * @param controller panel's controller to notify.
     */
    private void notifyPanelWillDisappear(IPanelController controller) {
        controller.panelWillDisappear();
    }

    /**
     * Notify panel's controller that panel is no more visible.
     * 
     * @param controller panel's controller to notify.
     */
    private void notifyPanelDidDisappear(IPanelController controller) {
        controller.panelDidDisappear();
    }

    /**
     * Notify panel's controller that panel is going to be visible.
     * 
     * @param controller panel's controller to notify.
     */
    private void notifyPanelWillAppear(IPanelController controller) {
        controller.panelWillAppear();
    }
    
    /**
     * Notify panel's controller that panel is visible.
     * 
     * @param controller panel's controller to notify.
     */
    private void notifyPanelDidAppear(IPanelController controller) {
        controller.panelDidAppear();
    }

    /**
     * Get the displayed frame.
     */
    public JFrame getCurrentFrame() {
        return currentFrame;
    }

    /**
     * Check if barController and contentPanelController determinate the current view controller.
     * 
     * @param barController controller of bar panel.
     * @param contentPanelController controller of content panel.
     * @return True if the two controller are present as last controller of the stack, False otherwise.
     */
    private boolean isCurrentView(IPanelController barController, IPanelController contentPanelController) {
        if(barControllers.size() <=0 && panelControllers.size() <= 0){
            return false;
        }
        return barController.equals(barControllers.get(barControllers.size() - 1)) && contentPanelController.equals(panelControllers.get(panelControllers.size() - 1));
    }
}

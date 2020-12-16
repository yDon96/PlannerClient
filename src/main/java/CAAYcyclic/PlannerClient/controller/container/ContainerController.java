///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package CAAYcyclic.PlannerClient.controller.container;
//
//import CAAYcyclic.PlannerClient.view.panel.container.ContainerView;
//import CAAYcyclic.PlannerClient.controller.bar.BarController;
//import CAAYcyclic.PlannerClient.navigation.NavigationController;
//import javax.swing.SwingUtilities;
//import CAAYcyclic.PlannerClient.controller.content.ContentPanelController;
//import CAAYcyclic.PlannerClient.factory.container.IContainerViewAbstractFactory;
//
///**
// *
// * @author Youssef
// */
//@Deprecated
//public class ContainerController {
//
//    private ContainerView containerView;
//
//    private IContainerViewAbstractFactory panelFrameAbstractFactory;
//
//    private BarController barController;
//
//    private ContentPanelController contentPanelController;
//
//    public ContainerController(IContainerViewAbstractFactory panelFrameAbstractFactory) {
//        NavigationController.getInstance().registerContainerController(this);
//        initView();
//        this.panelFrameAbstractFactory = panelFrameAbstractFactory;
//        NavigationController.getInstance().performViewNavigationTo(panelFrameAbstractFactory);
//    }
//    
//    private void initView() {
//        SwingUtilities.invokeLater(new Runnable(){
//            @Override 
//            public void run() {
//                containerView = new ContainerView();
//            }
//        });
//    }
//
//    public void setBarPanel(BarController iSideBarController) {
//        this.barController = iSideBarController;
//        containerView.setNavigationView(iSideBarController.getPanel());
//    }
//    
//    public void setContentPanel(ContentPanelController iContentPanelController) {
//        this.contentPanelController = iContentPanelController;
//        containerView.setContentView(iContentPanelController.getPanel());   
//    }
//    
//    public void refreshView(){
//        containerView.refresh();
//    }
//
//    public BarController getBarController() {
//        return barController;
//    }
//
//    public ContentPanelController getContentPanelController() {
//        return contentPanelController;
//    }
//
//    public ContainerView getContainerView() {
//        return containerView;
//    }
//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.delegate;

import java.util.List;

/**
 *
 * @author Youssef
 */
public interface ApiDelegate<T> {
    
    void onFailure(String message);
    
    abstract void onGetSuccess(T object);
    
    void onGetAllSuccess(List<T> objects);
    
    void onCreateSuccess();
}

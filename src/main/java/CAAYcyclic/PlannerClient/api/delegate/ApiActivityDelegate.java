/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.delegate;

import CAAYcyclic.PlannerClient.model.Activity;
import java.util.List;

/**
 *
 * @author User
 */
public interface ApiActivityDelegate extends ApiDelegate<Activity> {
    
    @Override
    public void onGetAllSuccess(List<Activity> activities);

    @Override
    public void onGetSuccess(Activity activity);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api.delegate;

import CAAYcyclic.PlannerClient.model.Procedure;
import java.util.List;

/**
 *
 * @author Amos
 */
public interface ApiProcedureDelegate extends ApiDelegate<Procedure> {

    @Override
    public void onGetAllSuccess(List<Procedure> procedures);

    @Override
    public void onGetSuccess(Procedure procedure);
       
}

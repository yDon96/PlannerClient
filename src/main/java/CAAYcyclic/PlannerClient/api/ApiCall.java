/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api;

import CAAYcyclic.PlannerClient.model.Activity;
import CAAYcyclic.PlannerClient.model.Procedure;
import CAAYcyclic.PlannerClient.model.User;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 *
 * @author Youssef
 */
public interface ApiCall {
    
    @GET("/procedure")
    public Call<Procedure> getProcedure(@Query("id") String id);
    
    @GET("/procedures")
    public Call<List<Procedure>> getAllProcedure();
    
    @POST("/procedure")
    public Call<ResponseBody> postProcedure(@Body Procedure procedure);
    
    @GET("/user")
    public Call<User> getUser(@Query("id") String id);
    
    @GET("/users")
    public Call<List<User>> getAllUser();
    
    @POST("/user")
    public Call<ResponseBody> postUser(@Body User user);
    
    @GET("/activity")
    public Call<List<Activity>> getActivity(@Query("id") String id);
    
    @GET("/activities")
    public Call<List<Activity>> getAllActivity();
    
    @POST("/activity")
    public Call<ResponseBody> postActivity(@Body Activity activity);
    
    @PUT("/activity")
    public Call<ResponseBody> putActivity(@Body Activity activity);
    
    
    
    
}

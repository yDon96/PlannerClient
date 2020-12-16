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
 * @author Amos
 */
public interface ApiCall {
    
    /*START PROCEDURES API */
    @GET("/procedure")
    public Call<Procedure> getProcedure(@Query("id") String id);
    
    @GET("/procedures")
    public Call<List<Procedure>> getAllProcedure();
    
    @POST("/procedure")
    public Call<ResponseBody> postProcedure(@Body Procedure procedure);    
    /*END PROCEDURES API */
    
    /*START USERS API */      
    @GET("/user")
    public Call<User> getUser(@Query("id") String id);
    
    @GET("/users")
    public Call<List<User>> getAllUser(@Query("roles") List<String> listRoles);
    
    @POST("/user")
    public Call<ResponseBody> postUser(@Body User user);    
    /*END USERS API */  
    
    /*START ACTIVITIES API */
    @GET("/activity")
    public Call<List<Activity>> getActivity(@Query("id") String id);
    
    @GET("/activities")
    public Call<List<Activity>> getAllActivity();
    
    @GET("/activities/week/{week}")
    public Call<List<Activity>> getActivityByWeek(@Path("week") Integer week);
    
    @POST("/activity")
    public Call<ResponseBody> postActivity(@Body Activity activity);
    
    @PUT("/activity")
    public Call<ResponseBody> putActivity(@Body Activity activity);
    
    @GET("/activities/week/{week}/day/{day}")
    public Call<List<Activity>> getActivityByWeekDayUser(@Path("week") Integer week,
    @Path("day") Integer day, @Query("userId") Integer id);
    /*END ACTIVITIES API */
    
    /*START COMPETENCIES API */   
    @GET("/competencies/count")
    public Call<Integer> getNumberOfMaintCompetenciesByProc(
    @Query("userId") Integer userId, @Query("procedureId") Integer procedureId);
    /*END COMPETENCIES API */   
    
    
    
    
}

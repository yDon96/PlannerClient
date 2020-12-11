/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAAYcyclic.PlannerClient.api;

import CAAYcyclic.PlannerClient.api.delegate.ApiDelegate;
import CAAYcyclic.PlannerClient.api.model.Activity;
import CAAYcyclic.PlannerClient.api.model.Procedure;
import CAAYcyclic.PlannerClient.api.model.ServerSettings;
import CAAYcyclic.PlannerClient.api.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import retrofit2.Call;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * @author Youssef
 */
public class ApiManager {

    private Integer seconds = 10;
    private ApiCall apiCall;
    private ApiDelegate apiDelegate;
         
    private static ApiManager istance = null;
    private static final Logger LOG = Logger.getLogger(ApiManager.class.getName());

        
    public static ApiManager getIstance() {
        if(istance==null)
            istance = new ApiManager();
        return istance;
    }
     
    public ApiManager() {
        LOG.log(java.util.logging.Level.CONFIG, "Init ApiManager");
        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(seconds,TimeUnit.SECONDS)
            .readTimeout(seconds,TimeUnit.SECONDS)
            .writeTimeout(seconds,TimeUnit.SECONDS)
            .build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(new ServerSettings().getUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build();

        apiCall = retrofit.create(ApiCall.class);
    }
    
    
    
    public void createUser(User user,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Post user: {0}.", user.getName() + " "  + user.getSurname());
        this.apiDelegate = apiDelegate;
        Call<ResponseBody> call = apiCall.postUser(user);
        call.enqueue(postCallback);
    }
    
    public void getUser(Integer id,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Get user with id: {0}.", String.valueOf(id));
        this.apiDelegate = apiDelegate;
        Call<User> call = apiCall.getUser(String.valueOf(id));
        call.enqueue(getUserCallback);
    }
    
    public void getUsers(ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Get all users.");
        this.apiDelegate = apiDelegate;
        Call<List<User>> call = apiCall.getAllUser();
        call.enqueue(getUsersCallback);
    }
    
    public void createProcedure(Procedure procedure,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Post procedure: {0}.", procedure.getTitle());
        this.apiDelegate = apiDelegate;
        Call<ResponseBody> call = apiCall.postProcedure(procedure);
        call.enqueue(postCallback);
    }
    
    public void getProcedure(Integer id,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Get procedure with id: {0}.", String.valueOf(id));
        this.apiDelegate = apiDelegate;
        Call<Procedure> call = apiCall.getProcedure(String.valueOf(id));
        call.enqueue(getProcedureCallback);
    }
    
    public void getProcedures(ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Get all procedure.");
        this.apiDelegate = apiDelegate;
        Call<List<Procedure>> call = apiCall.getAllProcedure();
        call.enqueue(getProceduresCallback);
    }
    
    
     public void createActivity(Activity activity,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Post activity: {0}.", activity.getDescription());
        this.apiDelegate = apiDelegate;
        Call<ResponseBody> call = apiCall.postActivity(activity);
  
        call.enqueue(postCallback);
    }
     
    public void updateActivity(Activity activity,ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Put activity: {0}.", activity.getDescription());
        this.apiDelegate = apiDelegate;
        Call<ResponseBody> call = apiCall.putActivity(activity);
  
        call.enqueue(putCallback);
        
    }
    
    public void getActivities (ApiDelegate apiDelegate){
        LOG.log(java.util.logging.Level.CONFIG, "Get all activities.");
        this.apiDelegate = apiDelegate;
        Call<List<Activity>> call = apiCall.getAllActivity();
        call.enqueue(getActivitiesCallback);
        
    }
    
    private Callback<ResponseBody> postCallback = new Callback<ResponseBody>(){
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Post response is successful.");
                ResponseBody body = response.body();
                if(response != null){
                    apiDelegate.onCreateSuccess();
                }
            } else {
                LOG.log(java.util.logging.Level.SEVERE, "Post response is not successful.");
                apiDelegate.onFailure("Creation response is not successful.");
            }
        };

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Post response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
     private Callback<ResponseBody> putCallback = new Callback<ResponseBody>(){
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Put response is successful.");
                ResponseBody body = response.body();
                if(response != null){
                    apiDelegate.onCreateSuccess();
                }
            } else {
                LOG.log(java.util.logging.Level.SEVERE, "Put response is not successful.");
                apiDelegate.onFailure("Update response is not successful.");
            }
        };

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Put response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
    private Callback<User> getUserCallback = new Callback<User>(){
        @Override
        public void onResponse(Call<User> call, Response<User> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Get user response is successful.");
                User user = response.body();
                if(user != null){
                    apiDelegate.onGetSuccess(user);
                }
            } else {
               LOG.log(java.util.logging.Level.SEVERE, "Get user response is not successful.");
               apiDelegate.onFailure("Could not get user.");
            }
        };

        @Override
        public void onFailure(Call<User> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Get user response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
    private Callback<List<User>> getUsersCallback = new Callback<List<User>>(){
        @Override
        public void onResponse(Call<List<User>> call, Response<List<User>> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Get users response is successful.");
                List<User> users = response.body();
                LOG.log(java.util.logging.Level.CONFIG, "Get {0} users.", String.valueOf(users.size()));
                if(users != null){
                    apiDelegate.onGetAllSuccess(users);
                }
            } else {
                LOG.log(java.util.logging.Level.SEVERE, "Get users response is not successful.");
                apiDelegate.onFailure("Could not get users.");
            }
        };

        @Override
        public void onFailure(Call<List<User>> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Get users response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
    private Callback<Procedure> getProcedureCallback = new Callback<Procedure>(){
        @Override
        public void onResponse(Call<Procedure> call, Response<Procedure> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Get procedure response is successful.");
                Procedure procedure = response.body();
                if(procedure != null){
                    apiDelegate.onGetSuccess(procedure);
                }
            } else {
               LOG.log(java.util.logging.Level.SEVERE, "Get procedure response is not successful.");
               apiDelegate.onFailure("Could not get procedure.");
            }
        };

        @Override
        public void onFailure(Call<Procedure> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Get procedure response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
    
    private Callback<List<Procedure>> getProceduresCallback = new Callback<List<Procedure>>(){
        @Override
        public void onResponse(Call<List<Procedure>> call, Response<List<Procedure>> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Get procedures response is successful.");
                List<Procedure> procedures = response.body();
                LOG.log(java.util.logging.Level.CONFIG, "Get {0} procedures.", String.valueOf(procedures.size()));
                if(procedures != null){
                    apiDelegate.onGetAllSuccess(procedures);
                }
            } else {
                LOG.log(java.util.logging.Level.SEVERE, "Get procedures response is not successful.");
                apiDelegate.onFailure("Could not get procedures.");
            }
        };

        @Override
        public void onFailure(Call<List<Procedure>> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Get procedures response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
    private Callback<List<Activity>> getActivitiesCallback = new Callback<List<Activity>>(){
        @Override
        public void onResponse(Call<List<Activity>> call, Response<List<Activity>> response){
            if (response.isSuccessful()) {
                LOG.log(java.util.logging.Level.CONFIG, "Get activities response is successful.");
                List<Activity> activities = response.body();
                LOG.log(java.util.logging.Level.CONFIG, "Get {0} activities.", String.valueOf(activities.size()));
                if(activities != null){
                    apiDelegate.onGetAllSuccess(activities);
                }
            } else {
                LOG.log(java.util.logging.Level.SEVERE, "Get activities response is not successful.");
                apiDelegate.onFailure("Could not get activities.");
            }
        };

        @Override
        public void onFailure(Call<List<Activity>> call, Throwable t){
            LOG.log(java.util.logging.Level.SEVERE, "Get activities response is not successful, message: {0}.", t.getMessage());
            apiDelegate.onFailure(t.getMessage());
        };
    };
    
}

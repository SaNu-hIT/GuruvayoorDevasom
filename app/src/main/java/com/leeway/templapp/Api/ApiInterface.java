package com.leeway.templapp.Api;

import com.leeway.templapp.Connection.ListNewsDetailsModel;
import com.leeway.templapp.Connection.Model.AttendEventModel;
import com.leeway.templapp.Connection.Model.Event;
import com.leeway.templapp.Connection.Model.ListRemarkModel.RemarkModelForList;
import com.leeway.templapp.Connection.Model.ListSchedules.ListSchedules;
import com.leeway.templapp.Connection.Model.News.Model_Two.AddNewsModel;
import com.leeway.templapp.Connection.Model.News.Model_Two.DeleteImageModel;
import com.leeway.templapp.Connection.Model.News.Model_Two.DeleteNewsModel;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.NewsAddBody;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.UpdateNewsBody;
import com.leeway.templapp.Connection.Model.News.Model_Two.Model_Two.UpdateNewsModel;
import com.leeway.templapp.Connection.Model.RemarkModel.RemarkStatus;
import com.leeway.templapp.MainScreens.ModelClass.AddCountBean.StatusBean;
import com.leeway.templapp.MainScreens.ModelClass.ListCountModel.ListCountModel;
import com.leeway.templapp.MainScreens.ModelClass.MemberListMain;
import com.leeway.templapp.Retrofit.CreateUserRetrofit.CreateUser;
import com.leeway.templapp.Retrofit.DeleteIllam.DeleteDetails;
import com.leeway.templapp.Retrofit.ModelClass.AddIllamModel.ResponceCommon;
import com.leeway.templapp.Retrofit.ModelClass.HomeBean.HomeBea;
import com.leeway.templapp.Retrofit.ModelClass.IllamModel;
import com.leeway.templapp.Retrofit.ModelClass.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by intellyelabs on 30/07/17.
 */

public interface ApiInterface {
  @FormUrlEncoded
  @POST("{path}")
  Call<LoginModel> getlogin(@Path("path") String path, @Field("mobile") String phone, @Field("password") String password);

  @POST("listillams")
  Call<IllamModel> getillam();
  @FormUrlEncoded
  @POST("listquantity")
  Call<ListCountModel> ListQuantity(@Field("userId") String userid);


  @POST("listremarks")
  Call<RemarkModelForList> ListRemark();

  @POST("listusers")
  Call<MemberListMain> getUsers();

  @POST("listshedules")
  Call<ListSchedules> ListSchedules();

  @FormUrlEncoded
  @POST("Addillam")
  Call<ResponceCommon> AddIllam(@Field("illam_name") String illam_name, @Field("illam_address") String illam_address);


  @FormUrlEncoded
  @POST("deleteillam")
  Call<DeleteDetails> getdelete(@Field("Illam_id") String illam_id);


  @FormUrlEncoded
  @POST("createusers")
  Call<CreateUser> getuser(@Field("name") String name, @Field("password") String password, @Field("mobile") String phone, @Field("Illam_id") String illam_id);

  @FormUrlEncoded
  @POST("Addquantity")
  Call<StatusBean> AddCount(@Field("c_date") String c_date, @Field("c_quantity") String c_quantity, @Field("Remark") String Remark, @Field("userId") String userId);

  @FormUrlEncoded
  @POST("AddRemark")
  Call<RemarkStatus> AddRemarkNew(@Field("Content") String Remark, @Field("userId") String userId, @Field("r_date") String r_date);


  @GET("{path}")
  Call<CreateUser> getcalendar(@Path("path") String path, @Query("name") String name, @Query("password") String password, @Query("mobile") String phone, @Query("illam_id") String illam_id);



  @POST("list_news")
  Call<ListNewsDetailsModel> getNewsResponse();



  @POST("listAll")
  Call<HomeBea> getallResponse();


  @FormUrlEncoded
  @POST("list_events")
  Call<Event> ListEvent(@Field("Event_id") String Event_id, @Field("User_Id") String User_Id);


  @FormUrlEncoded
  @POST("attend_an_event")
  Call<AttendEventModel> getAttendEventResponse(@Field("event_id") int event_id, @Field("userId") int userId, @Field("attendVal") String attendVal);

  @FormUrlEncoded
  @POST("delete_news")
  Call<DeleteNewsModel> getDeleteNewsResponse(@Field("newsid") int newsid);


  @POST("create_news_json")
  Call<AddNewsModel> createNews(@Body NewsAddBody data);


  @Headers({
          "Content-Type: application/json"
  })
  @POST("update_news_json")
  Call<UpdateNewsModel> updateNews(@Body UpdateNewsBody data);


  @FormUrlEncoded
  @POST("delete_news_image")
  Call<DeleteImageModel> getDeleteImageNewsResponse(@Field("news_Imageid") int newsid);



}
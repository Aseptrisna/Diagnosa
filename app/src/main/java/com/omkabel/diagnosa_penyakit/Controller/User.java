package com.omkabel.diagnosa_penyakit.Controller;

import android.util.Log;

import com.omkabel.diagnosa_penyakit.Model.Model_User;
import com.omkabel.diagnosa_penyakit.Response.Response_Login;
import com.omkabel.diagnosa_penyakit.Server.ApiServices;
import com.omkabel.diagnosa_penyakit.Server.InitRetrofit;
import com.omkabel.diagnosa_penyakit.View.ViewUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User {
    final ViewUser viewUser;

    public User(ViewUser viewUser) {
        this.viewUser = viewUser;
    }
    public void UserRegister(String nama, String email, String pass){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().UserDaftar(nama,email,pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
                            viewUser.Berhasil(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                viewUser.GagalLogin(Message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                        viewUser.NoInternet(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    viewUser.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void UserLogin(String email, String pass){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Login> menuCall = api.UserLogin(email,pass);
        menuCall.enqueue(new Callback<Response_Login>() {
            @Override
            public void onResponse(Call<Response_Login> call, Response<Response_Login> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Model_User> user= response.body().getUser();
                    boolean status = response.body().isStatus();
                    if (status){
                        try {
                        viewUser.BerhasilLogin(user);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            String Message="Login Gagal";
                            viewUser.GagalLogin(Message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response_Login> call, Throwable t) {
                try {
                    String Message="Tidak Ada Internet";
                    viewUser.NoInternet(Message);
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }
    public void userupdate( String id,String nama,String email,String pass){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().UserUpadate(id,nama,email,pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
                            viewUser.Berhasil(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                viewUser.GagalLogin(Message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        String error_message ="Ada Masalah Internet";
                        viewUser.NoInternet(error_message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    String error_message ="Server Tidak Merespon";
                    viewUser.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

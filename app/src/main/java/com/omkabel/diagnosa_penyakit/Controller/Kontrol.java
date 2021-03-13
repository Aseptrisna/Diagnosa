package com.omkabel.diagnosa_penyakit.Controller;

import android.util.Log;

import com.omkabel.diagnosa_penyakit.Server.InitRetrofit;
import com.omkabel.diagnosa_penyakit.View.MyKontol;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Kontrol {
   final MyKontol myKontol;
    public Kontrol(MyKontol myKontol) {
        this.myKontol = myKontol;
    }
    public void Kontrol_Kipas(String Message){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Kipas(Message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
                           myKontol.BerhasilKontrol(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                myKontol.GagalKontrol(Message);
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
                        myKontol.NoInternet(error_message);
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
                    myKontol.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void Kontrol_Lampu(String Message){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Lampu(Message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
                            myKontol.BerhasilKontrol(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                myKontol.GagalKontrol(Message);
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
                        myKontol.NoInternet(error_message);
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
                    myKontol.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void Kontrol_Kipas1(String Message){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Kipas(Message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
//                            myKontol.BerhasilKontrol(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                myKontol.GagalKontrol(Message);
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
                        myKontol.NoInternet(error_message);
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
                    myKontol.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void Kontrol_Lampu1(String Message){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Lampu(Message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
//                            myKontol.BerhasilKontrol(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                myKontol.GagalKontrol(Message);
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
                        myKontol.NoInternet(error_message);
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
                    myKontol.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

package com.omkabel.diagnosa_penyakit.Controller;

import android.util.Log;

import com.omkabel.diagnosa_penyakit.Server.InitRetrofit;
import com.omkabel.diagnosa_penyakit.View.MyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
Request {
    final MyRequest myRequest;

    public Request(MyRequest myRequest) {
        this.myRequest = myRequest;
    }
    public void RequestServer(){
        retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().RequestServer();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("status").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String Message=jsonRESULTS.getString("message");
                            myRequest.ServerResponse(Message);
                        } else {
                            try {
                                Log.d("response api", jsonRESULTS.toString());
                                String Message=jsonRESULTS.getString("message");
                                myRequest.ServerNoResponse(Message);
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
                        myRequest.ServerNoResponse(error_message);
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
                    myRequest.NoInternet(error_message);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

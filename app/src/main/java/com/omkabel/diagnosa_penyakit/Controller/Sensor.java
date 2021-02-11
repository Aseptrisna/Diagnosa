package com.omkabel.diagnosa_penyakit.Controller;

import android.util.Log;

import com.omkabel.diagnosa_penyakit.Model.Model_Sensor;
import com.omkabel.diagnosa_penyakit.Response.Response_Sensor;
import com.omkabel.diagnosa_penyakit.Server.ApiServices;
import com.omkabel.diagnosa_penyakit.Server.InitRetrofit;
import com.omkabel.diagnosa_penyakit.View.MySensor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sensor {
    final MySensor mySensor;


    public Sensor(MySensor mySensor) {
        this.mySensor = mySensor;
    }
    public void getSensor(){
        ApiServices api = InitRetrofit.getInstance().getApi();
        Call<Response_Sensor> menuCall = api.getsensor();
        menuCall.enqueue(new Callback<Response_Sensor>() {
            @Override
            public void onResponse(Call<Response_Sensor> call, Response<Response_Sensor> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Model_Sensor> histories= response.body().getSensor();
                    boolean status = response.body().isStatus();
                    if (status){
                        try {
                            mySensor.Berhasil(histories);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            String Message="Tidak Ada data";
                            mySensor.Gagal(Message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Response_Sensor> call, Throwable t) {
                try {
                    String Message="Server No Response";
                    mySensor.NoInternet(Message);
                    t.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

}

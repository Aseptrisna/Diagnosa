package com.omkabel.diagnosa_penyakit.Response;

import com.google.gson.annotations.SerializedName;
import com.omkabel.diagnosa_penyakit.Model.Model_Sensor;

import java.util.List;

public class Response_Sensor {
    @SerializedName("status")
    private boolean status;
    @SerializedName("sensor")
    private List<Model_Sensor> sensor;

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }

    public void setSensor(List<Model_Sensor> sensor){
        this.sensor =sensor;
    }

    public List<Model_Sensor> getSensor(){
        return sensor;
    }

    @Override
    public String toString(){
        return
                "Response_Sensor{" +
                        "status = '" + status + '\'' +
                        ",sensor = '" + sensor + '\'' +
                        "}";
    }
}

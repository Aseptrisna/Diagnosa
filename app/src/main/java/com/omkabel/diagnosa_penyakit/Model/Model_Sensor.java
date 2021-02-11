package com.omkabel.diagnosa_penyakit.Model;

import com.google.gson.annotations.SerializedName;

public class Model_Sensor {

    @SerializedName("id")
    private String id;

    @SerializedName("value")
    private String value;

    @SerializedName("jam")
    private String jam;

    @SerializedName("tanggal")
    private String tanggal;

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public void setJam(String jam){
        this.jam = jam;
    }

    public String getJam(){
        return jam;
    }
    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

    public String getTanggal(){
        return tanggal;
    }
    @Override
    public String toString(){
        return
                "Model_Sensor{" +
                        "id = '" + id + '\'' +
                        ",value = '" + value + '\'' +
                        ",jam = '" + jam + '\'' +
                        ",tanggal = '" + tanggal + '\'' +
                        "}";
    }
}

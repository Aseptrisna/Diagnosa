package com.omkabel.diagnosa_penyakit.View;

public interface MyRequest {
    void ServerResponse(String Message);
    void ServerNoResponse(String Message);
    void NoInternet(String Message);
}

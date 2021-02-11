package com.omkabel.diagnosa_penyakit.View;

import com.omkabel.diagnosa_penyakit.Model.Model_Sensor;

import java.util.List;

public interface MySensor {
    void Berhasil(List<Model_Sensor> sensors);

    void Gagal(String Message);
    void NoInternet(String Message);
}

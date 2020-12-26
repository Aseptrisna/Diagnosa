package com.omkabel.diagnosa_penyakit.View;

import com.omkabel.diagnosa_penyakit.Model.Model_User;

import java.util.List;

public interface ViewUser {
    void Berhasil(String Message);

    void BerhasilLogin(List<Model_User>user);

    void GagalLogin(String Message);

    void NoInternet(String Message);
}

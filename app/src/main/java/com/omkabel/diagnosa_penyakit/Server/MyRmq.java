package com.omkabel.diagnosa_penyakit.Server;

public interface MyRmq {
    void Berhasil(String message);
    void Gagal();
}

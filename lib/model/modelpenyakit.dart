import 'dart:convert';

class ModelPenyakit {
  String nama;
  String jenis;
  String id;

  ModelPenyakit({
  this.nama = "",
  this.jenis = "",
  this.id = "",
  });

  ModelPenyakit.fromJson(Map<String, dynamic>  map) :
        nama = map['nama']  ?? "",
        jenis = map['jenis']  ?? "",
        id = map['id']  ?? "";

  Map<String, dynamic> toJson() => {
        'nama': nama,
        'jenis': jenis,
        'id': id,
      };

  ModelPenyakit copyWith({
    String nama,
    String jenis,
    String id,
  }) {
    return ModelPenyakit(
      nama: nama ?? this.nama,
      jenis: jenis ?? this.jenis,
      id: id ?? this.id,
    );
  }
}


import 'dart:convert';

class Hh {
  String nama;
  String jenis;
  String id;

  Hh({
  this.nama = "",
  this.jenis = "",
  this.id = "",
  });

  Hh.fromJson(Map<String, dynamic>  map) :
        nama = map['nama']  ?? "",
        jenis = map['jenis']  ?? "",
        id = map['id']  ?? "";

  Map<String, dynamic> toJson() => {
        'nama': nama,
        'jenis': jenis,
        'id': id,
      };

  Hh copyWith({
    String nama,
    String jenis,
    String id,
  }) {
    return Hh(
      nama: nama ?? this.nama,
      jenis: jenis ?? this.jenis,
      id: id ?? this.id,
    );
  }
}


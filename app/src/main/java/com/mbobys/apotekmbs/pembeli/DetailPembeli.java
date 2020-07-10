package com.mbobys.apotekmbs.pembeli;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mbobys.apotekmbs.R;
import com.mbobys.apotekmbs.server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailPembeli extends AppCompatActivity {

    EditText edtKodeObat, edtNamaObat, edtJenisObat, edtSatuan, edtStok, edtHarga;
    ImageView imgGambarObat;

    String strKodeObat, strNamaObat, strJenisObat, strSatuan, strStok, strHarga, strGambarObat, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembeli);

        edtKodeObat = (EditText) findViewById(R.id.edtKodeObat);
        edtNamaObat = (EditText) findViewById(R.id.edtNamaObat);
        edtJenisObat = (EditText) findViewById(R.id.edtJenisObat);
        edtSatuan = (EditText) findViewById(R.id.edtSatuan);
        edtStok = (EditText) findViewById(R.id.edtStok);
        edtHarga = (EditText) findViewById(R.id.edtHargaObat);

        imgGambarObat = (ImageView) findViewById(R.id.Gambar);

        Intent i = getIntent();
        strKodeObat = i.getStringExtra("KodeObat");
        strNamaObat = i.getStringExtra("NamaObat");
        strJenisObat = i.getStringExtra("JenisObat");
        strSatuan = i.getStringExtra("Satuan");
        strStok = i.getStringExtra("Stok");
        strHarga = i.getStringExtra("HargaObat");
        strGambarObat = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtKodeObat.setText(strKodeObat);
        edtNamaObat.setText(strNamaObat);
        edtJenisObat.setText(strJenisObat);
        edtSatuan.setText(strSatuan);
        edtStok.setText(strStok);
        edtHarga.setText(strHarga);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambarObat)
                .into(imgGambarObat);
    }
}

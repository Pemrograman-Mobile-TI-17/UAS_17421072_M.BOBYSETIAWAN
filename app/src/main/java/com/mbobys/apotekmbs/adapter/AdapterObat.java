package com.mbobys.apotekmbs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbobys.apotekmbs.R;
import com.mbobys.apotekmbs.model.ModelObat;
import com.mbobys.apotekmbs.server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterObat extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelObat> item;

    public AdapterObat(Activity activity, List<ModelObat> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_obat, null);


        TextView NamaObat = (TextView) convertView.findViewById(R.id.txtNamaObat);
        TextView JenisObat     = (TextView) convertView.findViewById(R.id.txtJenisObat);
        TextView Satuan          = (TextView) convertView.findViewById(R.id.txtSatuan);
        TextView Harga        = (TextView) convertView.findViewById(R.id.txtHargaObat);
        TextView Stok         = (TextView) convertView.findViewById(R.id.txtStok);
        ImageView GambarObat         = (ImageView) convertView.findViewById(R.id.GambarObat);

        NamaObat.setText(item.get(position).getNamaObat());
        JenisObat.setText(item.get(position).getJenisObat());
        Satuan.setText(item.get(position).getSatuan());
        Harga.setText("Rp." + item.get(position).getHargaObat());
        Stok.setText(item.get(position).getStok());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(GambarObat);
        return convertView;
    }

}
package com.mbobys.apotekmbs.pembeli;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mbobys.apotekmbs.R;
import com.mbobys.apotekmbs.adapter.AdapterObat;
import com.mbobys.apotekmbs.model.ModelObat;
import com.mbobys.apotekmbs.server.BaseURL;
import com.mbobys.apotekmbs.session.PrefSetting;
import com.mbobys.apotekmbs.session.SessionManager;
import com.mbobys.apotekmbs.users.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePembeliActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterObat adapter;
    ListView list;

    ArrayList<ModelObat> newsList = new ArrayList<ModelObat>();
    private RequestQueue mRequestQueue;

    FloatingActionButton floatingExit;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pembeli);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreference();

        session = new SessionManager(HomePembeliActivity.this);

        prefSetting.isLogin(session, prefs);

        getSupportActionBar().setTitle("Data Obat");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);

        floatingExit = (FloatingActionButton) findViewById(R.id.exit);

        newsList.clear();
        adapter = new AdapterObat(HomePembeliActivity.this, newsList);
        list.setAdapter(adapter);
        getAllObat();

        floatingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomePembeliActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void getAllObat() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataObat, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data obat = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelObat obat = new ModelObat();
                                    final String _id = jsonObject.getString("_id");
                                    final String NamaObat = jsonObject.getString("NamaObat");
                                    final String KodeObat = jsonObject.getString("KodeObat");
                                    final String JenisObat = jsonObject.getString("JenisObat");
                                    final String Satuan = jsonObject.getString("Satuan");
                                    final String HargaObat = jsonObject.getString("HargaObat");
                                    final String Stok = jsonObject.getString("Stok");
                                    final String Gambar = jsonObject.getString("gambar");
                                    obat.setKodeObat(KodeObat);
                                    obat.setNamaObat(NamaObat);
                                    obat.setJenisObat(JenisObat);
                                    obat.setSatuan(Satuan);
                                    obat.setHargaObat(HargaObat);
                                    obat.setStok(Stok);
                                    obat.setGambar(Gambar);
                                    obat.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(HomePembeliActivity.this, DetailPembeli.class);
                                            a.putExtra("KodeObat", newsList.get(position).getKodeObat());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("NamaObat", newsList.get(position).getNamaObat());
                                            a.putExtra("JenisObat", newsList.get(position).getJenisObat());
                                            a.putExtra("Satuan", newsList.get(position).getSatuan());
                                            a.putExtra("HargaObat", newsList.get(position).getHargaObat());
                                            a.putExtra("Stok", newsList.get(position).getStok());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(obat);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

package com.example.holiday.Edit_Account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.holiday.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Choose_type extends AppCompatActivity {
    private ListView listView;
    private TextView tvBack,title;
    private String[] type;
    private String[] gender;
    private String[] pob;
    private String[] married_status;
    private String[] location;
    private ArrayAdapter<String> adapter_type, adapter_gender, adapter_Married, adapter_Location, adapter_Pob;
    private ArrayAdapter<String> ad_id_province;
    private ArrayAdapter<Integer> ad_id_type;
    private RequestQueue mQueue;
    private List<String> datalist_location = new ArrayList<>();
    private List<String> datalist_type = new ArrayList<>();
    private List<String> datalist_id_province = new ArrayList<>();
    private List<Integer> datalist_id_type = new ArrayList<>();
    private  String url;
    private  String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        mQueue = Volley.newRequestQueue(this);

        tvBack = findViewById(R.id.toolbar_chooseType);
        title = findViewById(R.id.title_Choose_type);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView)findViewById(R.id.lv_type);


        gender = getResources().getStringArray(R.array.gender);
        pob =    getResources().getStringArray(R.array.pob);
        married_status = getResources().getStringArray(R.array.married);


        adapter_gender = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, gender);
        adapter_Pob = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, pob);
        adapter_Married = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, married_status);



        String fields = getIntent().getExtras().getString("Choose_Fields");
        if (fields.equals("Type") ){
            title.setText("Choose type");
            st="Type";

            GetLocation(st);

        }else if (fields.equals("Gender")){
            title.setText("Choose gender");
            st="Gender";
            listView.setAdapter(adapter_gender);
        }else if (fields.equals("POB")){
            title.setText("Choose Place of Birth");
            st="POB";
           GetLocation(st);
        }else if (fields.equals("Location")){
            title.setText("Choose Location");
            st="Location";

            GetLocation(st);

        }else if (fields.equals("Married")){
            title.setText("Choose married status");
            st="Married";
            listView.setAdapter(adapter_Married);
        }


        ListClick(st);

    }  // create

    private void GetLocation(final String st) {
        String t = null;
        if (st.equals("Type")){
            t="group";
            url="http://192.168.1.239:7000/api/v1/groups/";
        }else if (st.equals("Location")|st.equals("POB")){
            t="provinces";
            url = "http://192.168.1.239:7000/api/v1/provinces/";
        }
        final String finalT = t;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            if (finalT.equals("group")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject field_Name = jsonArray.getJSONObject(i);
                                    int id      = field_Name.getInt("id");
                                    String name = field_Name.getString("name");
                                    datalist_id_type.add(id);
                                    Log.d("Response",datalist_id_type.toString());
                                    datalist_type.add(name);
                                    ad_id_type = new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_list_item_1,datalist_id_type);
                                    adapter_type = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, android.R.id.text1, datalist_type);
                                    listView.setAdapter(adapter_type);
                                }
                            }else if (finalT.equals("provinces")){
                                for (int i=0;i< jsonArray.length(); i++){
                                    JSONObject  field_province = jsonArray.getJSONObject(i);
                                    String id = field_province.getString("province_id");
                                    String province = field_province.getString("province");
                                    datalist_location.add(province);
                                    datalist_id_province.add(id);
                                    ad_id_province = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,datalist_id_province);
                                    adapter_Location = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, datalist_location);
                                    listView.setAdapter(adapter_Location);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void ListClick(final String st) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String adapter = null;
                int ID_Type = 0;
                String ID = null;
                if (st.equals("Type")){
                    ID_Type = ad_id_type.getItem(position);
                    adapter = adapter_type.getItem(position);
                }else if (st.equals("Gender")){
                    adapter = adapter_gender.getItem(position);
                }else if (st.equals("POB")){
                    ID = ad_id_province.getItem(position);
                    adapter = adapter_Location.getItem(position);
                }else if (st.equals("Location")){
                    adapter = adapter_Location.getItem(position);
                }else if (st.equals("Married")){
                    adapter = adapter_Married.getItem(position);
                }

                Intent intent = new Intent(getApplicationContext(), Edit_account.class);
                intent.putExtra("id_province",ID);
                intent.putExtra("id_type",ID_Type);
                intent.putExtra("field",adapter );
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

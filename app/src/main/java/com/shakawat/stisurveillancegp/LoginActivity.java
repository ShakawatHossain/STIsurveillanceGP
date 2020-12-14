package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shakawat.stisurveillancegp.model.MainModel;
import com.shakawat.stisurveillancegp.model.MyDB;
import com.shakawat.stisurveillancegp.model.UtilModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText uid,pass;
    Button submit,see_db;
    ProgressBar pBar;
    MyDB myDB;

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (MainModel.user_id==null
                || !MainModel.user_id.isEmpty()){
            startActivity(new Intent(LoginActivity.this,ListActivity.class));
        }
        init();
    }
    private void init(){
        myDB = new MyDB(LoginActivity.this);
        uid = (EditText) findViewById(R.id.user_id);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this.listener);
        pBar = (ProgressBar) findViewById(R.id.pBar);
        see_db = (Button) findViewById(R.id.see_db);
        see_db.setOnClickListener(listener);
        Cursor dis = myDB.getAll_dis();
        if (!dis.moveToFirst()){
            Log.d("Dis","No Districts");
            loaddis();
        }
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.see_db){
                printDB();
                return;
            }
            if (uid.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Please fill user name",Toast.LENGTH_SHORT).show();
                return;
            }else if(pass.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Please fill password",Toast.LENGTH_SHORT).show();
                return;
            }
//            btn_toggle();
            check_login();
        }
    };
    private void check_login(){
        UtilModel modelUtil = new UtilModel();
        MainModel.user_id = modelUtil.getLogin(uid.getText().toString(),pass.getText().toString());
        if (MainModel.user_id!=null && !MainModel.user_id.isEmpty()){
            startActivity(new Intent(LoginActivity.this,ListActivity.class));
        }else {
            Toast.makeText(LoginActivity.this,"Please invalid name or password",Toast.LENGTH_SHORT).show();
        }
    }
    private void btn_toggle(){
        if(pBar.getVisibility()==View.GONE) pBar.setVisibility(View.VISIBLE); else pBar.setVisibility(View.GONE);
        if(submit.getVisibility()==View.GONE) submit.setVisibility(View.VISIBLE); else submit.setVisibility(View.GONE);
    }

    private void printDB(){
//        MyDB myDB = new MyDB(LoginActivity.this);
        Cursor cDemo = myDB.getAll_sti();

        if (cDemo.moveToFirst()){
            do {
                String s = "";
                s +=" main_user_id : "+  cDemo.getString(cDemo.getColumnIndex("main_user_id"));
                s +=" main_hos_code : "+  cDemo.getString(cDemo.getColumnIndex("main_hos_code"));
                s +=" main_dept : "+  cDemo.getString(cDemo.getColumnIndex("main_dept"));
                s +=" main_sample : "+  cDemo.getString(cDemo.getColumnIndex("main_sample"));
                s +=" main_sample_id : "+  cDemo.getString(cDemo.getColumnIndex("main_sample_id"));
                s +=" main_in_date : "+  cDemo.getString(cDemo.getColumnIndex("main_in_date"));
                s +=" main_case_id : "+  cDemo.getString(cDemo.getColumnIndex("main_case_id"));
                s +=" demo_name : "+  cDemo.getString(cDemo.getColumnIndex("demo_name"));
                s +=" demo_age : "+  cDemo.getString(cDemo.getColumnIndex("demo_age"));
                s +=" demo_mob : "+  cDemo.getString(cDemo.getColumnIndex("demo_mob"));
                s +=" demo_ocu : "+  cDemo.getString(cDemo.getColumnIndex("demo_ocu"));
                s +=" demo_child_num : "+  cDemo.getString(cDemo.getColumnIndex("demo_child_num"));
                s +=" demo_edu : "+  cDemo.getString(cDemo.getColumnIndex("demo_edu"));
                s +=" demo_edu_oth_txt : "+  cDemo.getString(cDemo.getColumnIndex("demo_edu_oth_txt"));
                s +=" demo_sex : "+  cDemo.getString(cDemo.getColumnIndex("demo_sex"));
                s +=" demo_marrige : "+  cDemo.getString(cDemo.getColumnIndex("demo_marrige"));
                s +=" demo_edu_type : "+  cDemo.getString(cDemo.getColumnIndex("demo_edu_type"));
                s +=" demo_income : "+  cDemo.getString(cDemo.getColumnIndex("demo_income"));
                s +=" demo_child : "+  cDemo.getString(cDemo.getColumnIndex("demo_child"));
                s +=" demo_un : "+  cDemo.getString(cDemo.getColumnIndex("demo_un"));
                s +=" demo_up : "+  cDemo.getString(cDemo.getColumnIndex("demo_up"));
                s +=" demo_dis : "+  cDemo.getString(cDemo.getColumnIndex("demo_dis"));
                s +=" demo_add_un : "+  cDemo.getString(cDemo.getColumnIndex("demo_add_un"));
                s +=" clin_urth : "+  cDemo.getString(cDemo.getColumnIndex("clin_urth"));
                s +=" clin_urth_type : "+  cDemo.getString(cDemo.getColumnIndex("clin_urth_type"));
                s +=" clin_vagi : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi"));
                s +=" clin_vagi_amount : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_amount"));
                s +=" clin_vagi_type : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_type"));
                s +=" clin_vagi_smell : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_smell"));
                s +=" clin_vagi_itch : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_itch"));
                s +=" clin_oro_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_oro_ulcer"));
                s +=" clin_oro_dis : "+  cDemo.getString(cDemo.getColumnIndex("clin_oro_dis"));
                s +=" clin_ano_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_ano_ulcer"));
                s +=" clin_ano_dis : "+  cDemo.getString(cDemo.getColumnIndex("clin_ano_dis"));
                s +=" clin_abd_pain : "+  cDemo.getString(cDemo.getColumnIndex("clin_abd_pain"));
                s +=" clin_uri_pain : "+  cDemo.getString(cDemo.getColumnIndex("clin_uri_pain"));
                s +=" clin_pain_inter : "+  cDemo.getString(cDemo.getColumnIndex("clin_pain_inter"));
                s +=" clin_gen_war : "+  cDemo.getString(cDemo.getColumnIndex("clin_gen_war"));
                s +=" clin_gen_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer"));
                s +=" clin_pain_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_pain_ulcer"));
                s +=" clin_blis_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_blis_ulcer"));
                s +=" clin_dis_ulcer : "+  cDemo.getString(cDemo.getColumnIndex("clin_dis_ulcer"));
                s +=" clin_swel_ingui : "+  cDemo.getString(cDemo.getColumnIndex("clin_swel_ingui"));
                s +=" clin_ulcer_ingui : "+  cDemo.getString(cDemo.getColumnIndex("clin_ulcer_ingui"));
                s +=" clin_scro_pain : "+  cDemo.getString(cDemo.getColumnIndex("clin_scro_pain"));
                s +=" clin_urth_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_urth_days"));
                s +=" clin_urth_type_oth_text : "+  cDemo.getString(cDemo.getColumnIndex("clin_urth_type_oth_text"));
                s +=" clin_vagi_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_days"));
                s +=" clin_vagi_type_oth_text : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_type_oth_text"));
                s +=" clin_vagi_smell_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_smell_days"));
                s +=" clin_vagi_colour : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_colour"));
                s +=" clin_vagi_colour_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_colour_days"));
                s +=" clin_vagi_itch_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_vagi_itch_days"));
                s +=" clin_oro_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_oro_ulcer_days"));
                s +=" clin_oro_dis_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_oro_dis_days"));
                s +=" clin_ano_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_ano_ulcer_days"));
                s +=" clin_ano_dis_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_ano_dis_days"));
                s +=" clin_abd_pain_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_abd_pain_days"));
                s +=" clin_uri_pain_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_uri_pain_days"));
                s +=" clin_pain_inter_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_pain_inter_days"));
                s +=" clin_gen_war_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_gen_war_days"));
                s +=" clin_gen_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer_days"));
                s +=" clin_gen_ulcer_num : "+  cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer_num"));
                s +=" clin_pain_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_pain_ulcer_days"));
                s +=" clin_blis_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_blis_ulcer_days"));
                s +=" clin_dis_ulcer_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_dis_ulcer_days"));
                s +=" clin_swel_ingui_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_swel_ingui_days"));
                s +=" clin_ulcer_ingui_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_ulcer_ingui_days"));
                s +=" clin_scro_pain_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_scro_pain_days"));
                s +=" clin_oth_name : "+  cDemo.getString(cDemo.getColumnIndex("clin_oth_name"));
                s +=" clin_clin_oth_days : "+  cDemo.getString(cDemo.getColumnIndex("clin_clin_oth_days"));
                s +=" past_pat_symp : "+  cDemo.getString(cDemo.getColumnIndex("past_pat_symp"));
                s +=" past_symp : "+  cDemo.getString(cDemo.getColumnIndex("past_symp"));
                s +=" past_hiv : "+  cDemo.getString(cDemo.getColumnIndex("past_hiv"));
                s +=" past_hiv_test : "+  cDemo.getString(cDemo.getColumnIndex("past_hiv_test"));
                s +=" past_symp_time : "+  cDemo.getString(cDemo.getColumnIndex("past_symp_time"));
                s +=" risk_age : "+  cDemo.getString(cDemo.getColumnIndex("risk_age"));
                s +=" risk_no_sex_week : "+  cDemo.getString(cDemo.getColumnIndex("risk_no_sex_week"));
                s +=" risk_no_sex_mth : "+  cDemo.getString(cDemo.getColumnIndex("risk_no_sex_mth"));
                s +=" risk_type_sex_oth_text : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_oth_text"));
                s +=" risk_oth_text : "+  cDemo.getString(cDemo.getColumnIndex("risk_oth_text"));
                s +=" risk_prev_preg_oth_text : "+  cDemo.getString(cDemo.getColumnIndex("risk_prev_preg_oth_text"));
                s +=" risk_age_no : "+  cDemo.getString(cDemo.getColumnIndex("risk_age_no"));
                s +=" risk_type_sex_spouse : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_spouse"));
                s +=" risk_type_sex_csw : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_csw"));
                s +=" risk_type_sex_op : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_op"));
                s +=" risk_type_sex_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_oth"));
                s +=" risk_type_sex_nor : "+  cDemo.getString(cDemo.getColumnIndex("risk_type_sex_nor"));
                s +=" risk_safe_method : "+  cDemo.getString(cDemo.getColumnIndex("risk_safe_method"));
                s +=" risk_oral_pill : "+  cDemo.getString(cDemo.getColumnIndex("risk_oral_pill"));
                s +=" risk_inj : "+  cDemo.getString(cDemo.getColumnIndex("risk_inj"));
                s +=" risk_nor : "+  cDemo.getString(cDemo.getColumnIndex("risk_nor"));
                s +=" risk_iud : "+  cDemo.getString(cDemo.getColumnIndex("risk_iud"));
                s +=" risk_mc : "+  cDemo.getString(cDemo.getColumnIndex("risk_mc"));
                s +=" risk_fc : "+  cDemo.getString(cDemo.getColumnIndex("risk_fc"));
                s +=" risk_sperm : "+  cDemo.getString(cDemo.getColumnIndex("risk_sperm"));
                s +=" risk_oth : "+  cDemo.getString(cDemo.getColumnIndex("risk_oth"));
                s +=" risk_na : "+  cDemo.getString(cDemo.getColumnIndex("risk_na"));
                s +=" risk_multi_sex : "+  cDemo.getString(cDemo.getColumnIndex("risk_multi_sex"));
                s +=" risk_condom : "+  cDemo.getString(cDemo.getColumnIndex("risk_condom"));
                s +=" risk_condom_freq : "+  cDemo.getString(cDemo.getColumnIndex("risk_condom_freq"));
                s +=" risk_partner_ill : "+  cDemo.getString(cDemo.getColumnIndex("risk_partner_ill"));
                s +=" risk_offspring_trans : "+  cDemo.getString(cDemo.getColumnIndex("risk_offspring_trans"));
                s +=" risk_prev_preg : "+  cDemo.getString(cDemo.getColumnIndex("risk_prev_preg"));
                s +=" visit_pt_type : "+  cDemo.getString(cDemo.getColumnIndex("visit_pt_type"));
                s +=" visit_anti_treat : "+  cDemo.getString(cDemo.getColumnIndex("visit_anti_treat"));
                s +=" visit_treat_place : "+  cDemo.getString(cDemo.getColumnIndex("visit_treat_place"));
                s +=" visit_sm_remarks : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_remarks"));
                s +=" visit_remarks : "+  cDemo.getString(cDemo.getColumnIndex("visit_remarks"));
                s +=" visit_sample_date : "+  cDemo.getString(cDemo.getColumnIndex("visit_sample_date"));
                s +=" visit_pd_uds : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_uds"));
                s +=" visit_pd_vds : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_vds"));
                s +=" visit_pd_guds : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_guds"));
                s +=" visit_pd_lap : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_lap"));
                s +=" visit_pd_oro : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_oro"));
                s +=" visit_pd_ano : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_ano"));
                s +=" visit_pd_ibs : "+  cDemo.getString(cDemo.getColumnIndex("visit_pd_ibs"));
                s +=" visit_sm_us : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_us"));
                s +=" visit_sm_vs : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_vs"));
                s +=" visit_sm_ur : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_ur"));
                s +=" visit_sm_es : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_es"));
                s +=" visit_sm_bd : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_bd"));
                s +=" visit_sm_nc : "+  cDemo.getString(cDemo.getColumnIndex("visit_sm_nc"));
                Log.d("Demo",s);
            }while (cDemo.moveToNext());

        }
        String districts = "";
        Cursor cDis = myDB.getAll_dis();
        if(cDis.moveToFirst()){
            do {
                districts+=cDis.getString(cDis.getColumnIndex("name"))+",";
            }while (cDis.moveToNext());
        }
        Log.d("Districts",districts);
        String upazillas = "";
        Cursor cUp = myDB.getAll_up();
        if(cUp.moveToFirst()){
            do {
                upazillas+=cUp.getString(cUp.getColumnIndex("name"))+",";
            }while (cUp.moveToNext());
        }
        Log.d("Upazillas",upazillas);
    }
    private void loaddis(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://119.40.84.187/surveillance/public/js/compact.json";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        new LoadDB().execute(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LoadDis","Volley error occured");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    private class LoadDB extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            Log.d("disres",strings[0]);
            try {
                JSONObject jsonObject = new JSONObject(strings[0]);
                JSONArray jsonArrayDis = jsonObject.getJSONArray("district");
                for (int i=0;i<jsonArrayDis.length();i++){
                    JSONObject jobj = jsonArrayDis.getJSONObject(i);
                    ContentValues cvDis = new ContentValues();
                    String name=jobj.getString("name");
                    cvDis.put("name",name);
                    myDB.insertDis(cvDis);
//                    Log.d("districts",jobj.getString("name"));
                }


                JSONArray jsonArrayUp = jsonObject.getJSONArray("upazilla");
                for (int j=0;j<jsonArrayUp.length();j++){
                    JSONObject jobj = jsonArrayUp.getJSONObject(j);
                    ContentValues cvUp = new ContentValues();
                    cvUp.put("name",jobj.getString("name"));
                    myDB.insertUp(cvUp);
//                    Log.d("upz",jobj.getString("name"));
                }
                JSONArray jsonArrayCity = jsonObject.getJSONArray("city");
                for (int j=0;j<jsonArrayCity.length();j++){
                    JSONObject jobj = jsonArrayCity.getJSONObject(j);
                    ContentValues cvUp = new ContentValues();
                    cvUp.put("name",jobj.getString("name"));
                    myDB.insertUp(cvUp);
//                    Log.d("city",jobj.getString("name"));
                }
                JSONArray jsonArrayThana = jsonObject.getJSONArray("thana");
                for (int j=0;j<jsonArrayThana.length();j++){
                    JSONObject jobj = jsonArrayThana.getJSONObject(j);
                    ContentValues cvUp = new ContentValues();
                    cvUp.put("name",jobj.getString("name"));
                    myDB.insertUp(cvUp);
//                    Log.d("thana",jobj.getString("name"));
                }
                JSONArray jsonArrayMc = jsonObject.getJSONArray("municipalty");
                for (int j=0;j<jsonArrayMc.length();j++){
                    JSONObject jobj = jsonArrayMc.getJSONObject(j);
                    ContentValues cvUp = new ContentValues();
                    cvUp.put("name",jobj.getString("name"));
                    myDB.insertUp(cvUp);
//                    Log.d("MC",jobj.getString("name"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
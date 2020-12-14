package com.shakawat.stisurveillancegp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shakawat.stisurveillancegp.adapter.ListRecAdapter;
import com.shakawat.stisurveillancegp.model.ListModel;
import com.shakawat.stisurveillancegp.model.MainModel;
import com.shakawat.stisurveillancegp.model.MyDB;
import com.shakawat.stisurveillancegp.model.UtilModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListModel> modelLists;
    ListRecAdapter listRecAdapter;
    LinearLayoutManager linearLayoutManager;
    TextView txt;
    Button sync;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainModel.clear();
                startActivity(new Intent(ListActivity.this,MainActivity.class));
            }
        });
        txt = (TextView) findViewById(R.id.txt);
        linearLayoutManager = new LinearLayoutManager(ListActivity.this);
        sync=(Button) findViewById(R.id.sync);
        sync.setOnClickListener(clickListener);
        db = new MyDB(ListActivity.this);
        modelLists = new ArrayList<>();
        checkLogin();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        new FacSave().execute();
    }

    private void checkLogin(){
        if(MainModel.user_id==null || MainModel.user_id.isEmpty()){
            startActivity(new Intent(ListActivity.this,LoginActivity.class));
            finish();
        }
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==sync.getId()){
                if (modelLists.size()>0){
                    uploadRecord();
                    btn_toggle();
                }
            }
        }
    };
    private void btn_toggle(){
        if (((ProgressBar) findViewById(R.id.progress)).getVisibility()!=View.VISIBLE)
            ((ProgressBar) findViewById(R.id.progress)).setVisibility(View.VISIBLE);
        else
            ((ProgressBar) findViewById(R.id.progress)).setVisibility(View.GONE);
    }
    private void uploadRecord(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String link = "http://119.148.17.100:8080/sti/insert.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        btn_toggle();
                        Log.d("Upload Record",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int success = jsonObject.getInt("success");
                            String msg = jsonObject.getString("message");
                            if(success == 200) {
                                Toast.makeText(ListActivity.this,msg,Toast.LENGTH_SHORT).show();
                                db.updateDemo();
                                startActivity(new Intent(ListActivity.this,ListActivity.class));
                                finish();
                            }else{
                                Toast.makeText(ListActivity.this,msg,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_toggle();
                Toast.makeText(ListActivity.this,"Upload error!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                try {
                    for(ListModel mlist: modelLists){
                        JSONObject jobj = new JSONObject();
                        for (String key:mlist.mainParam.keySet()) jobj.put("main_"+key,mlist.mainParam.get(key));
                        for (String key:mlist.demoParam.keySet()) jobj.put("demo_"+key,mlist.demoParam.get(key));
                        for (String key:mlist.clinParam.keySet()) jobj.put("clin_"+key,mlist.clinParam.get(key));
                        for (String key:mlist.pastParam.keySet()) jobj.put("past_"+key,mlist.pastParam.get(key));
                        for (String key:mlist.riskParam.keySet()) jobj.put("risk_"+key,mlist.riskParam.get(key));
                        for (String key:mlist.visitParam.keySet()) jobj.put("visit_"+key,mlist.visitParam.get(key));
                        jobj.put("main_user_id", MainModel.user_id);
                        jobj.put("mob_created_at",mlist.created_at);
                        jsonArray.put(jobj);
                    }
                    jsonObject.put("demo",jsonArray);
                }catch (JSONException ex){
                    ex.printStackTrace();
                }
                params.put("request",jsonObject.toString());
                return params;
            }

        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public class FacSave extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Cursor cDemo = db.get_demo();
//            Cursor fam = db.get_fam();
            int count =1;
            if(cDemo.moveToFirst()){
                do {
                    ListModel modelList = new ListModel();
                    modelList.mainParam.put("hos_code", cDemo.getString(cDemo.getColumnIndex("main_hos_code")));
                    modelList.mainParam.put("dept", cDemo.getString(cDemo.getColumnIndex("main_dept")));
                    modelList.mainParam.put("sample", cDemo.getString(cDemo.getColumnIndex("main_sample")));
                    modelList.mainParam.put("sample_id", cDemo.getString(cDemo.getColumnIndex("main_sample_id")));
                    modelList.mainParam.put("in_date", cDemo.getString(cDemo.getColumnIndex("main_in_date")));
                    modelList.mainParam.put("case_id", cDemo.getString(cDemo.getColumnIndex("main_case_id")));
                    modelList.demoParam.put("name", cDemo.getString(cDemo.getColumnIndex("demo_name")));
                    modelList.demoParam.put("age", cDemo.getString(cDemo.getColumnIndex("demo_age")));
                    modelList.demoParam.put("mob", cDemo.getString(cDemo.getColumnIndex("demo_mob")));
                    modelList.demoParam.put("ocu", cDemo.getString(cDemo.getColumnIndex("demo_ocu")));
                    modelList.demoParam.put("child_num", cDemo.getString(cDemo.getColumnIndex("demo_child_num")));
                    modelList.demoParam.put("edu", cDemo.getString(cDemo.getColumnIndex("demo_edu")));
                    modelList.demoParam.put("edu_oth_txt", cDemo.getString(cDemo.getColumnIndex("demo_edu_oth_txt")));
                    modelList.demoParam.put("sex", cDemo.getString(cDemo.getColumnIndex("demo_sex")));
                    modelList.demoParam.put("marrige", cDemo.getString(cDemo.getColumnIndex("demo_marrige")));
                    modelList.demoParam.put("edu_type", cDemo.getString(cDemo.getColumnIndex("demo_edu_type")));
                    modelList.demoParam.put("income", cDemo.getString(cDemo.getColumnIndex("demo_income")));
                    modelList.demoParam.put("child", cDemo.getString(cDemo.getColumnIndex("demo_child")));
                    modelList.demoParam.put("un", cDemo.getString(cDemo.getColumnIndex("demo_un")));
                    modelList.demoParam.put("up", cDemo.getString(cDemo.getColumnIndex("demo_up")));
                    modelList.demoParam.put("dis", cDemo.getString(cDemo.getColumnIndex("demo_dis")));
                    modelList.demoParam.put("add_un", cDemo.getString(cDemo.getColumnIndex("demo_add_un")));
                    modelList.demoParam.put("student", cDemo.getString(cDemo.getColumnIndex("demo_student")));
                    modelList.demoParam.put("service", cDemo.getString(cDemo.getColumnIndex("demo_service")));
                    modelList.demoParam.put("business", cDemo.getString(cDemo.getColumnIndex("demo_business")));
                    modelList.demoParam.put("farmer", cDemo.getString(cDemo.getColumnIndex("demo_farmer")));
                    modelList.demoParam.put("rickshaw", cDemo.getString(cDemo.getColumnIndex("demo_rickshaw")));
                    modelList.demoParam.put("driver", cDemo.getString(cDemo.getColumnIndex("demo_driver")));
                    modelList.demoParam.put("housewife", cDemo.getString(cDemo.getColumnIndex("demo_housewife")));
                    modelList.demoParam.put("unemployed", cDemo.getString(cDemo.getColumnIndex("demo_unemployed")));
                    modelList.demoParam.put("dgh", cDemo.getString(cDemo.getColumnIndex("demo_dgh")));
                    modelList.demoParam.put("dl", cDemo.getString(cDemo.getColumnIndex("demo_dl")));
                    modelList.demoParam.put("carpenter", cDemo.getString(cDemo.getColumnIndex("demo_carpenter")));
                    modelList.demoParam.put("huckster", cDemo.getString(cDemo.getColumnIndex("demo_huckster")));
                    modelList.demoParam.put("ocu_oth", cDemo.getString(cDemo.getColumnIndex("demo_ocu_oth")));
                    modelList.clinParam.put("urth", cDemo.getString(cDemo.getColumnIndex("clin_urth")));
                    modelList.clinParam.put("urth_type", cDemo.getString(cDemo.getColumnIndex("clin_urth_type")));
                    modelList.clinParam.put("vagi", cDemo.getString(cDemo.getColumnIndex("clin_vagi")));
                    modelList.clinParam.put("vagi_amount", cDemo.getString(cDemo.getColumnIndex("clin_vagi_amount")));
                    modelList.clinParam.put("vagi_type", cDemo.getString(cDemo.getColumnIndex("clin_vagi_type")));
                    modelList.clinParam.put("vagi_smell", cDemo.getString(cDemo.getColumnIndex("clin_vagi_smell")));
                    modelList.clinParam.put("vagi_itch", cDemo.getString(cDemo.getColumnIndex("clin_vagi_itch")));
                    modelList.clinParam.put("oro_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_oro_ulcer")));
                    modelList.clinParam.put("oro_dis", cDemo.getString(cDemo.getColumnIndex("clin_oro_dis")));
                    modelList.clinParam.put("ano_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_ano_ulcer")));
                    modelList.clinParam.put("ano_dis", cDemo.getString(cDemo.getColumnIndex("clin_ano_dis")));
                    modelList.clinParam.put("abd_pain", cDemo.getString(cDemo.getColumnIndex("clin_abd_pain")));
                    modelList.clinParam.put("uri_pain", cDemo.getString(cDemo.getColumnIndex("clin_uri_pain")));
                    modelList.clinParam.put("pain_inter", cDemo.getString(cDemo.getColumnIndex("clin_pain_inter")));
                    modelList.clinParam.put("gen_war", cDemo.getString(cDemo.getColumnIndex("clin_gen_war")));
                    modelList.clinParam.put("gen_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer")));
                    modelList.clinParam.put("pain_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_pain_ulcer")));
                    modelList.clinParam.put("blis_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_blis_ulcer")));
                    modelList.clinParam.put("dis_ulcer", cDemo.getString(cDemo.getColumnIndex("clin_dis_ulcer")));
                    modelList.clinParam.put("swel_ingui", cDemo.getString(cDemo.getColumnIndex("clin_swel_ingui")));
                    modelList.clinParam.put("ulcer_ingui", cDemo.getString(cDemo.getColumnIndex("clin_ulcer_ingui")));
                    modelList.clinParam.put("scro_pain", cDemo.getString(cDemo.getColumnIndex("clin_scro_pain")));
                    modelList.clinParam.put("urth_days", cDemo.getString(cDemo.getColumnIndex("clin_urth_days")));
                    modelList.clinParam.put("urth_type_oth_text", cDemo.getString(cDemo.getColumnIndex("clin_urth_type_oth_text")));
                    modelList.clinParam.put("vagi_days", cDemo.getString(cDemo.getColumnIndex("clin_vagi_days")));
                    modelList.clinParam.put("vagi_type_oth_text", cDemo.getString(cDemo.getColumnIndex("clin_vagi_type_oth_text")));
                    modelList.clinParam.put("vagi_smell_days", cDemo.getString(cDemo.getColumnIndex("clin_vagi_smell_days")));
                    modelList.clinParam.put("vagi_colour", cDemo.getString(cDemo.getColumnIndex("clin_vagi_colour")));
                    modelList.clinParam.put("vagi_colour_days", cDemo.getString(cDemo.getColumnIndex("clin_vagi_colour_days")));
                    modelList.clinParam.put("vagi_itch_days", cDemo.getString(cDemo.getColumnIndex("clin_vagi_itch_days")));
                    modelList.clinParam.put("oro_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_oro_ulcer_days")));
                    modelList.clinParam.put("oro_dis_days", cDemo.getString(cDemo.getColumnIndex("clin_oro_dis_days")));
                    modelList.clinParam.put("ano_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_ano_ulcer_days")));
                    modelList.clinParam.put("ano_dis_days", cDemo.getString(cDemo.getColumnIndex("clin_ano_dis_days")));
                    modelList.clinParam.put("abd_pain_days", cDemo.getString(cDemo.getColumnIndex("clin_abd_pain_days")));
                    modelList.clinParam.put("uri_pain_days", cDemo.getString(cDemo.getColumnIndex("clin_uri_pain_days")));
                    modelList.clinParam.put("pain_inter_days", cDemo.getString(cDemo.getColumnIndex("clin_pain_inter_days")));
                    modelList.clinParam.put("gen_war_days", cDemo.getString(cDemo.getColumnIndex("clin_gen_war_days")));
                    modelList.clinParam.put("gen_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer_days")));
                    modelList.clinParam.put("gen_ulcer_num", cDemo.getString(cDemo.getColumnIndex("clin_gen_ulcer_num")));
                    modelList.clinParam.put("pain_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_pain_ulcer_days")));
                    modelList.clinParam.put("blis_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_blis_ulcer_days")));
                    modelList.clinParam.put("dis_ulcer_days", cDemo.getString(cDemo.getColumnIndex("clin_dis_ulcer_days")));
                    modelList.clinParam.put("swel_ingui_days", cDemo.getString(cDemo.getColumnIndex("clin_swel_ingui_days")));
                    modelList.clinParam.put("ulcer_ingui_days", cDemo.getString(cDemo.getColumnIndex("clin_ulcer_ingui_days")));
                    modelList.clinParam.put("scro_pain_days", cDemo.getString(cDemo.getColumnIndex("clin_scro_pain_days")));
                    modelList.clinParam.put("oth_name", cDemo.getString(cDemo.getColumnIndex("clin_oth_name")));
                    modelList.clinParam.put("clin_oth_days", cDemo.getString(cDemo.getColumnIndex("clin_clin_oth_days")));
                    modelList.pastParam.put("pat_symp", cDemo.getString(cDemo.getColumnIndex("past_pat_symp")));
                    modelList.pastParam.put("symp", cDemo.getString(cDemo.getColumnIndex("past_symp")));
                    modelList.pastParam.put("hiv", cDemo.getString(cDemo.getColumnIndex("past_hiv")));
                    modelList.pastParam.put("hiv_test", cDemo.getString(cDemo.getColumnIndex("past_hiv_test")));
                    modelList.pastParam.put("symp_time", cDemo.getString(cDemo.getColumnIndex("past_symp_time")));
                    modelList.riskParam.put("age", cDemo.getString(cDemo.getColumnIndex("risk_age")));
                    modelList.riskParam.put("no_sex_week", cDemo.getString(cDemo.getColumnIndex("risk_no_sex_week")));
                    modelList.riskParam.put("no_sex_mth", cDemo.getString(cDemo.getColumnIndex("risk_no_sex_mth")));
                    modelList.riskParam.put("type_sex_oth_text", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_oth_text")));
                    modelList.riskParam.put("oth_text", cDemo.getString(cDemo.getColumnIndex("risk_oth_text")));
                    modelList.riskParam.put("prev_preg_oth_text", cDemo.getString(cDemo.getColumnIndex("risk_prev_preg_oth_text")));
                    modelList.riskParam.put("age_no", cDemo.getString(cDemo.getColumnIndex("risk_age_no")));
                    modelList.riskParam.put("type_sex_spouse", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_spouse")));
                    modelList.riskParam.put("type_sex_csw", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_csw")));
                    modelList.riskParam.put("type_sex_op", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_op")));
                    modelList.riskParam.put("type_sex_oth", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_oth")));
                    modelList.riskParam.put("type_sex_nor", cDemo.getString(cDemo.getColumnIndex("risk_type_sex_nor")));
                    modelList.riskParam.put("safe_method", cDemo.getString(cDemo.getColumnIndex("risk_safe_method")));
                    modelList.riskParam.put("oral_pill", cDemo.getString(cDemo.getColumnIndex("risk_oral_pill")));
                    modelList.riskParam.put("inj", cDemo.getString(cDemo.getColumnIndex("risk_inj")));
                    modelList.riskParam.put("nor", cDemo.getString(cDemo.getColumnIndex("risk_nor")));
                    modelList.riskParam.put("iud", cDemo.getString(cDemo.getColumnIndex("risk_iud")));
                    modelList.riskParam.put("mc", cDemo.getString(cDemo.getColumnIndex("risk_mc")));
                    modelList.riskParam.put("fc", cDemo.getString(cDemo.getColumnIndex("risk_fc")));
                    modelList.riskParam.put("sperm", cDemo.getString(cDemo.getColumnIndex("risk_sperm")));
                    modelList.riskParam.put("oth", cDemo.getString(cDemo.getColumnIndex("risk_oth")));
                    modelList.riskParam.put("na", cDemo.getString(cDemo.getColumnIndex("risk_na")));
                    modelList.riskParam.put("multi_sex", cDemo.getString(cDemo.getColumnIndex("risk_multi_sex")));
                    modelList.riskParam.put("condom", cDemo.getString(cDemo.getColumnIndex("risk_condom")));
                    modelList.riskParam.put("condom_freq", cDemo.getString(cDemo.getColumnIndex("risk_condom_freq")));
                    modelList.riskParam.put("partner_ill", cDemo.getString(cDemo.getColumnIndex("risk_partner_ill")));
                    modelList.riskParam.put("offspring_trans", cDemo.getString(cDemo.getColumnIndex("risk_offspring_trans")));
                    modelList.riskParam.put("prev_preg", cDemo.getString(cDemo.getColumnIndex("risk_prev_preg")));
                    modelList.visitParam.put("pt_type", cDemo.getString(cDemo.getColumnIndex("visit_pt_type")));
                    modelList.visitParam.put("anti_treat", cDemo.getString(cDemo.getColumnIndex("visit_anti_treat")));
                    modelList.visitParam.put("treat_place", cDemo.getString(cDemo.getColumnIndex("visit_treat_place")));
                    modelList.visitParam.put("sm_remarks", cDemo.getString(cDemo.getColumnIndex("visit_sm_remarks")));
                    modelList.visitParam.put("remarks", cDemo.getString(cDemo.getColumnIndex("visit_remarks")));
                    modelList.visitParam.put("sample_date", cDemo.getString(cDemo.getColumnIndex("visit_sample_date")));
                    modelList.visitParam.put("pd_uds", cDemo.getString(cDemo.getColumnIndex("visit_pd_uds")));
                    modelList.visitParam.put("pd_vds", cDemo.getString(cDemo.getColumnIndex("visit_pd_vds")));
                    modelList.visitParam.put("pd_guds", cDemo.getString(cDemo.getColumnIndex("visit_pd_guds")));
                    modelList.visitParam.put("pd_lap", cDemo.getString(cDemo.getColumnIndex("visit_pd_lap")));
                    modelList.visitParam.put("pd_oro", cDemo.getString(cDemo.getColumnIndex("visit_pd_oro")));
                    modelList.visitParam.put("pd_ano", cDemo.getString(cDemo.getColumnIndex("visit_pd_ano")));
                    modelList.visitParam.put("pd_sss", cDemo.getString(cDemo.getColumnIndex("visit_pd_sss")));
                    modelList.visitParam.put("pd_ibs", cDemo.getString(cDemo.getColumnIndex("visit_pd_ibs")));
                    modelList.visitParam.put("sm_us", cDemo.getString(cDemo.getColumnIndex("visit_sm_us")));
                    modelList.visitParam.put("sm_vs", cDemo.getString(cDemo.getColumnIndex("visit_sm_vs")));
                    modelList.visitParam.put("sm_ur", cDemo.getString(cDemo.getColumnIndex("visit_sm_ur")));
                    modelList.visitParam.put("sm_es", cDemo.getString(cDemo.getColumnIndex("visit_sm_es")));
                    modelList.visitParam.put("sm_bd", cDemo.getString(cDemo.getColumnIndex("visit_sm_bd")));
                    modelList.visitParam.put("sm_nc", cDemo.getString(cDemo.getColumnIndex("visit_sm_nc")));
                    modelList.created_at = cDemo.getString(cDemo.getColumnIndex(MyDB.CREATED_AT));
                    modelLists.add(modelList);
                }while (cDemo.moveToNext());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (modelLists.size()==0){
                if (txt.getVisibility()!=View.VISIBLE)
                    txt.setVisibility(View.VISIBLE);
            }else{
                if (txt.getVisibility()==View.VISIBLE)
                    txt.setVisibility(View.GONE);
            }
            recyclerView.setLayoutManager(linearLayoutManager);
            listRecAdapter = new ListRecAdapter(ListActivity.this,recyclerView,ListActivity.this,modelLists);
            recyclerView.setAdapter(listRecAdapter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
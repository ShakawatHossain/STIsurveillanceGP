package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.shakawat.stisurveillancegp.fragments.CalenderDialog;
import com.shakawat.stisurveillancegp.iview.CalenderInterface;
import com.shakawat.stisurveillancegp.model.MainModel;
import com.shakawat.stisurveillancegp.model.MyDB;

public class VisitActivity extends AppCompatActivity implements CalenderInterface {
    RadioGroup pt_type,anti_treat;
    EditText treat_place,sm_remarks,remarks;
    TextView sample_date;
    CheckBox pd_uds,pd_vds,pd_guds,pd_lap,pd_oro,pd_ano,pd_sss,pd_ibs,sm_us,sm_vs,sm_ur,
            sm_es,sm_bd,sm_nc;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);
        init();
    }
    private void init(){
//        Init
        pt_type=(RadioGroup) findViewById(R.id.pt_type);
        anti_treat=(RadioGroup) findViewById(R.id.anti_treat);
        treat_place=(EditText) findViewById(R.id.treat_place);
        sample_date=(TextView) findViewById(R.id.sample_date);
        sm_remarks=(EditText) findViewById(R.id.sm_remarks);
        remarks=(EditText) findViewById(R.id.remarks);
        pd_uds=(CheckBox) findViewById(R.id.pd_uds);
        pd_vds=(CheckBox) findViewById(R.id.pd_vds);
        pd_guds=(CheckBox) findViewById(R.id.pd_guds);
        pd_lap=(CheckBox) findViewById(R.id.pd_lap);
        pd_oro=(CheckBox) findViewById(R.id.pd_oro);
        pd_ano=(CheckBox) findViewById(R.id.pd_ano);
        pd_sss=(CheckBox) findViewById(R.id.pd_sss);
        pd_ibs=(CheckBox) findViewById(R.id.pd_ibs);
        sm_us=(CheckBox) findViewById(R.id.sm_us);
        sm_vs=(CheckBox) findViewById(R.id.sm_vs);
        sm_ur=(CheckBox) findViewById(R.id.sm_ur);
        sm_es=(CheckBox) findViewById(R.id.sm_es);
        sm_bd=(CheckBox) findViewById(R.id.sm_bd);
        sm_nc=(CheckBox) findViewById(R.id.sm_nc);
        submit=(Button) findViewById(R.id.submit);
        sample_date.setOnClickListener(clickListener);
        submit.setOnClickListener(clickListener);
//        Prev Value
        if(Integer.parseInt(MainModel.mainParam.get("sample"))==1){
            if(((TableRow) findViewById(R.id.sample_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.sample_row)).setVisibility(View.VISIBLE);
            if(((TableRow) findViewById(R.id.sample_date_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.sample_date_row)).setVisibility(View.VISIBLE);
            if(((TableRow) findViewById(R.id.sm_remarks_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.sm_remarks_row)).setVisibility(View.VISIBLE);
        }else if(Integer.parseInt(MainModel.mainParam.get("sample"))==2){
            if(((TableRow) findViewById(R.id.sample_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.sample_row)).setVisibility(View.GONE);
            if(((TableRow) findViewById(R.id.sample_date_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.sample_date_row)).setVisibility(View.GONE);
            if(((TableRow) findViewById(R.id.sm_remarks_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.sm_remarks_row)).setVisibility(View.GONE);
        }
        if (MainModel.visitParam.containsKey("pt_type")){
            if(Integer.parseInt(MainModel.visitParam.get("pt_type"))==1) pt_type.check(R.id.pt_type_new);
            else if(Integer.parseInt(MainModel.visitParam.get("pt_type"))==2) pt_type.check(R.id.pt_type_rec);
            else if(Integer.parseInt(MainModel.visitParam.get("pt_type"))==3) pt_type.check(R.id.pt_type_fu);
        }
        if (MainModel.visitParam.containsKey("anti_treat")){
            if(Integer.parseInt(MainModel.visitParam.get("anti_treat"))==1) anti_treat.check(R.id.anti_treat_yes);
            else if(Integer.parseInt(MainModel.visitParam.get("anti_treat"))==2) anti_treat.check(R.id.anti_treat_no);
        }
        treat_place.setText(MainModel.visitParam.containsKey("treat_place")?MainModel.visitParam.get("treat_place"):"");
        sample_date.setText(MainModel.visitParam.containsKey("sample_date")?MainModel.visitParam.get("sample_date"):"");
        sm_remarks.setText(MainModel.visitParam.containsKey("sm_remarks")?MainModel.visitParam.get("sm_remarks"):"");
        remarks.setText(MainModel.visitParam.containsKey("remarks")?MainModel.visitParam.get("remarks"):"");
        pd_uds.setChecked(MainModel.visitParam.containsKey("pd_uds")&&Integer.parseInt(MainModel.visitParam.get("pd_uds"))==1);
        pd_vds.setChecked(MainModel.visitParam.containsKey("pd_vds")&&Integer.parseInt(MainModel.visitParam.get("pd_vds"))==1);
        pd_guds.setChecked(MainModel.visitParam.containsKey("pd_guds")&&Integer.parseInt(MainModel.visitParam.get("pd_guds"))==1);
        pd_lap.setChecked(MainModel.visitParam.containsKey("pd_lap")&&Integer.parseInt(MainModel.visitParam.get("pd_lap"))==1);
        pd_oro.setChecked(MainModel.visitParam.containsKey("pd_oro")&&Integer.parseInt(MainModel.visitParam.get("pd_oro"))==1);
        pd_ano.setChecked(MainModel.visitParam.containsKey("pd_ano")&&Integer.parseInt(MainModel.visitParam.get("pd_ano"))==1);
        pd_sss.setChecked(MainModel.visitParam.containsKey("pd_sss")&&Integer.parseInt(MainModel.visitParam.get("pd_sss"))==1);
        pd_ibs.setChecked(MainModel.visitParam.containsKey("pd_ibs")&&Integer.parseInt(MainModel.visitParam.get("pd_ibs"))==1);
        sm_us.setChecked(MainModel.visitParam.containsKey("sm_us")&&Integer.parseInt(MainModel.visitParam.get("sm_us"))==1);
        sm_vs.setChecked(MainModel.visitParam.containsKey("sm_vs")&&Integer.parseInt(MainModel.visitParam.get("sm_vs"))==1);
        sm_ur.setChecked(MainModel.visitParam.containsKey("sm_ur")&&Integer.parseInt(MainModel.visitParam.get("sm_ur"))==1);
        sm_es.setChecked(MainModel.visitParam.containsKey("sm_es")&&Integer.parseInt(MainModel.visitParam.get("sm_es"))==1);
        sm_bd.setChecked(MainModel.visitParam.containsKey("sm_bd")&&Integer.parseInt(MainModel.visitParam.get("sm_bd"))==1);
        sm_nc.setChecked(MainModel.visitParam.containsKey("sm_nc")&&Integer.parseInt(MainModel.visitParam.get("sm_nc"))==1);
    }
    private void setValue(){
        if (pt_type.getCheckedRadioButtonId()==R.id.pt_type_new) MainModel.visitParam.put("pt_type","1") ;
        else if(pt_type.getCheckedRadioButtonId()==R.id.pt_type_rec) MainModel.visitParam.put("pt_type","2");
        else if( pt_type.getCheckedRadioButtonId()==R.id.pt_type_fu) MainModel.visitParam.put("pt_type","3");
        if(anti_treat.getCheckedRadioButtonId()==R.id.anti_treat_yes) MainModel.visitParam.put("anti_treat","1");
        else if(anti_treat.getCheckedRadioButtonId()==R.id.anti_treat_no) MainModel.visitParam.put("anti_treat","2");
        MainModel.visitParam.put("treat_place",treat_place.getText().toString());
        MainModel.visitParam.put("sample_date",sample_date.getText().toString());
        MainModel.visitParam.put("sm_remarks",sm_remarks.getText().toString());
        MainModel.visitParam.put("remarks",remarks.getText().toString());
        MainModel.visitParam.put("pd_uds",pd_uds.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_vds",pd_vds.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_guds",pd_guds.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_lap",pd_lap.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_oro",pd_oro.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_ano",pd_ano.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_sss",pd_sss.isChecked()?"1":"2");
        MainModel.visitParam.put("pd_ibs",pd_ibs.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_us",sm_us.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_vs",sm_vs.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_ur",sm_ur.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_es",sm_es.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_bd",sm_bd.isChecked()?"1":"2");
        MainModel.visitParam.put("sm_nc",sm_nc.isChecked()?"1":"2");
    }
    private boolean check(){
        if(!MainModel.visitParam.containsKey("pt_type")) {
            Toast.makeText(VisitActivity.this,"Check patient type",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.visitParam.containsKey("anti_treat")) {
            Toast.makeText(VisitActivity.this,"Check antibiotics history",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.visitParam.containsKey("treat_place") || MainModel.visitParam.get("treat_place")==null ||
                MainModel.visitParam.get("treat_place").isEmpty()){
            Toast.makeText(VisitActivity.this,"Input treatment place",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private TextView.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValue();
                if (check()){
                    new MyDB(VisitActivity.this).insert();
                    MainModel.clear();
                    MainActivity.mainActivity.finish();
                    DemoActivity.demoActivity.finish();
                    ClinicalActivity.clinicalActivity.finish();
                    PastActivity.pastActivity.finish();
                    RiskActivity.riskActivity.finish();
                    finish();
                    startActivity(new Intent(VisitActivity.this,ListActivity.class));
                }
            }else if(v.getId()==sample_date.getId()){
                new CalenderDialog(VisitActivity.this,
                        VisitActivity.this,sample_date.getText().toString(),"Date",sample_date).show();
            }
        }
    };
    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
    }
}
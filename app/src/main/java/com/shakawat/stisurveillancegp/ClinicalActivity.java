package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.shakawat.stisurveillancegp.model.MainModel;

public class ClinicalActivity extends AppCompatActivity {
    RadioGroup urth,urth_type,vagi,vagi_amount,vagi_type,vagi_smell,vagi_itch,oro_ulcer,oro_dis,
            ano_ulcer,ano_dis,abd_pain,uri_pain,pain_inter,gen_war,gen_ulcer,pain_ulcer,blis_ulcer,
            dis_ulcer,swel_ingui,ulcer_ingui,scro_pain;
    EditText urth_days,urth_type_oth_text,vagi_days,vagi_type_oth_text,vagi_smell_days,vagi_colour,
            vagi_colour_days,vagi_itch_days,oro_ulcer_days,oro_dis_days,ano_ulcer_days,ano_dis_days,
            abd_pain_days,uri_pain_days,pain_inter_days,gen_war_days,gen_ulcer_days,gen_ulcer_num,
            pain_ulcer_days,blis_ulcer_days,dis_ulcer_days,swel_ingui_days,ulcer_ingui_days,
            scro_pain_days,oth_name,clin_oth_days;
    public static ClinicalActivity clinicalActivity;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_clinical);
        init();
        clinicalActivity=this;
    }
    private void init(){
        if(Integer.parseInt(MainModel.demoParam.get("sex"))!=2){
            ((TableRow) findViewById(R.id.vagi_row)).setVisibility(View.GONE);
            ((TableRow) findViewById(R.id.vagi_amount_row)).setVisibility(View.GONE);
            ((TableRow) findViewById(R.id.vagi_type_row)).setVisibility(View.GONE);
            ((TableRow) findViewById(R.id.vagi_smell_row)).setVisibility(View.GONE);
            ((TableRow) findViewById(R.id.vagi_colour_row)).setVisibility(View.GONE);
            ((TableRow) findViewById(R.id.vagi_itch_row)).setVisibility(View.GONE);
        }
        urth=(RadioGroup) findViewById(R.id.urth);
        urth_type=(RadioGroup) findViewById(R.id.urth_type);
        vagi=(RadioGroup) findViewById(R.id.vagi);
        vagi_amount=(RadioGroup) findViewById(R.id.vagi_amount);
        vagi_type=(RadioGroup) findViewById(R.id.vagi_type);
        vagi_smell=(RadioGroup) findViewById(R.id.vagi_smell);
        vagi_itch=(RadioGroup) findViewById(R.id.vagi_itch);
        oro_ulcer=(RadioGroup) findViewById(R.id.oro_ulcer);
        oro_dis=(RadioGroup) findViewById(R.id.oro_dis);
        ano_ulcer=(RadioGroup) findViewById(R.id.ano_ulcer);
        ano_dis=(RadioGroup) findViewById(R.id.ano_dis);
        abd_pain=(RadioGroup) findViewById(R.id.abd_pain);
        uri_pain=(RadioGroup) findViewById(R.id.uri_pain);
        pain_inter=(RadioGroup) findViewById(R.id.pain_inter);
        gen_war=(RadioGroup) findViewById(R.id.gen_war);
        gen_ulcer=(RadioGroup) findViewById(R.id.gen_ulcer);
        pain_ulcer=(RadioGroup) findViewById(R.id.pain_ulcer);
        blis_ulcer=(RadioGroup) findViewById(R.id.blis_ulcer);
        dis_ulcer=(RadioGroup) findViewById(R.id.dis_ulcer);
        swel_ingui=(RadioGroup) findViewById(R.id.swel_ingui);
        ulcer_ingui=(RadioGroup) findViewById(R.id.ulcer_ingui);
        scro_pain=(RadioGroup) findViewById(R.id.scro_pain);
        urth_days=(EditText) findViewById(R.id.urth_days);
        urth_type_oth_text=(EditText) findViewById(R.id.urth_type_oth_text);
        vagi_days=(EditText) findViewById(R.id.vagi_days);
        vagi_type_oth_text=(EditText) findViewById(R.id.vagi_type_oth_text);
        vagi_smell_days=(EditText) findViewById(R.id.vagi_smell_days);
        vagi_colour=(EditText) findViewById(R.id.vagi_colour);
        vagi_colour_days=(EditText) findViewById(R.id.vagi_colour_days);
        vagi_itch_days=(EditText) findViewById(R.id.vagi_itch_days);
        oro_ulcer_days=(EditText) findViewById(R.id.oro_ulcer_days);
        oro_dis_days=(EditText) findViewById(R.id.oro_dis_days);
        ano_ulcer_days=(EditText) findViewById(R.id.ano_ulcer_days);
        ano_dis_days=(EditText) findViewById(R.id.ano_dis_days);
        abd_pain_days=(EditText) findViewById(R.id.abd_pain_days);
        uri_pain_days=(EditText) findViewById(R.id.uri_pain_days);
        pain_inter_days=(EditText) findViewById(R.id.pain_inter_days);
        gen_war_days=(EditText) findViewById(R.id.gen_war_days);
        gen_ulcer_days=(EditText) findViewById(R.id.gen_ulcer_days);
        gen_ulcer_num=(EditText) findViewById(R.id.gen_ulcer_num);
        pain_ulcer_days=(EditText) findViewById(R.id.pain_ulcer_days);
        blis_ulcer_days=(EditText) findViewById(R.id.blis_ulcer_days);
        dis_ulcer_days=(EditText) findViewById(R.id.dis_ulcer_days);
        swel_ingui_days=(EditText) findViewById(R.id.swel_ingui_days);
        ulcer_ingui_days=(EditText) findViewById(R.id.ulcer_ingui_days);
        scro_pain_days=(EditText) findViewById(R.id.scro_pain_days);
        oth_name=(EditText) findViewById(R.id.oth_name);
        clin_oth_days=(EditText) findViewById(R.id.clin_oth_days);
        submit=(Button) findViewById(R.id.submit);
        urth.setOnCheckedChangeListener(checkedChangeListener);
        urth_type.setOnCheckedChangeListener(checkedChangeListener);
        vagi.setOnCheckedChangeListener(checkedChangeListener);
        gen_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        vagi_type.setOnCheckedChangeListener(checkedChangeListener);
        vagi_smell.setOnCheckedChangeListener(checkedChangeListener);
        vagi_itch.setOnCheckedChangeListener(checkedChangeListener);
        oro_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        oro_dis.setOnCheckedChangeListener(checkedChangeListener);
        ano_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        ano_dis.setOnCheckedChangeListener(checkedChangeListener);
        abd_pain.setOnCheckedChangeListener(checkedChangeListener);
        uri_pain.setOnCheckedChangeListener(checkedChangeListener);
        pain_inter.setOnCheckedChangeListener(checkedChangeListener);
        gen_war.setOnCheckedChangeListener(checkedChangeListener);
        gen_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        pain_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        blis_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        dis_ulcer.setOnCheckedChangeListener(checkedChangeListener);
        swel_ingui.setOnCheckedChangeListener(checkedChangeListener);
        ulcer_ingui.setOnCheckedChangeListener(checkedChangeListener);
        scro_pain.setOnCheckedChangeListener(checkedChangeListener);
        submit.setOnClickListener(clickListener);
//        PREV Value
        urth_days.setText(MainModel.clinParam.containsKey("urth_days")?MainModel.clinParam.get("urth_days"):"");
        urth_type_oth_text.setText(MainModel.clinParam.containsKey("urth_type_oth_text")?MainModel.clinParam.get("urth_type_oth_text"):"");
        vagi_days.setText(MainModel.clinParam.containsKey("vagi_days")?MainModel.clinParam.get("vagi_days"):"");
        vagi_type_oth_text.setText(MainModel.clinParam.containsKey("vagi_type_oth_text")?MainModel.clinParam.get("vagi_type_oth_text"):"");
        vagi_smell_days.setText(MainModel.clinParam.containsKey("vagi_smell_days")?MainModel.clinParam.get("vagi_smell_days"):"");
        vagi_colour.setText(MainModel.clinParam.containsKey("vagi_colour")?MainModel.clinParam.get("vagi_colour"):"");
        vagi_colour_days.setText(MainModel.clinParam.containsKey("vagi_colour_days")?MainModel.clinParam.get("vagi_colour_days"):"");
        vagi_itch_days.setText(MainModel.clinParam.containsKey("vagi_itch_days")?MainModel.clinParam.get("vagi_itch_days"):"");
        oro_ulcer_days.setText(MainModel.clinParam.containsKey("oro_ulcer_days")?MainModel.clinParam.get("oro_ulcer_days"):"");
        oro_dis_days.setText(MainModel.clinParam.containsKey("oro_dis_days")?MainModel.clinParam.get("oro_dis_days"):"");
        ano_ulcer_days.setText(MainModel.clinParam.containsKey("ano_ulcer_days")?MainModel.clinParam.get("ano_ulcer_days"):"");
        ano_dis_days.setText(MainModel.clinParam.containsKey("ano_dis_days")?MainModel.clinParam.get("ano_dis_days"):"");
        abd_pain_days.setText(MainModel.clinParam.containsKey("abd_pain_days")?MainModel.clinParam.get("abd_pain_days"):"");
        uri_pain_days.setText(MainModel.clinParam.containsKey("uri_pain_days")?MainModel.clinParam.get("uri_pain_days"):"");
        pain_inter_days.setText(MainModel.clinParam.containsKey("pain_inter_days")?MainModel.clinParam.get("pain_inter_days"):"");
        gen_war_days.setText(MainModel.clinParam.containsKey("gen_war_days")?MainModel.clinParam.get("gen_war_days"):"");
        gen_ulcer_days.setText(MainModel.clinParam.containsKey("gen_ulcer_days")?MainModel.clinParam.get("gen_ulcer_days"):"");
        gen_ulcer_num.setText(MainModel.clinParam.containsKey("gen_ulcer_num")?MainModel.clinParam.get("gen_ulcer_num"):"");
        pain_ulcer_days.setText(MainModel.clinParam.containsKey("pain_ulcer_days")?MainModel.clinParam.get("pain_ulcer_days"):"");
        blis_ulcer_days.setText(MainModel.clinParam.containsKey("blis_ulcer_days")?MainModel.clinParam.get("blis_ulcer_days"):"");
        dis_ulcer_days.setText(MainModel.clinParam.containsKey("dis_ulcer_days")?MainModel.clinParam.get("dis_ulcer_days"):"");
        swel_ingui_days.setText(MainModel.clinParam.containsKey("swel_ingui_days")?MainModel.clinParam.get("swel_ingui_days"):"");
        ulcer_ingui_days.setText(MainModel.clinParam.containsKey("ulcer_ingui_days")?MainModel.clinParam.get("ulcer_ingui_days"):"");
        scro_pain_days.setText(MainModel.clinParam.containsKey("scro_pain_days")?MainModel.clinParam.get("scro_pain_days"):"");
        oth_name.setText(MainModel.clinParam.containsKey("oth_name")?MainModel.clinParam.get("oth_name"):"");
        clin_oth_days.setText(MainModel.clinParam.containsKey("clin_oth_days")?MainModel.clinParam.get("clin_oth_days"):"");
        if(MainModel.clinParam.containsKey("urth")){
            if(Integer.parseInt(MainModel.clinParam.get("urth"))==1) urth.check(R.id.urth_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("urth"))==2) urth.check(R.id.urth_ab);
        }
        if(MainModel.clinParam.containsKey("urth_type")){
            if(Integer.parseInt(MainModel.clinParam.get("urth_type"))==1){
                urth_type.check(R.id.urth_type_clr);
                if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("urth_type"))==2){
                urth_type.check(R.id.urth_type_purulent);
                if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("urth_type"))==3){
                urth_type.check(R.id.urth_type_muco);
                if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("urth_type"))==4){
                urth_type.check(R.id.urth_type_oth);
                if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()!=View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.VISIBLE);
            }
        }
        if(MainModel.clinParam.containsKey("vagi")){
            if(Integer.parseInt(MainModel.clinParam.get("vagi"))==1) vagi.check(R.id.vagi_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("vagi"))==2) vagi.check(R.id.vagi_ab);
        }
        if(MainModel.clinParam.containsKey("vagi_amount")){
            if(Integer.parseInt(MainModel.clinParam.get("vagi_amount"))==1) vagi_amount.check(R.id.vagi_amount_mild);
            else if(Integer.parseInt(MainModel.clinParam.get("vagi_amount"))==2) vagi_amount.check(R.id.vagi_amount_mod);
            else if(Integer.parseInt(MainModel.clinParam.get("vagi_amount"))==3) vagi_amount.check(R.id.vagi_amount_pro);
        }
        if(MainModel.clinParam.containsKey("vagi_type")){
            if(Integer.parseInt(MainModel.clinParam.get("vagi_type"))==1){
                vagi_type.check(R.id.vagi_type_clr);
                if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("vagi_type"))==2){
                vagi_type.check(R.id.vagi_type_purulent);
                if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("vagi_type"))==3){
                vagi_type.check(R.id.vagi_type_muco);
                if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
            }else if(Integer.parseInt(MainModel.clinParam.get("vagi_type"))==4){
                vagi_type.check(R.id.vagi_type_oth);
                if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()!=View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.VISIBLE);
            }
        }
        if(MainModel.clinParam.containsKey("vagi_smell")){
            if(Integer.parseInt(MainModel.clinParam.get("vagi_smell"))==1) vagi_smell.check(R.id.vagi_smell_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("vagi_smell"))==2) vagi_smell.check(R.id.vagi_smell_ab);
        }
        if(MainModel.clinParam.containsKey("vagi_itch")){
            if(Integer.parseInt(MainModel.clinParam.get("vagi_itch"))==1) vagi_itch.check(R.id.vagi_itch_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("vagi_itch"))==2) vagi_itch.check(R.id.vagi_itch_ab);
        }
        if(MainModel.clinParam.containsKey("oro_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("oro_ulcer"))==1) oro_ulcer.check(R.id.oro_ulcer_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("oro_ulcer"))==2) oro_ulcer.check(R.id.oro_ulcer_ab);
        }
        if(MainModel.clinParam.containsKey("oro_dis")){
            if(Integer.parseInt(MainModel.clinParam.get("oro_dis"))==1) oro_dis.check(R.id.oro_dis_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("oro_dis"))==2) oro_dis.check(R.id.oro_dis_ab);
        }
        if(MainModel.clinParam.containsKey("ano_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("ano_ulcer"))==1) ano_ulcer.check(R.id.ano_ulcer_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("ano_ulcer"))==2) ano_ulcer.check(R.id.ano_ulcer_ab);
        }
        if(MainModel.clinParam.containsKey("ano_dis")){
            if(Integer.parseInt(MainModel.clinParam.get("ano_dis"))==1) ano_dis.check(R.id.ano_dis_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("ano_dis"))==2) ano_dis.check(R.id.ano_dis_ab);
        }
        if(MainModel.clinParam.containsKey("abd_pain")){
            if(Integer.parseInt(MainModel.clinParam.get("abd_pain"))==1) abd_pain.check(R.id.abd_pain_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("abd_pain"))==2) abd_pain.check(R.id.abd_pain_ab);
        }
        if(MainModel.clinParam.containsKey("uri_pain")){
            if(Integer.parseInt(MainModel.clinParam.get("uri_pain"))==1) uri_pain.check(R.id.uri_pain_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("uri_pain"))==2) uri_pain.check(R.id.uri_pain_ab);
        }
        if(MainModel.clinParam.containsKey("pain_inter")){
            if(Integer.parseInt(MainModel.clinParam.get("pain_inter"))==1) pain_inter.check(R.id.pain_inter_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("pain_inter"))==2) pain_inter.check(R.id.pain_inter_ab);
        }
        if(MainModel.clinParam.containsKey("gen_war")){
            if(Integer.parseInt(MainModel.clinParam.get("gen_war"))==1) gen_war.check(R.id.gen_war_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("gen_war"))==2) gen_war.check(R.id.gen_war_ab);
        }
        if(MainModel.clinParam.containsKey("gen_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("gen_ulcer"))==1) {gen_ulcer.check(R.id.gen_ulcer_pr);
                if(((LinearLayout) findViewById(R.id.gen_ulcer_row)).getVisibility()!= View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.gen_ulcer_row)).setVisibility(View.VISIBLE);
            }
            else if(Integer.parseInt(MainModel.clinParam.get("gen_ulcer"))==2) {gen_ulcer.check(R.id.gen_ulcer_ab);
                if(((LinearLayout) findViewById(R.id.gen_ulcer_row)).getVisibility()== View.VISIBLE)
                    ((LinearLayout) findViewById(R.id.gen_ulcer_row)).setVisibility(View.GONE);
            }
        }
        if(MainModel.clinParam.containsKey("pain_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("pain_ulcer"))==1) pain_ulcer.check(R.id.pain_ulcer_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("pain_ulcer"))==2) pain_ulcer.check(R.id.pain_ulcer_ab);
        }
        if(MainModel.clinParam.containsKey("blis_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("blis_ulcer"))==1) blis_ulcer.check(R.id.blis_ulcer_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("blis_ulcer"))==2) blis_ulcer.check(R.id.blis_ulcer_ab);
        }
        if(MainModel.clinParam.containsKey("dis_ulcer")){
            if(Integer.parseInt(MainModel.clinParam.get("dis_ulcer"))==1) dis_ulcer.check(R.id.dis_ulcer_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("dis_ulcer"))==2) dis_ulcer.check(R.id.dis_ulcer_ab);
        }
        if(MainModel.clinParam.containsKey("swel_ingui")){
            if(Integer.parseInt(MainModel.clinParam.get("swel_ingui"))==1) swel_ingui.check(R.id.swel_ingui_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("swel_ingui"))==2) swel_ingui.check(R.id.swel_ingui_ab);
        }
        if(MainModel.clinParam.containsKey("ulcer_ingui")){
            if(Integer.parseInt(MainModel.clinParam.get("ulcer_ingui"))==1) ulcer_ingui.check(R.id.ulcer_ingui_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("ulcer_ingui"))==2) ulcer_ingui.check(R.id.ulcer_ingui_ab);
        }
        if(MainModel.clinParam.containsKey("scro_pain")){
            if(Integer.parseInt(MainModel.clinParam.get("scro_pain"))==1) scro_pain.check(R.id.scro_pain_pr);
            else if(Integer.parseInt(MainModel.clinParam.get("scro_pain"))==2) scro_pain.check(R.id.scro_pain_ab);
        }

    }
    private void setValue(){
        MainModel.clinParam.put("urth_days",urth_days.getText().toString());
        MainModel.clinParam.put("urth_type_oth_text",urth_type_oth_text.getText().toString());
        MainModel.clinParam.put("vagi_days",vagi_days.getText().toString());
        MainModel.clinParam.put("vagi_type_oth_text",vagi_type_oth_text.getText().toString());
        MainModel.clinParam.put("vagi_smell_days",vagi_smell_days.getText().toString());
        MainModel.clinParam.put("vagi_colour",vagi_colour.getText().toString());
        MainModel.clinParam.put("vagi_colour_days",vagi_colour_days.getText().toString());
        MainModel.clinParam.put("vagi_itch_days",vagi_itch_days.getText().toString());
        MainModel.clinParam.put("oro_ulcer_days",oro_ulcer_days.getText().toString());
        MainModel.clinParam.put("oro_dis_days",oro_dis_days.getText().toString());
        MainModel.clinParam.put("ano_ulcer_days",ano_ulcer_days.getText().toString());
        MainModel.clinParam.put("ano_dis_days",ano_dis_days.getText().toString());
        MainModel.clinParam.put("abd_pain_days",abd_pain_days.getText().toString());
        MainModel.clinParam.put("uri_pain_days",uri_pain_days.getText().toString());
        MainModel.clinParam.put("pain_inter_days",pain_inter_days.getText().toString());
        MainModel.clinParam.put("gen_war_days",gen_war_days.getText().toString());
        MainModel.clinParam.put("gen_ulcer_days",gen_ulcer_days.getText().toString());
        MainModel.clinParam.put("gen_ulcer_num",gen_ulcer_num.getText().toString());
        MainModel.clinParam.put("pain_ulcer_days",pain_ulcer_days.getText().toString());
        MainModel.clinParam.put("blis_ulcer_days",blis_ulcer_days.getText().toString());
        MainModel.clinParam.put("dis_ulcer_days",dis_ulcer_days.getText().toString());
        MainModel.clinParam.put("swel_ingui_days",swel_ingui_days.getText().toString());
        MainModel.clinParam.put("ulcer_ingui_days",ulcer_ingui_days.getText().toString());
        MainModel.clinParam.put("scro_pain_days",scro_pain_days.getText().toString());
        MainModel.clinParam.put("oth_name",oth_name.getText().toString());
        MainModel.clinParam.put("clin_oth_days",clin_oth_days.getText().toString());
        if(urth.getCheckedRadioButtonId()==R.id.urth_pr) MainModel.clinParam.put("urth","1");
        else if(urth.getCheckedRadioButtonId()==R.id.urth_ab) MainModel.clinParam.put("urth","2");
        if(urth_type.getCheckedRadioButtonId()==R.id.urth_type_clr)  MainModel.clinParam.put("urth_type","1");
        else if(urth_type.getCheckedRadioButtonId()==R.id.urth_type_purulent) MainModel.clinParam.put("urth_type","2");
        else if(urth_type.getCheckedRadioButtonId()==R.id.urth_type_muco) MainModel.clinParam.put("urth_type","3");
        else if(urth_type.getCheckedRadioButtonId()==R.id.urth_type_oth) MainModel.clinParam.put("urth_type","4");
        if(vagi.getCheckedRadioButtonId()==R.id.vagi_pr) MainModel.clinParam.put("vagi","1");
        else if (vagi.getCheckedRadioButtonId()==R.id.vagi_ab) MainModel.clinParam.put("vagi","2") ;
        if(vagi_amount.getCheckedRadioButtonId()==R.id.vagi_amount_mild) MainModel.clinParam.put("vagi_amount","1");
        else if(vagi_amount.getCheckedRadioButtonId()==R.id.vagi_amount_mod) MainModel.clinParam.put("vagi_amount","2");
        else if(vagi_amount.getCheckedRadioButtonId()==R.id.vagi_amount_pro) MainModel.clinParam.put("vagi_amount","3");
        if(vagi_type.getCheckedRadioButtonId()==R.id.vagi_type_clr) MainModel.clinParam.put("vagi_type","1");
        else if(vagi_type.getCheckedRadioButtonId()==R.id.vagi_type_purulent) MainModel.clinParam.put("vagi_type","2");
        else if(vagi_type.getCheckedRadioButtonId()==R.id.vagi_type_muco) MainModel.clinParam.put("vagi_type","3");
        else if(vagi_type.getCheckedRadioButtonId()==R.id.vagi_type_oth) MainModel.clinParam.put("vagi_type","4");
        if(vagi_smell.getCheckedRadioButtonId()==R.id.vagi_smell_pr) MainModel.clinParam.put("vagi_smell","1");
        else if(vagi_smell.getCheckedRadioButtonId()==R.id.vagi_smell_ab) MainModel.clinParam.put("vagi_smell","2");
        if(vagi_itch.getCheckedRadioButtonId()==R.id.vagi_itch_pr) MainModel.clinParam.put("vagi_itch","1");
        else if(vagi_itch.getCheckedRadioButtonId()==R.id.vagi_itch_ab) MainModel.clinParam.put("vagi_itch","2");
        if(oro_ulcer.getCheckedRadioButtonId()==R.id.oro_ulcer_pr) MainModel.clinParam.put("oro_ulcer","1");
        else if( oro_ulcer.getCheckedRadioButtonId()==R.id.oro_ulcer_ab) MainModel.clinParam.put("oro_ulcer","2");
        if(oro_dis.getCheckedRadioButtonId()==R.id.oro_dis_pr) MainModel.clinParam.put("oro_dis","1");
        else if(oro_dis.getCheckedRadioButtonId()==R.id.oro_dis_ab) MainModel.clinParam.put("oro_dis","2");
        if(ano_ulcer.getCheckedRadioButtonId()==R.id.ano_ulcer_pr) MainModel.clinParam.put("ano_ulcer","1");
        else if(ano_ulcer.getCheckedRadioButtonId()==R.id.ano_ulcer_ab) MainModel.clinParam.put("ano_ulcer","2");
        if(ano_dis.getCheckedRadioButtonId()==R.id.ano_dis_pr) MainModel.clinParam.put("ano_dis","1");
        else if(ano_dis.getCheckedRadioButtonId()==R.id.ano_dis_ab) MainModel.clinParam.put("ano_dis","2");
        if(abd_pain.getCheckedRadioButtonId()==R.id.abd_pain_pr) MainModel.clinParam.put("abd_pain","1");
        else if(abd_pain.getCheckedRadioButtonId()==R.id.abd_pain_ab) MainModel.clinParam.put("abd_pain","2");
        if(uri_pain.getCheckedRadioButtonId()==R.id.uri_pain_pr) MainModel.clinParam.put("uri_pain","1");
        else if(uri_pain.getCheckedRadioButtonId()==R.id.uri_pain_ab) MainModel.clinParam.put("uri_pain","2");
        if(pain_inter.getCheckedRadioButtonId()==R.id.pain_inter_pr) MainModel.clinParam.put("pain_inter","1");
        else if(pain_inter.getCheckedRadioButtonId()==R.id.pain_inter_ab) MainModel.clinParam.put("pain_inter","2");
        if(gen_war.getCheckedRadioButtonId()==R.id.gen_war_pr) MainModel.clinParam.put("gen_war","1");
        else if(gen_war.getCheckedRadioButtonId()==R.id.gen_war_ab) MainModel.clinParam.put("gen_war","2");
        if(gen_ulcer.getCheckedRadioButtonId()==R.id.gen_ulcer_pr) MainModel.clinParam.put("gen_ulcer","1");
        else if(gen_ulcer.getCheckedRadioButtonId()==R.id.gen_ulcer_ab) MainModel.clinParam.put("gen_ulcer","2");
        if(pain_ulcer.getCheckedRadioButtonId()==R.id.pain_ulcer_pr) MainModel.clinParam.put("pain_ulcer","1");
        else if(pain_ulcer.getCheckedRadioButtonId()==R.id.pain_ulcer_ab) MainModel.clinParam.put("pain_ulcer","2");
        if(blis_ulcer.getCheckedRadioButtonId()==R.id.blis_ulcer_pr) MainModel.clinParam.put("blis_ulcer","1");
        else if(blis_ulcer.getCheckedRadioButtonId()==R.id.blis_ulcer_ab) MainModel.clinParam.put("blis_ulcer","2");
        if(dis_ulcer.getCheckedRadioButtonId()==R.id.dis_ulcer_pr) MainModel.clinParam.put("dis_ulcer","1");
        else if(dis_ulcer.getCheckedRadioButtonId()==R.id.dis_ulcer_ab) MainModel.clinParam.put("dis_ulcer","2");
        if(swel_ingui.getCheckedRadioButtonId()==R.id.swel_ingui_pr) MainModel.clinParam.put("swel_ingui","1");
        else if(swel_ingui.getCheckedRadioButtonId()==R.id.swel_ingui_ab) MainModel.clinParam.put("swel_ingui","2");
        if(ulcer_ingui.getCheckedRadioButtonId()==R.id.ulcer_ingui_pr) MainModel.clinParam.put("ulcer_ingui","1");
        else if(ulcer_ingui.getCheckedRadioButtonId()==R.id.ulcer_ingui_ab) MainModel.clinParam.put("ulcer_ingui","2");
        if(scro_pain.getCheckedRadioButtonId()==R.id.scro_pain_pr) MainModel.clinParam.put("scro_pain","1");
        else if(scro_pain.getCheckedRadioButtonId()==R.id.scro_pain_ab) MainModel.clinParam.put("scro_pain","2") ;
    }
    private boolean checkValue(){

        if(!MainModel.clinParam.containsKey("urth") || Integer.parseInt(MainModel.clinParam.get("urth"))==1 && (urth_days.getText().toString()==null ||urth_days.getText().toString().isEmpty())) return false;
        if(MainModel.clinParam.containsKey("urth") && Integer.parseInt(MainModel.clinParam.get("urth"))==1)
            if(!MainModel.clinParam.containsKey("urth_type") || Integer.parseInt(MainModel.clinParam.get("urth_type"))==4 && (urth_type_oth_text.getText().toString()==null ||urth_type_oth_text.getText().toString().isEmpty())) return false;
        if(Integer.parseInt(MainModel.demoParam.get("sex"))==2) {
            if (!MainModel.clinParam.containsKey("vagi") || Integer.parseInt(MainModel.clinParam.get("vagi")) == 1 && (vagi_days.getText().toString() == null || vagi_days.getText().toString().isEmpty()))
                return false;
            if(MainModel.clinParam.containsKey("vagi") && Integer.parseInt(MainModel.clinParam.get("vagi")) == 1){
                if (!MainModel.clinParam.containsKey("vagi_type") || Integer.parseInt(MainModel.clinParam.get("vagi_type")) == 4 && (vagi_type_oth_text.getText().toString() == null || vagi_type_oth_text.getText().toString().isEmpty()))
                    return false;
                if (!MainModel.clinParam.containsKey("vagi_smell") || Integer.parseInt(MainModel.clinParam.get("vagi_smell")) == 1 && (vagi_smell_days.getText().toString() == null || vagi_smell_days.getText().toString().isEmpty()))
                    return false;
                if (!MainModel.clinParam.containsKey("vagi_itch") || Integer.parseInt(MainModel.clinParam.get("vagi_itch")) == 1 && (vagi_itch_days.getText().toString() == null || vagi_itch_days.getText().toString().isEmpty()))
                    return false;
            }
        }
        if(!MainModel.clinParam.containsKey("oro_ulcer") || Integer.parseInt(MainModel.clinParam.get("oro_ulcer"))==1 && (oro_ulcer_days.getText().toString()==null ||oro_ulcer_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("oro_dis") || Integer.parseInt(MainModel.clinParam.get("oro_dis"))==1 && (oro_dis_days.getText().toString()==null ||oro_dis_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("ano_ulcer") || Integer.parseInt(MainModel.clinParam.get("ano_ulcer"))==1 && (ano_ulcer_days.getText().toString()==null ||ano_ulcer_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("ano_dis") || Integer.parseInt(MainModel.clinParam.get("ano_dis"))==1 && (ano_dis_days.getText().toString()==null ||ano_dis_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("abd_pain") || Integer.parseInt(MainModel.clinParam.get("abd_pain"))==1 && (abd_pain_days.getText().toString()==null ||abd_pain_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("uri_pain") || Integer.parseInt(MainModel.clinParam.get("uri_pain"))==1 && (uri_pain_days.getText().toString()==null ||uri_pain_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("pain_inter") || Integer.parseInt(MainModel.clinParam.get("pain_inter"))==1 && (pain_inter_days.getText().toString()==null ||pain_inter_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("gen_war") || Integer.parseInt(MainModel.clinParam.get("gen_war"))==1 && (gen_war_days.getText().toString()==null ||gen_war_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("gen_ulcer") || Integer.parseInt(MainModel.clinParam.get("gen_ulcer"))==1 && (gen_ulcer_days.getText().toString()==null ||gen_ulcer_days.getText().toString().isEmpty())) return false;
        if(MainModel.clinParam.containsKey("gen_ulcer") && Integer.parseInt(MainModel.clinParam.get("gen_ulcer"))==1) {
            if (!MainModel.clinParam.containsKey("pain_ulcer") || Integer.parseInt(MainModel.clinParam.get("pain_ulcer")) == 1 && (pain_ulcer_days.getText().toString() == null || pain_ulcer_days.getText().toString().isEmpty()))
                return false;
            if (!MainModel.clinParam.containsKey("blis_ulcer") || Integer.parseInt(MainModel.clinParam.get("blis_ulcer")) == 1 && (blis_ulcer_days.getText().toString() == null || blis_ulcer_days.getText().toString().isEmpty()))
                return false;
            if (!MainModel.clinParam.containsKey("dis_ulcer") || Integer.parseInt(MainModel.clinParam.get("dis_ulcer")) == 1 && (dis_ulcer_days.getText().toString() == null || dis_ulcer_days.getText().toString().isEmpty()))
                return false;
        }
        if(!MainModel.clinParam.containsKey("swel_ingui") || Integer.parseInt(MainModel.clinParam.get("swel_ingui"))==1 && (swel_ingui_days.getText().toString()==null ||swel_ingui_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("ulcer_ingui") || Integer.parseInt(MainModel.clinParam.get("ulcer_ingui"))==1 && (ulcer_ingui_days.getText().toString()==null ||ulcer_ingui_days.getText().toString().isEmpty())) return false;
        if(!MainModel.clinParam.containsKey("scro_pain") || Integer.parseInt(MainModel.clinParam.get("scro_pain"))==1 && (scro_pain_days.getText().toString()==null ||scro_pain_days.getText().toString().isEmpty())) return false;
        return true;
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(group.getId()==urth.getId()){
                if (checkedId==R.id.urth_pr){
                    if(((TableRow)findViewById(R.id.urth_type_row)).getVisibility()!=View.VISIBLE) ((TableRow)findViewById(R.id.urth_type_row)).setVisibility(View.VISIBLE);
                    if (urth_days.getVisibility()!=View.VISIBLE) urth_days.setVisibility(View.VISIBLE);
                }else{
                    if(((TableRow)findViewById(R.id.urth_type_row)).getVisibility()==View.VISIBLE) ((TableRow)findViewById(R.id.urth_type_row)).setVisibility(View.GONE);
                    if (urth_days.getVisibility()==View.VISIBLE) urth_days.setVisibility(View.GONE);
                }
            }else if(group.getId()==gen_ulcer.getId()){
                if(checkedId==R.id.gen_ulcer_pr){
                    if(((LinearLayout) findViewById(R.id.gen_ulcer_row)).getVisibility()!= View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.gen_ulcer_row)).setVisibility(View.VISIBLE);
                    if(((TableRow)findViewById(R.id.pain_ulcer_row)).getVisibility()!=View.VISIBLE) ((TableRow)findViewById(R.id.pain_ulcer_row)).setVisibility(View.VISIBLE) ;
                    if(((TableRow)findViewById(R.id.blis_ulcer_row)).getVisibility()!=View.VISIBLE) ((TableRow)findViewById(R.id.blis_ulcer_row)).setVisibility(View.VISIBLE) ;
                    if(((TableRow)findViewById(R.id.dis_ulcer_row)).getVisibility()!=View.VISIBLE) ((TableRow)findViewById(R.id.dis_ulcer_row)).setVisibility(View.VISIBLE) ;
                }
                else if(checkedId==R.id.gen_ulcer_ab){
                    if(((LinearLayout) findViewById(R.id.gen_ulcer_row)).getVisibility()== View.VISIBLE)
                        ((LinearLayout) findViewById(R.id.gen_ulcer_row)).setVisibility(View.GONE);
                    if(((TableRow)findViewById(R.id.pain_ulcer_row)).getVisibility()==View.VISIBLE) ((TableRow)findViewById(R.id.pain_ulcer_row)).setVisibility(View.GONE) ;
                    if(((TableRow)findViewById(R.id.blis_ulcer_row)).getVisibility()==View.VISIBLE) ((TableRow)findViewById(R.id.blis_ulcer_row)).setVisibility(View.GONE) ;
                    if(((TableRow)findViewById(R.id.dis_ulcer_row)).getVisibility()==View.VISIBLE) ((TableRow)findViewById(R.id.dis_ulcer_row)).setVisibility(View.GONE) ;
                }
            }else if(group.getId()== vagi_type.getId()){
                if(checkedId==R.id.vagi_type_clr){
                    if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.vagi_type_purulent){
                    if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.vagi_type_muco){
                    if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.vagi_type_oth){
                    if(((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).getVisibility()!=View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.vagi_type_oth_layout)).setVisibility(View.VISIBLE);
                }
            }else if(group.getId()== urth_type.getId()){
                if(checkedId==R.id.urth_type_clr){
                    if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.urth_type_purulent){
                    if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.urth_type_muco){
                    if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.GONE);
                }else if(checkedId==R.id.urth_type_oth){
                    if(((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).getVisibility()!=View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.urth_type_oth_layout)).setVisibility(View.VISIBLE);
                }
            }else if(group.getId()==vagi.getId()){
                if(checkedId==R.id.vagi_pr){
                    if(vagi_days.getVisibility()!=View.VISIBLE) vagi_days.setVisibility(View.VISIBLE);
                    if(((TableRow) findViewById(R.id.vagi_amount_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.vagi_amount_row)).setVisibility(View.VISIBLE);
                    if(((TableRow) findViewById(R.id.vagi_type_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.vagi_type_row)).setVisibility(View.VISIBLE);
                    if(((TableRow) findViewById(R.id.vagi_smell_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.vagi_smell_row)).setVisibility(View.VISIBLE);
                    if(((TableRow) findViewById(R.id.vagi_colour_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.vagi_colour_row)).setVisibility(View.VISIBLE);
                    if(((TableRow) findViewById(R.id.vagi_itch_row)).getVisibility()!=View.VISIBLE)((TableRow) findViewById(R.id.vagi_itch_row)).setVisibility(View.VISIBLE);
                }else if(checkedId==R.id.vagi_ab){
                    if(vagi_days.getVisibility()==View.VISIBLE) vagi_days.setVisibility(View.GONE);
                    if(((TableRow) findViewById(R.id.vagi_amount_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.vagi_amount_row)).setVisibility(View.GONE);
                    if(((TableRow) findViewById(R.id.vagi_type_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.vagi_type_row)).setVisibility(View.GONE);
                    if(((TableRow) findViewById(R.id.vagi_smell_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.vagi_smell_row)).setVisibility(View.GONE);
                    if(((TableRow) findViewById(R.id.vagi_colour_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.vagi_colour_row)).setVisibility(View.GONE);
                    if(((TableRow) findViewById(R.id.vagi_itch_row)).getVisibility()==View.VISIBLE)((TableRow) findViewById(R.id.vagi_itch_row)).setVisibility(View.GONE);
                }
            }else if(group.getId()==vagi_smell.getId()){
                if(checkedId==R.id.vagi_smell_pr) {
                    if(vagi_smell_days.getVisibility()!=View.VISIBLE) vagi_smell_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.vagi_smell_ab)
                    if(vagi_smell_days.getVisibility()==View.VISIBLE) vagi_smell_days.setVisibility(View.GONE) ;
            }else if(group.getId()==vagi_itch.getId()){
                if(checkedId==R.id.vagi_itch_pr) {
                    if(vagi_itch_days.getVisibility()!=View.VISIBLE) vagi_itch_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.vagi_itch_ab)
                    if(vagi_itch_days.getVisibility()==View.VISIBLE) vagi_itch_days.setVisibility(View.GONE) ;
            }else if(group.getId()==oro_ulcer.getId()){
                if(checkedId==R.id.oro_ulcer_pr) {
                    if(oro_ulcer_days.getVisibility()!=View.VISIBLE) oro_ulcer_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.oro_ulcer_ab)
                    if(oro_ulcer_days.getVisibility()==View.VISIBLE) oro_ulcer_days.setVisibility(View.GONE) ;
            }else if(group.getId()==oro_dis.getId()){
                if(checkedId==R.id.oro_dis_pr) {
                    if(oro_dis_days.getVisibility()!=View.VISIBLE) oro_dis_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.oro_dis_ab)
                    if(oro_dis_days.getVisibility()==View.VISIBLE) oro_dis_days.setVisibility(View.GONE) ;
            }else if(group.getId()==ano_ulcer.getId()){
                if(checkedId==R.id.ano_ulcer_pr) {
                    if(ano_ulcer_days.getVisibility()!=View.VISIBLE) ano_ulcer_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.ano_ulcer_ab)
                    if(ano_ulcer_days.getVisibility()==View.VISIBLE) ano_ulcer_days.setVisibility(View.GONE) ;
            }else if(group.getId()==ano_dis.getId()){
                if(checkedId==R.id.ano_dis_pr) {
                    if(ano_dis_days.getVisibility()!=View.VISIBLE) ano_dis_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.ano_dis_ab)
                    if(ano_dis_days.getVisibility()==View.VISIBLE) ano_dis_days.setVisibility(View.GONE) ;
            }else if(group.getId()==abd_pain.getId()){
                if(checkedId==R.id.abd_pain_pr) {
                    if(abd_pain_days.getVisibility()!=View.VISIBLE) abd_pain_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.abd_pain_ab)
                    if(abd_pain_days.getVisibility()==View.VISIBLE) abd_pain_days.setVisibility(View.GONE) ;
            }else if(group.getId()==uri_pain.getId()){
                if(checkedId==R.id.uri_pain_pr) {
                    if(uri_pain_days.getVisibility()!=View.VISIBLE) uri_pain_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.uri_pain_ab)
                    if(uri_pain_days.getVisibility()==View.VISIBLE) uri_pain_days.setVisibility(View.GONE) ;
            }else if(group.getId()==pain_inter.getId()){
                if(checkedId==R.id.pain_inter_pr) {
                    if(pain_inter_days.getVisibility()!=View.VISIBLE) pain_inter_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.pain_inter_ab)
                    if(pain_inter_days.getVisibility()==View.VISIBLE) pain_inter_days.setVisibility(View.GONE) ;
            }else if(group.getId()==gen_war.getId()){
                if(checkedId==R.id.gen_war_pr) {
                    if(gen_war_days.getVisibility()!=View.VISIBLE) gen_war_days.setVisibility(View.VISIBLE) ;
                } else if (checkedId==R.id.gen_war_ab)
                    if(gen_war_days.getVisibility()==View.VISIBLE) gen_war_days.setVisibility(View.GONE) ;
            }
            else if(group.getId()==pain_ulcer.getId()){if(checkedId==R.id.pain_ulcer_pr) { if(pain_ulcer_days.getVisibility()!=View.VISIBLE) pain_ulcer_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.pain_ulcer_ab) if(pain_ulcer_days.getVisibility()==View.VISIBLE) pain_ulcer_days.setVisibility(View.GONE) ; }
            else if(group.getId()==blis_ulcer.getId()){if(checkedId==R.id.blis_ulcer_pr) { if(blis_ulcer_days.getVisibility()!=View.VISIBLE) blis_ulcer_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.blis_ulcer_ab) if(blis_ulcer_days.getVisibility()==View.VISIBLE) blis_ulcer_days.setVisibility(View.GONE) ; }
            else if(group.getId()==dis_ulcer.getId()){if(checkedId==R.id.dis_ulcer_pr) { if(dis_ulcer_days.getVisibility()!=View.VISIBLE) dis_ulcer_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.dis_ulcer_ab) if(dis_ulcer_days.getVisibility()==View.VISIBLE) dis_ulcer_days.setVisibility(View.GONE) ; }
            else if(group.getId()==swel_ingui.getId()){if(checkedId==R.id.swel_ingui_pr) { if(swel_ingui_days.getVisibility()!=View.VISIBLE) swel_ingui_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.swel_ingui_ab) if(swel_ingui_days.getVisibility()==View.VISIBLE) swel_ingui_days.setVisibility(View.GONE) ; }
            else if(group.getId()==ulcer_ingui.getId()){if(checkedId==R.id.ulcer_ingui_pr) { if(ulcer_ingui_days.getVisibility()!=View.VISIBLE) ulcer_ingui_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.ulcer_ingui_ab) if(ulcer_ingui_days.getVisibility()==View.VISIBLE) ulcer_ingui_days.setVisibility(View.GONE) ; }
            else if(group.getId()==scro_pain.getId()){if(checkedId==R.id.scro_pain_pr) { if(scro_pain_days.getVisibility()!=View.VISIBLE) scro_pain_days.setVisibility(View.VISIBLE) ; } else if (checkedId==R.id.scro_pain_ab) if(scro_pain_days.getVisibility()==View.VISIBLE) scro_pain_days.setVisibility(View.GONE) ; }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValue();
                if (checkValue()){
                    startActivity(new Intent(ClinicalActivity.this,PastActivity.class));
                }else {
                    Toast.makeText(ClinicalActivity.this,"Please write days",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
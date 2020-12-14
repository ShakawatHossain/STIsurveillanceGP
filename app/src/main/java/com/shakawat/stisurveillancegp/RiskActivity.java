package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.shakawat.stisurveillancegp.model.MainModel;

public class RiskActivity extends AppCompatActivity {
    EditText age,no_sex_week,no_sex_mth,type_sex_oth_text,oth_text,prev_preg_oth_text;
    CheckBox age_no,type_sex_spouse,type_sex_csw,type_sex_op,type_sex_oth,type_sex_nor,
            safe_method,oral_pill,inj,nor,iud,mc,fc,sperm,oth,na;
    RadioGroup multi_sex,condom,condom_freq,partner_ill,offspring_trans,prev_preg;
    public static RiskActivity riskActivity;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_risk);
        init();
        riskActivity=this;
    }
    private void init(){
//        Init
        age=(EditText) findViewById(R.id.age);
        no_sex_week=(EditText) findViewById(R.id.no_sex_week);
        no_sex_mth=(EditText) findViewById(R.id.no_sex_mth);
        type_sex_oth_text=(EditText) findViewById(R.id.type_sex_oth_text);
        oth_text=(EditText) findViewById(R.id.oth_text);
        prev_preg_oth_text  =(EditText) findViewById(R.id.prev_preg_oth_text);
        age_no=(CheckBox) findViewById(R.id.age_no);
        type_sex_spouse=(CheckBox) findViewById(R.id.type_sex_spouse);
        type_sex_csw=(CheckBox) findViewById(R.id.type_sex_csw);
        type_sex_op=(CheckBox) findViewById(R.id.type_sex_op);
        type_sex_oth=(CheckBox) findViewById(R.id.type_sex_oth);
        type_sex_nor=(CheckBox) findViewById(R.id.type_sex_nor);
        safe_method=(CheckBox) findViewById(R.id.safe_method);
        oral_pill=(CheckBox) findViewById(R.id.oral_pill);
        inj=(CheckBox) findViewById(R.id.inj);
        nor=(CheckBox) findViewById(R.id.nor);
        iud=(CheckBox) findViewById(R.id.iud);
        mc=(CheckBox) findViewById(R.id.mc);
        fc=(CheckBox) findViewById(R.id.fc);
        sperm=(CheckBox) findViewById(R.id.sperm);
        oth=(CheckBox) findViewById(R.id.oth);
        na=(CheckBox) findViewById(R.id.na);
        multi_sex=(RadioGroup) findViewById(R.id.multi_sex);
        condom=(RadioGroup) findViewById(R.id.condom);
        condom_freq=(RadioGroup) findViewById(R.id.condom_freq);
        partner_ill=(RadioGroup) findViewById(R.id.partner_ill);
        offspring_trans=(RadioGroup) findViewById(R.id.offspring_trans);
        prev_preg=(RadioGroup) findViewById(R.id.prev_preg);
        submit=(Button) findViewById(R.id.submit);
        age_no.setOnCheckedChangeListener(changeListener);
        type_sex_oth.setOnCheckedChangeListener(changeListener);
        oth.setOnCheckedChangeListener(changeListener);
        multi_sex.setOnCheckedChangeListener(checkedChangeListener);
        prev_preg.setOnCheckedChangeListener(checkedChangeListener);
        submit.setOnClickListener(clickListener);
//        prevValue
        age.setText(MainModel.riskParam.containsKey("age")?MainModel.riskParam.get("age"):"");
        no_sex_week.setText(MainModel.riskParam.containsKey("no_sex_week")?MainModel.riskParam.get("no_sex_week"):"");
        no_sex_mth.setText(MainModel.riskParam.containsKey("no_sex_mth")?MainModel.riskParam.get("no_sex_mth"):"");
        type_sex_oth_text.setText(MainModel.riskParam.containsKey("type_sex_oth_text")?MainModel.riskParam.get("type_sex_oth_text"):"");
        oth_text.setText(MainModel.riskParam.containsKey("oth_text")?MainModel.riskParam.get("oth_text"):"");
        prev_preg_oth_text.setText(MainModel.riskParam.containsKey("prev_preg_oth_text  ")? MainModel.riskParam.get("prev_preg_oth_text"):"");
        age_no.setChecked(MainModel.riskParam.containsKey("age_no")&&Integer.parseInt(MainModel.riskParam.get("age_no"))==1);
        type_sex_spouse.setChecked(MainModel.riskParam.containsKey("type_sex_spouse")&&Integer.parseInt(MainModel.riskParam.get("type_sex_spouse"))==1);
        type_sex_csw.setChecked(MainModel.riskParam.containsKey("type_sex_csw")&&Integer.parseInt(MainModel.riskParam.get("type_sex_csw"))==1);
        type_sex_op.setChecked(MainModel.riskParam.containsKey("type_sex_op")&&Integer.parseInt(MainModel.riskParam.get("type_sex_op"))==1);
        type_sex_oth.setChecked(MainModel.riskParam.containsKey("type_sex_oth")&&Integer.parseInt(MainModel.riskParam.get("type_sex_oth"))==1);
        type_sex_nor.setChecked(MainModel.riskParam.containsKey("type_sex_nor")&&Integer.parseInt(MainModel.riskParam.get("type_sex_nor"))==1);
        safe_method.setChecked(MainModel.riskParam.containsKey("safe_method")&&Integer.parseInt(MainModel.riskParam.get("safe_method"))==1);
        oral_pill.setChecked(MainModel.riskParam.containsKey("oral_pill")&&Integer.parseInt(MainModel.riskParam.get("oral_pill"))==1);
        inj.setChecked(MainModel.riskParam.containsKey("inj")&&Integer.parseInt(MainModel.riskParam.get("inj"))==1);
        nor.setChecked(MainModel.riskParam.containsKey("nor")&&Integer.parseInt(MainModel.riskParam.get("nor"))==1);
        iud.setChecked(MainModel.riskParam.containsKey("iud")&&Integer.parseInt(MainModel.riskParam.get("iud"))==1);
        mc.setChecked(MainModel.riskParam.containsKey("mc")&&Integer.parseInt(MainModel.riskParam.get("mc"))==1);
        fc.setChecked(MainModel.riskParam.containsKey("fc")&&Integer.parseInt(MainModel.riskParam.get("fc"))==1);
        sperm.setChecked(MainModel.riskParam.containsKey("sperm")&&Integer.parseInt(MainModel.riskParam.get("sperm"))==1);
        if(MainModel.riskParam.containsKey("oth")&&Integer.parseInt(MainModel.riskParam.get("oth"))==1){
            oth.setChecked(true);
            if(((TextInputLayout)findViewById(R.id.oth_layout)).getVisibility()!=View.VISIBLE)
                ((TextInputLayout)findViewById(R.id.oth_layout)).setVisibility(View.VISIBLE);
        }
        na.setChecked(MainModel.riskParam.containsKey("na")&&Integer.parseInt(MainModel.riskParam.get("na"))==1);
        if(MainModel.riskParam.containsKey("multi_sex")){
            if (Integer.parseInt(MainModel.riskParam.get("multi_sex"))==1) multi_sex.check(R.id.multi_sex_yes);
            else if (Integer.parseInt(MainModel.riskParam.get("multi_sex"))==2) multi_sex.check(R.id.multi_sex_no);
            else if (Integer.parseInt(MainModel.riskParam.get("multi_sex"))==3) multi_sex.check(R.id.multi_sex_nor);
        }
        if(MainModel.riskParam.containsKey("condom")){
            if (Integer.parseInt(MainModel.riskParam.get("condom"))==1) condom.check(R.id.condom_yes);
            else if (Integer.parseInt(MainModel.riskParam.get("condom"))==2) condom.check(R.id.condom_no);
        }
        if(MainModel.riskParam.containsKey("condom_freq")){
            if (Integer.parseInt(MainModel.riskParam.get("condom_freq"))==1) condom_freq.check(R.id.condom_freq_always);
            else if (Integer.parseInt(MainModel.riskParam.get("condom_freq"))==2) condom_freq.check(R.id.condom_freq_some);
            else if (Integer.parseInt(MainModel.riskParam.get("condom_freq"))==3) condom_freq.check(R.id.condom_freq_never);
        }
        if(MainModel.riskParam.containsKey("partner_ill")){
            if (Integer.parseInt(MainModel.riskParam.get("partner_ill"))==1) partner_ill.check(R.id.partner_ill_yes);
            else if (Integer.parseInt(MainModel.riskParam.get("partner_ill"))==2) partner_ill.check(R.id.partner_ill_no);
            else if (Integer.parseInt(MainModel.riskParam.get("partner_ill"))==3) partner_ill.check(R.id.partner_ill_don);
        }
        if(MainModel.riskParam.containsKey("offspring_trans")){
            if (Integer.parseInt(MainModel.riskParam.get("offspring_trans"))==1) offspring_trans.check(R.id.offspring_trans_yes);
            else if (Integer.parseInt(MainModel.riskParam.get("offspring_trans"))==2) offspring_trans.check(R.id.offspring_trans_no);
            else if (Integer.parseInt(MainModel.riskParam.get("offspring_trans"))==3) offspring_trans.check(R.id.offspring_trans_don);
        }
        if(MainModel.riskParam.containsKey("prev_preg")){
            if (Integer.parseInt(MainModel.riskParam.get("prev_preg"))==1) {
                prev_preg.check(R.id.prev_preg_mis);
                if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
            }
            else if (Integer.parseInt(MainModel.riskParam.get("prev_preg"))==2) {
                prev_preg.check(R.id.prev_preg_sb);
                if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
            }
            else if (Integer.parseInt(MainModel.riskParam.get("prev_preg"))==3) {
                prev_preg.check(R.id.prev_preg_oth);
                if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()!= View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.VISIBLE);
            }
            else if (Integer.parseInt(MainModel.riskParam.get("prev_preg"))==4) {
                prev_preg.check(R.id.prev_preg_na);
                if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
            }
        }


    }
    private boolean check(){
        if(!MainModel.riskParam.containsKey("age") || MainModel.riskParam.get("age")==null || MainModel.riskParam.get("age").isEmpty()) {
            Toast.makeText(RiskActivity.this,"Input age",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.riskParam.containsKey("multi_sex")) {
            Toast.makeText(RiskActivity.this,"Check sex partner",Toast.LENGTH_SHORT).show(); return false;
        }
        if(!MainModel.riskParam.containsKey("condom")) {
            Toast.makeText(RiskActivity.this,"Check condom usability",Toast.LENGTH_SHORT).show(); return false;
        }
        if(!MainModel.riskParam.containsKey("condom_freq")) {
            Toast.makeText(RiskActivity.this,"Check frequency of condom use",Toast.LENGTH_SHORT).show(); return false;
        }
        if(!MainModel.riskParam.containsKey("partner_ill")) {
            Toast.makeText(RiskActivity.this,"Check partner illness",Toast.LENGTH_SHORT).show(); return false;
        }
        if(!MainModel.riskParam.containsKey("offspring_trans")) {
            Toast.makeText(RiskActivity.this,"Check offspring transmission",Toast.LENGTH_SHORT).show(); return false;
        }
        if(!MainModel.riskParam.containsKey("prev_preg")) {
            Toast.makeText(RiskActivity.this,"Check previous pregnancy",Toast.LENGTH_SHORT).show(); return false;
        }
        return true;
    }
    private void setValue(){
        MainModel.riskParam.put("age",age.getText().toString());
        MainModel.riskParam.put("no_sex_week",no_sex_week.getText().toString());
        MainModel.riskParam.put("no_sex_mth",no_sex_mth.getText().toString());
        MainModel.riskParam.put("type_sex_oth_text",type_sex_oth_text.getText().toString());
        MainModel.riskParam.put("oth_text",oth_text.getText().toString());
        MainModel.riskParam.put("prev_preg_oth_text  ",prev_preg_oth_text  .getText().toString());
        MainModel.riskParam.put("age_no",age_no.isChecked()?"1":"2");
        MainModel.riskParam.put("type_sex_spouse",type_sex_spouse.isChecked()?"1":"2");
        MainModel.riskParam.put("type_sex_csw",type_sex_csw.isChecked()?"1":"2");
        MainModel.riskParam.put("type_sex_op",type_sex_op.isChecked()?"1":"2");
        MainModel.riskParam.put("type_sex_oth",type_sex_oth.isChecked()?"1":"2");
        MainModel.riskParam.put("type_sex_nor",type_sex_nor.isChecked()?"1":"2");
        MainModel.riskParam.put("safe_method",safe_method.isChecked()?"1":"2");
        MainModel.riskParam.put("oral_pill",oral_pill.isChecked()?"1":"2");
        MainModel.riskParam.put("inj",inj.isChecked()?"1":"2");
        MainModel.riskParam.put("nor",nor.isChecked()?"1":"2");
        MainModel.riskParam.put("iud",iud.isChecked()?"1":"2");
        MainModel.riskParam.put("mc",mc.isChecked()?"1":"2");
        MainModel.riskParam.put("fc",fc.isChecked()?"1":"2");
        MainModel.riskParam.put("sperm",sperm.isChecked()?"1":"2");
        MainModel.riskParam.put("oth",oth.isChecked()?"1":"2");
        MainModel.riskParam.put("na",na.isChecked()?"1":"2");
        if(multi_sex.getCheckedRadioButtonId()==R.id.multi_sex_yes) MainModel.riskParam.put("multi_sex","1");
        else if(multi_sex.getCheckedRadioButtonId()==R.id.multi_sex_no) MainModel.riskParam.put("multi_sex","2");
        else if(multi_sex.getCheckedRadioButtonId()==R.id.multi_sex_nor) MainModel.riskParam.put("multi_sex","3");
        if(condom.getCheckedRadioButtonId()==R.id.condom_yes) MainModel.riskParam.put("condom","1");
        else if(condom.getCheckedRadioButtonId()==R.id.condom_no) MainModel.riskParam.put("condom","2");
        if(condom_freq.getCheckedRadioButtonId()==R.id.condom_freq_always) MainModel.riskParam.put("condom_freq","1");
        else if(condom_freq.getCheckedRadioButtonId()==R.id.condom_freq_some) MainModel.riskParam.put("condom_freq","2");
        else if(condom_freq.getCheckedRadioButtonId()==R.id.condom_freq_never) MainModel.riskParam.put("condom_freq","3");
        if(partner_ill.getCheckedRadioButtonId()==R.id.partner_ill_yes) MainModel.riskParam.put("partner_ill","1");
        else if(partner_ill.getCheckedRadioButtonId()==R.id.partner_ill_no) MainModel.riskParam.put("partner_ill","2");
        else if(partner_ill.getCheckedRadioButtonId()==R.id.partner_ill_don) MainModel.riskParam.put("partner_ill","3");
        if(offspring_trans.getCheckedRadioButtonId()==R.id.offspring_trans_yes) MainModel.riskParam.put("offspring_trans","1");
        else if(offspring_trans.getCheckedRadioButtonId()==R.id.offspring_trans_no) MainModel.riskParam.put("offspring_trans","2");
        else if(offspring_trans.getCheckedRadioButtonId()==R.id.offspring_trans_don) MainModel.riskParam.put("offspring_trans","3");
        if(prev_preg.getCheckedRadioButtonId()==R.id.prev_preg_mis) MainModel.riskParam.put("prev_preg","1");
        else if(prev_preg.getCheckedRadioButtonId()==R.id.prev_preg_sb) MainModel.riskParam.put("prev_preg","2");
        else if(prev_preg.getCheckedRadioButtonId()==R.id.prev_preg_oth) MainModel.riskParam.put("prev_preg","3");
        else if(prev_preg.getCheckedRadioButtonId()==R.id.prev_preg_na) MainModel.riskParam.put("prev_preg","4");
    }
    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId()==age_no.getId() && isChecked) age.setText("88");
            else if (buttonView.getId()==type_sex_oth.getId()){
                if (isChecked){
                    if(((TextInputLayout)findViewById(R.id.type_sex_oth_layout)).getVisibility()!=View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.type_sex_oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if(((TextInputLayout)findViewById(R.id.type_sex_oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.type_sex_oth_layout)).setVisibility(View.GONE);
                }
            }else if (buttonView.getId()==oth.getId()){
                if (isChecked){
                    if(((TextInputLayout)findViewById(R.id.oth_layout)).getVisibility()!=View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.oth_layout)).setVisibility(View.VISIBLE);
                }else{
                    if(((TextInputLayout)findViewById(R.id.oth_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.oth_layout)).setVisibility(View.GONE);
                }
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==prev_preg.getId()){
                if (checkedId==R.id.prev_preg_mis){
                    if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
                }
                else if (checkedId==R.id.prev_preg_sb){
                    if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
                }
                else if (checkedId==R.id.prev_preg_oth){
                    if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()!= View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.VISIBLE);
                }
                else if (checkedId==R.id.prev_preg_na){
                    if (((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).getVisibility()== View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.prev_preg_oth_layout)).setVisibility(View.GONE);
                }
            }else if (group.getId()==multi_sex.getId()){
                if (checkedId==R.id.multi_sex_yes){
                    if (((TableRow) findViewById(R.id.no_sex_row)).getVisibility()!= View.VISIBLE)
                        ((TableRow) findViewById(R.id.no_sex_row)).setVisibility(View.VISIBLE);
                }
                else {
                    if (((TableRow) findViewById(R.id.no_sex_row)).getVisibility()== View.VISIBLE)
                        ((TableRow) findViewById(R.id.no_sex_row)).setVisibility(View.GONE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValue();
                if (check()){
                    startActivity(new Intent(RiskActivity.this,VisitActivity.class));
                }
            }
        }
    };
}
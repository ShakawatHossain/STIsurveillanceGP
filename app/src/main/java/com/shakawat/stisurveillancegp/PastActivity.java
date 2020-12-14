package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.shakawat.stisurveillancegp.model.MainModel;

public class PastActivity extends AppCompatActivity {
    RadioGroup pat_symp,symp,hiv,hiv_test;
    EditText symp_time;
    public static PastActivity pastActivity;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_past);
        init();
        pastActivity=this;
    }
    private void init(){
        pat_symp=(RadioGroup) findViewById(R.id.pat_symp);
        symp=(RadioGroup) findViewById(R.id.symp);
        hiv=(RadioGroup) findViewById(R.id.hiv);
        hiv_test=(RadioGroup) findViewById(R.id.hiv_test);
        symp_time=(EditText) findViewById(R.id.symp_time);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
        symp.setOnCheckedChangeListener(checkedChangeListener);
        hiv.setOnCheckedChangeListener(checkedChangeListener);
//        Prev Value
        if(MainModel.pastParam.containsKey("pat_symp")){
            if (Integer.parseInt(MainModel.pastParam.get("pat_symp"))==1){pat_symp.check(R.id.pat_symp_yes);}
            else if (Integer.parseInt(MainModel.pastParam.get("pat_symp"))==2){pat_symp.check(R.id.pat_symp_no);}
            else if (Integer.parseInt(MainModel.pastParam.get("pat_symp"))==3){pat_symp.check(R.id.pat_symp_un);}
            else if (Integer.parseInt(MainModel.pastParam.get("pat_symp"))==4){pat_symp.check(R.id.pat_symp_nor);}
        }
        if(MainModel.pastParam.containsKey("symp")){
            if (Integer.parseInt(MainModel.pastParam.get("symp"))==1){
                symp.check(R.id.symp_yes);
                if(((TextInputLayout) findViewById(R.id.symp_time_layout)).getVisibility()!= View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.symp_time_layout)).setVisibility(View.VISIBLE);
            }
            else if (Integer.parseInt(MainModel.pastParam.get("symp"))==2){
                symp.check(R.id.symp_no);
                if(((TextInputLayout) findViewById(R.id.symp_time_layout)).getVisibility()== View.VISIBLE)
                    ((TextInputLayout) findViewById(R.id.symp_time_layout)).setVisibility(View.GONE);
            }
        }
        if(MainModel.pastParam.containsKey("hiv")){
            if (Integer.parseInt(MainModel.pastParam.get("hiv"))==1){
                hiv.check(R.id.hiv_yes);
                if(((TableRow) findViewById(R.id.hiv_test_row)).getVisibility()!=View.VISIBLE)
                    ((TableRow) findViewById(R.id.hiv_test_row)).setVisibility(View.VISIBLE);
            }
            else if (Integer.parseInt(MainModel.pastParam.get("hiv"))==2){
                hiv.check(R.id.hiv_no);
                if(((TableRow) findViewById(R.id.hiv_test_row)).getVisibility()==View.VISIBLE)
                    ((TableRow) findViewById(R.id.hiv_test_row)).setVisibility(View.GONE);
            }
        }
        if(MainModel.pastParam.containsKey("hiv_test")){
            if (Integer.parseInt(MainModel.pastParam.get("hiv_test"))==1){hiv_test.check(R.id.hiv_test_yes);}
            else if (Integer.parseInt(MainModel.pastParam.get("hiv_test"))==2){hiv_test.check(R.id.hiv_test_no);}
        }
        symp_time.setText(MainModel.pastParam.containsKey("symp_time")?MainModel.pastParam.get("symp_time"):"");

    }
    private void setValue() {
        MainModel.pastParam.put("symp_time", symp_time.getText().toString());
        if (pat_symp.getCheckedRadioButtonId() == R.id.pat_symp_yes)
            MainModel.pastParam.put("pat_symp", "1");
        else if (pat_symp.getCheckedRadioButtonId() == R.id.pat_symp_no)
            MainModel.pastParam.put("pat_symp", "2");
        else if (pat_symp.getCheckedRadioButtonId() == R.id.pat_symp_un)
            MainModel.pastParam.put("pat_symp", "3");
        else if (pat_symp.getCheckedRadioButtonId() == R.id.pat_symp_nor)
            MainModel.pastParam.put("pat_symp", "4");
        if (symp.getCheckedRadioButtonId() == R.id.symp_yes)
            MainModel.pastParam.put("symp", "1");
        else if (symp.getCheckedRadioButtonId() == R.id.symp_no)
            MainModel.pastParam.put("symp", "2");
        if (hiv.getCheckedRadioButtonId() == R.id.hiv_yes)
            MainModel.pastParam.put("hiv", "1");
        else if (hiv.getCheckedRadioButtonId() == R.id.hiv_no)
            MainModel.pastParam.put("hiv", "2");
        if (hiv_test.getCheckedRadioButtonId() == R.id.hiv_test_yes)
            MainModel.pastParam.put("hiv_test", "1");
        else if (hiv_test.getCheckedRadioButtonId() == R.id.hiv_test_no)
            MainModel.pastParam.put("hiv_test", "2");
    }
    private boolean check(){
        if(!MainModel.pastParam.containsKey("pat_symp")) {
            Toast.makeText(PastActivity.this,"Check previous symptom",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.pastParam.containsKey("symp")) {
            Toast.makeText(PastActivity.this,"Check symptom",Toast.LENGTH_SHORT).show();
            return false;
        }else if(Integer.parseInt(MainModel.pastParam.get("symp"))==1 && (!MainModel.pastParam.containsKey("symp_time") || MainModel.pastParam.get("symp_time").isEmpty())){
            Toast.makeText(PastActivity.this,"No. of symptom times",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.pastParam.containsKey("hiv")) {
            Toast.makeText(PastActivity.this,"Check HIV",Toast.LENGTH_SHORT).show();
            return false;
        }else if(Integer.parseInt(MainModel.pastParam.get("hiv"))==1 && !MainModel.pastParam.containsKey("hiv_test")){
            Toast.makeText(PastActivity.this,"Test result",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()== symp.getId()) {
                if (checkedId==R.id.symp_yes){
                    if(((TextInputLayout) findViewById(R.id.symp_time_layout)).getVisibility()!= View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.symp_time_layout)).setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.symp_no){
                    if(((TextInputLayout) findViewById(R.id.symp_time_layout)).getVisibility()== View.VISIBLE)
                        ((TextInputLayout) findViewById(R.id.symp_time_layout)).setVisibility(View.GONE);
                }
            }
            if (group.getId()== hiv.getId()) {
                if (checkedId==R.id.hiv_yes){
                    if(((TableRow) findViewById(R.id.hiv_test_row)).getVisibility()!= View.VISIBLE)
                        ((TableRow) findViewById(R.id.hiv_test_row)).setVisibility(View.VISIBLE);
                }else if (checkedId==R.id.hiv_no){
                    if(((TableRow) findViewById(R.id.hiv_test_row)).getVisibility()== View.VISIBLE)
                        ((TableRow) findViewById(R.id.hiv_test_row)).setVisibility(View.GONE);
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
                    startActivity(new Intent(PastActivity.this,RiskActivity.class));
                }
            }
        }
    };
}
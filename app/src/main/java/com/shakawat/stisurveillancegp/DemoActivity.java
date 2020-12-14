package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.shakawat.stisurveillancegp.model.MainModel;
import com.shakawat.stisurveillancegp.model.MyDB;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {
    EditText name,age,mob,ocu,child_num,edu,edu_oth_txt;
    RadioGroup sex,marrige,edu_type,income,child;
    AutoCompleteTextView un,up,dis;
    CheckBox add_un,student,service,business,farmer,rickshaw,driver,housewife,unemployed,dgh,dl,carpenter,huckster,ocu_oth;
    public static DemoActivity demoActivity;
    Button submit;
    MyDB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_demo);
        init();
        demoActivity=this;
    }
    private void init(){
//        init
        myDB= new MyDB(DemoActivity.this);
        name=(EditText) findViewById(R.id.name);
        age=(EditText) findViewById(R.id.age);
        mob=(EditText) findViewById(R.id.mob);
        ocu=(EditText) findViewById(R.id.ocu);
        child_num=(EditText) findViewById(R.id.child_num);
        edu_oth_txt=(EditText) findViewById(R.id.edu_oth_txt);
        edu=(EditText) findViewById(R.id.edu);
        sex=(RadioGroup) findViewById(R.id.sex);
        marrige=(RadioGroup) findViewById(R.id.marrige);
        edu_type=(RadioGroup) findViewById(R.id.edu_type);
        income=(RadioGroup) findViewById(R.id.income);
        child=(RadioGroup) findViewById(R.id.child);
        un=(AutoCompleteTextView) findViewById(R.id.un);
        up=(AutoCompleteTextView) findViewById(R.id.up);
        dis=(AutoCompleteTextView) findViewById(R.id.dis);
        add_un=(CheckBox)findViewById(R.id.add_un);
        student=(CheckBox) findViewById(R.id.student);
        service=(CheckBox) findViewById(R.id.service);
        business=(CheckBox) findViewById(R.id.business);
        farmer=(CheckBox) findViewById(R.id.farmer);
        rickshaw=(CheckBox) findViewById(R.id.rickshaw);
        driver=(CheckBox) findViewById(R.id.driver);
        housewife=(CheckBox) findViewById(R.id.housewife);
        unemployed=(CheckBox) findViewById(R.id.unemployed);
        dgh=(CheckBox) findViewById(R.id.dgh);
        dl=(CheckBox) findViewById(R.id.dl);
        carpenter=(CheckBox) findViewById(R.id.carpenter);
        huckster=(CheckBox) findViewById(R.id.huckster);
        ocu_oth=(CheckBox) findViewById(R.id.ocu_oth);
        submit=(Button)findViewById(R.id.submit);
        edu_type.setOnCheckedChangeListener(checkedChangeListener);
        child.setOnCheckedChangeListener(checkedChangeListener);
        ocu_oth.setOnCheckedChangeListener(changeListener);
        submit.setOnClickListener(clickListener);
        new loadDB().execute();
//      Prev value
        name.setText(MainModel.demoParam.containsKey("name")?MainModel.demoParam.get("name"):"");
        age.setText(MainModel.demoParam.containsKey("age")?MainModel.demoParam.get("age"):"");
        mob.setText(MainModel.demoParam.containsKey("mob")?MainModel.demoParam.get("mob"):"");
        ocu.setText(MainModel.demoParam.containsKey("ocu")?MainModel.demoParam.get("ocu"):"");
        child_num.setText(MainModel.demoParam.containsKey("child_num")?MainModel.demoParam.get("child_num"):"");
        edu_oth_txt.setText(MainModel.demoParam.containsKey("edu_oth_txt")?MainModel.demoParam.get("edu_oth_txt"):"");
        edu.setText(MainModel.demoParam.containsKey("edu")?MainModel.demoParam.get("edu"):"");
        if(MainModel.demoParam.containsKey("sex")){
            if (Integer.parseInt(MainModel.demoParam.get("sex"))==1){sex.check(R.id.male);}
            else if (Integer.parseInt(MainModel.demoParam.get("sex"))==2){sex.check(R.id.female);}
            else if (Integer.parseInt(MainModel.demoParam.get("sex"))==3){sex.check(R.id.transgender);}
        }
        if(MainModel.demoParam.containsKey("marrige")){
            if (Integer.parseInt(MainModel.demoParam.get("marrige"))==1){marrige.check(R.id.unm);}
            else if (Integer.parseInt(MainModel.demoParam.get("marrige"))==2){marrige.check(R.id.mar);}
            else if (Integer.parseInt(MainModel.demoParam.get("marrige"))==3){marrige.check(R.id.sep);}
            else if (Integer.parseInt(MainModel.demoParam.get("marrige"))==4){marrige.check(R.id.wid);}
        }
        if(MainModel.demoParam.containsKey("edu_type")){
            if (Integer.parseInt(MainModel.demoParam.get("edu_type"))==1){edu_type.check(R.id.edu_il);if(edu_oth_txt.getVisibility()== View.VISIBLE)edu_oth_txt.setVisibility(View.GONE);}
            else if (Integer.parseInt(MainModel.demoParam.get("edu_type"))==2){edu_type.check(R.id.edu_sig);if(edu_oth_txt.getVisibility()== View.VISIBLE)edu_oth_txt.setVisibility(View.GONE);}
            else if (Integer.parseInt(MainModel.demoParam.get("edu_type"))==3){edu_type.check(R.id.edu_oth);if(edu_oth_txt.getVisibility()!= View.VISIBLE)edu_oth_txt.setVisibility(View.VISIBLE); }
        }
        if(MainModel.demoParam.containsKey("income")){
            if (Integer.parseInt(MainModel.demoParam.get("income"))==1){income.check(R.id.lt10);}
            else if (Integer.parseInt(MainModel.demoParam.get("income"))==2){income.check(R.id.lt20);}
            else if (Integer.parseInt(MainModel.demoParam.get("income"))==3){income.check(R.id.lt30);}
            else if (Integer.parseInt(MainModel.demoParam.get("income"))==4){income.check(R.id.gt30);}
        }
        if(MainModel.demoParam.containsKey("child")){
            if (Integer.parseInt(MainModel.demoParam.get("child"))==1){child.check(R.id.child_yes);
                if(((TextInputLayout)findViewById(R.id.child_num_layout)).getVisibility()!=View.VISIBLE)
                    ((TextInputLayout)findViewById(R.id.child_num_layout)).setVisibility(View.VISIBLE);
            }
            else if (Integer.parseInt(MainModel.demoParam.get("child"))==2){child.check(R.id.child_no);
                if(((TextInputLayout)findViewById(R.id.child_num_layout)).getVisibility()==View.VISIBLE)
                    ((TextInputLayout)findViewById(R.id.child_num_layout)).setVisibility(View.GONE);
            }
        }
        //        un,up,dis add data to scroll
        un.setText(MainModel.demoParam.containsKey("un")?MainModel.demoParam.get("un"):"");
        up.setText(MainModel.demoParam.containsKey("up")?MainModel.demoParam.get("up"):"");
        dis.setText(MainModel.demoParam.containsKey("dis")?MainModel.demoParam.get("dis"):"");
        if(MainModel.demoParam.containsKey("add_un") && Integer.parseInt(MainModel.demoParam.get("add_un"))==1) add_un.setChecked(true);
        student.setChecked(MainModel.demoParam.containsKey("student")&&Integer.parseInt(MainModel.demoParam.get("student"))==1);
        service.setChecked(MainModel.demoParam.containsKey("service")&&Integer.parseInt(MainModel.demoParam.get("service"))==1);
        business.setChecked(MainModel.demoParam.containsKey("business")&&Integer.parseInt(MainModel.demoParam.get("business"))==1);
        farmer.setChecked(MainModel.demoParam.containsKey("farmer")&&Integer.parseInt(MainModel.demoParam.get("farmer"))==1);
        rickshaw.setChecked(MainModel.demoParam.containsKey("rickshaw")&&Integer.parseInt(MainModel.demoParam.get("rickshaw"))==1);
        driver.setChecked(MainModel.demoParam.containsKey("driver")&&Integer.parseInt(MainModel.demoParam.get("driver"))==1);
        housewife.setChecked(MainModel.demoParam.containsKey("housewife")&&Integer.parseInt(MainModel.demoParam.get("housewife"))==1);
        unemployed.setChecked(MainModel.demoParam.containsKey("unemployed")&&Integer.parseInt(MainModel.demoParam.get("unemployed"))==1);
        dgh.setChecked(MainModel.demoParam.containsKey("dgh")&&Integer.parseInt(MainModel.demoParam.get("dgh"))==1);
        dl.setChecked(MainModel.demoParam.containsKey("dl")&&Integer.parseInt(MainModel.demoParam.get("dl"))==1);
        carpenter.setChecked(MainModel.demoParam.containsKey("carpenter")&&Integer.parseInt(MainModel.demoParam.get("carpenter"))==1);
        huckster.setChecked(MainModel.demoParam.containsKey("huckster")&&Integer.parseInt(MainModel.demoParam.get("huckster"))==1);
        if(MainModel.demoParam.containsKey("ocu_oth")&&Integer.parseInt(MainModel.demoParam.get("ocu_oth"))==1){
            ocu_oth.setChecked(true);
            if(ocu.getVisibility()!=View.VISIBLE) ocu.setVisibility(View.VISIBLE);
        }else{
            if(ocu.getVisibility()==View.VISIBLE) ocu.setVisibility(View.GONE);
        }
    }
    private void setValue(){
        MainModel.demoParam.put("name",name.getText().toString());
        MainModel.demoParam.put("age",age.getText().toString());
        MainModel.demoParam.put("mob",mob.getText().toString());
        MainModel.demoParam.put("ocu",ocu.getText().toString());
        MainModel.demoParam.put("child_num",child_num.getText().toString());
        MainModel.demoParam.put("edu_oth_txt",edu_oth_txt.getText().toString());
        MainModel.demoParam.put("edu",edu.getText().toString());
        MainModel.demoParam.put("un",un.getText().toString());
        MainModel.demoParam.put("up",up.getText().toString());
        MainModel.demoParam.put("dis",dis.getText().toString());
        MainModel.demoParam.put("add_un",add_un.isChecked()?"1":"2");
        MainModel.demoParam.put("student",student.isChecked()?"1":"2");
        MainModel.demoParam.put("service",service.isChecked()?"1":"2");
        MainModel.demoParam.put("business",business.isChecked()?"1":"2");
        MainModel.demoParam.put("farmer",farmer.isChecked()?"1":"2");
        MainModel.demoParam.put("rickshaw",rickshaw.isChecked()?"1":"2");
        MainModel.demoParam.put("driver",driver.isChecked()?"1":"2");
        MainModel.demoParam.put("housewife",housewife.isChecked()?"1":"2");
        MainModel.demoParam.put("unemployed",unemployed.isChecked()?"1":"2");
        MainModel.demoParam.put("dgh",dgh.isChecked()?"1":"2");
        MainModel.demoParam.put("dl",dl.isChecked()?"1":"2");
        MainModel.demoParam.put("carpenter",carpenter.isChecked()?"1":"2");
        MainModel.demoParam.put("huckster",huckster.isChecked()?"1":"2");
        MainModel.demoParam.put("ocu_oth",ocu_oth.isChecked()?"1":"2");
        if(sex.getCheckedRadioButtonId()==R.id.male) MainModel.demoParam.put("sex","1");
        else if(sex.getCheckedRadioButtonId()==R.id.female) MainModel.demoParam.put("sex","2");
        else if(sex.getCheckedRadioButtonId()==R.id.transgender) MainModel.demoParam.put("sex","3");
        if(marrige.getCheckedRadioButtonId()==R.id.unm) MainModel.demoParam.put("marrige","1");
        else if(marrige.getCheckedRadioButtonId()==R.id.mar) MainModel.demoParam.put("marrige","2");
        else if(marrige.getCheckedRadioButtonId()==R.id.sep) MainModel.demoParam.put("marrige","3");
        else if(marrige.getCheckedRadioButtonId()==R.id.wid) MainModel.demoParam.put("marrige","4");
        if(edu_type.getCheckedRadioButtonId()==R.id.edu_il) MainModel.demoParam.put("edu_type","1");
        else if(edu_type.getCheckedRadioButtonId()==R.id.edu_sig) MainModel.demoParam.put("edu_type","2");
        else if(edu_type.getCheckedRadioButtonId()==R.id.edu_oth) MainModel.demoParam.put("edu_type","3");
        if(income.getCheckedRadioButtonId()==R.id.lt10) MainModel.demoParam.put("income","1");
        else if(income.getCheckedRadioButtonId()==R.id.lt20) MainModel.demoParam.put("income","2");
        else if(income.getCheckedRadioButtonId()==R.id.lt30) MainModel.demoParam.put("income","3");
        else if(income.getCheckedRadioButtonId()==R.id.gt30) MainModel.demoParam.put("income","4");
        if(child.getCheckedRadioButtonId()==R.id.child_yes)MainModel.demoParam.put("child","1");
        else if(child.getCheckedRadioButtonId()==R.id.child_no)MainModel.demoParam.put("child","2");
    }
    private boolean checkValue(){
        if(!MainModel.demoParam.containsKey("name") || MainModel.demoParam.get("name").isEmpty()) {Toast.makeText(DemoActivity.this,"Check Name",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.demoParam.containsKey("age") || MainModel.demoParam.get("age").isEmpty()) {Toast.makeText(DemoActivity.this,"Check age",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.demoParam.containsKey("mob") || MainModel.demoParam.get("mob").isEmpty()) {Toast.makeText(DemoActivity.this,"Check Mobile no",Toast.LENGTH_SHORT).show();return false;}
        if(MainModel.demoParam.containsKey("ocu_oth")&&Integer.parseInt(MainModel.demoParam.get("ocu_oth"))==1) {
            if(!MainModel.demoParam.containsKey("ocu") || MainModel.demoParam.get("ocu").isEmpty()) { Toast.makeText(DemoActivity.this,"Check Ocupation",Toast.LENGTH_SHORT).show();return false;}
        }
        if(!MainModel.demoParam.containsKey("marrige")) {
            Toast.makeText(DemoActivity.this,"Check marrige",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.demoParam.containsKey("income")) {
            Toast.makeText(DemoActivity.this,"Check income",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!MainModel.demoParam.containsKey("child")) {
            Toast.makeText(DemoActivity.this,"Check child",Toast.LENGTH_SHORT).show();
            return false;
        }else if(Integer.parseInt(MainModel.demoParam.get("child"))==1 && child_num.getText().toString().isEmpty()){
            Toast.makeText(DemoActivity.this,"Input child number",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==edu_type.getId()){
//                Log.d("Edu_tpe","CheckChange");
                if (checkedId==R.id.edu_il) {if(edu_oth_txt.getVisibility()== View.VISIBLE)edu_oth_txt.setVisibility(View.GONE);}
                else if (checkedId==R.id.edu_sig) {if(edu_oth_txt.getVisibility()== View.VISIBLE)edu_oth_txt.setVisibility(View.GONE);}
                else if(checkedId==R.id.edu_oth) {if(edu_oth_txt.getVisibility()!= View.VISIBLE)edu_oth_txt.setVisibility(View.VISIBLE);}
            }else if(group.getId()==child.getId()){
                if (checkedId==R.id.child_yes){
                    if(((TextInputLayout)findViewById(R.id.child_num_layout)).getVisibility()!=View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.child_num_layout)).setVisibility(View.VISIBLE);
                }
                else if(checkedId==R.id.child_no){
                    if(((TextInputLayout)findViewById(R.id.child_num_layout)).getVisibility()==View.VISIBLE)
                        ((TextInputLayout)findViewById(R.id.child_num_layout)).setVisibility(View.GONE);
                }
            }
        }
    };
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValue();
                if (checkValue()){
                    startActivity(new Intent(DemoActivity.this,ClinicalActivity.class));
                }
            }
        }
    };
    private CompoundButton.OnCheckedChangeListener changeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId()==ocu_oth.getId()){
                if (isChecked){
                    if(ocu.getVisibility()!=View.VISIBLE) ocu.setVisibility(View.VISIBLE);
                }else{
                    if(ocu.getVisibility()==View.VISIBLE) ocu.setVisibility(View.GONE);
                }
            }
        }
    };
    private class loadDB extends AsyncTask<Void,Void,Void>{
        ArrayList<String> adis = new ArrayList<>();
        ArrayList<String> aup = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {
            Cursor cdis = myDB.getAll_dis();
            Cursor cup = myDB.getAll_up();

            if(cdis.moveToFirst()){
                do {
                    adis.add(cdis.getString(cdis.getColumnIndex("name")));
                }while (cdis.moveToNext());
            }

            if(cup.moveToFirst()){
                do {
                    aup.add(cup.getString(cup.getColumnIndex("name")));
                }while (cup.moveToNext());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dis.setAdapter(new ArrayAdapter<String>(DemoActivity.this,android.R.layout.simple_dropdown_item_1line,adis));
            up.setAdapter(new ArrayAdapter<String>(DemoActivity.this,android.R.layout.simple_dropdown_item_1line,aup));
        }
    }
}
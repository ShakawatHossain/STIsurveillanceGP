package com.shakawat.stisurveillancegp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shakawat.stisurveillancegp.fragments.CalenderDialog;
import com.shakawat.stisurveillancegp.iview.CalenderInterface;
import com.shakawat.stisurveillancegp.model.MainModel;

public class MainActivity extends AppCompatActivity implements CalenderInterface {
    Spinner hos_code;
    RadioGroup dept,sample;
    EditText sample_id;
    TextView in_date,case_id;
    Button submit;
    public static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_main);
        if(MainModel.user_id==null || MainModel.user_id.isEmpty()){
            finish();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
        init();
        mainActivity=this;
    }
    private void init(){
//        init
        hos_code=(Spinner) findViewById(R.id.hos_code);
        dept=(RadioGroup) findViewById(R.id.dept);
        sample=(RadioGroup) findViewById(R.id.sample);
        sample_id=(EditText) findViewById(R.id.sample_id);
        in_date=(TextView) findViewById(R.id.in_date);
        case_id=(TextView) findViewById(R.id.case_id);
        hos_code.setOnItemSelectedListener(selectedListener);
        dept.setOnCheckedChangeListener(checkedChangeListener);
        sample.setOnCheckedChangeListener(checkedChangeListener);
        sample_id.addTextChangedListener(textWatcher);
        in_date=(TextView) findViewById(R.id.in_date);
        in_date.setOnClickListener(clickListener);
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(clickListener);
//        prev value
        if(MainModel.mainParam.containsKey("hos_code")){
            hos_code.setSelection(Integer.parseInt(MainModel.mainParam.get("hos_code")));
        }
        if(MainModel.mainParam.containsKey("dept")){
            if (Integer.parseInt(MainModel.mainParam.get("dept"))==1){dept.check(R.id.gynae);}
            if (Integer.parseInt(MainModel.mainParam.get("dept"))==2){dept.check(R.id.skin);}
        }
        if(MainModel.mainParam.containsKey("sample")){
            if (Integer.parseInt(MainModel.mainParam.get("sample"))==1){sample.check(R.id.sc);}
            if (Integer.parseInt(MainModel.mainParam.get("sample"))==2){sample.check(R.id.nc);}
        }
        if(MainModel.mainParam.containsKey("sample_id")){sample_id.setText(MainModel.mainParam.get("sample_id"));}
        if(MainModel.mainParam.containsKey("in_date")){in_date.setText(MainModel.mainParam.get("in_date"));}
        if(MainModel.mainParam.containsKey("case_id")){case_id.setText(MainModel.mainParam.get("case_id"));}
    }
    private void setValues(){
        MainModel.mainParam.put("case_id",case_id.getText().toString());
    }
    private boolean check(){
        if(!MainModel.mainParam.containsKey("hos_code") || Integer.parseInt(MainModel.mainParam.get("hos_code"))==0){
            Toast.makeText(MainActivity.this,"Select Hospital code",Toast.LENGTH_SHORT).show(); return false;}
        if(!MainModel.mainParam.containsKey("dept")) {
            Toast.makeText(MainActivity.this,"Check Department",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.mainParam.containsKey("sample")) {
            Toast.makeText(MainActivity.this,"Select sample status",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.mainParam.containsKey("sample_id")) {
            Toast.makeText(MainActivity.this,"Insert sample id",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.mainParam.containsKey("in_date")) {
            Toast.makeText(MainActivity.this,"Insert Date",Toast.LENGTH_SHORT).show();return false;}
        if(!MainModel.mainParam.containsKey("case_id")) return false;
        return true;
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==submit.getId()){
                setValues();
                if (check()){
                    startActivity(new Intent(MainActivity.this,DemoActivity.class));
                }
            }else if(v.getId()==in_date.getId()){
                new CalenderDialog(MainActivity.this,MainActivity.this,in_date.getText().toString(),
                        "Date",in_date).show();
                updateID();
            }
        }
    };
    private Spinner.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position>0){
                MainModel.mainParam.put("hos_code",String.valueOf(position));
                updateID();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (group.getId()==dept.getId()){
                if (checkedId==R.id.gynae) MainModel.mainParam.put("dept","1");
                else if (checkedId==R.id.skin) MainModel.mainParam.put("dept","2");
                updateID();
            }
            else if (group.getId()==sample.getId()){
                if (checkedId==R.id.sc) MainModel.mainParam.put("sample","1");
                else if (checkedId==R.id.nc) MainModel.mainParam.put("sample","2");
                updateID();
            }
        }
    };
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            MainModel.mainParam.put("sample_id",s.toString());
            updateID();
        }
    };
    private void updateID(){
        String sci="";
        if(MainModel.mainParam.containsKey("hos_code")) sci="0"+MainModel.mainParam.get("hos_code");
        if(MainModel.mainParam.containsKey("dept")) sci+="0"+MainModel.mainParam.get("dept");
        if(MainModel.mainParam.containsKey("sample")) {
            if(Integer.parseInt(MainModel.mainParam.get("sample"))==1){ sci+="SC"; }
            else if(Integer.parseInt(MainModel.mainParam.get("sample"))==2){ sci+="NC"; }
        }
        if(MainModel.mainParam.containsKey("in_date")) {sci+=MainModel.mainParam.get("in_date").substring(2,4);}
        if(MainModel.mainParam.containsKey("sample_id")) sci+=MainModel.mainParam.get("sample_id");
            case_id.setText(sci);
    }

    @Override
    public void getDate(String dat, TextView editText) {
        editText.setText(dat);
        MainModel.mainParam.put("in_date",dat);
        updateID();
    }
}
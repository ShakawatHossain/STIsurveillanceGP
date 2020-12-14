package com.shakawat.stisurveillancegp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "stigp";
    private static String TABLE_STI = "sti";
    private static String TABLE_DIS = "district";
    private static String TABLE_UP = "up";
    public static String KEY_ID = "id";
    public static String ISUPLOAD = "is_upload";
    public static String CREATED_AT = "created_at";
    public static final String CREATETABLEDIS ="CREATE TABLE " + TABLE_DIS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " name TEXT,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";
    public static final String CREATETABLEUP ="CREATE TABLE " + TABLE_UP
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " name TEXT,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";
    public static final String CREATETABLE="CREATE TABLE " + TABLE_STI
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"main_user_id TEXT NOT NULL DEFAULT '',"
            +"main_hos_code TEXT NOT NULL DEFAULT '',"
            +"main_dept TEXT NOT NULL DEFAULT '',"
            +"main_sample TEXT NOT NULL DEFAULT '',"
            +"main_sample_id TEXT NOT NULL DEFAULT '',"
            +"main_in_date TEXT NOT NULL DEFAULT '',"
            +"main_case_id TEXT NOT NULL DEFAULT '',"
            +"demo_name TEXT NOT NULL DEFAULT '',"
            +"demo_age TEXT NOT NULL DEFAULT '',"
            +"demo_mob TEXT NOT NULL DEFAULT '',"
            +"demo_ocu TEXT NOT NULL DEFAULT '',"
            +"demo_child_num TEXT NOT NULL DEFAULT '',"
            +"demo_edu TEXT NOT NULL DEFAULT '',"
            +"demo_edu_oth_txt TEXT NOT NULL DEFAULT '',"
            +"demo_sex TEXT NOT NULL DEFAULT '',"
            +"demo_marrige TEXT NOT NULL DEFAULT '',"
            +"demo_edu_type TEXT NOT NULL DEFAULT '',"
            +"demo_income TEXT NOT NULL DEFAULT '',"
            +"demo_child TEXT NOT NULL DEFAULT '',"
            +"demo_un TEXT NOT NULL DEFAULT '',"
            +"demo_up TEXT NOT NULL DEFAULT '',"
            +"demo_dis TEXT NOT NULL DEFAULT '',"
            +"demo_add_un TEXT NOT NULL DEFAULT '',"
            +"demo_student TEXT NOT NULL DEFAULT '',"
            +"demo_service TEXT NOT NULL DEFAULT '',"
            +"demo_business TEXT NOT NULL DEFAULT '',"
            +"demo_farmer TEXT NOT NULL DEFAULT '',"
            +"demo_rickshaw TEXT NOT NULL DEFAULT '',"
            +"demo_driver TEXT NOT NULL DEFAULT '',"
            +"demo_housewife TEXT NOT NULL DEFAULT '',"
            +"demo_unemployed TEXT NOT NULL DEFAULT '',"
            +"demo_dgh TEXT NOT NULL DEFAULT '',"
            +"demo_dl TEXT NOT NULL DEFAULT '',"
            +"demo_carpenter TEXT NOT NULL DEFAULT '',"
            +"demo_huckster TEXT NOT NULL DEFAULT '',"
            +"demo_ocu_oth TEXT NOT NULL DEFAULT '',"
            +"clin_urth TEXT NOT NULL DEFAULT '',"
            +"clin_urth_type TEXT NOT NULL DEFAULT '',"
            +"clin_vagi TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_amount TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_type TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_smell TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_itch TEXT NOT NULL DEFAULT '',"
            +"clin_oro_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_oro_dis TEXT NOT NULL DEFAULT '',"
            +"clin_ano_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_ano_dis TEXT NOT NULL DEFAULT '',"
            +"clin_abd_pain TEXT NOT NULL DEFAULT '',"
            +"clin_uri_pain TEXT NOT NULL DEFAULT '',"
            +"clin_pain_inter TEXT NOT NULL DEFAULT '',"
            +"clin_gen_war TEXT NOT NULL DEFAULT '',"
            +"clin_gen_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_pain_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_blis_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_dis_ulcer TEXT NOT NULL DEFAULT '',"
            +"clin_swel_ingui TEXT NOT NULL DEFAULT '',"
            +"clin_ulcer_ingui TEXT NOT NULL DEFAULT '',"
            +"clin_scro_pain TEXT NOT NULL DEFAULT '',"
            +"clin_urth_days TEXT NOT NULL DEFAULT '',"
            +"clin_urth_type_oth_text TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_days TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_type_oth_text TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_smell_days TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_colour TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_colour_days TEXT NOT NULL DEFAULT '',"
            +"clin_vagi_itch_days TEXT NOT NULL DEFAULT '',"
            +"clin_oro_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_oro_dis_days TEXT NOT NULL DEFAULT '',"
            +"clin_ano_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_ano_dis_days TEXT NOT NULL DEFAULT '',"
            +"clin_abd_pain_days TEXT NOT NULL DEFAULT '',"
            +"clin_uri_pain_days TEXT NOT NULL DEFAULT '',"
            +"clin_pain_inter_days TEXT NOT NULL DEFAULT '',"
            +"clin_gen_war_days TEXT NOT NULL DEFAULT '',"
            +"clin_gen_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_gen_ulcer_num TEXT NOT NULL DEFAULT '',"
            +"clin_pain_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_blis_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_dis_ulcer_days TEXT NOT NULL DEFAULT '',"
            +"clin_swel_ingui_days TEXT NOT NULL DEFAULT '',"
            +"clin_ulcer_ingui_days TEXT NOT NULL DEFAULT '',"
            +"clin_scro_pain_days TEXT NOT NULL DEFAULT '',"
            +"clin_oth_name TEXT NOT NULL DEFAULT '',"
            +"clin_clin_oth_days TEXT NOT NULL DEFAULT '',"
            +"past_pat_symp TEXT NOT NULL DEFAULT '',"
            +"past_symp TEXT NOT NULL DEFAULT '',"
            +"past_hiv TEXT NOT NULL DEFAULT '',"
            +"past_hiv_test TEXT NOT NULL DEFAULT '',"
            +"past_symp_time TEXT NOT NULL DEFAULT '',"
            +"risk_age TEXT NOT NULL DEFAULT '',"
            +"risk_no_sex_week TEXT NOT NULL DEFAULT '',"
            +"risk_no_sex_mth TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_oth_text TEXT NOT NULL DEFAULT '',"
            +"risk_oth_text TEXT NOT NULL DEFAULT '',"
            +"risk_prev_preg_oth_text TEXT NOT NULL DEFAULT '',"
            +"risk_age_no TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_spouse TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_csw TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_op TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_oth TEXT NOT NULL DEFAULT '',"
            +"risk_type_sex_nor TEXT NOT NULL DEFAULT '',"
            +"risk_safe_method TEXT NOT NULL DEFAULT '',"
            +"risk_oral_pill TEXT NOT NULL DEFAULT '',"
            +"risk_inj TEXT NOT NULL DEFAULT '',"
            +"risk_nor TEXT NOT NULL DEFAULT '',"
            +"risk_iud TEXT NOT NULL DEFAULT '',"
            +"risk_mc TEXT NOT NULL DEFAULT '',"
            +"risk_fc TEXT NOT NULL DEFAULT '',"
            +"risk_sperm TEXT NOT NULL DEFAULT '',"
            +"risk_oth TEXT NOT NULL DEFAULT '',"
            +"risk_na TEXT NOT NULL DEFAULT '',"
            +"risk_multi_sex TEXT NOT NULL DEFAULT '',"
            +"risk_condom TEXT NOT NULL DEFAULT '',"
            +"risk_condom_freq TEXT NOT NULL DEFAULT '',"
            +"risk_partner_ill TEXT NOT NULL DEFAULT '',"
            +"risk_offspring_trans TEXT NOT NULL DEFAULT '',"
            +"risk_prev_preg TEXT NOT NULL DEFAULT '',"
            +"visit_pt_type TEXT NOT NULL DEFAULT '',"
            +"visit_anti_treat TEXT NOT NULL DEFAULT '',"
            +"visit_treat_place TEXT NOT NULL DEFAULT '',"
            +"visit_sm_remarks TEXT NOT NULL DEFAULT '',"
            +"visit_remarks TEXT NOT NULL DEFAULT '',"
            +"visit_sample_date TEXT NOT NULL DEFAULT '',"
            +"visit_pd_uds TEXT NOT NULL DEFAULT '',"
            +"visit_pd_vds TEXT NOT NULL DEFAULT '',"
            +"visit_pd_guds TEXT NOT NULL DEFAULT '',"
            +"visit_pd_lap TEXT NOT NULL DEFAULT '',"
            +"visit_pd_oro TEXT NOT NULL DEFAULT '',"
            +"visit_pd_ano TEXT NOT NULL DEFAULT '',"
            +"visit_pd_sss TEXT NOT NULL DEFAULT '',"
            +"visit_pd_ibs TEXT NOT NULL DEFAULT '',"
            +"visit_sm_us TEXT NOT NULL DEFAULT '',"
            +"visit_sm_vs TEXT NOT NULL DEFAULT '',"
            +"visit_sm_ur TEXT NOT NULL DEFAULT '',"
            +"visit_sm_es TEXT NOT NULL DEFAULT '',"
            +"visit_sm_bd TEXT NOT NULL DEFAULT '',"
            +"visit_sm_nc TEXT NOT NULL DEFAULT '',"
            + ISUPLOAD + " INTEGER DEFAULT 0,"
            + CREATED_AT +" DATETIME DEFAULT CURRENT_TIMESTAMP )";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETABLE);
        db.execSQL(CREATETABLEDIS);
        db.execSQL(CREATETABLEUP);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UP);
        onCreate(db);
    }
    public void checkndelRec(){
        Log.d("prev.","Record Check");
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteDatabase db1 = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_STI +" WHERE " + "main_case_id" + " = '"+MainModel.mainParam.get("case_id") +"'";
        Log.d("checkQ",selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do {
                Log.d("prev.","STI Record Found");
                String deletetQuery = "DELETE FROM " + TABLE_STI +" WHERE " + "main_case_id" + " = '"+MainModel.mainParam.get("case_id") +"'";
                db1.execSQL(deletetQuery);
            }while (c.moveToNext());
        }
    }
    public void insert(){
        checkndelRec();
        ContentValues cvdemo = new ContentValues();
        for (String key:MainModel.mainParam.keySet()) cvdemo.put("main_"+key,MainModel.mainParam.get(key));
        for (String key:MainModel.demoParam.keySet()) cvdemo.put("demo_"+key,MainModel.demoParam.get(key));
        for (String key:MainModel.clinParam.keySet()) cvdemo.put("clin_"+key,MainModel.clinParam.get(key));
        for (String key:MainModel.pastParam.keySet()) cvdemo.put("past_"+key,MainModel.pastParam.get(key));
        for (String key:MainModel.riskParam.keySet()) cvdemo.put("risk_"+key,MainModel.riskParam.get(key));
        for (String key:MainModel.visitParam.keySet()) cvdemo.put("visit_"+key,MainModel.visitParam.get(key));
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STI, null, cvdemo);
    }
    public void insertDis(ContentValues cv) {
//        Log.d("DBDistrict",cv.getAsString("name"));
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DIS, null, cv);
    }
    public void insertUp(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_UP, null, cv);
    }
    public Cursor getAll_sti(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STI ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getAll_dis(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DIS ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor getAll_up(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_UP ;
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public Cursor get_demo(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STI +" WHERE "+ ISUPLOAD + " =0";
        Cursor c = db.rawQuery(selectQuery, null);
        return c;
    }
    public void updateDemo(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ISUPLOAD,1);
        db.update(TABLE_STI,cv,ISUPLOAD+ " = ? ",new String[]{String.valueOf(0)});
    }
}

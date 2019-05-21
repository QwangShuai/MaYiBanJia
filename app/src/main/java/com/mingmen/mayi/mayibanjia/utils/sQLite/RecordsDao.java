package com.mingmen.mayi.mayibanjia.utils.sqlite;

/**
 * Created by 刘佳 on 2017/11/20.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索记录操作类
 * Created by 05 on 2016/7/27.
 */
public class RecordsDao {
    RecordSQLiteOpenHelper recordHelper;

    SQLiteDatabase recordsDb;

    public RecordsDao(Context context) {
        recordHelper = new RecordSQLiteOpenHelper(context);
    }

    //添加搜索记录   店铺
    public void addDianpu(String record) {
        if (!dianpuIsHas(record)) {
            recordsDb = recordHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", record);
            //添加
            recordsDb.insert("dianpu", null, values);
            //关闭
            recordsDb.close();
        }
    }
    //添加搜索记录   商品
    public void addShangpin(String record) {
        if (!shangpinIsHas(record)) {
            recordsDb = recordHelper.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", record);
            //添加
            recordsDb.insert("shangpin", null, values);
            //关闭
            recordsDb.close();
        }
    }

    //判断店铺中是否含有该搜索记录
    public boolean dianpuIsHas(String record) {
        boolean isHasRecord = false;
        recordsDb = recordHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query("dianpu", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                isHasRecord = true;
            }
        }
        //关闭数据库
        recordsDb.close();
        cursor.close();
        return isHasRecord;
    }
    //判断商品中是否含有该搜索记录
    public boolean shangpinIsHas(String record) {
        boolean isHasRecord = false;
        recordsDb = recordHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query("shangpin", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if (record.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                isHasRecord = true;
            }
        }
        //关闭数据库
        recordsDb.close();
        cursor.close();
        return isHasRecord;
    }

    //获取店铺全部搜索记录
    public List<String> getdianpu() {
        List<String> recordsList = new ArrayList<>();
        recordsDb = recordHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query("dianpu", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            recordsList.add(name);
        }

        ArrayList<String> list = new ArrayList<>();
        if (recordsList.size()>10){
            for (int i = 0; i <10; i++) {
                list.add(recordsList.get(recordsList.size()-1-i));
            }
        }else{
            for (int i = 0; i <recordsList.size() ; i++) {
                list.add(recordsList.get(recordsList.size()-1-i));
            }
        }

        //关闭数据库
        recordsDb.close();
        cursor.close();
        return list;
    }

    //获取商品全部搜索记录
    public List<String> getshangpin() {
        List<String> recordsList = new ArrayList<>();
        recordsDb = recordHelper.getReadableDatabase();
        Cursor cursor = recordsDb.query("shangpin", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            recordsList.add(name);
        }

        ArrayList<String> list = new ArrayList<>();
        if (recordsList.size()>10){
            for (int i = 0; i <10; i++) {
                list.add(recordsList.get(recordsList.size()-1-i));
            }
        }else{
            for (int i = 0; i <recordsList.size() ; i++) {
                list.add(recordsList.get(recordsList.size()-1-i));
            }
        }

        //关闭数据库
        recordsDb.close();
        cursor.close();
        return list;
    }

    //模糊查询
    public List<String> querySimlarRecord(String record){
        String queryStr = "select * from records where name like '%" + record + "%' order by name ";
        List<String> similarRecords = new ArrayList<>();
        Cursor cursor= recordHelper.getReadableDatabase().rawQuery(queryStr,null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            similarRecords.add(name);
        }

        cursor.close();
        return similarRecords;
    }

    //清空店铺搜索记录
    public void deleteAllDianpu() {
        recordsDb = recordHelper.getWritableDatabase();
        recordsDb.execSQL("delete from dianpu");
        recordsDb.close();

    }
    //清空商品搜索记录
    public void deleteAllShangpin() {
        recordsDb = recordHelper.getWritableDatabase();
        recordsDb.execSQL("delete from shangpin");
        recordsDb.close();

    }
    //删除 店铺的 一条记录
    public void deleteOneDianpu(String s) {
        recordsDb = recordHelper.getWritableDatabase();
        recordsDb.execSQL("delete from dianpu where name  = '"+s+"'");
        recordsDb.close();

    }
    //删除 商品的 一条记录
    public void deleteOneShangpin(String s) {
        recordsDb = recordHelper.getWritableDatabase();
        recordsDb.execSQL("delete from shangpin where name  = '"+s+"'");
        recordsDb.close();

    }

}
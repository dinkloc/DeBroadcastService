package com.example.truefalseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SqliteDB_ThuChi database;
    private EditText edtSearch;
    private static final String NAME_DATABASE = "Sqlite_ThuChi1.db";
    private static final String NAME_TABLE = "ThuChi_Table";
    private static final String COLUMN_NAME1 = "id";
    private static final String COLUMN_NAME2 = "TenKhoan";
    private static final String COLUMN_NAME3 = "NgayThang";
    private static final String COLUMN_NAME4 = "SoTien";
    private static final String COLUMN_NAME5 = "ThuChi";

    BroadcastReceiver broadcastReceiver = null;

    double tongchi = 0;
    double tongthu = 0;

    private ListView listView;
    private TextView soDu;
    private List<ThuChi> listData;
    private ThuChi_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SetDatabase();
        setListView();
        LoadData();
        setSearch();
        broadcastReceiver = new InternetReceiver();
        Internetstatus();
        soDu.setText("Số dư:                            " + (tongthu-tongchi));
    }


    public void Internetstatus(){
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    public void setListView() {
        listData = new ArrayList<>();
        adapter = new ThuChi_Adapter(this, R.layout.list_item, listData);
        listView.setAdapter(adapter);
        LoadData();
        registerForContextMenu(listView);
    }

    public void SetDatabase() {
        database = new SqliteDB_ThuChi(this, NAME_DATABASE, null, 1);
        database.Query(String.format("CREATE TABLE IF NOT EXISTS %s (" +
                        " %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " %s NVARCHAR(30)," +
                        " %s NVARCHAR(30)," +
                        " %s REAL, " +
                        " %s INTEGER DEFAULT 0);"
                , NAME_TABLE
                , COLUMN_NAME1
                , COLUMN_NAME2
                , COLUMN_NAME3
                , COLUMN_NAME4
                , COLUMN_NAME5));


//        insertExample();
    }


    public void setSearch() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void InsertDataBase(String tenKhoan, String ngayThang, double SoTien, int ThuChi) {
        String sql = String.format(Locale.US, "INSERT INTO %s VALUES(null, '%s', '%s', %f, %d)"
                , NAME_TABLE
                , tenKhoan
                , ngayThang
                , SoTien
                , ThuChi);
        database.Query(sql);
    }


    public void insertExample() {
        InsertDataBase("Luong", "2023-01-15", 1200000, 1);
        InsertDataBase("Thue Nha", "2023-01-15", 2200000, 0);
    }

    public void LoadData() {
        listData.clear();
        Cursor cursor = database.getData("SELECT * FROM " + NAME_TABLE);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String tenKhoan = cursor.getString(1);
            String ngayThang = cursor.getString(2);
            String SoTien = cursor.getString(3);
            int ThuChi = cursor.getInt(4);
            if (ThuChi == 1) {
                double soTien = Double.parseDouble(cursor.getString(3).toString());
                tongthu += soTien;
            } else if (ThuChi == 0) {
                double soTien = Double.parseDouble(cursor.getString(3).toString());
                tongchi += soTien;
            }
            listData.add(new ThuChi(id, tenKhoan, ngayThang, SoTien, ThuChi));
        }
        //Sort
        Collections.sort(listData);
        adapter.notifyDataSetChanged();
    }


    public void initView() {
        edtSearch = findViewById(R.id.edtSearch);
        listView = findViewById(R.id.lstView);
        soDu = findViewById(R.id.tvSoDu);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        LoadData();
//    }

}
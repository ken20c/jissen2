package com.example.y3033130kadai2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] spinnerItems = { "red", "blue", "black" };
    String[] spinnerItems2 = { "細", "中", "太"};

    @Override
    protected void onCreate(Bundle savedInstanceState) { // アプリ起動時に設定
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xmlファイルをMainActivity に読み込む

        /**
         * FindViewByID欄
         */
        // 変数で扱えるようにする
        MyView myview =   findViewById(R.id.myView);
        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner2 = findViewById(R.id.spinner2);
        // Button button =   findViewById(R.id.button);

        /**
         * スピナーの設定
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerItems);
        // ドロップダウンのリストのデフォルトのレイアウト
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // スピナーにadapter をセット
        spinner.setAdapter(adapter);
        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                myview.colorflg = item;
            }
            //　アイテムが選択されなかった時
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerItems2);
        // ドロップダウンのリストのデフォルトのレイアウト
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // スピナーにadapter をセット
        spinner2.setAdapter(adapter2);
        // リスナーを登録
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                Spinner spinner2 = (Spinner) parent;
                String item2 = (String) spinner2.getSelectedItem();
                myview.widthflg = item2;
            }
            //　アイテムが選択されなかった時
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myview.array_clear();
            }
        });

    }

    @Override
    public void onClick(View v) {}

    public void ShowToast(String string){
        Toast t = Toast.makeText(
                this, string, Toast.LENGTH_SHORT
        );
        t.show();
    }
}
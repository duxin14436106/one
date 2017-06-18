package com.example.fcapp.fcapp;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OnCheckChangeListener extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplication();
        setContentView(R.layout.activity_main);
        //[0,1,2]代表的是活鸟，托鸟，胡息值
        //[0,1,2,3]代表的是甲乙丙丁玩家
        final EditText editText[][] = new EditText[3][4];
        //倍数文本框
        final EditText price_edit = (EditText)findViewById(R.id.beishu);
        //甲乙丙丁的活鸟值
        final int hn[] = {0,0,0,0};
        //甲乙丙丁的托鸟值
        final int tn[] = {0,0,0,0};
        //甲乙丙丁的胡息值
        final int hx[] = {0,0,0,0};
        //甲乙丙丁的收入编辑框
        final EditText result_edit[] = new EditText[4];
        //甲乙丙丁的收入
        final int result[] = {0,0,0,0};

        //计算
        Button cal = (Button) findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try {
                 //获取甲乙丙丁的活鸟文本框
                 editText[0][0] = (EditText)findViewById(R.id.hn);
                 editText[0][1] = (EditText)findViewById(R.id.hn2);
                 editText[0][2] = (EditText)findViewById(R.id.hn3);
                 editText[0][3] = (EditText)findViewById(R.id.hn4);
                 for(int i = 0;i < 4;i++){
                     hn[i] = Integer.parseInt(editText[0][i].getText().toString());
                 }

                 //获取甲乙丙丁的托鸟文本框
                 editText[1][0] = (EditText)findViewById(R.id.tn);
                 editText[1][1] = (EditText)findViewById(R.id.tn2);
                 editText[1][2] = (EditText)findViewById(R.id.tn3);
                 editText[1][3] = (EditText)findViewById(R.id.tn4);
                 for(int i = 0;i < 4;i++){
                     tn[i] = Integer.parseInt(editText[1][i].getText().toString());
                 }

                 //获取甲乙丙丁的胡息文本框
                 editText[2][0] = (EditText)findViewById(R.id.hx);
                 editText[2][1] = (EditText)findViewById(R.id.hx2);
                 editText[2][2] = (EditText)findViewById(R.id.hx3);
                 editText[2][3] = (EditText)findViewById(R.id.hx4);
                 for(int i = 0;i < 4;i++){
                     hx[i] = Integer.parseInt(editText[2][i].getText().toString());
                 }

                 //计算收入
                 result[0] = 0;result[1] = 0;result[2] = 0;result[3] = 0;
                 double beishu = Double.parseDouble(price_edit.getText().toString());
                 for(int j = 0;j < 4;j++)
                     for(int i = 0;i < 4;i++){
                         if(j == i){
                             continue;
                         }else{
                             //1.活鸟收入
                             result[j] += (myInt(hx[j])-myInt(hx[i]))*beishu*(hn[j]+1)*(hn[i]+1);
                             //80*0.5*（hn1+1）*（hn2+1）;
                             //2.托鸟收入
                             result[j] += max(tn[j],tn[i]);
                         }
                     }
                 result_edit[0] = (EditText)findViewById(R.id.result);
                 result_edit[1] = (EditText)findViewById(R.id.result2);
                 result_edit[2] = (EditText)findViewById(R.id.result3);
                 result_edit[3] = (EditText)findViewById(R.id.result4);

                 for(int i = 0;i < 4;i++){
                     result_edit[i].setText(String.valueOf(result[i]));
                 }

             }catch (NumberFormatException e){
                 e.printStackTrace();
             }
            }
        });

        //清除
        Button del = (Button) findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try{
                 editText[0][0] = (EditText)findViewById(R.id.hn);
                 editText[0][1] = (EditText)findViewById(R.id.hn2);
                 editText[0][2] = (EditText)findViewById(R.id.hn3);
                 editText[0][3] = (EditText)findViewById(R.id.hn4);

                 editText[1][0] = (EditText)findViewById(R.id.tn);
                 editText[1][1] = (EditText)findViewById(R.id.tn2);
                 editText[1][2] = (EditText)findViewById(R.id.tn3);
                 editText[1][3] = (EditText)findViewById(R.id.tn4);

                 editText[2][0] = (EditText)findViewById(R.id.hx);
                 editText[2][1] = (EditText)findViewById(R.id.hx2);
                 editText[2][2] = (EditText)findViewById(R.id.hx3);
                 editText[2][3] = (EditText)findViewById(R.id.hx4);

                 result_edit[0] = (EditText)findViewById(R.id.result);
                 result_edit[1] = (EditText)findViewById(R.id.result2);
                 result_edit[2] = (EditText)findViewById(R.id.result3);
                 result_edit[3] = (EditText)findViewById(R.id.result4);

                 EditText price_edit = (EditText)findViewById(R.id.beishu);
                 price_edit.setText("0.5");

                 for(int i = 0;i < 3;i++)
                     for(int j = 0;j < 4;j++){
                         editText[i][j].setText("0");
                         result_edit[j].setText("0");
                     }
             }catch (NumberFormatException e){
                 e.printStackTrace();
             }
            }
        });

        //退出
        Button exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        //选择
        Button choose_4 = (Button) findViewById(R.id.choose_4);
        choose_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView q = (TextView) findViewById(R.id.q);
                EditText hn4 = (EditText)findViewById(R.id.hn4);
                EditText tn4 = (EditText)findViewById(R.id.tn4);
                EditText hx4 = (EditText)findViewById(R.id.hx4);
                EditText result4 = (EditText)findViewById(R.id.result4);

                q.setVisibility(View.VISIBLE);
                hn4.setVisibility(View.VISIBLE);
                tn4.setVisibility(View.VISIBLE);
                hx4.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
            }
        });

        Button choose_3 = (Button) findViewById(R.id.choose_3);
        choose_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView q = (TextView) findViewById(R.id.q);
                EditText hn4 = (EditText)findViewById(R.id.hn4);
                EditText tn4 = (EditText)findViewById(R.id.tn4);
                EditText hx4 = (EditText)findViewById(R.id.hx4);
                EditText result4 = (EditText)findViewById(R.id.result4);

                hn4.setText("0");
                tn4.setText("0");
                hx4.setText("0");
                result4.setText("0");

                q.setVisibility(View.INVISIBLE);
                hn4.setVisibility(View.INVISIBLE);
                tn4.setVisibility(View.INVISIBLE);
                hx4.setVisibility(View.INVISIBLE);
                result4.setVisibility(View.INVISIBLE);
            }
        });
    }
    //四舍五入方法
    private int myInt(int huxi){
        int tag=1,huxi_abs;
        huxi_abs=Math.abs(huxi);
        if(huxi<0) tag = -1;
        if(huxi_abs%10>=5)
            huxi_abs=(huxi_abs/10+1)*10;
        else
            huxi_abs=huxi_abs/10*10;
        return huxi_abs*tag;
    }
    private int max(int a,int b){
        if(a-b>0){
            return (a+b);
        }else if(a-b == 0){
            return 0;
        }else{
            return -(a+b);
        }
    }
}
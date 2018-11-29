package com.example.fpm0322.myfirstapp.utils;

import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CommonUtils {

    public static String account = "";

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     * @param currentActivity 处于前台的activity
     */
    public static void backgroundHoleAlpha(float bgAlpha, final Activity currentActivity) {
        WindowManager.LayoutParams lp = currentActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        currentActivity.getWindow().setAttributes(lp);
    }

    /**
     * 在数字型字符串千分位加逗号
     * @param str
     * @return sb.toString()
     */
    public static String addComma(String str, EditText etTest){
        account = etTest.getText().toString().trim().replaceAll(",","");
        boolean neg = false;
        if (str.startsWith("-")){  //处理负数
            str = str.substring(1);
            neg = true;
        }
        if (str.startsWith("0") && str.indexOf(".") != 1){
            str = "0";
        }
        if (str.startsWith(".")){
            str = "";
        }
        String tail = null;
        if (str.indexOf('.') != -1){ //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4){
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg){
            sb.insert(0, '-');
        }
        if (tail != null){
            if (tail.length()>3){
              tail = tail.substring(0,3);
            }
            sb.append(tail);
        }
        return sb.toString();
    }

    /**
     * 保留两位小数
     * @param number
     * @return
     */
    public static String keep2Num(String number){
        String num = "";
        DecimalFormat df = new DecimalFormat("#,###.00");
//        if (number.contains(".")){
//            BigDecimal bd = new BigDecimal(number);
//            num =  df.format(bd);
//        }else{
//            StringBuffer sbf = new StringBuffer(number);
//            sbf.append(".00");
//            BigDecimal bd = new BigDecimal(sbf.toString());
//            num =  df.format(bd);
//        }
        BigDecimal bd = new BigDecimal(number);
        num =  df.format(bd);
        Log.e("NumberUtuls","num:"+num);
        return num;
    }
}

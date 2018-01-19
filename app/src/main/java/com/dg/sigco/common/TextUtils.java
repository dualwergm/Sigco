package com.dg.sigco.common;

import android.text.Editable;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Sofia on 25/11/2017.
 */

public class TextUtils {

    public static String removeAccents(String s){
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        return s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public static String formatMoney(String s){
        String str = s.replaceAll( "[^\\d]", "" );
        if(android.text.TextUtils.isEmpty(str)){
            return "";
        }
        int indexOf = s.indexOf(".");
        if(indexOf > 0){
            str = str.substring(0, indexOf);
        }
        double s1 = Double.parseDouble(str);
        NumberFormat nf2 = NumberFormat.getInstance(Locale.ENGLISH);
        ((DecimalFormat)nf2).applyPattern("###,###.###");
        return nf2.format(s1);
    }

    public static void formatMoney(Editable s){
        s.replace(0, s.length(), formatMoney(s.toString()));
    }

    public static String removeComma(String s){
         return s.replaceAll(",","");
    }
}

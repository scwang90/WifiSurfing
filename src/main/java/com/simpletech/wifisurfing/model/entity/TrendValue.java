package com.simpletech.wifisurfing.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simpletech.wifisurfing.model.constant.Period;
import com.simpletech.wifisurfing.util.AfReflecter;
import com.simpletech.wifisurfing.util.AfStringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 趋势统计数据（基类）
 * Created by 树朾 on 2015/9/25.
 */
public abstract class TrendValue {

    private static final SimpleDateFormat format = new SimpleDateFormat();

    private Date time;
    private String date;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        this.bindTime(date);
    }

    private void bindTime(String date) {
        try {
            if (AfStringUtil.isNotEmpty(date)){
                for (String period: Period.PERIODS){
                    if (date.length() == period.length()){
                        format.applyPattern(period);
                        time = format.parse(date);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @JsonIgnore
    public void setEmpty() {
        try {
            Field[] fields = AfReflecter.getField(this.getClass(), TrendValue.class);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    if (field.get(this) == null) {
                        if (field.getType().equals(String.class)) {
                            field.set(this, "");
                        } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)){
                            field.set(this,0);
                        } else if (field.getType().equals(Long.class) || field.getType().equals(long.class)){
                            field.set(this,0l);
                        } else if (field.getType().equals(Float.class) || field.getType().equals(float.class)){
                            field.set(this,0f);
                        } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)){
                            field.set(this,0d);
                        } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)){
                            field.set(this,false);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

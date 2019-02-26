package com.mingmen.mayi.mayibanjia.utils;

import java.math.BigDecimal;

/**
 *
 * @author duyb
 * @date 2017年8月22日 下午2:29:15
 */
public class MyMath {
    // 进行加法运算 返回double
    public static double add(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return (b1.add(b2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    // 进行加法运算 返回BigDecimal
    public static BigDecimal addBigDec(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return (b1.add(b2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    // 进行加法运算 参数类型BigDecimal
    public static BigDecimal addBigDecType(BigDecimal d1, BigDecimal d2){
        return (d1.add(d2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    // 进行减法运算
    public static double sub(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return (b1.subtract(b2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    // 进行减法运算
    public static BigDecimal subBigDec(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return (b1.subtract(b2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    // 进行减法运算
    public static BigDecimal subBigDecType(BigDecimal d1, BigDecimal d2){        
        return (d1.subtract(d2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    // 进行乘法运算
    public static double mul(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
          BigDecimal b2 = new BigDecimal(d2);
        return (b1.multiply(b2)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    // 进行乘法运算
    public static BigDecimal mulBigDec(double d1, double d2){        
        BigDecimal b1 = new BigDecimal(d1);
          BigDecimal b2 = new BigDecimal(d2);
        return (b1.multiply(b2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    // 进行乘法运算
    public static BigDecimal mulBigDecType(BigDecimal d1, BigDecimal d2){        

        return (d1.multiply(d2)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mulAll(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 
     * 进行除法运算
     * @param d1 被除数
     * @param d2 除数
     * @param len 小数点后余几位
     * @return
     * @author lw
     * @date 2017年8月1日 下午3:07:00
     */
    public static double div(double d1,double d2,int len) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static BigDecimal divBigDec(double d1,double d2,int len) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal divBigDecType(BigDecimal d1,BigDecimal d2,int len) {

        return d1.divide(d2,len,BigDecimal.ROUND_HALF_UP);
    }
    // 进行四舍五入操作
    public static double round(double d,int len) {     
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static double getDouble(double d){
    	return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    };


}

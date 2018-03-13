/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mjapon
 */
public class FechasUtil {
    
    public static String formato = "dd/MM/yyyy";
    public static SimpleDateFormat dateFormat;
    
    static{
        dateFormat = new SimpleDateFormat(formato);
    }
    
    public static Date parse(String fecha) throws ParseException{        
        return dateFormat.parse(fecha);
    }
    
    public static String format(Date date){
        return dateFormat.format(date);
    }
    
    public static String getFechaActual(){
        return format(new Date());
    }
}

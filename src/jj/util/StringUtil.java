/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.util;

import java.text.MessageFormat;

/**
 *
 * @author mjapon
 */
public class StringUtil {
    
    public static String format(String template, Object... args){
        
        return new MessageFormat(template).format(new Object[]{args});
        
    }
    
}

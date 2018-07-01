package com.cvte.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/** 
* @author: jan 
* @date: 2018年5月29日 下午8:23:29 
*/
public class InputStreamCacher {  

    /** 
     * 将InputStream中的字节保存到ByteArrayOutputStream中。 
     */  
    private ByteArrayOutputStream byteArrayOutputStream = null;  

    public InputStreamCacher(InputStream inputStream) {  
//        if (ObjectUtils.isNull(inputStream))  
//            return;  

        byteArrayOutputStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024*1024];
        int len;    
        try {  
            while ((len = inputStream.read(buffer)) > -1 ) {    
                byteArrayOutputStream.write(buffer, 0, len);    
            }  
            byteArrayOutputStream.flush();  
        } catch (IOException e) {  
            System.out.println(99);
        }    
    }  

    public InputStream getInputStream() {  
//        if (ObjectUtils.isNull(byteArrayOutputStream))  
//            return null;  

        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());  
    }  
} 

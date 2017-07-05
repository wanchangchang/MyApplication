/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.Uitl;

import com.google.gson.Gson;

public class GsonUtil {
	private static Gson gson = null;
    
    static {  
        if (gson == null) {  
            gson = new Gson();
        }  
    }  
    private GsonUtil() {  
        
    } 
    
    public static String objToString(Object obj){
    	 String jsonStr = null;  
         if (gson != null) {  
             jsonStr = gson.toJson(obj);  
         }  
         return jsonStr; 
    }
    @SuppressWarnings("unchecked")
	public static <T> T jsonToObj(String json,Class<T> cl){
    	Object obj = null;
    	if (gson != null) {  
            obj = gson.fromJson(json,cl);  
        } 
    	return (T) obj;
    }
}

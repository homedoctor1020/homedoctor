package com.dw.util;


/**¼ì²é×Ö·û´®ÊÇ·ñÎª¿Õ
 * @author crossnote
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)||str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
}

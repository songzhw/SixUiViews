package ca.six.views.util;

public class StringUtil {
	
	/**把账户名中间变为*号， 用于保障隐私*/
	public static String getMaskName(String str){
		if(isEmpty(str)){
			return "";
		}
		
		int atIndex = str.indexOf("@");
		String end = str.substring(atIndex);
		String name = str.substring(0, atIndex);
		int length = name.length();
		if(length <= 4){
			return name+end;
		} else {
			String ret = str.substring(0,2)+"***"+str.substring(length-2, length)+end;
			return ret;
		}
	}
	
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    
    public static boolean isNotEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return false;
        else
            return true;
    }
	
	
	
}
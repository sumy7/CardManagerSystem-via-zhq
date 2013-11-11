package md5;

import java.security.MessageDigest;

public class Md5 {
	public static String MD5(String oldStr){
		char[] hexdigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		try{
			byte[] oldBytes=oldStr.getBytes();
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(oldBytes);
			byte[] newBytes=md.digest();
			char[] newStr=new char[32];
			for(int i=0;i<16;i++){
				byte tmp=newBytes[i];
				newStr[2*i]=hexdigits[tmp>>>4 & 0xf];
				newStr[2*i+1]=hexdigits[tmp & 0xf];
			}
			return new String(newStr);
		}catch(Exception e){
			System.out.println(e.toString());
			return null;
		}
	}

}

package utility;

import java.time.LocalDateTime;

public class Utilites {
	
	public static String generateEmailWithTimeStamp() {
		LocalDateTime date = LocalDateTime.now();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "symtst"+timestamp+"@gmail.com";
//		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
	}

}

package cn.edu.xmu.software.ijoke.factory;

import java.sql.Timestamp;
import java.util.Date;



public class IdFactroy {

	public static String createId() {

		String catalogId = null;
		Timestamp date = new Timestamp((new Date().getTime()));
		catalogId = date.toString().replace(" ", "");
		catalogId = catalogId.replace("-", "");
		catalogId = catalogId.replace(":", "");
		catalogId = catalogId.replace(".", "");
		return catalogId;

	}
}

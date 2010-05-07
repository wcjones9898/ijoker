package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;

public class CallWSMethodFactory {
	public static AbstractWSMethod CreateWSMethod(String methodName,
			Handler handler, HashMap<String, Object> parms) {
		if (methodName.equalsIgnoreCase("getJokeList"))

			return new GetJokeListWSMethod(methodName, handler, parms);
		else
			return null;
	}
}

package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;

public class CallWSMethodFactory {
	public static AbstractWSMethod CreateWSMethod(String methodName,
			Handler handler, HashMap<String, Object> parms)
			throws ClassNotFoundException {
		if (methodName.equalsIgnoreCase("getJokeList"))

			return new GetJokeListWSMethod(methodName, handler, parms);
		else if (methodName.equalsIgnoreCase("authenticate"))
			return new AuthenticateWSMethod(methodName, handler, parms);
		else
			throw new ClassNotFoundException("no class named as " + methodName);
	}
}

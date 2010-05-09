package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import cn.edu.xmu.software.ijoker.util.Consts;

import android.os.Handler;

public class CallWSMethodFactory {
	public static AbstractWSMethod CreateWSMethod(String methodName,
			Handler handler, HashMap<String, Object> parms)
			throws ClassNotFoundException {
		if (methodName.equalsIgnoreCase(Consts.METHODNAME_GETJOKELIST))

			return new GetJokeListWSMethod(methodName, handler, parms);
		else if (methodName.equalsIgnoreCase(Consts.METHODNAME_AUTHORIZATION))
			return new AuthorizationWSMethod(methodName, handler, parms);
		else
			throw new ClassNotFoundException("no class named as " + methodName);
	}
}

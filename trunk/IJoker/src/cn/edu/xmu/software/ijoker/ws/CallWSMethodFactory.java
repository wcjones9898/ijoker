package cn.edu.xmu.software.ijoker.ws;

import java.util.HashMap;

import android.os.Handler;
import cn.edu.xmu.software.ijoker.util.Consts;

public class CallWSMethodFactory {
	public static AbstractWSMethod CreateWSMethod(String methodName,
			Handler handler, HashMap<String, Object> parms)
			throws ClassNotFoundException {
		if (methodName.equalsIgnoreCase(Consts.METHODNAME_GETJOKELIST))

			return new GetJokeListWSMethod(methodName, handler, parms);
		else if (methodName.equalsIgnoreCase(Consts.METHODNAME_AUTHORIZATION))
			return new AuthorizationWSMethod(methodName, handler, parms);
		else if (methodName.equalsIgnoreCase(Consts.METHODNAME_GETDIVISIONLIST))
			return new GetDivisionListWSMethod(methodName, handler, parms);
		else if (methodName.equalsIgnoreCase(Consts.METHODNAME_SCORE))
			return new ScoreWSMethod(methodName, handler, parms);
		else
			throw new ClassNotFoundException("no class named as " + methodName);
	}
}

package cn.edu.xmu.software.ijoker.ws;

public class CallWSMethodFactory {
	public static AbstractWSMethod CreateWSMethod(String methodName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return (AbstractWSMethod) Class.forName(methodName + "WSMethod")
				.newInstance();
	}
}

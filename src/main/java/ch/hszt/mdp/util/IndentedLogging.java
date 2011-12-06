package ch.hszt.mdp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.joda.time.DateTime;

public class IndentedLogging {

	private static int SPACING = 5;
	private int indentationLevel = 0;
	private static String SPACE = " ";

	public Object loggingOperation(ProceedingJoinPoint thisJoinPoint) throws Throwable {

		StringBuilder sb = new StringBuilder();
		indentationLevel++;

		for (int i = 0, spaces = indentationLevel * SPACING; i < spaces; i++) {
			sb.append(SPACE);
		}

		beforeLog(sb.toString(), thisJoinPoint);
		Object result;
		try {
			result = thisJoinPoint.proceed();
		} finally {
			afterLog(sb.toString(), thisJoinPoint);
			indentationLevel--;
		}

		return result;
	}

	private void beforeLog(String indent, ProceedingJoinPoint thisJoinPoint) {
		System.out.println(indent + "--> " + new DateTime().toString("HH:mm:ss, SSS") + " ["
				+ thisJoinPoint.getSignature().getDeclaringTypeName() + "." + thisJoinPoint.getSignature().getName() + "]");
	}

	private void afterLog(String indent, ProceedingJoinPoint thisJoinPoint) {
		System.out.println(indent + "<-- " + new DateTime().toString("HH:mm:ss, SSS") + " ["
				+ thisJoinPoint.getSignature().getDeclaringTypeName() + "." + thisJoinPoint.getSignature().getName() + "]");
	}
}

package ch.hszt.mdp.util;

import org.aspectj.lang.ProceedingJoinPoint;

public class IndentedLogging {
	
	private static int SPACING = 5;
	private int indentationlevel = 0;
	private static char SPACE = ' ';
	
	public Object loggingOperation(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		
		StringBuilder sb = new StringBuilder();
        increase();
        
        for (int i = 0, spaces = indentationlevel; i < spaces; i++) {
            sb.append(SPACE);
        }
        
        beforeLog(sb.toString(), thisJoinPoint);
        Object result;
        try {
            result = thisJoinPoint.proceed();
        } finally {
            afterLog(sb.toString(), thisJoinPoint);
            decrease();
        }
		
		return result;
	}
	
	private void increase() {
		indentationlevel += SPACING;
	}
	
	private void decrease() {
		indentationlevel -= SPACING;
	}
	
	private void beforeLog(String spacing, ProceedingJoinPoint thisJoinPoint) {
		System.out.println(spacing + thisJoinPoint.toString()+"->");
	}
	
	private void afterLog(String spacing, ProceedingJoinPoint thisJoinPoint) {
		//System.out.println(spacing + thisJoinPoint.toString());
	}
	
}

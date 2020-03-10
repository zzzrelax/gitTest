package org.lzm.aop;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.lzm.util.ServiceFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component("errorLodAop")
public class ErrorLodAop {
	
	@Around("execution(public * org.lzm.service.*.*(..))")
	public Object around(ProceedingJoinPoint pjp) {
		Object obj = null;
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			StackTraceElement[] stackTrace = e.getStackTrace();
			Logger log  = Logger.getLogger(ServiceFactory.class);
			log.debug(e.getMessage());
			for(int i=0;i<stackTrace.length;i++) {
				log.debug(stackTrace[i].getFileName()+":"+stackTrace[i].getLineNumber());
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}

package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.IAnnotation;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;
import org.testng.internal.annotations.ITest;

public class ITestRetryMachanisum implements IRetryAnalyzer, IAnnotationTransformer {

	static int retryCount=0;
	static int maxRetryCount=2;
	@Override
	public boolean retry(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {  
			if(retryCount<maxRetryCount) {
				retryCount++;
				return true;
			}
			
		}
		return false;
	}
	
	 public void transform( ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor,Method testMethod,Class<?> occurringClazz) {
		   testAnnotation.setRetryAnalyzer(ITestRetryMachanisum.class);
		   
		   testAnnotation.setRetryAnalyzer(ITestRetryMachanisum.class);
		  }

}

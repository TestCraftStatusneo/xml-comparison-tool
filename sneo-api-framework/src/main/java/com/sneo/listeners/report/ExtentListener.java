package com.sneo.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sneo.dataprovider.TestmethodData;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Map;

/**
 *  ExtentListener for Extent Reporting
 *
 * @author ikumar
 */
public class ExtentListener implements ITestListener {

	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getTestClass().getName() + ": " + result.getMethod().getMethodName() + " " + getTestMethodParameters(result)));
		logParameters(result);
	}

	public void logParameters(ITestResult result){
		if (result.getParameters().length > 0) {
			for (int i = 0; i < result.getParameters().length; i++) {
				//&& result.getParameters().length == 1
				if (result.getParameters()[i] instanceof TestmethodData) {
					String desc = "Description " + ": " + ((TestmethodData) result.getParameters()[0]).getDescription();
					test.get().info(desc);
					String expectedResults = "Expected Results " + ": " + ((TestmethodData) result.getParameters()[0]).getExpectedResults();
					test.get().info(expectedResults);
				} else
					test.get().info("Parameter " + (i + 1) + ": " + result.getParameters()[i].toString());

			}
		}
	}

	private String getTestMethodParameters(ITestResult result) {
		if (result.getParameters()[0] instanceof Map) {
			Map map = ((Map<String, String>) result.getParameters()[0]);
			//return "[" + map.get(SCENARIO) + ", " + map.get(DESC) + "]";
			return "[" + map.get("identifier") + "]";

		}
		if (result.getParameters().length > 0 && !(result.getParameters()[0] instanceof TestmethodData)) {
			return Arrays.toString(result.getParameters());
		} else
			return "";
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Testcase: " + methodName + " Passed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {
//		test.set(extent.createTest( result.getTestClass().getName() + ": " +result.getMethod().getMethodName() + " " + getTestMethodParameters(result)));
//		logParameters(result);

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String throwableString = result.getThrowable().toString();

		test.get().fail(throwableString);
		test.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Testcase: " + methodName + " Failed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.get().fail(m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Testcase: " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		test.get().skip(m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
		if (extent != null)
			extent.flush();
	}

	public static void logCodeBlock(String code) {
		try {
			Markup m = MarkupHelper.createCodeBlock(code);
			ExtentListener.test.get().info(m);
		} catch (Exception e) {

		}
	}

	public static void logInfo(String info) {
		try {
			ExtentListener.test.get().info(info);
		} catch (Exception e) {

		}
	}

}

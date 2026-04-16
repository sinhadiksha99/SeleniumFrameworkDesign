package dikshasinha.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String curr = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(curr);
		reporter.config().setReportName("Selenium Framework Design");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);		
		return extent;
	}
}

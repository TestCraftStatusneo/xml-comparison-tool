package com.sneo.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sneo.core.Configuration;

/**
 *  ExtentManager for Extent Reporting
 *
 * @author ikumar
 */
public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {

        //String date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        //String fileName = "../avis-api-testsdata/extent-reports/" + "extent_" + date.replace(":", "_").replace("-", "_") + ".html";
        String fileName = "./testreport/" + "extent_report.html";


        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("ENVIRONMENT", Configuration.ENVIRONMENT);
        extent.setSystemInfo("BASE_URI", Configuration.BASE_URI);

        return extent;
    }

    }


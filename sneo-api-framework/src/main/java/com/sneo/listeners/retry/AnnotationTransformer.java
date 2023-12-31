package com.sneo.listeners.retry;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 *  AnnotationTransformer for setting retry Analyzer
 *
 * @author ikumar
 */
public class AnnotationTransformer implements IAnnotationTransformer {
    public static Boolean retryFlag = false;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        retryFlag = true;
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
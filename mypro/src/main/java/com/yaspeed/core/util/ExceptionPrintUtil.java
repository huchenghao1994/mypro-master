package com.yaspeed.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionPrintUtil {

	public static String printException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw, true));
		return sw.toString();
	}

}

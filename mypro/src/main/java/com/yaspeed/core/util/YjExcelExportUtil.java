package com.yaspeed.core.util;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * excel下载工具类
 * 
 * @author Administrator
 *
 */
public class YjExcelExportUtil {
	private YjExcelExportUtil() {
	}

	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			boolean isCreateHeader, HttpServletResponse response) throws Exception {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		defaultExport(list, pojoClass, fileName, response, exportParams);
	}

	public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
			HttpServletResponse response) throws Exception {
		defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
	}

	public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
			throws Exception {
		defaultExport(list, fileName, response);
	}

	private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
			ExportParams exportParams) throws Exception {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}

	private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook)
			throws Exception {
		final String userAgent = RequestHolder.getRequest().getHeader("USER-AGENT");

		if (userAgent.contains("Firefox")) {
			// 是火狐浏览器，使用BASE64编码
			fileName = "=?utf-8?b?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
		} else {
			// 给文件名进行URL编码
			// URLEncoder.encode()需要两个参数，第一个参数时要编码的字符串，第二个是编码所采用的字符集
			fileName = URLEncoder.encode(fileName, "utf-8");
		}
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			workbook.write(response.getOutputStream());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
			throws Exception {
		Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
		if (workbook != null)
			;
		downLoadExcel(fileName, response, workbook);
	}

	public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass)
			throws Exception {
		if (StringUtils.isBlank(filePath)) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = cn.afterturn.easypoi.excel.ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
		} catch (NoSuchElementException e) {
			throw new Exception("模板不能为空");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return list;
	}

	public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)
			throws Exception {
		if (file == null) {
			return null;
		}
		ImportParams params = new ImportParams();
		params.setTitleRows(titleRows);
		params.setHeadRows(headerRows);
		List<T> list = null;
		try {
			list = cn.afterturn.easypoi.excel.ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return list;
	}

}

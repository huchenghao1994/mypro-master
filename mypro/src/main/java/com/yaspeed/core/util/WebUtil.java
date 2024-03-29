package com.yaspeed.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web操作类
 * 
 * @author Administrator
 *
 */
public class WebUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 将数据以 JSON 格式写入响应中
	 */
	public static void writeJSON(HttpServletResponse response, Object data) {
		try {
			// 设置响应头
			response.setContentType("application/json"); // 指定内容类型为 JSON 格式
            response.setCharacterEncoding("UTF-8"); // 防止中文乱码
            // 向响应中写入数据
			PrintWriter writer = response.getWriter();
			writer.write(JsonUtils.objectToJson(data)); // 转为 JSON 字符串
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("在响应中写数据出错！", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将数据以 HTML 格式写入响应中（在 JS 中获取的是 JSON 字符串，而不是 JSON 对象）
	 */
	public static void writeHTML(HttpServletResponse response, Object data) {
		try {
			// 设置响应头
			response.setContentType("application/html"); // 指定内容类型为 HTML 格式
			response.setCharacterEncoding("UTF-8"); // 防止中文乱码
			// 向响应中写入数据
			PrintWriter writer = response.getWriter();
			writer.write(JsonUtils.objectToJson(data)); // 转为 JSON 字符串
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("在响应中写数据出错！", e);
			throw new RuntimeException(e);
		}
	}

	private static boolean checkParamName(String paramName) {
		return !paramName.equals("_"); // 忽略 jQuery 缓存参数
	}

	/**
	 * 转发请求
	 */
	public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			logger.error("转发请求出错！", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 重定向请求
	 */
	public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + path);
		} catch (Exception e) {
			logger.error("重定向请求出错！", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 发送错误代码
	 */
	public static void sendError(int code, String message, HttpServletResponse response) {
		try {
			response.sendError(code, message);
		} catch (Exception e) {
			logger.error("发送错误代码出错！", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 判断是否为 AJAX 请求
	 */
	public static boolean isAJAX(HttpServletRequest request) {
		return request.getHeader("X-Requested-With") != null;
	}

	/**
	 * 获取请求路径
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String pathInfo = StringUtils.defaultIfEmpty(request.getPathInfo(), "");
		return servletPath + pathInfo;
	}

	/**
	 * 下载文件
	 */
	public static void downloadFile(HttpServletResponse response, String filePath) {
		try {
			String originalFileName = FilenameUtils.getName(filePath);
			String downloadedFileName = new String(originalFileName.getBytes("GBK"), "ISO8859_1"); // 防止中文乱码

			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + downloadedFileName + "\"");

			InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			StreamUtil.copyStream(inputStream, outputStream);
		} catch (Exception e) {
			logger.error("下载文件出错！", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 设置 Redirect URL 到 Session 中
	 */
	public static void setRedirectUrl(HttpServletRequest request, String sessionKey) {
		if (!isAJAX(request)) {
			String requestPath = getRequestPath(request);
			request.getSession().setAttribute(sessionKey, requestPath);
		}
	}

	/**
	 * 创建验证码
	 */
	public static String createCaptcha(HttpServletResponse response) {
		StringBuilder captcha = new StringBuilder();
		try {
			// 参数初始化
			int width = 60; // 验证码图片的宽度
			int height = 25; // 验证码图片的高度
			int codeCount = 4; // 验证码字符个数
			int codeX = width / (codeCount + 1); // 字符横向间距
			int codeY = height - 4; // 字符纵向间距
			int fontHeight = height - 2; // 字体高度
			int randomSeed = 10; // 随机数种子
			char[] codeSequence = { // 验证码中可出现的字符
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			// 创建图像
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			// 将图像填充为白色
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			// 设置字体
			g.setFont(new Font("Courier New", Font.BOLD, fontHeight));
			// 绘制边框
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, width - 1, height - 1);
			// 产生随机干扰线（160条）
			g.setColor(Color.WHITE);
			// 创建随机数生成器
			Random random = new Random();
			for (int i = 0; i < 160; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			// 生成随机验证码
			int red, green, blue;
			for (int i = 0; i < codeCount; i++) {
				// 获取随机验证码
				String validateCode = String.valueOf(codeSequence[random.nextInt(randomSeed)]);
				// 随机构造颜色值
				red = random.nextInt(255);
				green = random.nextInt(255);
				blue = random.nextInt(255);
				// 将带有颜色的验证码绘制到图像中
				g.setColor(new Color(red, green, blue));
				g.drawString(validateCode, (i + 1) * codeX - 6, codeY);
				// 将产生的随机数拼接起来
				captcha.append(validateCode);
			}
			// 禁止图像缓存
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			// 设置响应类型为 JPEG 图片
			response.setContentType("image/jpeg");
			// 将缓冲图像写到 Servlet 输出流中
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(bi, "jpeg", sos);
			sos.close();
		} catch (Exception e) {
			logger.error("创建验证码出错！", e);
			throw new RuntimeException(e);
		}
		return captcha.toString();
	}

	/**
	 * 是否为 IE 浏览器
	 */
	public boolean isIE(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		return agent != null && agent.contains("MSIE");
	}
}
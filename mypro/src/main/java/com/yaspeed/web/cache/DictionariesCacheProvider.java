package com.yaspeed.web.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据字典缓存
 * 
 * @author Administrator
 *
 */
public class DictionariesCacheProvider {

	private Map<String, Map<String, String>> cacheContainer = new ConcurrentHashMap<String,Map<String,String>>();

	public void put(String key, Map<String, String> value) {
		cacheContainer.put(key, value);
	}

	public Map<String, String> get(String key) {
		return cacheContainer.get(key);
	}

	public void remove(String key) {
		cacheContainer.remove(key);
	}

	public void clear() {
		cacheContainer.clear();
	}

	public void loadAllCache() {
		Map<String, String> public1902Maps = new HashMap<String,String>();
		public1902Maps.put("1", "命中规则");
		public1902Maps.put("0", "未命中规则");
		cacheContainer.put("public1902", public1902Maps);

		Map<String, String> public1903Maps = new HashMap<String,String>();
		public1903Maps.put("01", "单值比较");
		public1903Maps.put("02", "公共约定");
		public1903Maps.put("03", "数据区间");
		public1903Maps.put("04", "数据集合");
		cacheContainer.put("public1904", public1903Maps);

		Map<String, String> public1906Maps = new HashMap<String,String>();
		public1906Maps.put("S", "字符串");
		public1906Maps.put("I", "int整数");
		public1906Maps.put("I_min", "整型最小值");
		public1906Maps.put("I_max", "整型最大值");
		public1906Maps.put("I_per", "整型百分比");
		public1906Maps.put("D", "Double型");
		cacheContainer.put("public1906", public1906Maps);

		Map<String, String> public1907Maps = new HashMap<String,String>();
		public1907Maps.put("=", "=：等于");
		public1907Maps.put(">", ">：大于");
		public1907Maps.put(">=", ">=：大于等于");
		public1907Maps.put("<", "<：小于");
		public1907Maps.put("<=", "<=：小于等于");
		public1907Maps.put("<>", "<>：不等于");
		public1907Maps.put("IN", "IN：包含");
		public1907Maps.put("NOT_IN", "NOT_IN：不包含");
		cacheContainer.put("public1907", public1907Maps);

		Map<String, String> public1908Maps = new HashMap<String,String>();
		public1908Maps.put("01", "低");
		public1908Maps.put("01", "中");
		public1908Maps.put("03", "高");
		public1908Maps.put("09", "直接拒绝");
		cacheContainer.put("public1908", public1908Maps);

		Map<String, String> public1909Maps = new HashMap<String,String>();
		public1909Maps.put("OR", "OR：或");
		public1909Maps.put("AND", "AND：与");
		public1909Maps.put("NOT", "NOT：非");
		cacheContainer.put("public1909", public1909Maps);

		Map<String, String> public1910Maps = new HashMap<String,String>();
		public1910Maps.put("01", "取默认值");
		public1910Maps.put("AND", "用户手工输入");
		cacheContainer.put("public1909", public1910Maps);

	}

	public Map<String, Map<String, String>> getCacheContainer() {
		return cacheContainer;
	}

	public void setCacheContainer(Map<String, Map<String, String>> cacheContainer) {
		this.cacheContainer = cacheContainer;
	}

}

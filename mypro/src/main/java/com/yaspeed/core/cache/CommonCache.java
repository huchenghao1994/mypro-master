package com.yaspeed.core.cache;//package com.yaspeed.core.cache;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.ServletContextAware;
//
//import com.yaspeed.core.util.DateTimeUtil;
//import com.yaspeed.web.pojo.SysDic;
//import com.yaspeed.web.pojo.SysUser;
//import com.yaspeed.web.pojo.UnitInfo;
//import com.yaspeed.web.pojo.ZtreeNodes;
//import com.yaspeed.web.service.SysDicService;
//import com.yaspeed.web.service.SysUserService;
//import com.yaspeed.web.service.UnitInfoService;
//
//public class CommonCache implements InitializingBean, ServletContextAware {
//	private static Logger logger = LoggerFactory.getLogger(CommonCache.class);
//	// 实现成为单例
//	private static CommonCache commonCache = new CommonCache();
//
//	private CommonCache() {
//	}
//
//	public static CommonCache getInstance() {
//		return commonCache;
//	}
//
//	@Autowired
//	private UnitInfoService unitInfoService;
//
//	@Autowired
//	private SysUserService sysUserService;
//
//	@Autowired
//	private SysDicService sysDicService;
//
//	// 当前登陆人所属机构以及下级
//	public static Map<String, List<ZtreeNodes>> nodesMap = new HashMap<>();
//	// unit_info id转换名称
//	public static Map<String, String> unitIdToName = new HashMap<>();
//
//	// unit_info id转对象
//	public static Map<String, UnitInfo> unitIdTolist = new HashMap<>();
//	
//	// unit_info 法人机构
//	public static List<UnitInfo> lgealUnitInfoList = new ArrayList<>();
//	
//	// sys_dic id转换名称
//	public static LinkedHashMap<String, LinkedHashMap<String, String>> dicIdToList = new LinkedHashMap<>();
//
//	// sys_user id转换名称
//	public static Map<String, String> tellerIdToName = new HashMap<>();
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//
//	}
//
//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		try {
//			this.init();
//		}catch (Exception e){
//
//		}
//
//	}
//
//	public void init() throws Exception{
//		initUnit();
//		initSysUser();
//		initSysDic();
//	}
//
//	public void refresh(UnitInfoService unitInfoService,SysUserService sysUserService,SysDicService sysDicService) throws Exception{
//			this.unitInfoService=unitInfoService;
//			this.sysUserService=sysUserService;
//			this.sysDicService=sysDicService;
//		    init();
//	}
//
//	private void initUnit() throws Exception{
//			LinkedList<ZtreeNodes> nodesList = new LinkedList<>();
//			logger.debug("===>机构加载时间:" + DateTimeUtil.getNowTime());
//			List<UnitInfo> unitInfoList = unitInfoService.getAllUnitInfo();
//			for (int i = 0; i < unitInfoList.size(); i++) {
//				ZtreeNodes zNodes = new ZtreeNodes();
//				unitIdTolist.put(unitInfoList.get(i).getUnitId(), unitInfoList.get(i));
//				unitIdToName.put(unitInfoList.get(i).getUnitId(), unitInfoList.get(i).getUnitName());
//				zNodes.setId(unitInfoList.get(i).getUnitId());
//				zNodes.setPid(unitInfoList.get(i).getLegUnit());
//				zNodes.setName(unitInfoList.get(i).getUnitName());
//				nodesList.add(zNodes);
//				if(unitInfoList.get(i).getLegUnit().equals("01121")&&!unitInfoList.get(i).getUnitId().equals("01121")) {
//					lgealUnitInfoList.add(unitInfoList.get(i));
//				}
//			}
//			/********************************/
//			for (int i = 0; i < nodesList.size(); i++) {
//				if (nodesList.get(i).getId().equals("01121")) {
//					nodesMap.put("01121", nodesList);
//				}
//				List<ZtreeNodes> znodeList = new ArrayList<>();
//
//				if (nodesList.get(i).getPid().equals("01121") && !nodesList.get(i).getId().equals("01121")) {
//					znodeList.add(nodesList.get(i));
//					for (int j = 0; j < nodesList.size(); j++) {
//						if (nodesList.get(j).getPid().equals(nodesList.get(i).getId())) {
//							znodeList.add(nodesList.get(j));
//						}
//					}
//					nodesMap.put(nodesList.get(i).getId(), znodeList);
//				}
//			}
//			logger.debug("===>机构加载结束时间:" + DateTimeUtil.getNowTime());
//	}
//
//	private void initSysUser() throws Exception{
//			logger.debug("===>会员加载时间:" + DateTimeUtil.getNowTime());
//			List<SysUser> sysUserList = sysUserService.findAllSysUserList();
//			for (int i = 0; i < sysUserList.size(); i++) {
//				tellerIdToName.put(sysUserList.get(i).getTellerId(), sysUserList.get(i).getTellerName());
//			}
//			logger.debug("===>会员加载结束时间:" + DateTimeUtil.getNowTime());
//	}
//
//	private void initSysDic() throws Exception{
//			logger.debug("===>字典加载时间:" + DateTimeUtil.getNowTime());
//			 List<SysDic> sysDicList=sysDicService.findSysDicList(new SysDic());
//			 String pkey="";
//			 LinkedHashMap<String,String> map=new LinkedHashMap<>();
//			 for(int i=0;i<sysDicList.size();i++) {
//				 if(i==0) {
//					 pkey=sysDicList.get(i).getDicPubId();
//					 map.put(sysDicList.get(i).getDicValue(), sysDicList.get(i).getDicName());
//				 }else if(sysDicList.get(i).getDicPubId().equals(pkey)){
//					 map.put(sysDicList.get(i).getDicValue(), sysDicList.get(i).getDicName());
//				 }else if(!sysDicList.get(i).getDicPubId().equals(pkey)) {
//					 dicIdToList.put(pkey, map);
//					 map=new LinkedHashMap<>();
//					 pkey=sysDicList.get(i).getDicPubId();
//					 map.put(sysDicList.get(i).getDicValue(), sysDicList.get(i).getDicName());
//				 }
//				 
//				 if(i==sysDicList.size()-1) {
//					 dicIdToList.put(pkey, map);
//				 }
//			 }
//			logger.debug("===>字典加载结束时间:" + DateTimeUtil.getNowTime());
//	}
//}

package com.yaspeed.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaspeed.common.DataGridResult;
import com.yaspeed.core.util.StringUtil;
import com.yaspeed.web.dao.RdsUserMapper;
import com.yaspeed.web.dao.RdsUserMenuMapper;
import com.yaspeed.web.dao.RdsUserRoleMapper;
import com.yaspeed.web.pojo.*;
import com.yaspeed.web.service.RdsUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class RdsUserServiceImpl implements RdsUserService {
	Logger logger = LoggerFactory.getLogger(RdsUserServiceImpl.class);

	@Autowired
	private RdsUserMapper rdsUserMapper;
	
	@Autowired
	private RdsUserMenuMapper rdsUserMenuMapper;

	@Autowired
	private RdsUserRoleMapper rdsUserRoleMapper;
	
	
	@Override
	public RdsUser findRdsUserByUserId(String userId) {
		return rdsUserMapper.selectByPrimaryKey(userId);
	}


//	@Autowired
//	private SysMenuUserMapper sysMenuUserMapper;
//
//	@Autowired
//	private SysUserRoleMapper sysUserRoleMapper;
//
//	private static String salt;
//	private static Integer hashIterations;
//	private static String IS_CUSTOMER_MANAGER = "1";
//
//	static {
//		salt = PropertyUtil.getProperty("salt");
//		hashIterations = Integer.valueOf(PropertyUtil.getProperty("hashIterations"));
//	}
//
//	@Override
//	public DataGridResult findSysUserList(SysUser sysUser, Integer page, Integer rows) throws Exception {
//		ActiveUser activeUser = CoreMediator.getInstance().getActiveUser();
//		if(activeUser.getUnitType().equals("19")) {
//			return findSysUserbyIdList(sysUser,page,rows);
//		}
//		return findLegSysUserbyIdList(sysUser,page,rows);
//	}
//
//	
	public DataGridResult findRdsUserList(RdsUser rdsUser, Integer page, Integer rows) throws Exception {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		RdsUserExample example = new RdsUserExample();
		RdsUserExample.Criteria criteria = example.createCriteria();
//		if (StringUtil.isNotEmpty(sysUser.getTellerName())) {
//			criteria.andTellerNameLike(sysUser.getTellerName());
//		}
//		if (StringUtil.isNotEmpty(sysUser.getTellerId())) {
//			criteria.andTellerIdEqualTo(sysUser.getTellerId());
//		}
//		if (!StringUtil.isNotEmpty(sysUser.getTellerName())&&!StringUtil.isNotEmpty(sysUser.getTellerId())) {
//			criteria.andUnitIdEqualTo(sysUser.getUnitId());
//		}
//		
//		example.setOrderByClause("id with ur");
		List<RdsUser> userList = rdsUserMapper.selectByExample(example);
		// 取查询结果
		PageInfo<RdsUser> pageInfo = new PageInfo<RdsUser>(userList);

		DataGridResult result = new DataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(userList);
		return result;
	}
	
//	public DataGridResult findLegSysUserbyIdList(SysUser sysUser, Integer page, Integer rows) throws Exception {
//		ActiveUser activeUser = CoreMediator.getInstance().getActiveUser();
//		
//		// 设置分页信息
//		PageHelper.startPage(page, rows);
//		List<SysUser> userList=new ArrayList<>();
//		
//		if (StringUtil.isNotEmpty(sysUser.getTellerName())||StringUtil.isNotEmpty(sysUser.getTellerId())) {
//			sysUser.setUnitId(activeUser.getUnitId());
//			userList = sysUserMapper.selectBySysUser(sysUser);
//		}else {
//			userList = sysUserMapper.selectBySysUserUnitId(sysUser.getUnitId());
//		}
//		// 取查询结果
//		PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
//
//		DataGridResult result = new DataGridResult();
//		result.setTotal(pageInfo.getTotal());
//		result.setRows(userList);
//		return result;
//	}
//	@Override
//	public Integer changePasswd(SysUser sysUser) {
//		// 加密新密码
//		String mD5Password = EncryptPasswd.encrypt("123456", salt, hashIterations);
//		sysUser.setPaswd(mD5Password);
//		return sysUserMapper.updateByPrimaryKeySelective(sysUser);
//	}
//
//	@Override
//	public Integer updatePassword(SysUser sysUser) {
//		// 加密新密码
//		String mD5Password = EncryptPasswd.encrypt(sysUser.getPaswd(), salt, hashIterations);
//		sysUser.setPaswd(mD5Password);
//		return sysUserMapper.updateByPrimaryKeySelective(sysUser);
//	}
//
//	@Override
//	public SysUser findUserByTellerId(String tellerId) {
//		return sysUserMapper.selectByPrimaryKey(tellerId);
//	}
//
//	@Override
//	public Integer addUserSysRole(SysUser sysUser) throws Exception {
//		SysUserRoleExample example = new SysUserRoleExample();
//		SysUserRoleExample.Criteria criteria = example.createCriteria();
//		criteria.andSysUserIdEqualTo(sysUser.getTellerId());
//		sysUserRoleMapper.deleteByExample(example);
//
//		SysUserRole sysUserRole = new SysUserRole();
//		sysUserRole.setSysUserId(sysUser.getTellerId());
//		if(sysUser.getRoleIds()!=null) {
//		for (int i = 0; i < sysUser.getRoleIds().size(); i++) {
//			if (StringUtil.isNotEmpty(sysUser.getRoleIds().get(i))) {
//				sysUserRole.setSysRoleId(sysUser.getRoleIds().get(i));
//				sysUserRoleMapper.insertSelective(sysUserRole);
//			}
//
//		}
//		}
//		return 0;
//	}
//

	@Override
	public List<RdsMenu> findPercodeListByUserId(String userId) throws Exception {
		return rdsUserMenuMapper.findMenusListByUserId(userId);
	}
	
	@Override
	public List<MenuItem> findMenuItemListByUserId(String userId) throws Exception {
		return loadMenus(userId);
	}

	/**
	 * 根据用户ID获取菜单
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	private List<MenuItem> loadMenus(String userId) throws Exception {
		List<RdsMenu> menus = rdsUserMenuMapper.findMenusListByUserId(userId);
		// 创建菜单集合,即获取parentID为0的顶层菜单
		LinkedHashMap<String, MenuItem> root = new LinkedHashMap<>();
		// 循环菜单添加到菜单集合
		for (RdsMenu menu : menus) {
			MenuItem item = new MenuItem(menu.getMenuName(), null);
			item.setId(menu.getId());
			item.setMenuId(menu.getMenuId());
			item.setPid(menu.getPid());
			item.setMenuType(menu);
			item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
			item.setIcon(menu.getIcon());
			if (item.isRootMenu()) {
				root.put(item.getMenuId(), item);
			}
		}

		for (RdsMenu menu : menus) {
			MenuItem item = new MenuItem(menu.getMenuName(), null);
			item.setId(menu.getId());
			item.setMenuId(menu.getMenuId());
			item.setPid(menu.getPid());
			item.setMenuType(menu);
			item.setIcon(menu.getIcon());
			item.setUrl(StringUtils.trimToEmpty(menu.getUrl()));
			if (!item.isRootMenu() && !item.isButton()) {
				MenuItem parentItem = root.get(menu.getPid());
				if (parentItem != null) {
					parentItem.addClild(item);
				} else {
					logger.warn("菜单项{}({})没有对应的父级菜单", item.getName(), item.getId());
				}
			}
		}
		return new LinkedList<>(root.values());
	}


	@Override
	@Transactional
	public int addUserRdsRoleByBatch(RdsUser rdsUser) throws Exception {
		
		rdsUserRoleMapper.deleteByPrimaryKey(rdsUser.getUserId());
		
		LinkedList<RdsUserRole> rdsUserRoles=new LinkedList<RdsUserRole>();
		if(rdsUser.getRoleIds()!=null) {
		for(String roleId:rdsUser.getRoleIds()) {
			if(StringUtil.isNotEmpty(roleId)) {
				RdsUserRole rdsUserRole=new RdsUserRole();
				rdsUserRole.setUserId(rdsUser.getUserId());
				rdsUserRole.setRoleId(roleId);
				rdsUserRoles.add(rdsUserRole);
			}
			
		}
		return rdsUserRoleMapper.insertUserRdsRoleByBatch(rdsUserRoles);
		}
		return 0;
	}


	@Override
	public List<RdsUserRole> findRdsRoleListByUserId(String userId) throws Exception {
		RdsUserRoleExample example = new RdsUserRoleExample();
		RdsUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return rdsUserRoleMapper.selectByExample(example);
	}

//	@Override
//	public List<SysUser> findAllSysUserList() throws Exception {
//		SysUserExample example = new SysUserExample();
//		return sysUserMapper.selectByExample(example);
//	}
//
//	@Override
//	public Boolean pswIsBoolean(String paswdOld) throws Exception {
//		ActiveUser activeUser = CoreMediator.getInstance().getActiveUser();
//		String mD5Password = EncryptPasswd.encrypt(paswdOld, salt, hashIterations);
//		SysUser sysUser = sysUserMapper.selectByPrimaryKey(activeUser.getTellerId());
//		return sysUser.getPaswd().equals(mD5Password);
//	}
//
//	@Override
//	public List<SysUser> findCustomerManagerByOriganizationId(String unitId) {
//		SysUserExample example = new SysUserExample();
//		SysUserExample.Criteria criteria = example.createCriteria();
//		criteria.andIsCusEqualTo(IS_CUSTOMER_MANAGER);
//		criteria.andUnitIdEqualTo(unitId);
//		return sysUserMapper.selectByExample(example);
//	}




}

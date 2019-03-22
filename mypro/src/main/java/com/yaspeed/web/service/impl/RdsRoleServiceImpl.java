package com.yaspeed.web.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaspeed.common.DataGridResult;
import com.yaspeed.core.util.StringUtil;
import com.yaspeed.web.dao.RdsRoleMapper;
import com.yaspeed.web.dao.RdsRoleMenuMapper;
import com.yaspeed.web.pojo.RdsRole;
import com.yaspeed.web.pojo.RdsRoleExample;
import com.yaspeed.web.pojo.RdsRoleMenu;
import com.yaspeed.web.service.RdsRoleService;

@Service
public class RdsRoleServiceImpl implements RdsRoleService {

	@Autowired
	private RdsRoleMapper rdsRoleMapper;
	
	@Autowired
	private RdsRoleMenuMapper rdsRoleMenuMapper;
	

	@Override
	public DataGridResult findRdsRoleList(RdsRole rdsRole, Integer page, Integer rows) throws Exception {
		// 设置分页信息
		PageHelper.startPage(page, rows);

		RdsRoleExample example = new RdsRoleExample();
		RdsRoleExample.Criteria criteria = example.createCriteria();

		if (StringUtil.isNotEmpty(rdsRole.getRoleName())) {
			criteria.andRoleNameLike(rdsRole.getRoleName());
		}
		
		example.setOrderByClause(" role_id");
		List<RdsRole> rdsRoleList = rdsRoleMapper.selectByExample(example);

		// 取查询结果
		PageInfo<RdsRole> pageInfo = new PageInfo<>(rdsRoleList);

		DataGridResult result = new DataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(rdsRoleList);
		return result;
	}

	@Override
	@Transactional
	public int addRdsRole(RdsRole rdsRole) throws Exception {
		LinkedList<RdsRoleMenu> list=new LinkedList<>();
	  
		if(rdsRole.getMenuIds()!=null) {
		for(String menuId:rdsRole.getMenuIds()) {
			if(StringUtil.isNotEmpty(menuId)&&!menuId.equals("0")) {
				RdsRoleMenu rdsRoleMenu=new RdsRoleMenu();
				rdsRoleMenu.setRoleId(rdsRole.getRoleId());
				rdsRoleMenu.setMenuId(menuId);
				list.add(rdsRoleMenu);
			}
		}
		rdsRoleMenuMapper.insertByBatch(list);
		}
		return rdsRoleMapper.insertSelective(rdsRole);
	}

	@Override
	@Transactional
	public int deleteRdsRoleList(RdsRole rdsRole) throws Exception {
		RdsRoleExample example = new RdsRoleExample();
		RdsRoleExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdIn(rdsRole.getRoleIds());
		
		for(String roleId:rdsRole.getRoleIds()) {
			rdsRoleMenuMapper.deleteByRoleId(roleId);
		}
	
		return rdsRoleMapper.deleteByExample(example);
	}

	@Override
	public RdsRole getRdsRoleByRoleId(String roleId) throws Exception {
		return rdsRoleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	@Transactional
	public int updateRdsRole(RdsRole rdsRole) throws Exception {
		LinkedList<RdsRoleMenu> list=new LinkedList<>();
		
		rdsRoleMenuMapper.deleteByRoleId(rdsRole.getRoleId());
		if(rdsRole.getMenuIds()!=null) {
            for (String menuId : rdsRole.getMenuIds()) {
                if (StringUtil.isNotEmpty(menuId) && !menuId.equals("0")) {
                    RdsRoleMenu rdsRoleMenu = new RdsRoleMenu();
                    rdsRoleMenu.setRoleId(rdsRole.getRoleId());
                    rdsRoleMenu.setMenuId(menuId);
                    list.add(rdsRoleMenu);
                }
            }
            rdsRoleMenuMapper.insertByBatch(list);
        }
		
		return rdsRoleMapper.updateByPrimaryKeySelective(rdsRole);
	}

	@Override
	public List<RdsRoleMenu> getRdsRoleMenuByZtree(String roleId) throws Exception {
		return rdsRoleMenuMapper.selectByRoleId(roleId);
	}

	@Override
	public List<RdsRole> findRdsRoleList() throws Exception {
		RdsRoleExample example = new RdsRoleExample();
	
		return rdsRoleMapper.selectByExample(example);
	}

}

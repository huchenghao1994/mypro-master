package com.yaspeed.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaspeed.common.DataGridResult;
import com.yaspeed.core.util.StringUtil;
import com.yaspeed.web.dao.RdsMenuMapper;
import com.yaspeed.web.pojo.RdsMenu;
import com.yaspeed.web.pojo.RdsMenuExample;
import com.yaspeed.web.pojo.ZtreeNodes;
import com.yaspeed.web.service.RdsMenuService;

@Service
public class RdsMenuServiceImpl implements RdsMenuService {

	@Autowired
	private RdsMenuMapper rdsMenuMapper;

	@Override
	public DataGridResult findRdsMenuList(RdsMenu rdsMenu, Integer page, Integer rows) throws Exception {
		// 设置分页信息
		PageHelper.startPage(page, rows);

		RdsMenuExample example = new RdsMenuExample();
		RdsMenuExample.Criteria criteria = example.createCriteria();
		
		if(StringUtil.isNotEmpty(rdsMenu.getMenuName())) {
			criteria.andMenuNameLike(rdsMenu.getMenuName());
		}
		if(StringUtil.isNotEmpty(rdsMenu.getMenuType())) {
			criteria.andMenuTypeEqualTo(rdsMenu.getMenuType());
		}
		
		if(StringUtil.isNotEmpty(rdsMenu.getPid())) {
			criteria.andPidEqualTo(rdsMenu.getPid());
		}else {
			criteria.andPidEqualTo("0");
		}
		
		example.setOrderByClause(" order_num");
		List<RdsMenu> rdsMenuList = rdsMenuMapper.selectByExample(example);

		// 取查询结果
		PageInfo<RdsMenu> pageInfo = new PageInfo<>(rdsMenuList);

		DataGridResult result = new DataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(rdsMenuList);
		return result;
	}

	@Override
	public int deleteRdsMenuList(RdsMenu RdsMenu) throws Exception {
		RdsMenuExample example = new RdsMenuExample();
		RdsMenuExample.Criteria criteria = example.createCriteria();
		criteria.andMenuIdIn(RdsMenu.getMenuIds());
		return rdsMenuMapper.deleteByExample(example);
	}

	@Override
	public int addRdsMenu(RdsMenu rdsMenu) throws Exception {
		return rdsMenuMapper.insertSelective(rdsMenu);
	}
	
	@Override
	public RdsMenu getRdsMenuByMenuId(String menuId) throws Exception {
		return rdsMenuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public int updateRdsMenu(RdsMenu rdsMenu) throws Exception {
		return rdsMenuMapper.updateByPrimaryKey(rdsMenu);
	}

	@Override
	public List<ZtreeNodes> getRdsMenuByZtree() throws Exception {
		return rdsMenuMapper.getRdsMenuByZtree();
	}
	

}

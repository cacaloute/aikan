package com.aikan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.AuthorizationT;
import com.aikan.entity.RoleT;
import com.aikan.entity.RoleAuthorization;
import com.aikan.mapping.RoleTMapper;
import com.aikan.service.AuthorizationTService;
import com.aikan.service.RoleTService;

@Service("roleTServiceImpl")
public class RoleTServiceImpl implements RoleTService {
	
	@Autowired
	private RoleTMapper roleTMapper;
	
	@Resource(name="authorizationTServiceImpl")
	private AuthorizationTService authorizationService;
	
	@Override
	public List<String> getAuthorUrls(Integer roleId) {
		return roleTMapper.selectUrlsById(roleId);
	}

	@Override
	public Map<String, List<RoleAuthorization>> getAuthors(Integer roleId) {

		//通过RoleTMapper提供的方法完成指定id角色的查询
		List<RoleAuthorization> roleAuthorizations=roleTMapper.selectById(roleId);
		
		Map<String,List<RoleAuthorization>> menuTree=new HashMap<String,List<RoleAuthorization>>();
		for(int i=0;i<roleAuthorizations.size();i++){
			RoleAuthorization roleAuthorization=roleAuthorizations.get(i);
			//首先得到每个权限的authorParentId
			Integer authorParentId=roleAuthorization.getAuParentId();

			//System.out.println("authorParentId;"+authorParentId);
			
			//尝试从menuTree中，根据authorParentId获取角色权限List<RoleAuthor>
			List<RoleAuthorization> roleAuthorList=menuTree.get(authorParentId.toString());
			//如果没有，则需要新建一个roleAuthorList，再放入到menuTree
			if(roleAuthorList==null){
				roleAuthorList=new ArrayList<>();
				menuTree.put(authorParentId.toString(), roleAuthorList);
			}
			//再把相同parentId的权限对象，放入到对应的roleAuthorList
			roleAuthorList.add(roleAuthorization);
		}
		return menuTree;
	}

	@Override
	public List<RoleT> searchAll() {
		return roleTMapper.selectAll();
	}

	@Override
	public Map<String, Object> get(Integer roleId) {
		Map<String,Object> map=new HashMap<>();
		//指定角色
		RoleT role=roleTMapper.selectRoleById(roleId);
		map.put("role",role);
		//所有的权限
		List<AuthorizationT> allAuthorizations=authorizationService.searchAllByRoleId(roleId);
		map.put("allAuthorizations",allAuthorizations);
		
		return map;
	}

	@Override
	public int doAssign(Integer roleId, Integer[] authorIds) {
		//先删除此角色原有的权限
		int result=roleTMapper.deleteRoleAuthor(roleId);
		if(result==0){
			return 0;
		}
		
		//依次添加新的权限
		for(int i=0;i<authorIds.length;i++){
			RoleAuthorization roleAuthorization=new RoleAuthorization();
			roleAuthorization.setRoleId(roleId);
			roleAuthorization.setAuId(authorIds[i]);
			result=roleTMapper.insertRoleAuthor(roleAuthorization);
			if(result==0){
				return 0;
			}
		}
		return 1;
	}	
}

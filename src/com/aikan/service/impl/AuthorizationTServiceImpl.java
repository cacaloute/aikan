package com.aikan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.aikan.entity.AuthorizationT;
import com.aikan.mapping.AuthorizationTMapper;
import com.aikan.service.AuthorizationTService;
@Service("authorizationTServiceImpl")
public class AuthorizationTServiceImpl implements AuthorizationTService {

	@Autowired
	private AuthorizationTMapper authorizationTMapper;
	
	@Override
	public List<AuthorizationT> searchAll() {
		List<AuthorizationT> rootAuthors=authorizationTMapper.selectByParentId(0);
		for(int i=0;i<rootAuthors.size();i++){
			AuthorizationT rootAuthor=rootAuthors.get(i);
			//如果菜单权限类型为0，则是父类菜单，所以可能有子菜单
			if(rootAuthor.getaType().equals(0)){
				List<AuthorizationT> childAuthorizations=authorizationTMapper.selectByParentId(rootAuthor.getaId());
				rootAuthor.setChildAuthorizations(childAuthorizations);
			}
		}
		return rootAuthors;
	}

	@Override
	public List<AuthorizationT> searchAllByRoleId(Integer roleId) {
		Map<String,Integer> params=new HashMap<String,Integer>();
		params.put("roleId",roleId);
		params.put("parentId",0);
		
		List<AuthorizationT> rootAuthors=authorizationTMapper.selectByParentIdAndRoleId(params);
		for(int i=0;i<rootAuthors.size();i++){
			AuthorizationT rootAuthor=rootAuthors.get(i);
			if(rootAuthor.getaType().equals(0)){
				params.put("parentId",rootAuthor.getaId());
				List<AuthorizationT> childAuthors=authorizationTMapper.selectByParentIdAndRoleId(params);
				rootAuthor.setChildAuthorizations(childAuthors);
			}
		}
		return rootAuthors;
	}

}

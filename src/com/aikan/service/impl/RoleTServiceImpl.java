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

		//ͨ��RoleTMapper�ṩ�ķ������ָ��id��ɫ�Ĳ�ѯ
		List<RoleAuthorization> roleAuthorizations=roleTMapper.selectById(roleId);
		
		Map<String,List<RoleAuthorization>> menuTree=new HashMap<String,List<RoleAuthorization>>();
		for(int i=0;i<roleAuthorizations.size();i++){
			RoleAuthorization roleAuthorization=roleAuthorizations.get(i);
			//���ȵõ�ÿ��Ȩ�޵�authorParentId
			Integer authorParentId=roleAuthorization.getAuParentId();

			//System.out.println("authorParentId;"+authorParentId);
			
			//���Դ�menuTree�У�����authorParentId��ȡ��ɫȨ��List<RoleAuthor>
			List<RoleAuthorization> roleAuthorList=menuTree.get(authorParentId.toString());
			//���û�У�����Ҫ�½�һ��roleAuthorList���ٷ��뵽menuTree
			if(roleAuthorList==null){
				roleAuthorList=new ArrayList<>();
				menuTree.put(authorParentId.toString(), roleAuthorList);
			}
			//�ٰ���ͬparentId��Ȩ�޶��󣬷��뵽��Ӧ��roleAuthorList
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
		//ָ����ɫ
		RoleT role=roleTMapper.selectRoleById(roleId);
		map.put("role",role);
		//���е�Ȩ��
		List<AuthorizationT> allAuthorizations=authorizationService.searchAllByRoleId(roleId);
		map.put("allAuthorizations",allAuthorizations);
		
		return map;
	}

	@Override
	public int doAssign(Integer roleId, Integer[] authorIds) {
		//��ɾ���˽�ɫԭ�е�Ȩ��
		int result=roleTMapper.deleteRoleAuthor(roleId);
		if(result==0){
			return 0;
		}
		
		//��������µ�Ȩ��
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

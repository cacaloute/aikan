package com.aikan.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.ManagerT;
import com.aikan.entity.RoleAuthorization;
import com.aikan.entity.RoleT;
import com.aikan.mapping.ManagerTMapper;
import com.aikan.mapping.RoleTMapper;
import com.aikan.service.ManagerTService;
import com.aikan.service.RoleTService;

@Service("managerTServiceImpl")
public class ManagerTServiceImpl implements ManagerTService{
	
	@Autowired
	private ManagerTMapper managerTMapper;
	@Autowired
	private RoleTMapper roleTMapper;
	
	//�Զ���ʵ��spring������service�������󶨵���ǰ������
	@Resource(name="roleTServiceImpl")
	private RoleTService roleTService;

	@Override
	public ManagerT login(String managerName, String managerPassword) {
		// �ȸ����˺ţ���ѯ����Ա
		// ͨ��EmpMapper�ṩ�ķ������ָ��idԱ���Ĳ�ѯ
		ManagerT managerT = managerTMapper.selectOneManagerByName(managerName);
	//	System.out.println("----------------------managerT.getRoleId()1: "+managerT.getRoleId());
		if (managerT != null) {
			// ���ݲ�ѯ����������������������Ƚ�
			if (managerT.getManagerPassword().equals(managerPassword)) {
//����				
		//		System.out.println("----------------------managerT.getRoleId()2: "+managerT.getRoleId());
				// ���ݹ���Ա�Ľ�ɫid��Ѱ�����Ӧ��Ȩ�޲˵��б���
				Map<String, List<RoleAuthorization>> menuTree = roleTService.getAuthors(managerT.getRoleId());
				managerT.setMenuTree(menuTree);
				//�������menuTree���������ظ���
				List<String> authorUrls=roleTService.getAuthorUrls(managerT.getRoleId());
				managerT.setAuthorUrls(authorUrls);
								
				return managerT;
			}
		}
		return null;		
	}
		
	@Override
	public int save(ManagerT managerT) {
		int result=0;
		ManagerT managerTExist=managerTMapper.selectOneManager(managerT);
		if(managerTExist!=null){
			return -1;
		}
		result=managerTMapper.insert(managerT);
		return result;
	}

	@Override
	public int removeOne(Integer managerId) {
		int result=0;
		ManagerT managerTExist=managerTMapper.selectOneManagerById(managerId);
		if(managerTExist!=null){
			return -1;
		}
		result=managerTMapper.deleteOne(managerId);
		return result;
	}

	@Override
	public int removeManyByIds(Integer[] managerIds) {
		int result=0;
		result=managerTMapper.deleteByIds(managerIds);
		if(result==0){
			//ɾ��ʧ��
			return 0;
		}
		return 1;
	}

	@Override
	public int modify(ManagerT managerT) {
		int result = 0;
		ManagerT managerTExist=managerTMapper.selectOneManagerById(managerT.getManagerId());
		if(managerTExist==null){
			return -1;
		}
		result=managerTMapper.update(managerT);
		return result;
	}

	@Override
	public ManagerT getOneManager(ManagerT managerT) {
		// ���ݹ���Ա����������(managerT)����һ������Ա
		return managerTMapper.selectOneManager(managerT);
	}

	@Override
	public List<ManagerT> getAllManager() {
		// �鿴���й���Ա
		List<ManagerT> managers=managerTMapper.selectAllManager();
		if(managers==null){
			return null;
		}
		for(int i=0;i<managers.size();i++){
			ManagerT managerT=managers.get(i);
			
			Integer roleId=managerT.getRoleId();
			RoleT roleT=roleTMapper.selectRoleById(roleId);
			managers.get(i).setRoleT(roleT);
		}
		return managers;
	}

	

}

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
	
	//自动把实际spring创建的service组件对象绑定到当前变量上
	@Resource(name="roleTServiceImpl")
	private RoleTService roleTService;

	@Override
	public ManagerT login(String managerName, String managerPassword) {
		// 先根据账号，查询管理员
		// 通过EmpMapper提供的方法完成指定id员工的查询
		ManagerT managerT = managerTMapper.selectOneManagerByName(managerName);
	//	System.out.println("----------------------managerT.getRoleId()1: "+managerT.getRoleId());
		if (managerT != null) {
			// 根据查询出来的密码和输入密码做比较
			if (managerT.getManagerPassword().equals(managerPassword)) {
//测试				
		//		System.out.println("----------------------managerT.getRoleId()2: "+managerT.getRoleId());
				// 根据管理员的角色id，寻找其对应的权限菜单列表树
				Map<String, List<RoleAuthorization>> menuTree = roleTService.getAuthors(managerT.getRoleId());
				managerT.setMenuTree(menuTree);
				//和上面的menuTree数据是有重复的
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
			//删除失败
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
		// 根据管理员姓名和密码(managerT)查找一个管理员
		return managerTMapper.selectOneManager(managerT);
	}

	@Override
	public List<ManagerT> getAllManager() {
		// 查看所有管理员
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

package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.RoleT;
import com.aikan.entity.RoleAuthorization;

public interface RoleTMapper {
	
	/**
	 * 根据角色id查询拥有的权限url
	 * @param roleId
	 * @return List<String>
	 */
	public List<String> selectUrlsById(Integer roleId);
	
	/**
	 * 根据角色id查询角色拥有的角色权限
	 * @param roleId
	 * @return List<RoleAuthorization> 
	 */
	public List<RoleAuthorization> selectById(Integer roleId);
	
	/**
	 * 查询所有的角色
	 * @return List<RoleT>
	 */
	public List<RoleT> selectAll();
	
	/**
	 * 根据角色id查询一个角色
	 * @return RoleT
	 */
	public RoleT selectRoleById(Integer roleId);
	
	/**
	 * 根据角色id查询角色所拥有的角色权限id
	 * @return List<String>
	 */
	public List<String> selectRoleAuthorById(Integer roleId);
	
	/**
	 * 添加角色权限
	 * @param roleAuthor
	 * @return int
	 */
	public int insertRoleAuthor(RoleAuthorization roleAuthorization);
	
	/**
	 * 根据角色id删除原有角色权限
	 * @param roleId
	 * @return int
	 */
	public int deleteRoleAuthor(Integer roleId);
	
}

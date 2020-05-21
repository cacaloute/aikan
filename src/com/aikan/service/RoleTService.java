package com.aikan.service;

import java.util.List;
import java.util.Map;

import com.aikan.entity.RoleT;
import com.aikan.entity.RoleAuthorization;

public interface RoleTService {
	
	/**
	 * 查询角色所拥有的权限url
	 * @param roleId
	 * @return List<String>
	 */
	public List<String> getAuthorUrls(Integer roleId);
	/**
	 * 填充角色所拥有的权限
	 * @param roleId
	 * @return Map<String,List<RoleAuthorization>>
	 */
	public Map<String,List<RoleAuthorization>> getAuthors(Integer roleId);
	/**
	 * 查询所有的角色
	 * @return List<RoleT>
	 */
	public List<RoleT> searchAll();
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return Map<String,Object>
	 */
	public Map<String,Object> get(Integer roleId);
	/**
	 * 分配权限
	 * @param roleId
	 * @param authorIds
	 * @return int
	 */
	public int doAssign(Integer roleId,Integer[] authorIds);
}

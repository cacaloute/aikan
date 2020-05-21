package com.aikan.service;

import java.util.List;
import java.util.Map;

import com.aikan.entity.RoleT;
import com.aikan.entity.RoleAuthorization;

public interface RoleTService {
	
	/**
	 * ��ѯ��ɫ��ӵ�е�Ȩ��url
	 * @param roleId
	 * @return List<String>
	 */
	public List<String> getAuthorUrls(Integer roleId);
	/**
	 * ����ɫ��ӵ�е�Ȩ��
	 * @param roleId
	 * @return Map<String,List<RoleAuthorization>>
	 */
	public Map<String,List<RoleAuthorization>> getAuthors(Integer roleId);
	/**
	 * ��ѯ���еĽ�ɫ
	 * @return List<RoleT>
	 */
	public List<RoleT> searchAll();
	/**
	 * ���ݽ�ɫid��ѯ��ɫ
	 * @param roleId
	 * @return Map<String,Object>
	 */
	public Map<String,Object> get(Integer roleId);
	/**
	 * ����Ȩ��
	 * @param roleId
	 * @param authorIds
	 * @return int
	 */
	public int doAssign(Integer roleId,Integer[] authorIds);
}

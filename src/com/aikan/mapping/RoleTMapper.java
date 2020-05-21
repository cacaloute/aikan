package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.RoleT;
import com.aikan.entity.RoleAuthorization;

public interface RoleTMapper {
	
	/**
	 * ���ݽ�ɫid��ѯӵ�е�Ȩ��url
	 * @param roleId
	 * @return List<String>
	 */
	public List<String> selectUrlsById(Integer roleId);
	
	/**
	 * ���ݽ�ɫid��ѯ��ɫӵ�еĽ�ɫȨ��
	 * @param roleId
	 * @return List<RoleAuthorization> 
	 */
	public List<RoleAuthorization> selectById(Integer roleId);
	
	/**
	 * ��ѯ���еĽ�ɫ
	 * @return List<RoleT>
	 */
	public List<RoleT> selectAll();
	
	/**
	 * ���ݽ�ɫid��ѯһ����ɫ
	 * @return RoleT
	 */
	public RoleT selectRoleById(Integer roleId);
	
	/**
	 * ���ݽ�ɫid��ѯ��ɫ��ӵ�еĽ�ɫȨ��id
	 * @return List<String>
	 */
	public List<String> selectRoleAuthorById(Integer roleId);
	
	/**
	 * ��ӽ�ɫȨ��
	 * @param roleAuthor
	 * @return int
	 */
	public int insertRoleAuthor(RoleAuthorization roleAuthorization);
	
	/**
	 * ���ݽ�ɫidɾ��ԭ�н�ɫȨ��
	 * @param roleId
	 * @return int
	 */
	public int deleteRoleAuthor(Integer roleId);
	
}

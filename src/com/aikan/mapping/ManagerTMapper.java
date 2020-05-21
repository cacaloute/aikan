package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import com.aikan.entity.ManagerT;

public interface ManagerTMapper extends Serializable {
	/**
	 * ��������Ա
	 * @param managerT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(ManagerT managerT);
	/**
	 * ���ݹ���Ա���ɾ��һ������Ա
	 * @param managerId
	 * @return 0��ɾ���ɹ� 1��ɾ��ʧ��
	 */
	public int deleteOne(Integer managerId);
	/**
	 * ɾ��һ�����Ա
	 * @param managerIds
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int deleteByIds(Integer[] managerIds);
	/**
	 * �޸Ĺ���Ա
	 * @param managerT
	 * @return 0-�����޸�ʧ�ܣ�1-�����޸ĳɹ�
	 */
	public int update(ManagerT managerT);
	/**
	 * ���ݹ���Ա����������(managerT)����һ������Ա
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT selectOneManager(ManagerT managerT);
	/**
	 * ���ݹ���Ա��Ų���һ������Ա
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT selectOneManagerById(Integer managerId);
	/**
	 * ���ݹ���Ա��������һ������Ա
	 * @param managerName
	 * @return ManagerT
	 */
	public ManagerT selectOneManagerByName(String managerName);
	/**
	 * �鿴���й���Ա
	 * @return List<ManagerT>
	 */
	public List<ManagerT> selectAllManager();

}

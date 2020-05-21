package com.aikan.service;

import java.util.List;

import com.aikan.entity.ManagerT;

public interface ManagerTService {
	/**
	 * ����Ա��¼ҵ��
	 * @param managerName
	 * @param managerPassword
	 * @return ManagerT
	 */
	public ManagerT login(String managerName,String managerPassword);
	/**
	 * ��������Ա
	 * @param managerT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int save(ManagerT managerT);
	/**
	 * ���ݹ���Ա���ɾ��һ������Ա
	 * @param managerId
	 * @return 0��ɾ���ɹ� 1��ɾ��ʧ��
	 */
	public int removeOne(Integer managerId);
	/**
	 * ɾ��һ�����Ա
	 * @param managerIds
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int removeManyByIds(Integer[] managerIds);
	/**
	 * �޸Ĺ���Ա
	 * @param managerT
	 * @return 0-�����޸�ʧ�ܣ�1-�����޸ĳɹ�
	 */
	public int modify(ManagerT managerT);
	/**
	 * (��¼ҵ��)���ݹ���Ա����������(managerT)����һ������Ա
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT getOneManager(ManagerT managerT);
	/**
	 * �鿴���й���Ա
	 * @return List<ManagerT>
	 */
	public List<ManagerT> getAllManager();
}

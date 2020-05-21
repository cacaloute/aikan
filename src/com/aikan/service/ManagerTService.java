package com.aikan.service;

import java.util.List;

import com.aikan.entity.ManagerT;

public interface ManagerTService {
	/**
	 * 管理员登录业务
	 * @param managerName
	 * @param managerPassword
	 * @return ManagerT
	 */
	public ManagerT login(String managerName,String managerPassword);
	/**
	 * 新增管理员
	 * @param managerT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int save(ManagerT managerT);
	/**
	 * 根据管理员编号删除一个管理员
	 * @param managerId
	 * @return 0：删除成功 1：删除失败
	 */
	public int removeOne(Integer managerId);
	/**
	 * 删除一组管理员
	 * @param managerIds
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int removeManyByIds(Integer[] managerIds);
	/**
	 * 修改管理员
	 * @param managerT
	 * @return 0-代表修改失败；1-代表修改成功
	 */
	public int modify(ManagerT managerT);
	/**
	 * (登录业务)根据管理员姓名和密码(managerT)查找一个管理员
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT getOneManager(ManagerT managerT);
	/**
	 * 查看所有管理员
	 * @return List<ManagerT>
	 */
	public List<ManagerT> getAllManager();
}

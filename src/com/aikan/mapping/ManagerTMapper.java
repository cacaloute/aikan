package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import com.aikan.entity.ManagerT;

public interface ManagerTMapper extends Serializable {
	/**
	 * 新增管理员
	 * @param managerT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insert(ManagerT managerT);
	/**
	 * 根据管理员编号删除一个管理员
	 * @param managerId
	 * @return 0：删除成功 1：删除失败
	 */
	public int deleteOne(Integer managerId);
	/**
	 * 删除一组管理员
	 * @param managerIds
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int deleteByIds(Integer[] managerIds);
	/**
	 * 修改管理员
	 * @param managerT
	 * @return 0-代表修改失败；1-代表修改成功
	 */
	public int update(ManagerT managerT);
	/**
	 * 根据管理员姓名和密码(managerT)查找一个管理员
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT selectOneManager(ManagerT managerT);
	/**
	 * 根据管理员编号查找一个管理员
	 * @param managerT
	 * @return ManagerT
	 */
	public ManagerT selectOneManagerById(Integer managerId);
	/**
	 * 根据管理员姓名查找一个管理员
	 * @param managerName
	 * @return ManagerT
	 */
	public ManagerT selectOneManagerByName(String managerName);
	/**
	 * 查看所有管理员
	 * @return List<ManagerT>
	 */
	public List<ManagerT> selectAllManager();

}

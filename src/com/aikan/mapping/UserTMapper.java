package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.UserT;
import com.aikan.entity.UserTCondition;

/**
 * 
 * @author Administrator
 *
 */
public interface UserTMapper {
	
	/**
	 * 根据用户编号查找用户
	 * @param userId
	 * @return UserT
	 */
	public UserT select(Integer userId);
	/**
	 * 查找所有用户
	 * @return
	 */
	public List<UserT> selectAll();
	/**
	 * 根据用户姓名，查找用户
	 * @param userName
	 * @return UserT
	 */
	public UserT selectOneUserTByName(String userName);
	/**
	 * 添加新用户
	 * @param userT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insert(UserT userT);
	/**
	 * 更新（编辑）用户信息
	 * @param userT
	 * @return -1:原id(用户)不存在；0：更新失败；1：更新成功
	 */
	public int update(UserT userT);
	/**
	 * 更新老用户头像信息
	 * @param userT
	 * @return -1:原id(用户)不存在；0：更新失败；1：更新成功
	 */
	public int updateImg(UserT userT);
	/**
	  * 根据主键，删除用户
	  * @param userId
	  * @return -1:原id(用户)不存在；0：删除失败；1：删除成功
	  */
	 public int delete(Integer userId);
	/**
	 * 根据用户名Id删除一组用户
	 * @param userIds
	 * @return int
	 */
	public int deleteByIds(Integer[] userIds);
	 /**
	  * 多条件复合分页查询
	  * @param condition
	  * @return
	  */
	 public List<UserT> selectByCondition(UserTCondition userTcondition);
	 /**
	  * 根据条件统计出符合的记录数
	  * @param condition
	  * @return int
	  */
	 public int count(UserTCondition userTcondition);
	 /**
	 * 根据主键，查询用户数量
	 * @param userIds
	 * @return int
	 */
	public int countByIds(Integer[] userIds);
	/**
	 * 根据用户性别统计人数
	 * @return int
	 */
	public int countUserBySex(String sex);
}

package com.aikan.service;
import java.util.List;

import com.aikan.entity.UserT;

public interface UserTService {
	/**
	 * 用户登录
	 * @param userName
	 * @param userPassword
	 * @return UserT
	 */
	public UserT login(String userName,String userPassword);
	/**
	 * 根据用户编号查找用户<
	 * @param userId
	 * @return UserT 如果查不到，则返回null
	 */
	public UserT get(Integer userId);
	/**
	 * 取得所有的用户
	 * @return List<UserT>
	 */
	public List<UserT> getAllUserTs();
	/**
	 * 根据用户姓名查找用户<
	 * @param userName
	 * @return UserT 如果查不到，则返回null
	 */
	public UserT getUserTByName(String userName);
	/**
	 * 根据用户性别统计人数
	 * @return List<Integer>
	 */
	public List<Integer> countUserBySex();
	
	/**
	 * 添加新用户<
	 * @param userT
	 * @return -1:原id(用户)已经存在；0：添加失败；1：添加成功
	 */
    public int save(UserT userT);

    /**
     * 更新（编辑）老用户信息
     * @param userT<
     * @return -1:原id(用户)不存在；0：更新失败；1：更新成功
     */
    public int modify(UserT userT);
    
    /**
     * 更新老用户头像信息
     * @param userT
     * @return -1:原id(用户)不存在；0：更新失败；1：更新成功
     */
    public int modifyImg(UserT userT);
    
    /**
     * 根据主键删除用户信息
     * @param userId
     * @return  -1:原id(用户)不存在；0：删除失败；1：删除成功
     */
    public int remove(Integer userId);
    /**
     * 根据用户名Id 删除一组用户<
     * @param userIds
     * @return  0：删除失败；非0：删除成功
     */
    public int removeMany(Integer[] userIds);
    
}

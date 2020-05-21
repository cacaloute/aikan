package com.aikan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.UserT;
import com.aikan.entity.UserTCondition;
import com.aikan.mapping.UserTMapper;
import com.aikan.service.UserTService;
import com.aikan.util.MyMD5Util;

@Service("userTServiceImpl")
public class UserTServiceImpl implements UserTService {
	@Autowired
	private UserTMapper userTMapper;
	

	@Override
	public UserT login(String userName, String userPassword) {
		UserT userTExist=userTMapper.selectOneUserTByName(userName);
		if(userTExist!=null){
			//从数据库中得到的用户 密码 解密
			String dpass=MyMD5Util.decode(userTExist.getUserPassword());
			if(dpass.equals(userPassword)){
				return userTExist;
			}
		}
		return null;
	}

	@Override
	public UserT get(Integer userId) {
		
		return userTMapper.select(userId);
	}

	@Override
	public int save(UserT userT) {
		int result=0;
		UserT userTExist=userTMapper.selectOneUserTByName(userT.getUserName());
		if(userTExist!=null){
			return -1;
		}
		UserTCondition userTcondition=new UserTCondition();
		int userId=userTMapper.count(userTcondition);
		userT.setUserId(userId+1);
		result=userTMapper.insert(userT);
		return result;
	}

	@Override
	public int modify(UserT userT) {
		int result=0;
		UserT userExist=userTMapper.select(userT.getUserId());
		if(userExist==null){
			return -1;
		}
		//修改基本信息时，页面提交的数据中userT不涉及头像image的值
		userT.setUserImg(userExist.getUserImg());
		result=userTMapper.update(userT);
		return result;
	}

	@Override
	public int modifyImg(UserT userT) {
		int result=0;
		UserT userExist=userTMapper.select(userT.getUserId());
		if(userExist==null){
			return -1;
		}
		//修改头像信息时，页面提交的数据中user只涉及头像image的值
		userExist.setUserImg(userT.getUserImg());
		
		result=userTMapper.update(userExist);
		return result;
	}

	@Override
	public int remove(Integer userId) {
		int result=0;
		// 先检查userT的userId是否已经存在，如果存在了，则删除；如果不存在，不能删除
		// 根据传递进来的用户对象的id，去数据库中查找这个id的用户是否存在
		UserT userExist=userTMapper.select(userId);
		if(userExist==null){
			return -1;
		}
		result=userTMapper.delete(userId);
		return result;
	}

	@Override
	public int removeMany(Integer[] userIds) {
		int result=0;
		int count=0;
		int sum=0;
		for(int j=0;j<userIds.length;j++){
			count=userTMapper.select(userIds[j])==null?0:1;
			sum=sum+count;
		}
		if(sum!=userIds.length){
			//准备被删除的用户数和数据库中实际存在的用户人数不一致，不能做删除操作
			return -1;
		}
		result=userTMapper.deleteByIds(userIds);
		if(result==0){
			//删除失败
			return 0;
		}
		if(result!=userIds.length){
			//部分删除成功
			return 1;
		}
		//全部删除成功
		return 2;

	}

	@Override
	public UserT getUserTByName(String userName) {
		UserT userT=userTMapper.selectOneUserTByName(userName);
		return userT;
	}

	@Override
	public List<UserT> getAllUserTs() {
		List<UserT> lists=userTMapper.selectAll();
		return lists;
	}

	@Override
	public List<Integer> countUserBySex() {
		Integer mNums=userTMapper.countUserBySex("男");
		Integer wNums=userTMapper.countUserBySex("女");
		List<Integer> lists=new ArrayList<>();
		lists.add(mNums);
		lists.add(wNums);
		return lists;
	}


}

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
			//�����ݿ��еõ����û� ���� ����
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
		//�޸Ļ�����Ϣʱ��ҳ���ύ��������userT���漰ͷ��image��ֵ
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
		//�޸�ͷ����Ϣʱ��ҳ���ύ��������userֻ�漰ͷ��image��ֵ
		userExist.setUserImg(userT.getUserImg());
		
		result=userTMapper.update(userExist);
		return result;
	}

	@Override
	public int remove(Integer userId) {
		int result=0;
		// �ȼ��userT��userId�Ƿ��Ѿ����ڣ���������ˣ���ɾ������������ڣ�����ɾ��
		// ���ݴ��ݽ������û������id��ȥ���ݿ��в������id���û��Ƿ����
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
			//׼����ɾ�����û��������ݿ���ʵ�ʴ��ڵ��û�������һ�£�������ɾ������
			return -1;
		}
		result=userTMapper.deleteByIds(userIds);
		if(result==0){
			//ɾ��ʧ��
			return 0;
		}
		if(result!=userIds.length){
			//����ɾ���ɹ�
			return 1;
		}
		//ȫ��ɾ���ɹ�
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
		Integer mNums=userTMapper.countUserBySex("��");
		Integer wNums=userTMapper.countUserBySex("Ů");
		List<Integer> lists=new ArrayList<>();
		lists.add(mNums);
		lists.add(wNums);
		return lists;
	}


}

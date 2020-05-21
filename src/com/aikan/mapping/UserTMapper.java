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
	 * �����û���Ų����û�
	 * @param userId
	 * @return UserT
	 */
	public UserT select(Integer userId);
	/**
	 * ���������û�
	 * @return
	 */
	public List<UserT> selectAll();
	/**
	 * �����û������������û�
	 * @param userName
	 * @return UserT
	 */
	public UserT selectOneUserTByName(String userName);
	/**
	 * ������û�
	 * @param userT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(UserT userT);
	/**
	 * ���£��༭���û���Ϣ
	 * @param userT
	 * @return -1:ԭid(�û�)�����ڣ�0������ʧ�ܣ�1�����³ɹ�
	 */
	public int update(UserT userT);
	/**
	 * �������û�ͷ����Ϣ
	 * @param userT
	 * @return -1:ԭid(�û�)�����ڣ�0������ʧ�ܣ�1�����³ɹ�
	 */
	public int updateImg(UserT userT);
	/**
	  * ����������ɾ���û�
	  * @param userId
	  * @return -1:ԭid(�û�)�����ڣ�0��ɾ��ʧ�ܣ�1��ɾ���ɹ�
	  */
	 public int delete(Integer userId);
	/**
	 * �����û���Idɾ��һ���û�
	 * @param userIds
	 * @return int
	 */
	public int deleteByIds(Integer[] userIds);
	 /**
	  * ���������Ϸ�ҳ��ѯ
	  * @param condition
	  * @return
	  */
	 public List<UserT> selectByCondition(UserTCondition userTcondition);
	 /**
	  * ��������ͳ�Ƴ����ϵļ�¼��
	  * @param condition
	  * @return int
	  */
	 public int count(UserTCondition userTcondition);
	 /**
	 * ������������ѯ�û�����
	 * @param userIds
	 * @return int
	 */
	public int countByIds(Integer[] userIds);
	/**
	 * �����û��Ա�ͳ������
	 * @return int
	 */
	public int countUserBySex(String sex);
}

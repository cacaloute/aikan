package com.aikan.service;
import java.util.List;

import com.aikan.entity.UserT;

public interface UserTService {
	/**
	 * �û���¼
	 * @param userName
	 * @param userPassword
	 * @return UserT
	 */
	public UserT login(String userName,String userPassword);
	/**
	 * �����û���Ų����û�<
	 * @param userId
	 * @return UserT ����鲻�����򷵻�null
	 */
	public UserT get(Integer userId);
	/**
	 * ȡ�����е��û�
	 * @return List<UserT>
	 */
	public List<UserT> getAllUserTs();
	/**
	 * �����û����������û�<
	 * @param userName
	 * @return UserT ����鲻�����򷵻�null
	 */
	public UserT getUserTByName(String userName);
	/**
	 * �����û��Ա�ͳ������
	 * @return List<Integer>
	 */
	public List<Integer> countUserBySex();
	
	/**
	 * ������û�<
	 * @param userT
	 * @return -1:ԭid(�û�)�Ѿ����ڣ�0�����ʧ�ܣ�1����ӳɹ�
	 */
    public int save(UserT userT);

    /**
     * ���£��༭�����û���Ϣ
     * @param userT<
     * @return -1:ԭid(�û�)�����ڣ�0������ʧ�ܣ�1�����³ɹ�
     */
    public int modify(UserT userT);
    
    /**
     * �������û�ͷ����Ϣ
     * @param userT
     * @return -1:ԭid(�û�)�����ڣ�0������ʧ�ܣ�1�����³ɹ�
     */
    public int modifyImg(UserT userT);
    
    /**
     * ��������ɾ���û���Ϣ
     * @param userId
     * @return  -1:ԭid(�û�)�����ڣ�0��ɾ��ʧ�ܣ�1��ɾ���ɹ�
     */
    public int remove(Integer userId);
    /**
     * �����û���Id ɾ��һ���û�<
     * @param userIds
     * @return  0��ɾ��ʧ�ܣ���0��ɾ���ɹ�
     */
    public int removeMany(Integer[] userIds);
    
}

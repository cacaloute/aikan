package com.aikan.service;

import java.util.List;

import com.aikan.entity.Catalog;

public interface CatalogService {
	/**
	 * ����ͼ��Ŀ¼
	 * @param catalog
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int save(Catalog catalog);
	/**
	 * ����Ŀ¼��ź��½ڱ�ţ���������������һ��Ŀ¼��¼��ĳһ�£�
	 * @param catalog
	 * @return Catalog
	 */
	public Catalog getOneById(Catalog catalog);
	/**
	 * ����Ŀ¼��id���������е��½�
	 * @param catalogId
	 * @return List<Catalog>
	 */
	public List<Catalog> getAllChapter(Integer catalogId);
	/**
	 * �޸ģ��༭��ĳһ��Ŀ¼��¼��һ�£�
	 * @param catalog
	 * @return 0���޸�ʧ�� 1���޸ĳɹ�
	 */
	public int modify(Catalog catalog);
	/**
	 * ɾ��һ��Ŀ¼��¼��һ�£�
	 * @param catalog
	 * @return -1��������  0��ɾ��ʧ��   1��ɾ���ɹ�
	 */
	public int removeOne(Catalog catalog);
	/**
	 * �����½ڱ��ɾ��һ���½�
	 * @param bookChapterIds
	 * @param catalogId
	 * @return  0-����ɾ��ʧ�ܣ�<br/>
	 * 			1-����ɾ���ɹ���<br/>
	 */
	public int removeManyChapter(Integer[] bookChapterIds,Integer catalogId);

}

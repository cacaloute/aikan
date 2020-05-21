package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.Catalog;

public interface CatalogMapper extends Serializable {
	/**
	 * ����ͼ��Ŀ¼
	 * @param catalog
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(Catalog catalog);
	/**
	 * ����Ŀ¼��ź��½ڱ�ţ���������������һ��Ŀ¼��¼��ĳһ�£�
	 * @param catalog
	 * @return Catalog
	 */
	public Catalog selectOneById(Catalog catalog);
	/**
	 * ����Ŀ¼��id���������е��½�
	 * @param catalogId
	 * @return List<Catalog>
	 */
	public List<Catalog> selectAllChapter(Integer catalogId);
	/**
	 * �޸ģ��༭��ĳһ��Ŀ¼��¼��һ�£�
	 * @param catalog
	 * @return 0���޸�ʧ�� 1���޸ĳɹ�
	 */
	public int update(Catalog catalog);
	/**
	 * ɾ��һ��Ŀ¼��¼��һ�£�
	 * @param catalog
	 * @return -1��������  0��ɾ��ʧ��   1��ɾ���ɹ�
	 */
	public int deleteOne(Catalog catalog);
	/**
	 * �����½ڱ��ɾ��һ���½�
	 * @param bookChapterIds
	 * @return  0-����ɾ��ʧ�ܣ�<br/>
	 * 			1-����ɾ���ɹ���<br/>
	 */
	public int deleteManyChapter(@Param(value="bookChapterIds")Integer[] bookChapterIds,@Param(value="catalogId")Integer catalogId);
}

package com.aikan.service;

import com.aikan.entity.Record;

/**
 * 阅读记录业务层 recordService
 * @author msh10312
 *
 */
public interface RecordService {

	/**
	 * 更新一条旧的阅读记录
	 * @param Record
	 * @return 0：阅读记录存在，但更新失败
	 *         1：阅读记录存在，且更新成功
	 *         2：阅读记录不存在，新增失败
	 *         3：阅读记录不存在，新增成功
	 */
	public int modifyOldRecord(Record oldRecord);
	/**
	 * 通过用户名，书籍名查找阅读记录
	 * @param record
	 * @return
	 */
	public Record getRecord(Record record);
}

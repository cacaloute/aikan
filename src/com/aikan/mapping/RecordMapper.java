package com.aikan.mapping;

import com.aikan.entity.Record;


public interface RecordMapper {
    /**
     * 得到最大阅读记录编号
     * @return
     */
    public int selectMaxId();
	/**
	 * 通过用户名，书籍名查找阅读记录
	 * @param record
	 * @return Record
	 */
    public Record selectByIds(Record record);
    /**
     * 更新阅读记录
     * @param record
     * @return
     */
    public int updateRecord(Record updateRecord);
    /**
     * 添加新的阅读记录
     * @param record
     * @return
     */
    public int insertRecord(Record newRecord);
}

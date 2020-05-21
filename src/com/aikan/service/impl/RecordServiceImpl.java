package com.aikan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.Record;
import com.aikan.mapping.RecordMapper;
import com.aikan.service.RecordService;

@Service("recordServiceImpl")
public class RecordServiceImpl implements RecordService{
    
    @Autowired
    private RecordMapper recordMapper;
    
    /*
    @Override
    public int saveNewRecord(Record newRecord){
        Record recordExist=recordMapper.selectByIds(newRecord);
        if(recordExist!=null){
            return -1;
        }
        //设置阅读记录编号
        Integer recordId=recordMapper.selectMaxId()+1;
        newRecord.setRecordId(recordId);
        
        int result=recordMapper.insertRecord(newRecord);
        return result;
    }
   */
    
    /**
     * 更新一条旧的阅读记录，没有则创建
     */
    @Override
    public int modifyOldRecord(Record oldRecord){
        //先根据记录的用户id和书籍id查找该记录
        Record recordExist=recordMapper.selectByIds(oldRecord);
        
        //阅读记录不存在，则新建
        if(recordExist==null){
            Integer recordId=recordMapper.selectMaxId()+1;
            oldRecord.setRecordId(recordId);
            int result=recordMapper.insertRecord(oldRecord);
            //返回（0,1）--》2,3
            return result+2;
        }
        //阅读记录存在，则直接修改
        int result=recordMapper.updateRecord(oldRecord);
        return result;
    }

    @Override
    public Record getRecord(Record record){
        Record getRecord=recordMapper.selectByIds(record);
        return getRecord;
    }
}

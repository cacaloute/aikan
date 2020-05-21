package com.aikan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.AuthorT;
import com.aikan.mapping.AuthorTMapper;
import com.aikan.service.AuthorTService;

@Service("authorTServiceImpl")
public class AuthorTServiceImpl implements AuthorTService {
	//@Autowired,自动绑定运行期间，spring构建的authorTMapper接口对象，无需手动new
	@Autowired
	private AuthorTMapper authorTMapper;

	@Override
	public int save(AuthorT authorT) {
		int result=0;
		AuthorT authorExist=authorTMapper.selectId(authorT.getAuthorId());
		if(authorExist!=null){
			return -1;
		}
		result=authorTMapper.insert(authorT);
		return result;
	}

	@Override
	public int remove(Integer authorId) {
		int result = 0;
		AuthorT authorExist = authorTMapper.selectId(authorId);
		if(authorExist==null){
			return -1;
		}
		result = authorTMapper.delete(authorId);
		return result;
	}

	@Override
	public int removeManyByIds(Integer[] authorIds) {
		int result = 0;
		int count=0;
		int sum=0;
		for(int j=0;j<authorIds.length;j++){
			count=authorTMapper.selectId(authorIds[j])==null?0:1;
			sum=sum+count;
		}
		if(sum!=authorIds.length){
			//准备被删除的员工数和数据库中实际存在的作者数不一致，不能做删除工作
			return -1;
		}
		result = authorTMapper.deleteManyByIds(authorIds);
		if(result==0){
			//删除失败
			return 0;
		}
		if(result!=authorIds.length){
			//部分删除成功
			return 1;
		}
		//全部删除成功
		return 2;
	}

	@Override
	public int modify(AuthorT authorT) {
		int result=0;
		AuthorT authorExist=authorTMapper.selectId(authorT.getAuthorId());
		if(authorExist==null){
			return -1;
		}
		result = authorTMapper.update(authorT);
		return result;
	}

	@Override
	public AuthorT getById(Integer authorId) {
		
		return authorTMapper.selectId(authorId);
	}

	@Override
	public AuthorT getByName(String authorName) {
		
		return authorTMapper.selectName(authorName);
	}

	@Override
	public int authorNameToId(String authorName) {
		AuthorT authorTExist=authorTMapper.selectName(authorName);
		if(authorTExist!=null){
			return authorTExist.getAuthorId();
		}
		int count=authorTMapper.countAuthorT()+1;	
		
		AuthorT authorT=new AuthorT();
		authorT.setAuthorId(count);
		authorT.setAuthorName(authorName);
		authorT.setAuthorImg("/a/a.PNG");
		authorT.setAuthorInfor("作者介绍！");
		authorT.setAuthorWordNums(5000000);
		authorT.setAuthorWorksNums(10);
		authorT.setFansNums(1000000);
		
		authorTMapper.insert(authorT);
		
		return count;
	}

	@Override
	public List<AuthorT> getAllAuthorTs() {
		List<AuthorT> authorTs=authorTMapper.selectAllAuthorTs();
		return authorTs;
	}

}

package com.aikan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.CommentT;
import com.aikan.entity.UserT;
import com.aikan.mapping.CommentTMapper;
import com.aikan.mapping.UserTMapper;
import com.aikan.service.CommentTService;

@Service("commentTServiceImpl")
public class CommentTServiceImpl implements CommentTService {
	
	@Autowired
	private CommentTMapper commentTMapper;
	@Autowired
	private UserTMapper userTMapper;
	
	//添加评论
	@Override
	public int save(CommentT commentT) {
		int result=0;
		//设置评论编号
		if(commentT.getCommentId()==null){
			Integer commentId=commentTMapper.maxComments()+1;
			System.out.println("commentId:"+commentId);
			commentT.setCommentId(commentId);
		}
		
		CommentT commentTExist=commentTMapper.selectOneCommentByCommentConnent(commentT);
		if(commentTExist!=null){
			return -1;
		}
		
		result= commentTMapper.insert(commentT);
		return result;
	}
	//添加一条帖子
	@Override
	public int saveTiezi(CommentT commentT) {
		int result=0;
		//设置帖子编号
		if(commentT.getCommentId()==null){
			Integer commentId=commentTMapper.maxComments()+1;
			System.out.println("commentId:"+commentId);
			commentT.setCommentId(commentId);
		}
		//根据帖子标题查找帖子
		CommentT commentTExist=commentTMapper.selectOneCommentByCommentTitle(commentT);
		if(commentTExist!=null){
			return -1;		//帖子已存在
		}
		
		result= commentTMapper.insert(commentT);
		return result;
	}
	//删除一条评论
	@Override
	public int removeOne(Integer commentId) {
		int result=0;
		CommentT commentTExist=commentTMapper.selectOneCommentById(commentId);
		if(commentTExist==null){
			return -1;
		}
		result= commentTMapper.deleteOne(commentId);
		return result;
	}

	@Override
	public int removeManyByIds(Integer[] commentIds) {
		int result=0;
		result=commentTMapper.deleteByIds(commentIds);
		if(result==0){
			//删除失败
			return 0;
		}
		return 1;
	}

	@Override
	public int modify(CommentT commentT) {
		int result = 0;
		CommentT commentTExist=commentTMapper.selectOneCommentById(commentT.getCommentId());
		if(commentTExist==null){
			return -1;
		}
		result= commentTMapper.update(commentT);
		return result;
	}

	@Override
	public CommentT getOneCommentById(Integer commentId) {
		
		return commentTMapper.selectOneCommentById(commentId);
	}

	@Override
	public List<CommentT> getAllCommentsByBookId(Integer bookId) {
		//根据被评论书籍的Id,查询所有的评论
		return commentTMapper.selectAllCommentsBookId(bookId);
	}

	@Override
	public List<CommentT> getAllCommentsByParentId(Integer commentedId) {
		//根据被评论的评论Id,查询所有的评论
		return commentTMapper.selectAllCommentsByParentId(commentedId);
	}
	
	//查找所有帖子及子回复
	@Override
	public List<CommentT> getAllCommentsByCommentDiv(String commentdiv) {
		//根据评论区名称查找帖子
		List<CommentT> commentTs=commentTMapper.selectAllCommentsByCommentDiv(commentdiv);
		if(commentTs==null){
			return null;
		}
		for(int i=0;i<commentTs.size();i++){
			CommentT tiezi=commentTs.get(i);
			//帖子的id即为其底下回复的父id
			Integer parentId=tiezi.getCommentId();
			List<CommentT> childCommentTs=commentTMapper.selectAllCommentsByParentId(parentId);
			if(childCommentTs!=null){
				for(int j=0;j<childCommentTs.size();j++){
					CommentT commentChild=childCommentTs.get(j);
					Integer childUserId=commentChild.getUserId();
					
					UserT childUserT=userTMapper.select(childUserId);
					childCommentTs.get(j).setUserT(childUserT);
				}
				//将帖子的所有子回复（子评论），设置给相应的帖子
				commentTs.get(i).setChildComments(childCommentTs);
			}
			
		}
		return commentTs;
	}
	@Override
	public List<CommentT> getAllComments() {
		List<CommentT> lists=commentTMapper.selectAllComments();
		return lists;
	}

	

}

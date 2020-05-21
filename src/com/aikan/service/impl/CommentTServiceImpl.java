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
	
	//�������
	@Override
	public int save(CommentT commentT) {
		int result=0;
		//�������۱��
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
	//���һ������
	@Override
	public int saveTiezi(CommentT commentT) {
		int result=0;
		//�������ӱ��
		if(commentT.getCommentId()==null){
			Integer commentId=commentTMapper.maxComments()+1;
			System.out.println("commentId:"+commentId);
			commentT.setCommentId(commentId);
		}
		//�������ӱ����������
		CommentT commentTExist=commentTMapper.selectOneCommentByCommentTitle(commentT);
		if(commentTExist!=null){
			return -1;		//�����Ѵ���
		}
		
		result= commentTMapper.insert(commentT);
		return result;
	}
	//ɾ��һ������
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
			//ɾ��ʧ��
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
		//���ݱ������鼮��Id,��ѯ���е�����
		return commentTMapper.selectAllCommentsBookId(bookId);
	}

	@Override
	public List<CommentT> getAllCommentsByParentId(Integer commentedId) {
		//���ݱ����۵�����Id,��ѯ���е�����
		return commentTMapper.selectAllCommentsByParentId(commentedId);
	}
	
	//�����������Ӽ��ӻظ�
	@Override
	public List<CommentT> getAllCommentsByCommentDiv(String commentdiv) {
		//�������������Ʋ�������
		List<CommentT> commentTs=commentTMapper.selectAllCommentsByCommentDiv(commentdiv);
		if(commentTs==null){
			return null;
		}
		for(int i=0;i<commentTs.size();i++){
			CommentT tiezi=commentTs.get(i);
			//���ӵ�id��Ϊ����»ظ��ĸ�id
			Integer parentId=tiezi.getCommentId();
			List<CommentT> childCommentTs=commentTMapper.selectAllCommentsByParentId(parentId);
			if(childCommentTs!=null){
				for(int j=0;j<childCommentTs.size();j++){
					CommentT commentChild=childCommentTs.get(j);
					Integer childUserId=commentChild.getUserId();
					
					UserT childUserT=userTMapper.select(childUserId);
					childCommentTs.get(j).setUserT(childUserT);
				}
				//�����ӵ������ӻظ��������ۣ������ø���Ӧ������
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

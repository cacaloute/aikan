package com.aikan.mapping;

import java.util.List;
import java.util.Map;
import com.aikan.entity.AuthorizationT;

public interface AuthorizationTMapper {
	public List<AuthorizationT> selectAll();
	public List<AuthorizationT> selectByParentId(Integer parentId);
	public List<AuthorizationT> selectByParentIdAndRoleId(Map<String,Integer> params);
}

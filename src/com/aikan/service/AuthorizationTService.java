package com.aikan.service;

import java.util.List;
import com.aikan.entity.AuthorizationT;

public interface AuthorizationTService {
	public List<AuthorizationT> searchAll();
	public List<AuthorizationT> searchAllByRoleId(Integer roleId);
}

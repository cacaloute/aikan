package com.aikan.group;

//根据业务操作，人为地分组
public interface UserGroup {
	public static interface Add{}
	public static interface Delete{}
	public static interface Update{}
	public static interface Get{}
	public static interface Search{}
}

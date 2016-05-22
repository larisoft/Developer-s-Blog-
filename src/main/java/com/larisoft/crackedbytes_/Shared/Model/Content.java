package com.larisoft.crackedbytes_.Shared.Model;

import com.larisoft.crackedbytes_.User.Service.UserService;

//makes articles, bugs, and apps equally displayable to user in the front end...as though they were one object type
public interface Content extends Comparable<Content>{

	public String getTitle();
	public String getAge();
	public String getAuthorForDisplay(UserService service);
	public int getAuthorAssetId(UserService service);
	public long getDate();
	public String getContent();
	public String getFriendlyUrl();
	public String getAuthorUsername(UserService service);
	
}

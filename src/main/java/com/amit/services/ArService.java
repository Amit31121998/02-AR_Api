package com.amit.services;

import java.util.List;

import com.amit.binding.App;

public interface ArService {
	
	public String createApplication(App app);

	public List<App> fetchApps(Integer userId);
}

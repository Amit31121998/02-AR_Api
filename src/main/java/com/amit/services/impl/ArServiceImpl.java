package com.amit.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.amit.binding.App;
import com.amit.constants.AppConstants;
import com.amit.entity.AppEntity;
import com.amit.entity.UserEntity;
import com.amit.exceptions.SsaWebException;
import com.amit.repository.AppRepo;
import com.amit.repository.UserRepo;
import com.amit.services.ArService;

@Service
public class ArServiceImpl implements ArService {

	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private UserRepo userRepo;

	private static final String SSA_WEB_URL = "";

	@Override
	public String createApplication(App app) {
		try {
			WebClient webClient = WebClient.create();

			String state = webClient.get().uri(SSA_WEB_URL, app.getSsn()).retrieve().bodyToMono(String.class).block();

			if ("RI".equals(state)) {
				
				UserEntity userEntity = userRepo.findById(app.getUserId()).get();
				
				AppEntity entity = new AppEntity();
				entity.setUser(userEntity);
				BeanUtils.copyProperties(app, entity);
				entity = appRepo.save(entity);
				return "App created with caseNo :" + entity.getCaseNum();
			}
		} catch (Exception e) {
			throw new SsaWebException(e.getMessage());
		}

		return AppConstants.INVALID_SSN;
	}

	@Override
	public List<App> fetchApps(Integer userId) {
		
		UserEntity userEntity = userRepo.findById(userId).get();
		
		Integer rollId = userEntity.getRollId();
		
		List<AppEntity> appEntities=null;
		if(1==rollId) {
			appEntities=appRepo.fetchUserApps();
		}
		else {
			appRepo.fetchCwApps(userId);
		}
		List<App> apps=new ArrayList<>();
		
		for(AppEntity entity:appEntities) {
			App app=new App();
			BeanUtils.copyProperties(entity, apps);
			apps.add(app);
		}
		return apps;
	}

}

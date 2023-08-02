package com.amit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amit.entity.AppEntity;

public interface AppRepo extends JpaRepository<AppEntity, Integer> {
	
	@Query(value = "from AppEntity")
	public List<AppEntity> fetchUserApps();

	
	@Query(value = "from AppEntity where userId = :userId")
    public List<AppEntity> fetchCwApps(@Param("userId") Integer userId);
}

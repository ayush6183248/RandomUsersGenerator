package com.nagarro.randomuserprofilefetcher.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.randomuserprofilefetcher.entities.RandomUsersInfo;

@Repository
public interface RandomUsersInformationRepository extends JpaRepository<RandomUsersInfo, Integer> {

//	@Query(value = "SELECT * FROM RandomUsersInfo OFFSET ?1 ROWS FETCH NEXT ?2 ROWS ONLY", nativeQuery = true)
//	List<RandomUsersInfo> findAllWithLimitAndOffset(int offset, int limit);    
	
	@Query(value = "SELECT * FROM random_users_info LIMIT :limit OFFSET :offset", nativeQuery = true)
	List<RandomUsersInfo> findAllWithLimitAndOffset(@Param("offset") int offset, @Param("limit") int limit);
	
	public long count();
}

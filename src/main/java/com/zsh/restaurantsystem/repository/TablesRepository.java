package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TablesRepository extends CustomizedRepoistory<Tables, Integer> {
    @Query("SELECT t FROM Tables t WHERE t.no=:no")
    Tables findNo(@Param("no")int no);
}

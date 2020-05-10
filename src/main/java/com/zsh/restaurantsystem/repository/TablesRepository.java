package com.zsh.restaurantsystem.repository;

import com.zsh.restaurantsystem.entity.TableNum;
import com.zsh.restaurantsystem.entity.Tables;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TablesRepository extends CustomizedRepoistory<Tables, Integer> {
    @Query("SELECT t FROM Tables t WHERE t.no=:no")
    Tables findNo(@Param("no") int no);

    @Query("SELECT NEW com.zsh.restaurantsystem.entity.TableNum(t.num, COUNT (t.id)) " +
            "FROM Tables t GROUP BY t.num")
    List<TableNum> findTableNumBy();

    @Query("SELECT NEW com.zsh.restaurantsystem.entity.TableNum(t.num, COUNT (t.id)) " +
            "FROM Tables t WHERE t.statue=0 GROUP BY t.num")
    List<TableNum> findTableNumByStatue();

    @Query("SELECT t FROM Tables t WHERE t.num=:num AND t.statue=0")
    List<Tables> findOpenTableByNum(@Param("num")int num);
}

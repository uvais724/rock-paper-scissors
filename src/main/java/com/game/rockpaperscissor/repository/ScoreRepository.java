package com.game.rockpaperscissor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game.rockpaperscissor.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>{

	@Query("from Score order by id desc")
	public List<Score> findSecondLastRecord();
}

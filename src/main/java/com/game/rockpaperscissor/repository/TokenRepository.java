package com.game.rockpaperscissor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.rockpaperscissor.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	public Token findByToken(String token);
}

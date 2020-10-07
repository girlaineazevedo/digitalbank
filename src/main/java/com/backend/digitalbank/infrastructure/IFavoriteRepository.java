package com.backend.digitalbank.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.digitalbank.model.Favorite;

public interface IFavoriteRepository extends JpaRepository<Favorite, Integer>{

}

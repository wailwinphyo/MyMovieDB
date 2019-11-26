package com.hashtag.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hashtag.entity.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>{

}

package com.spring.myweb.rest;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IWeatherMapper {

	// 좌표 받기
	Map<String, String> getCoord(@Param("area1") String area1, @Param("area2")  String area2);

	
	
}

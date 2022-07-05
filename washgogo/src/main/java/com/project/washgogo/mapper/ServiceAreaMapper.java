package com.project.washgogo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServiceAreaMapper {
    public String selectProvince(String provinceName);
    public String selectCity(String cityName);
    public String selectNewTown(String newTownName);
    public String selectGu(String guName);
    public String selectDong(String dongName);
    public List<String> selectDongByCity(String cityName);
    public List<String> selectDongByNewTown(String newTownName);
    public List<String> selectDongByGu(String guName);
}

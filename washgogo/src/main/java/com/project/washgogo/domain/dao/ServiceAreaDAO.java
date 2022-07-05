package com.project.washgogo.domain.dao;

import com.project.washgogo.mapper.ServiceAreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ServiceAreaDAO {
    private final ServiceAreaMapper serviceAreaMapper;

    public String selectProvince(String provinceName){
        return serviceAreaMapper.selectProvince(provinceName);
    }
    public String selectCity(String cityName){
        return serviceAreaMapper.selectCity(cityName);
    }
    public String selectNewTown(String newTownName){
        return serviceAreaMapper.selectNewTown(newTownName);
    }
    public String selectGu(String guName){
        return serviceAreaMapper.selectGu(guName);
    }
    public String selectDong(String dongName){
        return serviceAreaMapper.selectDong(dongName);
    }
    public List<String> selectDongByCity(String cityName){
        return serviceAreaMapper.selectDongByCity(cityName);
    }
    public List<String> selectDongByNewTown(String newTownName){
        return serviceAreaMapper.selectDongByNewTown(newTownName);
    }
    public List<String> selectDongByGu(String guName){
        return serviceAreaMapper.selectDongByGu(guName);
    }
}

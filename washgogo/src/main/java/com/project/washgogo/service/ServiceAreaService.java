package com.project.washgogo.service;

import com.project.washgogo.domain.dao.ServiceAreaDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceAreaService {
    private final ServiceAreaDAO serviceAreaDAO;

    public String getProvince(String provinceName){
        return serviceAreaDAO.selectProvince(provinceName);
    }
    public String getCity(String cityName){
        return serviceAreaDAO.selectCity(cityName);
    }
    public String getNewTown(String newTownName){
        return serviceAreaDAO.selectNewTown(newTownName);
    }
    public String getGu(String guName){
        return serviceAreaDAO.selectGu(guName);
    }
    public String getDong(String dongName){
        return serviceAreaDAO.selectDong(dongName);
    }
    public List<String> getDongByCity(String cityName){
        return serviceAreaDAO.selectDongByCity(cityName);
    }
    public List<String> getDongByNewTown(String newTownName){
        return serviceAreaDAO.selectDongByNewTown(newTownName);
    }
    public List<String> getDongByGu(String guName){
        return serviceAreaDAO.selectDongByGu(guName);
    }

    public boolean isServiceArea(String address){
        StringBuilder addressBuffer = new StringBuilder(address);

        String provinceName = null;
        String cityName = null;
        String guName = null;
        String dongName = null;

        if(addressBuffer.charAt(0) == '"' && addressBuffer.charAt(addressBuffer.length()-1) == '"'){
            addressBuffer.deleteCharAt(0);
            addressBuffer.deleteCharAt(addressBuffer.length()-1);
        }
        String[] addressArr = addressBuffer.toString().split(" ");

        StringBuilder tempProvinceName = new StringBuilder(addressArr[0]);
        provinceName = getProvince(tempProvinceName.toString());

        if(provinceName != null){

            if(provinceName.equals("서울")){
                return true;
            } else if(provinceName.equals("인천")){
                StringBuilder tempGuName = new StringBuilder(addressArr[1]);
                StringBuilder tempDongName = new StringBuilder(addressArr[2]);
                tempGuName.substring(0, tempGuName.length()-1);
                tempDongName.substring(0, tempDongName.length()-1);
                cityName = provinceName;
                guName = getGu(tempGuName.toString());
                dongName = getDong(tempDongName.toString());
            } else if(provinceName.equals("경기")){
                StringBuilder tempStr = new StringBuilder(addressArr[2]);
                StringBuilder tempCityName = new StringBuilder(addressArr[1]);
                if(tempStr.indexOf("구")>=0){
                    StringBuilder tempGuName = new StringBuilder(addressArr[2]);
                    StringBuilder tempDongName = new StringBuilder(addressArr[3]);
                    tempGuName.substring(0, tempGuName.length()-1);
                    guName = getGu(tempGuName.toString());
                    tempDongName.substring(0, tempDongName.length()-1);
                    dongName = getDong(tempDongName.toString());
                } else if (tempStr.indexOf("동")>=0){
                    StringBuilder tempDongName = new StringBuilder(addressArr[2]);
                    tempDongName.substring(0, tempDongName.length()-1);
                    dongName = getDong(tempDongName.toString());
                }
                tempCityName.substring(0, tempCityName.length()-1);
                cityName = getCity(tempCityName.toString());
            }

            if(cityName == null){
                return false;
            }

            if (guName != null){
                if (dongName != null){
                    return true;
                } else {
                    if(getDongByGu(guName).get(0).equals("all")){
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (dongName != null) {
                    return true;
                } else {
                    if(getDongByCity(cityName).get(0).equals("all")){
                        return true;
                    } else {
                        return false;
                    }
                }
            }

        }
        return false;
    }
}

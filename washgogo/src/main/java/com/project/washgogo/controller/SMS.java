package com.project.washgogo.controller;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


public class SMS {
    public static void main(String[] args) {
        String api_key = "NCSHUXHNNINOL8AT";
        String api_secret = "IMDMEXAZSWQWO7OR993KMVDEXCOK0ZDV";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", "01088580291");    // 인증번호 받는 사람
        params.put("from", "01088580291");  // 인증번호 보내는 사람
        params.put("type", "SMS");  // 문자형태
        params.put("text", "[WashGoGo] 인증번호 ****를 입력하세요.");   // 보내는 문자
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}

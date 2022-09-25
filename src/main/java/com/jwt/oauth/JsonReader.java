package com.jwt.oauth;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonReader {
    public CertJsonBean readJsonFile(String inputFile) {
        ObjectMapper objectMapper;
        try {
            objectMapper = new ObjectMapper();
            CertJsonBean cert = objectMapper.readValue(new File(inputFile), CertJsonBean.class);
            return cert;
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }
}
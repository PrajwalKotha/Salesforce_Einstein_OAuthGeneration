package com.jwt.oauth;

import java.io.BufferedReader;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenCreator {
	
	public static void main(String[] args) throws Exception {
        
    }
	public static String createToken(String input) {
		JwtTokenCreator jwtTokenCreator = new JwtTokenCreator();
        JsonReader jsonReader = new JsonReader();
        CertJsonBean certJsonBean = jsonReader.readJsonFile(input);
        String token = jwtTokenCreator.generateToken(certJsonBean);
        return token;
	}
	public String generateToken(CertJsonBean certJsonBean) {
        String token = null;
        String privateKey;
        Map<String, Object> header = new HashMap<String, Object>();
        Map<String, Object> payload = new HashMap<String, Object>();
        try {
            privateKey = certJsonBean.privatekey;
            payload = certJsonBean.payload;
            header = certJsonBean.header;
            // get the private key from encoded key.
            
            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider()
            );
            PrivateKey pvtKey = getPrivateKey(privateKey);
            if (!certJsonBean.privatekey.isEmpty()) {
                token = Jwts.builder().setClaims(payload).setHeader(header).signWith(SignatureAlgorithm.RS256, pvtKey).compact();
            } else {
                token = "Something went wrong!!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
	private PrivateKey getPrivateKey(String privateKey) throws Exception {
        // Read in the key into a String
        StringBuilder pkcs8Lines = new StringBuilder();
        BufferedReader rdr = new BufferedReader(new StringReader(privateKey));
        String line;
        while ((line = rdr.readLine()) != null) {
            pkcs8Lines.append(line);
        }
        // Remove the "BEGIN" and "END" lines, as well as any whitespace
        String pkcs8Pem = pkcs8Lines.toString();
        pkcs8Pem = pkcs8Pem.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replace("-----END RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
        // Base64 decode the result
        byte[] pkcs8EncodedBytes = Base64.getDecoder().decode(pkcs8Pem);
        // extract the private key
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }
}


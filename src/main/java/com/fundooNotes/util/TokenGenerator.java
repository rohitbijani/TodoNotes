package com.fundooNotes.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import com.fundooNotes.exception.JwtException;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class TokenGenerator {
	
	final private String secretKey="hellowelcometofundoonotes";
	public String createJWT(String id, String issuer, String subject, long ttlMillis) {
		 
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
		//We will sign our JWT with our secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    return builder.compact();
	}
	
	public Integer parseJWT(String jwt) {
	    Integer id = null;
		try {
			Claims claims = Jwts.parser()         
				       .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
				       .parseClaimsJws(jwt).getBody();
			id=Integer.parseInt(claims.getId());
			
		} catch (ClaimJwtException | SignatureException | MalformedJwtException e) {
			throw new JwtException("JWT expired or signature validation error");
		}
		return id;
		 
	}
}

package com.fundooNotes.util;

import org.springframework.stereotype.Component;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisException;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Component
public class RedisUtil {
	
	public RedisCommands<String, String> syncRedis() {
		RedisCommands<String, String> syncCommands = null;
		
		try {
			RedisClient redisClient = RedisClient.create("redis://localhost:6379/");
			StatefulRedisConnection<String, String> connection = redisClient.connect();
			
			syncCommands = connection.sync();
		} catch (RedisException e) {
			e.printStackTrace();
		}
		
		return syncCommands;
	}
}

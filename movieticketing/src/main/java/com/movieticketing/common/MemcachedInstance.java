package com.movieticketing.common;

import org.springframework.stereotype.Component;

import com.movieticketing.memcached.MemcachedClient;
import com.movieticketing.memcached.SockIOPool;

@Component
public class MemcachedInstance {

	static MemcachedClient memcachedClient = null;

	static {
		System.out.println("Memcache server initialized");
		String[] servers = { "localhost:11111" };
		SockIOPool pool = SockIOPool.getInstance("MovieTicketing");
		pool.setServers(servers);
		pool.setFailover(true);
		pool.setInitConn(10);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setAliveCheck(true);
		pool.initialize();
		System.out.println("Pool initialized :)");
	}

	public static synchronized MemcachedClient getMemcachedMovieInstance() {
		if (memcachedClient == null) {
			memcachedClient = new MemcachedClient("MovieTicketing");
		}
		return memcachedClient;
	}
}

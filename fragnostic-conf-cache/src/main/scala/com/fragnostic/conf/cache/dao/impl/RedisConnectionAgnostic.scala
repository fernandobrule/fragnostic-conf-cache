package com.fragnostic.conf.cache.dao.impl

import redis.clients.jedis.Jedis

trait RedisConnectionAgnostic {

  val jedis: Jedis

}

package com.fragnostic.conf.cache.dao.impl

import io.lettuce.core.api.sync.RedisCommands

trait RedisConnectionAgnostic {

  val cache: RedisCommands[String, String]

}

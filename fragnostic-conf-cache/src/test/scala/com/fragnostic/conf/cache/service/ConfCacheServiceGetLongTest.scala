package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeCacheService.cacheServiceApi.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetLongTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyLong, valueLong)
  }

  override def afterEach(): Unit = {
    del(keyLong)
  }

  describe("Conf Service Get Long Test") {

    it("Can Get Value As Long from Cache") {

      val opt = CakeConfCacheService.confServiceApi.getLong(key = keyLong) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueLong)

    }

  }

}


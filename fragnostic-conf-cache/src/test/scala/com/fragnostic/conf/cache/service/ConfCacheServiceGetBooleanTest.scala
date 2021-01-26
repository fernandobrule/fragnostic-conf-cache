package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeCacheService.cacheServiceApi.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetBooleanTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyBoolean, valueBoolean)
  }

  override def afterEach(): Unit = {
    del(keyBoolean)
  }

  describe("Conf Cache Service Get Boolean Test") {

    it("Can Get Value As Boolean from Cache") {

      val opt = CakeConfCacheService.confServiceApi.getBoolean(key = keyBoolean) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueBoolean)

    }

  }

}


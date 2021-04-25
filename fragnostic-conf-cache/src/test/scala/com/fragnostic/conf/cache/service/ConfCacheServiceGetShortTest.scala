package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheServiceApi.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetShortTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyShort, valueShort)
  }

  override def afterEach(): Unit = {
    del(keyShort)
  }

  describe("***Conf Cache Service Get Short Test***") {

    it("Can Get Value As Short from Cache") {

      val opt = CakeConfCacheService.confCacheServiceApi.getShort(key = keyShort) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueShort)

    }

  }

}


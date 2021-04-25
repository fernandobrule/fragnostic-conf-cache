package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheServiceApi.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetIntTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyInt, valueInt)
  }

  override def afterEach(): Unit = {
    del(keyInt)
  }

  describe("***Conf Service Get Int Test***") {

    it("Can Get Value As Int from Cache") {

      val opt = CakeConfCacheService.confCacheServiceApi.getInt(key = keyInt) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      opt should not be None
      opt.get should be(valueInt)

    }

  }

}


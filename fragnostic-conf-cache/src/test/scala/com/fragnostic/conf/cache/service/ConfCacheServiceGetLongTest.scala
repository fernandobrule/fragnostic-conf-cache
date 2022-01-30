package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetLongTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {
    set(keyLong, valueLong)
  }

  override def afterEach(): Unit = {
    del(keyLong)
  }

  describe("***Conf Service Get Long Test***") {

    it("Can Get Value As Long from Cache") {

      val value = CakeConfCacheService.confCacheService.getLong(key = keyLong) fold (
        error => throw new IllegalStateException(error),
        value => value //
      )

      assertResult(valueLong)(value)

    }

    it("Can Not Get Value As Long from Cache") {

      val value = CakeConfCacheService.confCacheService.getLong(key = keyThatDoesNotExists) fold (
        error => error,
        value => value //
      )

      assertResult(valueThatNotExists)(value)

    }

  }

}


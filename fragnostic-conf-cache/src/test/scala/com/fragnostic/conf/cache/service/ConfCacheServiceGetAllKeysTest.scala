package com.fragnostic.conf.cache.service

import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.set
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

import java.util

class ConfCacheServiceGetAllKeysTest extends LifeCycleSupportCache {

  override def beforeEach(): Unit = {

    CakeConfCacheService.confCacheService.delAllKeys fold (
      error => throw new IllegalStateException(error),
      message => message)

    set("1", "1")
    set("2", "2")
    set("3", "3")
    set("4", "4")
    set("5", "5")
  }

  override def afterEach(): Unit = {
  }

  describe("***Conf Cache Service Get All Keys Test***") {

    it("Can Get All Keys") {

      val allKeys: util.List[String] = CakeConfCacheService.confCacheService.getAllKeys fold (
        error => throw new IllegalStateException(error),
        allKeys => allKeys)

      assertResult(allKeys.size())(5)
      assertResult(allKeys.contains("1"))(true)
      assertResult(allKeys.contains("2"))(true)
      assertResult(allKeys.contains("3"))(true)
      assertResult(allKeys.contains("4"))(true)
      assertResult(allKeys.contains("5"))(true)

    }

  }

}


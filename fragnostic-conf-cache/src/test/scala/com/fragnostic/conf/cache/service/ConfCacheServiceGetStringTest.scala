package com.fragnostic.conf.cache.service

import com.fragnostic.conf.base.service.support.KeyComposeSupport
import com.fragnostic.conf.cache.service.CakeConfCacheService.confCacheService.{ del, set }
import com.fragnostic.conf.cache.service.support.LifeCycleSupportCache

class ConfCacheServiceGetStringTest extends LifeCycleSupportCache with KeyComposeSupport {

  override def beforeEach(): Unit = {
    set(keyEsCl, valueEsCl)
    set(compose(Some(localeEsCl), keyEsCl), valueEsCl)
    set(compose(Some(localeEnUs), keyEnUs), valueEnUs)
    set(compose(Some(localePtBr), keyPtBr), valuePtBr)
  }

  override def afterEach(): Unit = {
    del(keyEsCl)
    del(compose(Some(localeEsCl), keyEsCl))
    del(compose(Some(localeEnUs), keyEnUs))
    del(compose(Some(localePtBr), keyPtBr))
  }

  describe("***Conf Service Get String Test***") {

    it("Can Get Value As String from Cache") {

      val opt = CakeConfCacheService.confCacheService.getString(key = keyEsCl) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      assertResult(opt.get)(valueEsCl)
    }

    it("Can Get Value As String es/CL from Cache") {

      val opt = CakeConfCacheService.confCacheService.getString(compose(Some(localeEsCl), keyEsCl)) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      assertResult(opt.get)(valueEsCl)
    }

    it("Can Get Value As String pt/BR from Cache") {

      val opt = CakeConfCacheService.confCacheService.getString(compose(Some(localePtBr), keyPtBr)) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      assertResult(opt.get)(valuePtBr)
    }

    it("Can Get Value As String en/US from Cache") {

      val opt = CakeConfCacheService.confCacheService.getString(compose(Some(localeEnUs), keyEnUs)) fold (
        error => throw new IllegalStateException(error),
        opt => opt)

      assertResult(opt.get)(valueEnUs)
    }

  }

}

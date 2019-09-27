package com.sudhan.findingfalcone.findfalconeresult.view

import com.sudhan.findingfalcone.findfalconeresult.model.FindFalconeResponse

interface IFindFalconeResultView {
    fun onFindFalconeResult(findFalconeResponse: FindFalconeResponse)
    fun onGetTokenApiSuccessResult()
    fun onHandleApiError(errorMsg: String?)
}
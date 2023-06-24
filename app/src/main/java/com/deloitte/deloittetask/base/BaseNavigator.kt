package com.deloitte.deloittetask.base

interface BaseNavigator {
    fun showMessage(message: String)
    fun showMessage(messageRecource: Int)
    fun onSignedOut()
    fun openNonUserPage()
    fun openUserPage()
}
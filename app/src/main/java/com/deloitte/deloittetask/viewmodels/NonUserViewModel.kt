package com.deloitte.deloittetask.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.deloitte.deloittetask.base.BaseViewModel
import com.deloitte.deloittetask.common.ValidationHelper
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.repository.models.User
import com.deloitte.deloittetask.ui.non_user_screen.NonUserNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NonUserViewModel @Inject constructor(usersRepository: UsersRepository) :
    BaseViewModel<NonUserNavigator>(usersRepository) {
    companion object {
        const val TAG = "NonUserViewModel"
    }

    val userName = MutableLiveData("")
    val useNameError = MutableLiveData(ValidationHelper.ValidationResult())

    val password = MutableLiveData("")
    val passwordError = MutableLiveData(ValidationHelper.ValidationResult())

    val nationalId = MutableLiveData("")
    val nationalIdError = MutableLiveData(ValidationHelper.ValidationResult())

    val email = MutableLiveData("")
    val emailError = MutableLiveData(ValidationHelper.ValidationResult())

    val birthOfDate = MutableLiveData("")
    val birthOfDateError = MutableLiveData(ValidationHelper.ValidationResult())

    val phoneNumber = MutableLiveData("")
    val phoneNumberError = MutableLiveData(ValidationHelper.ValidationResult())

    val validator = ValidationHelper()

    fun verifyLogin() {
        if (isLoginNotValid())
            return

        viewModelScope.launch {
            val hasSuccessfulLogin = repository.verifyLoginUser(userName.value!!, password.value!!)
            if (hasSuccessfulLogin) {
                getNav()?.openUserPage()
            } else {
                getNav()?.showMessage("User Not Found !")
            }
        }

    }

    fun register() {
        if (isSignupNotValid())
            return
        val user = User(
            fullName = userName.value!!,
            email = email.value!!,
            password = password.value!!,
            NationalId = nationalId.value!!,
            phoneNumber = phoneNumber.value!!,
            DateOfBirth = birthOfDate.value!!,
        )
        viewModelScope.launch {

            try {

                val result = repository.addUser(user)
                if (!result.equals(-1))
                    getNav()?.openUserPage()
                else
                    getNav()?.showMessage("something went wrong")

                Log.d(TAG, "register: $result")
            } catch (e: Exception) {

                Log.d(TAG, "register: Failed ${e.localizedMessage}")
            }
        }
    }

    private fun isLoginNotValid(): Boolean {
        useNameError.value =
            validator.validate(
                ValidationHelper.InputType.NAME,
                userName.value!!
            )
        passwordError.value =
            validator.validate(
                ValidationHelper.InputType.PASSWORD,
                password.value!!
            )

        val hasError = listOf(
            useNameError.value!!,
            passwordError.value!!
        ).any {
            !it.isSuccessful
        }
        return hasError
    }

    private fun isSignupNotValid(): Boolean {
        useNameError.value =
            validator.validate(
                ValidationHelper.InputType.NAME,
                userName.value!!
            )

        passwordError.value =
            validator.validate(
                ValidationHelper.InputType.PASSWORD,
                password.value!!
            )

        emailError.value=
            validator.validate(
                ValidationHelper.InputType.EMAIL,
                email.value!!
            )

        nationalIdError.value =
            validator.validate(
                ValidationHelper.InputType.NATIONAL_ID,
                nationalId.value!!
            )

        birthOfDateError.value =
            validator.validate(
                ValidationHelper.InputType.DATE,
                birthOfDate.value!!
            )

        phoneNumberError.value =
            validator.validate(
                ValidationHelper.InputType.PHONE,
                phoneNumber.value!!
            )

        val hasError = listOf(
            useNameError.value!!,
            passwordError.value!!,
            emailError.value!!,
            nationalIdError.value!!,
            birthOfDateError.value!!,
            phoneNumberError.value!!,

            ).any {
            !it.isSuccessful
        }
        return hasError
    }


}
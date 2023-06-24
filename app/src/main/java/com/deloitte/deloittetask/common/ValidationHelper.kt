package com.deloitte.deloittetask.common

import android.util.Patterns

class ValidationHelper {

    fun validate(inputType: InputType, input: String): ValidationResult {
        var errorMessage = ""
        var isValid = true
        when (inputType) {

            InputType.EMAIL -> {

                if (input.isBlank()) {
                    errorMessage = "The Email can't be blank"
                    isValid = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
                    errorMessage = "That is not a valid Email"
                    isValid = false
                }

            }

            InputType.PASSWORD -> {
                if (input.isBlank()) {
                    errorMessage = "The password must not be empty"
                    isValid = false
                } else if (input.length < 4) {
                    errorMessage = "The password must be 4 digits at least"
                    isValid = false
                }

            }

            InputType.NAME ->
            {
                if(input.isBlank()){
                    errorMessage = "The name must not be empty"
                isValid = false}
            }
            InputType.PHONE-> {
                if(input.isBlank()){
                    errorMessage = "The phone must not be empty"
                    isValid = false}
                else if(input.length<9){
                        errorMessage = "The phone must be 9 digits"
                    isValid = false
                }
                }
            InputType.NATIONAL_ID -> {
                if(input.isBlank()){
                    errorMessage = "The national id must not be empty"
                    isValid = false}
                else if(input.length<9){
                    errorMessage = "The national id must be 10 digits"
                    isValid = false
                }

            }
            InputType.DATE ->
                if(input.isBlank()){
                    errorMessage = "The date must not be empty"
                    isValid = false
                }
        }
        return ValidationResult(
            isSuccessful = isValid,
            errorMessage = errorMessage
        )
    }

    enum class InputType {
        EMAIL, PASSWORD, NAME, PHONE, NATIONAL_ID, DATE
    }

    data class ValidationResult(
        val isSuccessful: Boolean =true,
        val errorMessage: String? = ""
    )

}
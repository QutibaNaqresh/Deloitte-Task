package com.deloitte.deloittetask.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Users_table")
data class User(
    @PrimaryKey(autoGenerate = true)   val uId         : Long =0,
    @ColumnInfo("full_name")     val fullName    : String,
    @ColumnInfo("email")         val email       : String,
    @ColumnInfo("national_id")   val NationalId  : String,
    @ColumnInfo("phone_num")     val phoneNumber : String,
    @ColumnInfo("date_of_birth") val DateOfBirth : String,
    @ColumnInfo("password")      val password    : String,
)

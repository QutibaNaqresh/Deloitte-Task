package com.deloitte.deloittetask.repository.local_data_source.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.deloitte.deloittetask.repository.local_data_source.models.User
import kotlinx.coroutines.flow.Flow

/*
*
* CREATED BY QUTIBA 22/6/2023
*
* */
@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(user: User): Long

    @Query(
        "SELECT * FROM users_table WHERE full_name LIKE :fullName AND " +
                "password LIKE  :password  "
    )
   suspend fun verifyLogin(fullName: String, password: String): User?

   @Query(
       "SELECT * FROM users_table WHERE uId LIKE :uid"
   )
   suspend fun getUser(uid:Long): User?
    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}
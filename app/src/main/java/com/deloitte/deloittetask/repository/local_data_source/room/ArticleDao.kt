package com.deloitte.deloittetask.repository.local_data_source.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deloitte.deloittetask.repository.local_data_source.models.Article
import com.deloitte.deloittetask.repository.local_data_source.models.User
import com.deloitte.deloittetask.repository.local_data_source.models.UserWithArticles
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles_table")
    fun getArticles(): List<Article>?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Query("DELETE FROM articles_table ")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM articles_table WHERE uId = :userId")
    suspend fun getUserWithArticles(userId: Long): List<Article>


}
package com.deloitte.deloittetask.repository.local_data_source.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("articles_table")
 class Article(
    @PrimaryKey(autoGenerate = false)
    val articleId : Long,
    val uId         : Long =0,
    val title  :String,
    val imageUrl :String ,
    val publishedDate : String
)

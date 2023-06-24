package com.deloitte.deloittetask.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("articles_table")
 class Article(
    @PrimaryKey(autoGenerate = true)
    private val id : Int,
)

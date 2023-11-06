package com.alanturing.cpifp.todo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(val id:Int,
                var title:String,
                var description:String,
                var isCompleted: Boolean):Parcelable

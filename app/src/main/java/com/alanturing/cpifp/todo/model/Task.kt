package com.alanturing.cpifp.todo.model

import java.io.Serializable

data class Task(public val id:Int,
                public var title:String,
                public var description:String,
                public val isCompleted: Boolean):Serializable

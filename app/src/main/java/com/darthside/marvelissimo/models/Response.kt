package com.darthside.marvelissimo.models

data class Response(val code : Int,
                    val status : String,
                    val data : JsonData)
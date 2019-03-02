package com.darthside.marvelissimo.models

import com.google.gson.annotations.SerializedName

data class Character constructor(var name: String,
                                 var path: String,
                                 var extension: String,
                                 @SerializedName("url")
                                 var wikiUrl: String,
                                 var favourite: Boolean)
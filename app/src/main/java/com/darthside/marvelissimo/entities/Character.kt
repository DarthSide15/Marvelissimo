package com.darthside.marvelissimo.entities

import com.google.gson.annotations.SerializedName

class Character constructor(var name: String, var path: String, extension: String,
                            @SerializedName("url") var wikiUrl: String, var favourite: Boolean) {


    var extension = ".$extension"
}
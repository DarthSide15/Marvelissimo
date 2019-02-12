package com.darthside.marvelissimo.entities

class Character constructor(var name: String, var path: String, extension: String,
                            var wikiUrl: String, var favourite: Boolean) {


    var extension = ".$extension"
}
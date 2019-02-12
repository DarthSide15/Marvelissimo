package com.darthside.marvelissimo.entities

class Series constructor(var title: String, var imgUrl: String, extension: String, var startYear: Int,
                         var endYear: Int, var detailUrl: String, var favourite: Boolean) {

    var extension = ".$extension"
}
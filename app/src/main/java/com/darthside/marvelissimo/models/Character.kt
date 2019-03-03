package com.darthside.marvelissimo.models


data class Character constructor(val id : Int,
                                 val name: String,
                                 val description : String,
                                 val thumbnail : Thumbnail,
                                 val resourceURI : String,
                                 val series : SeriesData,
                                 val urls : List<Url>)
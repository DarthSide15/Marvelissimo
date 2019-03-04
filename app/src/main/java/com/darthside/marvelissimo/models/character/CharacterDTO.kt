package com.darthside.marvelissimo.models


data class CharacterDTO constructor(val id : Int,
                                    val name: String,
                                    val description : String,
                                    val thumbnail : ThumbnailDTO,
                                    val resourceURI : String,
                                    val series : CharacterSeriesDataDTO,
                                    val urls : List<UrlDTO>)
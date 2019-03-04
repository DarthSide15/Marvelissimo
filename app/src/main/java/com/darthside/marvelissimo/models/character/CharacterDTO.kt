package com.darthside.marvelissimo.models.character

data class CharacterDTO constructor(val id : Int,
                                    val name: String,
                                    val description : String,
                                    val thumbnail : CharacterThumbnailDTO,
                                    val resourceURI : String,
                                    val series : CharacterSeriesDataDTO,
                                    val urls : List<CharacterUrlDTO>)
package com.darthside.marvelissimo.models.character

data class CharacterSeriesDataDTO(val available : Int,
                                  val connectionURI : String,
                                  val items : List<CharacterSeriesDTO>,
                                  val returned : Int)

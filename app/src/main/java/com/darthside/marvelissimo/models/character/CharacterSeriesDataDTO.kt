package com.darthside.marvelissimo.models

data class CharacterSeriesDataDTO(val available : Int,
                                  val connectionURI : String,
                                  val items : List<CharacterSeriesDTO>,
                                  val returned : Int)

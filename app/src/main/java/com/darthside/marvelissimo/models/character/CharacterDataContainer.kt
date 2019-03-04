package com.darthside.marvelissimo.models.character

data class CharacterDataContainer(val total : Int,
                                  val count : Int,
                                  val results : List<CharacterDTO>)
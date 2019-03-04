package com.darthside.marvelissimo.models.series

data class SeriesCharactersDataDTO(val available : Int,
                                   val collectionURI : String,
                                   val items : List<SeriesCharactersDTO>,
                                   val returned : Int)
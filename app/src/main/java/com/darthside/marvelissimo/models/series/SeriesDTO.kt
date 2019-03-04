package com.darthside.marvelissimo.models.series

data class SeriesDTO(val id : Int,
                     val title: String,
                     val description : String,
                     val resourceURI : String,
                     val urls : List<SeriesUrlDTO>,
                     val startYear : Int,
                     val endYear : Int,
                     val rating : String,
                     val thumbnail : SeriesThumbnailDTO,
                     val characters : SeriesCharactersDataDTO)

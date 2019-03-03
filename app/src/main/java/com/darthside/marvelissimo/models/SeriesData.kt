package com.darthside.marvelissimo.models

data class SeriesData(val available : Int,
                      val connectionURI : String,
                      val items : List<Series>,
                      val returned : Int)

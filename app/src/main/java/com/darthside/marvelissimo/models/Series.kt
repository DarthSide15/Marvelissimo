package com.darthside.marvelissimo.models

data class Series constructor(var title: String,
                              var imgUrl: String,
                              var extension: String,
                              var startYear: Int,
                              var endYear: Int,
                              var detailUrl: String,
                              var favourite: Boolean)
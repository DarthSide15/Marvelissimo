package com.darthside.marvelissimo.entities

import com.google.gson.annotations.SerializedName

data class CharacterResult(@SerializedName("results") var result: List<Character>)
package com.darthside.marvelissimo.models

import com.google.gson.annotations.SerializedName

data class CharacterResult(@SerializedName("data") var result: List<Character>)
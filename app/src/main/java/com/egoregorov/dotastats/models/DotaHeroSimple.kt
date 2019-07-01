package com.egoregorov.dotastats.models

import com.squareup.moshi.Json

data class DotaHeroSimple(
    val id: String,
    val name: String,
    @field:Json(name = "localized_name")val localisedName: String,
    @field:Json(name = "primary_attr")val primaryAttr: String,
    @field:Json(name = "attack_type")val attackType: String,
    @field:Json(name = "icon")val iconUrl: String,
    val roles: List<String>
)
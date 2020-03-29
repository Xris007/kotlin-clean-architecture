package com.noblecilla.domain.vo

interface DataMapper<DB, REST, DOMAIN> {
    fun mapToDbEntity(type: DOMAIN): DB
    fun mapFromDbEntity(type: DB): DOMAIN
    fun mapFromDbListEntity(type: List<DB>): List<DOMAIN>
    fun mapFromRestListEntity(type: List<REST>): List<DOMAIN>
}

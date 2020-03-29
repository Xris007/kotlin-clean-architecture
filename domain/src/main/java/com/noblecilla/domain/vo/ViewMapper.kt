package com.noblecilla.domain.vo

interface ViewMapper<DOMAIN, VIEW> {
    fun mapToEntity(type: VIEW): DOMAIN
    fun mapFromEntity(type: DOMAIN): VIEW
    fun mapFromListEntity(type: List<DOMAIN>): List<VIEW>
}

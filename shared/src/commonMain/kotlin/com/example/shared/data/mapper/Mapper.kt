package com.example.shared.data.mapper

import com.example.shared.data.entities.Entity
import com.example.shared.domain.model.DomainData

interface Mapper<E : Entity, D : DomainData> {

    fun toData(entity: E): D

    fun toRaw(data: D): E
}
package ru.spiridonov.getreallocation.domain.entity

data class Cell(
    val lac: Int,
    val cid: Int,
    val psc: Int? = null
)

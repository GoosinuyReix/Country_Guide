package com.example.spravochnik

data class Country(val name: Name, val capital: List<String>, val population: Long, val area: Long, val flags: Flag)

data class Flag(val svg: String)

data class Name(val common: String)
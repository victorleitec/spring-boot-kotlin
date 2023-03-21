package br.com.victorlc.forum.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}

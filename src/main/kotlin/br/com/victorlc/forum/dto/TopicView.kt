package br.com.victorlc.forum.dto

import br.com.victorlc.forum.models.enums.TopicStatus
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val status: TopicStatus,
    val createdAt: LocalDateTime
)

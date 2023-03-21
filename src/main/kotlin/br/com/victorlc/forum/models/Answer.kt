package br.com.victorlc.forum.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "answers")
data class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val author: User,
    @ManyToOne val topic: Topic,
    val solution: Boolean = false,
)

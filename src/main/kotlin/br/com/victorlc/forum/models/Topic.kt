package br.com.victorlc.forum.models

import br.com.victorlc.forum.models.enums.TopicStatus
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "topics")
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val course: Course,
    @ManyToOne val author: User,
    @Enumerated(value = EnumType.STRING) val status: TopicStatus = TopicStatus.NOT_ANSWERED,
    @OneToMany(mappedBy = "topic") val answer: List<Answer> = ArrayList()
)
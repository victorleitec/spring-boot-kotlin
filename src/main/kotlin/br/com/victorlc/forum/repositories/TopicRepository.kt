package br.com.victorlc.forum.repositories

import br.com.victorlc.forum.dto.TopicPerCategoryDto
import br.com.victorlc.forum.models.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(courseName: String, pagenation: Pageable): Page<Topic>

    @Query("SELECT new br.com.victorlc.forum.dto.TopicPerCategoryDto(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun report(): List<TopicPerCategoryDto>
}
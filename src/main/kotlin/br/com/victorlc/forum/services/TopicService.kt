package br.com.victorlc.forum.services

import br.com.victorlc.forum.dto.RegisterTopicForm
import br.com.victorlc.forum.dto.TopicPerCategoryDto
import br.com.victorlc.forum.dto.TopicView
import br.com.victorlc.forum.dto.UpdateTopicForm
import br.com.victorlc.forum.exception.NotFoundException
import br.com.victorlc.forum.mappers.TopicFormMapper
import br.com.victorlc.forum.mappers.TopicViewMapper
import br.com.victorlc.forum.repositories.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
) {
    private val notFoundMessage: String = "Topic not found"

    fun list(courseName: String?, pagenation: Pageable): Page<TopicView> {
        return if (courseName != null) {
            topicRepository.findByCourseName(courseName, pagenation)
                .map { topicViewMapper.map(it) }
        } else {
            topicRepository.findAll(pagenation)
                .map { topicViewMapper.map(it) }
        }
    }

    fun searchById(id: Long): TopicView? =
        topicRepository.findById(id)
            .map { topicViewMapper.map(it) }
            .orElseThrow { NotFoundException(notFoundMessage) }

    fun register(topicForm: RegisterTopicForm): TopicView =
        topicRepository.save(topicFormMapper.map(topicForm))
            .let { topicViewMapper.map(it) }

    fun update(topicForm: UpdateTopicForm): TopicView {
        val topic = topicRepository.findById(topicForm.id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        topic.title = topicForm.title
        topic.message = topicForm.message
        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        val topic = topicRepository.findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        topicRepository.delete(topic)
    }

    fun report(): List<TopicPerCategoryDto> {
        return topicRepository.report()
    }
}

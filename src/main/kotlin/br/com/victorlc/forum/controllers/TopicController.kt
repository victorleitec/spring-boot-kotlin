package br.com.victorlc.forum.controllers

import br.com.victorlc.forum.dto.RegisterTopicForm
import br.com.victorlc.forum.dto.TopicPerCategoryDto
import br.com.victorlc.forum.dto.TopicView
import br.com.victorlc.forum.dto.UpdateTopicForm
import br.com.victorlc.forum.services.TopicService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    @Cacheable("topics")
    fun list(
        @RequestParam(required = false) courseName: String?,
        @PageableDefault(size = 5) pagination: Pageable
    ): Page<TopicView> =
        topicService.list(courseName, pagination)

    @GetMapping("{id}")
    fun searchById(@PathVariable id: Long): TopicView? = topicService.searchById(id)

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun register(
        @RequestBody @Valid topicForm: RegisterTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val topicView = topicService.register(topicForm)
        val uri = uriBuilder.path("/topics/{id}").buildAndExpand(topicView.id).toUri()
        return ResponseEntity.created(uri).body(topicView)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    fun update(@RequestBody @Valid topicForm: UpdateTopicForm): ResponseEntity<TopicView> {
        val topicView = topicService.update(topicForm)
        return ResponseEntity.ok(topicView)
    }

    @DeleteMapping("{id}")
    @Transactional
    @CacheEvict(value = ["topics"], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        topicService.delete(id)
    }

    @GetMapping("report")
    fun report(): List<TopicPerCategoryDto> {
        return topicService.report()
    }
}
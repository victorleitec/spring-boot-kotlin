package br.com.victorlc.forum.services

import br.com.victorlc.forum.exception.NotFoundException
import br.com.victorlc.forum.models.Course
import br.com.victorlc.forum.repositories.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun list(): List<Course> = courseRepository.findAll()

    fun searchById(id: Long): Course? =
        courseRepository.findById(id).orElseThrow { NotFoundException("Course not found") }
}

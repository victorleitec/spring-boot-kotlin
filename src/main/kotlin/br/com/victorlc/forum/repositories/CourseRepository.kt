package br.com.victorlc.forum.repositories

import br.com.victorlc.forum.models.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course, Long>
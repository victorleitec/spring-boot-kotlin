package br.com.victorlc.forum.mappers

import br.com.victorlc.forum.dto.RegisterTopicForm
import br.com.victorlc.forum.models.Topic
import br.com.victorlc.forum.services.CourseService
import br.com.victorlc.forum.services.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService
) :
    Mapper<RegisterTopicForm, Topic> {
    override fun map(t: RegisterTopicForm): Topic {
        return Topic(
            title = t.title,
            message = t.message,
            course = courseService.searchById(t.courseId)!!,
            author = userService.searchById(t.authorId)!!
        )
    }

}

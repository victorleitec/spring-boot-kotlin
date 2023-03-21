package br.com.victorlc.forum.mappers

import br.com.victorlc.forum.dto.TopicView
import br.com.victorlc.forum.models.Topic
import org.springframework.stereotype.Component

@Component
class TopicViewMapper : Mapper<Topic, TopicView> {
    override fun map(t: Topic): TopicView {
        return TopicView(
            id = t.id,
            title = t.title,
            message = t.message,
            status = t.status,
            createdAt = t.createdAt
        )
    }
}
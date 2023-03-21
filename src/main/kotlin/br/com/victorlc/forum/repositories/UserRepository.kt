package br.com.victorlc.forum.repositories

import br.com.victorlc.forum.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(username: String?): User?
}
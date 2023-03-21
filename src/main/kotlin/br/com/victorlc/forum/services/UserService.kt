package br.com.victorlc.forum.services

import br.com.victorlc.forum.exception.NotFoundException
import br.com.victorlc.forum.models.User
import br.com.victorlc.forum.models.UserDetail
import br.com.victorlc.forum.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    fun list(): List<User> = userRepository.findAll()

    fun searchById(id: Long): User? = userRepository.findById(id).orElseThrow { NotFoundException("User not found") }
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw NotFoundException("User not found")
        return UserDetail(user)
    }
}

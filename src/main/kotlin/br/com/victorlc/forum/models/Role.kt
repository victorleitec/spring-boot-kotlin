package br.com.victorlc.forum.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val name: String
) : GrantedAuthority {
    override fun getAuthority(): String = name
}

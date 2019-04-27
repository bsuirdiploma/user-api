package by.trusevich.house.user.repository

import by.trusevich.house.core.repository.BaseRepository
import by.trusevich.house.user.model.User

interface UserRepository : BaseRepository<User> {

    fun existsByLogin(name: String): Boolean

}

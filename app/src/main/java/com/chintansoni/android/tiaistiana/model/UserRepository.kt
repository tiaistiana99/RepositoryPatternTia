package com.chintansoni.android.tiaistiana.model

import com.chintansoni.android.tiaistiana.model.local.dao.UserDao
import com.chintansoni.android.tiaistiana.model.local.entity.User
import com.chintansoni.android.tiaistiana.model.remote.ApiService
import com.chintansoni.android.tiaistiana.model.remote.response.RandomUserResponse
import kotlinx.coroutines.Deferred

class UserRepository constructor(private var apiService: ApiService, private var userDao: UserDao) {


    private var pageNumber: Int = 0
    private var networkBoundSource: NetworkBoundResource<List<User>, RandomUserResponse> =
            object : NetworkBoundResource<List<User>, RandomUserResponse>() {
                override suspend fun getRemoteAsync(): Deferred<RandomUserResponse> {
                    return apiService.getUsers(pageNumber)
                }

                override suspend fun getLocal(): List<User> {
                    return userDao.getAll()
                }

                override suspend fun saveCallResult(data: List<User>, isForced: Boolean) {
                    if (isForced) {
                        userDao.deleteAll()
                    }
                    userDao.insertAllUsers(data)
                }

                override suspend fun mapper(remoteType: RandomUserResponse): List<User> {
                    val users: ArrayList<User> = ArrayList()
                    for (result in remoteType.results!!.iterator()) {
                        users.add(User(id = 0,
                                name = result.name,
                                email = result.email,
                                gender = result.gender,
                                cell = result.cell,
                                picture = result.picture,
                                location = result.location,
                                dob = result.dob))
                    }
                    return users.toList()
                }
            }

    val userLiveData = networkBoundSource.asLiveData()

    suspend fun refresh() {
        networkBoundSource.refresh()
    }

    suspend fun fetchUsers(isForced: Boolean) {
        networkBoundSource.fetch(isForced)
    }

    suspend fun getNextPageUsers() {
        pageNumber += 1
        networkBoundSource.getRemoteData(false)
    }
}
package com.example.streamplateandroidtest.repositories

import androidx.lifecycle.LiveData
import com.example.streamplateandroidtest.api.RetrofitBuilder
import com.example.streamplateandroidtest.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserRepository {

    var job: CompletableJob? = null

    fun getUsers(): LiveData<List<User>>{
        // Initialise job
        job = Job();
        return object: LiveData<List<User>>(){
            override fun onActive() {
                // when getUsers is called
                super.onActive()
                job?.let {
                    // If the job is not null then run the following code
                    theJob ->
                    // Creates a new coroutine on a background thread (IO dispatcher)
                    CoroutineScope(IO + theJob).launch {
                        val users = RetrofitBuilder.apiService.getUsers()
                        // Now that we have the users, set it to the live data's value
                        // However this must be done on the main thread (set live data value)
                        withContext(Main){
                            value = users
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}
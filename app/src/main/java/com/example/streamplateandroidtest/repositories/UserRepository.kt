package com.example.streamplateandroidtest.repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.streamplateandroidtest.api.RetrofitBuilder
import com.example.streamplateandroidtest.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserRepository {

    var job: CompletableJob? = null

    fun getUsers(context: Context): LiveData<List<User>>{
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
                        val response = RetrofitBuilder.apiService.getUsers()
                        if (response.code() == 200) {
                            // Now that we have the users, set it to the live data's value
                            // However this must be done on the main thread (set live data value)
                            withContext(Main) {
                                value = response.body()
                            }
                        } else {
                            // Something went wrong in retrieving the users
                            withContext(Main){
                                Toast.makeText(context, "Error in retrieving users [Status: ${response.code()}]", Toast.LENGTH_LONG).show()
                            }
                        }
                        theJob.complete()
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}
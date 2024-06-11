package viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.Injection
import data.Result
import data.Result.*
import data.Room
import data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel:ViewModel() {
    private val _rooms = MutableLiveData<List<Room>>()
    private val roomRepository:RoomRepository
    val rooms:LiveData<List<Room>>get() = _rooms

    init{
        roomRepository =RoomRepository(Injection.instance())
        loadRooms()
    }

    fun createRoom(name:String){
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }
    fun loadRooms(){
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()){
                is Success -> _rooms.value =result.data
                is Error ->{
                    Log.d("roomsload", "loadRooms: failed")
                }
            }
        }
    }
}
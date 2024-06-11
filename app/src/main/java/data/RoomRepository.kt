package data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import data.Result.Error
import data.Result.Success
import kotlinx.coroutines.tasks.await

class RoomRepository(private val firestore: FirebaseFirestore) {
    suspend fun createRoom(name:String): Result<Unit> = try{
        Log.d("roomstatus", "createRoom:reavhed ")
        val room= Room(name=name)
        firestore.collection("rooms").add(room).await()

        Success(Unit)
    }catch (e:Exception){
        Error(e)
    }

    suspend fun getRooms():Result<List<Room>> = try{
    val querySnapshot= firestore.collection("rooms").get().await()
        val rooms = querySnapshot.documents.map {
            document->
            document.toObject(Room::class.java)!!.copy(id= document.id)
        }
        Success(rooms)
    }catch(e:Exception){
        Error(e)
    }

}
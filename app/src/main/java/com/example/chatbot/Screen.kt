package com.example.chatbot

sealed class Screen (val route : String) {
    object LoginScreen:Screen("loginscreen")
    object SignupScreen:Screen("signupscreen")
    object ChatRoomScreen:Screen("chatroomlist")
    object ChatScreen:Screen("chatscreen")
}
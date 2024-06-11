package com.example.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatbot.ui.theme.ChatBotTheme
import screen.ChatRoomListScreen
import screen.ChatScreen
import screen.LoginScreen
import screen.SignUpScreen
import viewmodel.AuthViewModel
import viewmodel.RoomViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel : AuthViewModel = viewModel()

            ChatBotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavigationGraph(navController = navController, authViewModel = authViewModel )
                }

            }
        }
    }
}

@Composable
fun NavigationGraph (
    navController: NavHostController,
    authViewModel: AuthViewModel,

){
    NavHost(navController = navController, startDestination = Screen.SignupScreen.route ){
        composable(Screen.SignupScreen.route){
            SignUpScreen(
                onNavigateToLogin = { navController.navigate(Screen.LoginScreen.route) },
                authViewModel = authViewModel
            )
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(
                onNavigateToSignup = { navController.navigate(Screen.SignupScreen.route) },
                authViewModel = authViewModel,
                onSignInSuccess = {navController.navigate(Screen.ChatRoomScreen.route)}
            )
        }
        composable(Screen.ChatRoomScreen.route) {
            ChatRoomListScreen {
                 navController.navigate("${Screen.ChatScreen.route}/${it.id}")
            }
        }
        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId : String= it.arguments?.getString("roomId")?:""
            ChatScreen(roomId=roomId)
        }
    }
}

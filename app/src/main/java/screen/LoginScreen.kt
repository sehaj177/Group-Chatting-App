package screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import data.Result
import viewmodel.AuthViewModel

@Composable
fun LoginScreen( onNavigateToSignup:()-> Unit , authViewModel: AuthViewModel , onSignInSuccess: () -> Unit ){
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    val result by authViewModel.authResult.observeAsState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center) {
        OutlinedTextField(value = email, onValueChange = {email = it} , modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) , label = { Text(
            text = "E-mail"
        )} )


        OutlinedTextField(value = password, onValueChange = {password = it} , modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) , visualTransformation = PasswordVisualTransformation() ,label = { Text(
            text = "Password"
        )})

        Button(onClick = {
            authViewModel.login(email,password)
            when(result){
                is Result.Success -> {
                    onSignInSuccess()
                }
                is Result.Error ->{

                }
                else -> {

                }
            }
            email = ""
            password=""
        } , modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Don't have an account? Sign up", modifier = Modifier.clickable { onNavigateToSignup() })
    }

}


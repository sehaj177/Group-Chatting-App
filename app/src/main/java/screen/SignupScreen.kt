package screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    onNavigateToLogin:() -> Unit,
    authViewModel: AuthViewModel
){


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            OutlinedTextField(value = email , onValueChange ={email =it}, label = { Text(text = "E-mail")} , modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) )


            OutlinedTextField(value = password , onValueChange ={password =it}, label = { Text(text = "Password")} , modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), visualTransformation = PasswordVisualTransformation() )


            OutlinedTextField(value = firstname , onValueChange ={firstname =it}, label = { Text(text = "First Name")} , modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))


            OutlinedTextField(value = lastname , onValueChange ={lastname =it}, label = { Text(text = "Last Name")}, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) )

        Button(onClick = {
            authViewModel.signUp(email, password, firstname, lastname)
            email = ""
            password = ""
            firstname = ""
            lastname = "" } , modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = "Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Already have an account? Sign in.",
            modifier = Modifier.clickable {  onNavigateToLogin()
             })
    }
}


package com.example.retrofitjsonjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofitjsonjetpack.ui.theme.RetrofitJsonJetpackTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue




//https://www.youtube.com/watch?v=Ry7VvxDgrbk
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitJsonJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {





                    Llamada()
                }
            }
        }
    }
}


@Composable
fun Llamada() {
    val contest= LocalContext.current
   // lateinit var  userI:UserInfo
    var users by rememberSaveable { mutableStateOf(UserInfo()) }

    val user=UserInstance.userInterface.userInformation()
    user.enqueue(object:Callback<UserInfo> {
        override fun onResponse(
            call: Call<UserInfo>,
            response: Response<UserInfo>
        ) {
            val userInfo:UserInfo?=response.body()
            if (userInfo!=null){
            //    Toast.makeText(contest, userInfo.toString(), Toast.LENGTH_SHORT).show()

                users= userInfo

            }

        }

        override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            Toast.makeText(contest, t.toString(), Toast.LENGTH_SHORT).show()
        }

    })


    Text(text = users.toString())

}

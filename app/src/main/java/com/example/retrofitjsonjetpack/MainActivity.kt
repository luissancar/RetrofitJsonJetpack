package com.example.retrofitjsonjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter



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


    LazyColumn()
    
    {
        items(users){
            usu -> 
            Image(painter =  rememberImagePainter(usu.avatar_url),
                contentDescription = "Imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(100.dp)),
                contentScale = ContentScale.FillWidth
                 )

        }
    }
   // Text(text = users.toString())

}

package com.techdeity.pineenergy

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview(showBackground = true , showSystemUi = true)
fun JetPackComposeBasics(){

    Column() {
        Greeting(name = "Vishal Kumar")
    }

}

@Composable
fun Greeting(name:String){
    Text(text="Hello $name")
}
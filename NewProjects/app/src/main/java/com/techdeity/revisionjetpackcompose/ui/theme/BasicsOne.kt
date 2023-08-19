package com.techdeity.revisionjetpackcompose.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BasicsTest(name: String, modifier: Modifier = Modifier){

    Text(text= "Hello $name",
         modifier= modifier
        )
}

@Composable
fun Test(){

    Column {
        BasicsTest(name = "Vishal" )
        BasicsTest(name = "Vishal" )
        BasicsTest(name = "Vishal" )
    }
}
@Composable
@Preview(showSystemUi = true , showBackground = true)
fun Outuput(){
    Test()
}
package com.techdeity.apicleanarchitecture

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techdeity.apicleanarchitecture.features_dictionary.presentation.WordInfoViewModel
import com.techdeity.apicleanarchitecture.features_dictionary.presentation.WordinfoItem
import com.techdeity.apicleanarchitecture.ui.theme.ApiCleanArchitectureTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiCleanArchitectureTheme {

                val viewModel:WordInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(key1 = true ){
                    viewModel.eventFlow.collectLatest {
                        event-> when(event){
                            is WordInfoViewModel.UIEvent.ShowSnackbar ->{
                                snackbarHostState.showSnackbar(message = event.message)
                            }
                        }
                    }
                }

                Scaffold(){
                    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    ){
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {
                            TextField(
                                value = viewModel.searchQuery.value ,
                                onValueChange =viewModel::onSearch,
                                modifier = Modifier.fillMaxSize(),
                                placeholder = {
                                    Text(text = "Search..")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ){
                                items(state.wordInfoItems.size){
                                    i->val wordInfo = state.wordInfoItems[i]
                                    if(i>0){
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }

                                    WordinfoItem(wordInfo = wordInfo)
                                    if(i<state.wordInfoItems.size -1){
                                        Divider()
                                    }
                                }
                            }
                        }
                        if(state.isLoading){
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }


                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}


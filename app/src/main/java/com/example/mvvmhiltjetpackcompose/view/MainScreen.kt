package com.example.mvvmhiltjetpackcompose.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmhiltjetpackcompose.model.remote.New
import com.example.mvvmhiltjetpackcompose.viewModel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage

@Preview
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    LocalContext.current
    val searchText by remember { mutableStateOf("") }
    val response by viewModel.result.observeAsState()


    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Column {
            var textState by remember { mutableStateOf("") }
            val maxLength = 110
            val lightBlue = Color(0xFFE5E8EC)
            val blue = Color(0xFFC3C6CA)

            Text(
                text = "Search For News",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                textAlign = TextAlign.Start,
                color = blue
            )
            Row(Modifier.align(CenterHorizontally)) {
                TextField(
                    modifier = Modifier.width(270.dp),
                    value = textState,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = lightBlue,
                        cursorColor = Color.Black,
                        disabledLabelColor = lightBlue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        if (it.length <= maxLength) textState = it
                    },
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    trailingIcon = {
                        if (textState.isNotEmpty()) {
                            IconButton(onClick = { textState = "" }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.padding(3.dp))

                Button(
                    onClick = { viewModel.searchForImage(searchText) },
                    modifier = Modifier.height(53.dp)
                ) {
                    Text(text = "Search")
                }

            }

            Text(
                text = "${textState.length} / $maxLength",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                textAlign = TextAlign.End,
                color = blue
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))

        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.Cyan,
        ) {
            Column(Modifier.padding(5.dp)) {
                response?.let {
                    ImageList(images = it.news)
                }
            }
        }
    }
}

@Composable
fun ImageList(images: List<New>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(images) { news ->
            key(news) {

                Card(
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(Modifier.padding(10.dp)) {
                        GlideImage(modifier = Modifier.size(100.dp), imageModel = news.image)

                        Spacer(modifier = Modifier.padding(5.dp))

                        Column(Modifier.padding(1.dp)) {
                            Text(
                                text = news.title, style = MaterialTheme.typography.h6,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = news.description, style = MaterialTheme.typography.h6,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier.padding(2.dp),
                )
            }
        }
    }
}
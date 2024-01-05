package com.hanndlee.education.ui.screens.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hanndlee.education.R
import com.hanndlee.education.models.BaseModel
import com.hanndlee.education.ui.theme.Inter
import com.hanndlee.education.ui.theme.SectionColor
import kotlinx.coroutines.delay


import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.TextStyle


data class Language(

    @DrawableRes
    val flag: Int,

    val name: String,
    val code: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, viewModel: HomeViewModel = viewModel()) {

    val translation by viewModel.translation.collectAsState()

    val languages = listOf(
        Language(
            flag = R.drawable.united_states,
            name = "English",
            code = "en"
        ),
        Language(
            flag = R.drawable.china,
            name = "Chinese",
            code = "zh"
        ),
        Language(
            flag = R.drawable.korea,
            name = "Korean",
            code = "ko"
        ),
        Language(
            flag = R.drawable.vietnam,
            name = "Vietnamese",
            code = "vi"
        ),
    )


    var source by remember {
        mutableStateOf(languages.last())
    }

    var target by remember {
        mutableStateOf(languages.first())
    }

    var sourceChange by remember {
        mutableStateOf(false)
    }

    var isDialogOpen by remember {
        mutableStateOf(false)
    }



    if (isDialogOpen) {
        Dialog(onDismissRequest = {
            isDialogOpen = false
        }) {
            LazyVerticalGrid(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(12.dp)
            ) {

                items(
                    languages
                ) { item ->
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(SectionColor)
                            .aspectRatio(1f)
                            .clickable {
                                if (sourceChange) {
                                    source = item
                                } else {
                                    target = item
                                }
                                isDialogOpen = false
                            },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = item.flag),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(18.dp))
                        Text(
                            text = item.name,
                            color = Color(0xff212121),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }


    val context = LocalContext.current


    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val copyToClipboard: (String) -> Unit = {
        val clipData = ClipData.newPlainText(
            "Translated Text",
            it
        )
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Copied to Clipboard.", Toast.LENGTH_SHORT).show()
    }




    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Education",
                        color = Color.White,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        },

        ) { paddings ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddings.calculateTopPadding(),
                    end = 16.dp,
                    start = 16.dp,

                    )

        ) {

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // row with 2 regions and swap button

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, shape = CircleShape)
                        .clip(CircleShape)
                        .background(SectionColor)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // soure language
                    Row(
                        modifier = Modifier.clickable {
                            sourceChange = true
                            isDialogOpen = true
                        },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = source.flag),
                            contentDescription = source.name,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = source.name,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Inter,
                            color = Color.Black
                        )
                    }

                    // swap button
                    IconButton(
                        onClick = {
                            val s = source
                            source = target
                            target = s
                        },
                        modifier = Modifier.size(32.dp),

                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_swap),
                            contentDescription = "Swap",
                            tint = Color.Black
                        )
                    }

                    // target language

                    Row(
                        modifier = Modifier.clickable {
                            sourceChange = false
                            isDialogOpen = true
                        },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = target.flag),
                            contentDescription = target.name,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = target.name,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Inter,
                            color = Color.Black
                        )
                    }


                }
            }


            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shadow(
                            4.dp,
                            shape = RoundedCornerShape(16.dp),
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .background(SectionColor)
                        .padding(
                            vertical = 10.dp,
                            horizontal = 16.dp
                        )
                ) {
                    val (query, setQuery) = remember {
                        mutableStateOf("")
                    }
                    LaunchedEffect(query) {
                        delay(1000)
                        if (query.isNotEmpty()) {
                            viewModel.translate(
                                query = query,
                                source = source.code,
                                target = target.code
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = source.name,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = Inter
                            )
                        Icon(
                            modifier = Modifier.clickable {
                                setQuery("")
                                viewModel.clear()
                            }, contentDescription = null,
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth().background(color = Color.Transparent),
                        value = query,
                        onValueChange = {
                            setQuery(it)
                        },
                        placeholder = {
                            Text(
                                text = "Enter text to translate...",
                                color = Color.Gray,
                                fontFamily = Inter,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),

                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = remember { MutableInteractionSource() },
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Inter,
                            fontSize = 18.sp
                        )
                    )
                }

            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shadow(
                            4.dp,
                            shape = RoundedCornerShape(16.dp),

                            )
                        .clip(RoundedCornerShape(16.dp))
                        .background(SectionColor)
                        .padding(
                            vertical = 10.dp,
                            horizontal = 16.dp
                        )
                ) {
                    Text(text = target.name, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold, fontFamily = Inter)
                    Spacer(modifier = Modifier.height(16.dp))
                    when (val response = translation) {
                        is BaseModel.Loading -> {
                            Text(text = "Translating", color = Color.Gray)
                        }

                        is BaseModel.Success -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(1f),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = response.data.responseData.translatedText,
                                    color = Color(0xff212121),
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clickable {
                                                copyToClipboard(response.data.responseData.translatedText)
                                            }
                                        ,
                                        painter = painterResource(id = R.drawable.ic_copy),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,

                                    )
                                }
                            }
                        }

                        is BaseModel.Error -> {
                            Text(text = response.error, color = Color.Gray)
                        }

                        null -> {
                            Text(text = "Translated Text...", color = Color.Gray)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
            item {
                AnimatedVisibility(
                    visible = translation is BaseModel.Success,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    runCatching {
                        translation as BaseModel.Success
                    }.onSuccess { data ->
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            repeat(data.data.matches.count()) {
                                val match = data.data.matches[it]
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(MaterialTheme.colorScheme.primary)
                                    .clickable {
                                        copyToClipboard(match.translation)
                                    }
                                    .padding(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            modifier = Modifier.weight(.7f),
                                            text = match.translation,
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            modifier = Modifier.weight(.3f),
                                            text = "Match: ${match.match * 100}%",
                                            color = Color.White,
                                            fontSize = 12.sp,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = match.source + " to " + match.target,
                                        color = Color.Gray,
                                        fontSize = 10.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "By: ${match.createdBy}",
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = "Usage Count: ${match.usageCount}",
                                            color = Color.Gray,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }


}


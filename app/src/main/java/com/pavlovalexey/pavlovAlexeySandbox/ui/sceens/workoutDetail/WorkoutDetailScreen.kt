package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.workoutDetail

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.UiState
import androidx.hilt.navigation.compose.hiltViewModel
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.AlexIconButton
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.VSpacer
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp12
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp16
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp244
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailScreen(
    workoutId: Int,
    navController: NavHostController,
    viewModel: WorkoutDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val workout by viewModel.workout.collectAsState()
    val videoUrl by viewModel.videoUrl.collectAsState()

    LaunchedEffect(videoUrl) {
        videoUrl?.let { link ->
            val encoded = Uri.encode(link)
            navController.navigate("video_player?videoUrl=$encoded")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали тренировки") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is UiState.Success -> {
                    workout?.let { w ->
                        Column(
                            Modifier
                                .verticalScroll(rememberScrollState())
                                .padding(dp16)
                        ) {
                            w.imageUrl?.let { img ->
                                Image(
                                    painter = rememberImagePainter(img),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(dp244)
                                        .clip(RoundedCornerShape(dp12)),
                                    contentScale = ContentScale.Crop
                                )
                                VSpacer()
                            }
                            Text(w.title, style = MaterialTheme.typography.headlineSmall)
                            Spacer(Modifier.height(dp8))
                            Text(w.description, style = MaterialTheme.typography.bodyMedium)
                            Spacer(Modifier.height(dp8))
                            Text("Тип: ${w.type}", style = MaterialTheme.typography.bodySmall)
                            Text(
                                "Длительность: ${w.duration} мин",
                                style = MaterialTheme.typography.bodySmall
                            )
                            VSpacer()

                            AlexIconButton(
                                text = "Воспроизвести видео",
                                icon = Icons.Filled.OndemandVideo,
                                onClick = { viewModel.loadVideo() },
                                buttonColor = Color.Black,
                                isFillMaxWidth = true,
                                outlined = true
                            )
                        }
                    }
                }
            }
        }
    }
}
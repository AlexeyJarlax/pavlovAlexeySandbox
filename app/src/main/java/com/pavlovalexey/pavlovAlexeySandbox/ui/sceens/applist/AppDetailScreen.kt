package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.applist

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.UiState
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.AlexIconButton
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.MatrixBackground
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.VSpacer
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp16
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp8

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    navController: NavHostController,
    viewModel: AppDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val details by viewModel.details.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(details?.appName ?: "Информация о приложении") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(dp16)
        ) {
            MatrixBackground(100)
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
                    details?.let { app ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(dp8)
                        ) {
                            Text("Название: ${app.appName}")
                            HorizontalDivider()
                            Text("Имя пакета: ${app.packageName}")
                            HorizontalDivider()
                            Text("versionName: ${app.versionName ?: "—"}")
                            HorizontalDivider()
                            Text("versionCode: ${app.versionCode ?: 0}")
                            HorizontalDivider()
                            app.apkSizeBytes?.let { bytes ->
                                val mb = bytes.toDouble() / (1024 * 1024)
                                Text("Размер APK: ${"%.2f".format(mb)} МБ")
                                HorizontalDivider()
                            }
                            VSpacer()
                            Text(text = "Контрольная сумма APK по SHA-256:")
                            Text(text = app.apkChecksumSha256 ?: "Не удалось посчитать")
                            VSpacer(24)

                            AlexIconButton(
                                onClick = {
                                    val intent =
                                        context.packageManager.getLaunchIntentForPackage(
                                            app.packageName
                                        )
                                    if (intent != null) {
                                        context.startActivity(intent)
                                    }
                                },
                                isFillMaxWidth = false,
                                outlined = true,
                                text = "Открыть приложение"
                            )
                        }
                    }
                }
            }
        }
    }
}
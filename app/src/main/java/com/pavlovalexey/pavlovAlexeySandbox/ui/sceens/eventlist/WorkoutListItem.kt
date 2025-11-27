package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.eventlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.pavlovalexey.pavlovAlexeySandbox.model.Workout
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.HSpacer
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components.VSpacer
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp16
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp64
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.dp8

@Composable
fun WorkoutListItem(item: Workout, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(dp16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item.imageUrl?.let {
            Image(
                painter = rememberImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .size(dp64)
                    .clip(RoundedCornerShape(dp8)),
                contentScale = ContentScale.Crop
            )
            HSpacer()
        }
        Column {
            Text(item.title, style = MaterialTheme.typography.titleMedium)
            Text("${item.type} • ${item.duration} мин.", style = MaterialTheme.typography.bodySmall)
        }
    }
}
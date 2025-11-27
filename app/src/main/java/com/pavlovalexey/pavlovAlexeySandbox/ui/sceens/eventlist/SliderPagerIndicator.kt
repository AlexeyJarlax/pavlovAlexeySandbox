package com.pavlovalexey.pavlovAlexeySandbox.ui.sceens.eventlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pavlovalexey.pavlovAlexeySandbox.R
import com.pavlovalexey.pavlovAlexeySandbox.model.Workout
import com.pavlovalexey.pavlovAlexeySandbox.ui.theme.*

@Composable
fun SliderPagerIndicator(
    workouts: List<Workout>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState: PagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { workouts.size }
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(dp200)
            .padding(horizontal = dp16)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(dp12))
        ) { page ->
            val workout = workouts[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onItemClick(workout.id) }
            ) {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(workout.imageUrl)
                        .error(R.drawable.ic_dashboard_black_24dp)
                        .fallback(R.drawable.ic_dashboard_black_24dp)
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${workout.duration} мин.",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(dp8)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = dp8),
            horizontalArrangement = Arrangement.spacedBy(dp4)
        ) {
            repeat(workouts.size) { index ->
                val color = if (pagerState.currentPage == index) Color.Black else Color.White
                Box(
                    modifier = Modifier
                        .size(dp8)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }
    }
}

package com.headertostickytext.app.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.headertostickytext.app.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeaderContent(title: String = R.string.app_name.getString(), url: String) =
    Box(modifier = Modifier.fillMaxSize()) {
        GlideImage(
            modifier = Modifier.matchParentSize(),
            model = url,
            transition = CrossFade,
            contentScale = ContentScale.Crop,
            contentDescription = R.string.image_dummy_description.getString(),
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            text = title,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        )
    }

@Preview(showBackground = true)
@Composable
fun HeaderContentPreview() {
    HeaderContent(url = HEADER_IMAGE_1)
}
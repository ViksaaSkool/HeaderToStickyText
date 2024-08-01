package com.headertostickytext.app


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

val STICKY_CONTENT_HEIGHT = 56.dp
val HEADER_HEIGHT = 200.dp


@Composable
fun HeaderToSticky(
    stickyContentHeight: Dp = STICKY_CONTENT_HEIGHT,
    headerHeight: Dp = HEADER_HEIGHT,
    scrollAction: Boolean = false,
    onScrollAction: (ScrollState) -> Unit = {},
    stickyText: String = "",
    stickyContentBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    stickyContent: @Composable AnimatedVisibilityScope.() -> Unit = {},
    headerContent: @Composable () -> Unit,
    headerContentBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    headerContentAlignment: Alignment = Alignment.BottomStart,
    containerContent: @Composable ColumnScope.() -> Unit,
    onScrollStateChange: (ScrollState) -> Unit = {} //in case you need the change for extra animations
) {

    val scrollState = rememberScrollState()

    val overlapHeightPx = with(LocalDensity.current) {
        headerHeight.toPx() - stickyContentHeight.toPx()
    }

    var isCollapsed by remember { mutableStateOf(false) }

    scrollState.let {
        isCollapsed = if (it.value > overlapHeightPx) {
            true
        } else {
            false
        }
        onScrollStateChange(it)
    }

    if (scrollAction) {
        LaunchedEffect(Unit) {
            onScrollAction(scrollState)
        }
    }

    Box(Modifier.fillMaxSize()) {
        StickyContent(
            stickyText = stickyText,
            stickyContentBackgroundColor = stickyContentBackgroundColor,
            stickyContent = stickyContent,
            height = stickyContentHeight,
            isCollapsed = isCollapsed
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(stickyContentBackgroundColor)
                .verticalScroll(scrollState)
        ) {
            Header(
                height = headerHeight, color = headerContentBackgroundColor,
                contentAlignment = headerContentAlignment
            ) {
                headerContent()
            }
            containerContent()
        }
    }

}

@Composable
private fun StickyContent(
    stickyContentBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    height: Dp = STICKY_CONTENT_HEIGHT,
    isCollapsed: Boolean,
    stickyText: String = "",
    stickyContent: @Composable AnimatedVisibilityScope.() -> Unit = {}
) {
    val color = if (isCollapsed) {
        stickyContentBackgroundColor
    } else {
        Color.Transparent
    }

    val textColor = if (isCollapsed) {
        MaterialTheme.colorScheme.onSurface
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .background(color)
            .fillMaxWidth()
            .zIndex(2f)
            .height(height)
            .padding(16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AnimatedVisibility(visible = isCollapsed) {
            if (stickyText.trim().isEmpty()) {
                stickyContent()
            } else {
                Text(
                    text = stickyText,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun Header(
    color: Color = MaterialTheme.colorScheme.surface,
    height: Dp = HEADER_HEIGHT,
    contentAlignment: Alignment = Alignment.BottomStart,
    expandedContent: @Composable () -> Unit = {}
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .background(color)
        .height(height),
    contentAlignment = contentAlignment
) {
    expandedContent()
}


@Preview(showBackground = true)
@Composable
fun HeaderToStickyPreview() = HeaderToSticky(
    headerContentBackgroundColor = MaterialTheme.colorScheme.tertiary,
    stickyText = "Sticky Text",
    headerContent = {
        Text(
            modifier = Modifier.padding(32.dp),
            text = "Header Text",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    },
    containerContent = {
        for (i in 1..50) {
            Text(text = "Content item$i")
        }
    }
)
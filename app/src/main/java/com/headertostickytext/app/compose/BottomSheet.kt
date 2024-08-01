package com.headertostickytext.app.compose


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.headertostickytext.app.HeaderToSticky
import com.headertostickytext.app.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(type: BottomSheetType, onDismiss: () -> Unit) {

    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = false)

    var hasReachedEnd by remember { mutableStateOf(false) }
    var shouldScrollToEnd by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val title = "${R.string.button_text.getString()} ${type.buttonNumber}"

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = bottomSheetState,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            HeaderToSticky(headerContent = {
                HeaderContent(title = title,
                    url = type.imageUrl)
            },
                stickyContent = {
                    Text(
                        text = title,
                        color = Color.Red,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                containerContent = {
                    for (i in 1..4) {
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = R.string.very_long_text.getString()
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }, scrollAction = shouldScrollToEnd,
                onScrollStateChange = {
                    hasReachedEnd = it.value == it.maxValue
                },
                onScrollAction = {
                    coroutineScope.launch {
                        it.animateScrollTo(it.maxValue)
                        shouldScrollToEnd = false
                    }
                })

            if (!hasReachedEnd && type.withFabAction()) {
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 32.dp, end = 8.dp),
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = {
                        shouldScrollToEnd = true
                    },
                ) {
                    Icon(
                        imageVector = R.drawable.ic_down.getVector(),
                        contentDescription = ""
                    )
                }
            }
        }


    }
}

@Preview
@Composable
fun BottomSheetPlainPreview() {
    var isOpened by remember { mutableStateOf(true) }

    if (isOpened) {
        BottomSheet(type = BottomSheetType.PLAIN) {
            isOpened = false
        }
    }
}


@Preview
@Composable
fun BottomSheetWithFabActionPreview() {
    var isOpened by remember { mutableStateOf(true) }

    if (isOpened) {
        BottomSheet(type = BottomSheetType.WITH_FAB_ACTION) {
            isOpened = false
        }
    }
}
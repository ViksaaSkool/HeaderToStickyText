package com.headertostickytext.app.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.headertostickytext.app.R

data class BottomSheetData(
    val isOpened: Boolean = false,
    val button: Int = 0
)

@Composable
fun MainContent() {

    val textModifier = Modifier.padding(8.dp)
    var bottomSheetData by remember { mutableStateOf(BottomSheetData()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    modifier = textModifier,
                    text = "${R.string.dummy_title.getString()} 1"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(modifier = textModifier, text = R.string.long_text.getString())
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    modifier = textModifier,
                    text = "${R.string.dummy_title.getString()} 1"
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = textModifier,
                    text = R.string.long_text.getString()
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                bottomSheetData = bottomSheetData.copy(isOpened = true, button = 1)
            }) {
                Text(text = "${R.string.button_text.getString()} 1")
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(onClick = {
                bottomSheetData = bottomSheetData.copy(isOpened = true, button = 2)
            }) {
                Text(text = "${R.string.button_text.getString()} 2")

            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(modifier = textModifier, text = R.string.very_long_text.getString())
    }

    if (bottomSheetData.isOpened) {
        BottomSheet(type = BottomSheetType.fromButtonNumber(bottomSheetData.button)) {
            bottomSheetData = bottomSheetData.copy(isOpened = false)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() = MainContent()
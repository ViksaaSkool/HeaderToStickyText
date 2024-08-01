package com.headertostickytext.app.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource

@Composable
fun Int.getString() = stringResource(id = this)

@Composable
fun Int.getVector() = ImageVector.vectorResource(id = this)

enum class BottomSheetType(val buttonNumber: Int, val imageUrl: String){
    PLAIN(1, HEADER_IMAGE_1),
    WITH_FAB_ACTION(2, HEADER_IMAGE_2);

    companion object {
        fun fromButtonNumber(buttonNumber: Int): BottomSheetType {
            return entries.find { it.buttonNumber == buttonNumber }?: PLAIN
        }
    }
}

fun BottomSheetType.withFabAction() = this == BottomSheetType.WITH_FAB_ACTION

const val HEADER_IMAGE_0 = "https://tinyurl.com/24eu2khd"
const val HEADER_IMAGE_1 = "https://tinyurl.com/2aq2jc2t"
const val HEADER_IMAGE_2 = "https://tinyurl.com/22aty5mv"


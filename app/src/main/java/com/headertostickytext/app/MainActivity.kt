package com.headertostickytext.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.headertostickytext.app.compose.HEADER_IMAGE_0
import com.headertostickytext.app.compose.HEADER_IMAGE_2
import com.headertostickytext.app.compose.HeaderContent
import com.headertostickytext.app.compose.MainContent
import com.headertostickytext.app.compose.getString


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeaderToSticky(headerContent = {
                HeaderContent(url = HEADER_IMAGE_0)
            },
                stickyText = R.string.app_name.getString(),
                containerContent = {
                    MainContent()
                })
        }
    }
}

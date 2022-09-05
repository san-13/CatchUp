package com.sv.catchup.ui.videoCall

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.sv.catchup.R
import io.agora.agorauikit_android.*
import io.agora.rtc.Constants

class VideoConf : Activity() {
    // Fill the App ID of your project generated on Agora Console.
    private val appId = "3f7864367b3e439bb99a4cd31252c1de"

    // Fill the temp token generated on Agora Console.
    private val token = "007eJxTYPgxV3X9u3nfGhrfGhj/+lC0Pi9254kpG7zbL24U55fMuflcgcE4zdzCzMTYzDzJONXE2DIpydIy0SQ5xdjQyNQo2TAldcUc4WSNdyLJn8sVWRkZIBDEZ2cIdCzISCxOZWAAAMkiJIE="

    // Fill the channel name.
    private val channelName = "QAphase"

    private var agView: AgoraVideoViewer? = null
    private fun initializeAndJoinChannel(){
        // Create AgoraVideoViewer instance
        try {
            agView = AgoraVideoViewer(
                this, AgoraConnectionData(appId),
            )
        } catch (e: Exception) {
            print("Could not initialize AgoraVideoViewer. Check your App ID is valid.")
            print(e.message)
            return
        }
        // Fill the parent ViewGroup (MainActivity)
        this.addContentView(
            agView,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        // Check permission and join channel
        if (AgoraVideoViewer.requestPermissions(this)) {
            agView!!.join(channelName, token = token, role = Constants.CLIENT_ROLE_BROADCASTER)
        }

        else {
            val joinButton = Button(this)
            joinButton.text = "Allow Camera and Microphone, then click here"
            joinButton.setOnClickListener(View.OnClickListener {
                // When the button is clicked, check permissions again and join channel
                // if permissions are granted.
                if (AgoraVideoViewer.requestPermissions(this)) {
                    (joinButton.parent as ViewGroup).removeView(joinButton)
                    agView!!.join(channelName, token = token, role = Constants.CLIENT_ROLE_BROADCASTER)
                }
            })
            joinButton.setBackgroundColor(Color.GREEN)
            joinButton.setTextColor(Color.RED)
            this.addContentView(joinButton, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 300))
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_conf)
        initializeAndJoinChannel()
    }
}
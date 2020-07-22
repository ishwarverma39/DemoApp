package com.livtech.demo.core.models


import com.google.gson.annotations.SerializedName
import org.joda.time.format.DateTimeFormat

data class BranchMessage(
    @SerializedName("agent_id")
    var agentId: String?,
    @SerializedName("body")
    var body: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("thread_id")
    var threadId: Int,
    @SerializedName("timestamp")
    var timestamp: String, //"2020-07-17T10:31:55.698Z"
    @SerializedName("user_id")
    var userId: String
) {
    val serverDateFormat
        get() = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.000'Z'")

    val messageDate: String
        get() {
            val dateFormat = DateTimeFormat.forPattern("EEE d, MMM")
            return dateFormat.print(serverDateFormat.parseDateTime(timestamp))
        }

    val messageTime: String
        get() {
            val timeFormat = DateTimeFormat.forPattern("h:mm a")
            return timeFormat.print(serverDateFormat.parseDateTime(timestamp))
        }
    val dateAndTime
        get() = "$messageTime, $messageDate"

    val sender: String?
        get() = if (agentId.isNullOrEmpty()) "By: $userId" else "By: $agentId"
}
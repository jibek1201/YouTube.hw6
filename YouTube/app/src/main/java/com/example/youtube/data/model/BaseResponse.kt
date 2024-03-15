package com.example.youtube.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse(
    @SerializedName("etag")
    val eTag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
):Serializable{
    data class Item(
        val contentDetails: ContentDetails,
        @SerializedName("etag")
        val eTag: String,
        val id: String,
        val kind: String,
        val snippet: Snippet
    ) {
        data class ContentDetails(
            val itemCount: Int
        )

        data class Snippet(
            val channelId: String,
            val channelTitle: String,
            val description: String,
            val localized: Localized,
            val publishedAt: String,
            val thumbnails: Thumbnails,
            val title: String
        ) {
            data class Localized(
                val description: String,
                val title: String
            )

            data class Thumbnails(
                val default: Default,
                val high: High,
                @SerializedName("maxres")
                val maxRes: Maxres,
                val medium: Medium,
                val standard: Standard
            ) {
                data class Default(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class High(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Maxres(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Medium(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Standard(
                    val height: Int,
                    val url: String,
                    val width: Int
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int,
        val totalResults: Int
    )
}
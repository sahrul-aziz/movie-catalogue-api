package com.dicoding.submission.movieapi.model

data class ErrorResponse(
    private var statusCode: Int = 0,
    private var statusMessage: String = "",
    private var success: Boolean = false
)
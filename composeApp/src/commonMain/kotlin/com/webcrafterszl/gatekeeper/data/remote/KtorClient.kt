package com.webcrafterszl.gatekeeper.data.remote

import io.ktor.client.HttpClient

expect fun createPlatformHttpClient(): HttpClient

object KtorClient {
	val client: HttpClient by lazy { createPlatformHttpClient() }
}
package org.smartmetering.client.data.api

import org.smartmetering.client.API_ENDPOINT
import retrofit2.http.GET

interface ApiEndpoint {
    @GET(API_ENDPOINT)
    suspend fun getData(): ArrayList<UnitResponseItem>
}
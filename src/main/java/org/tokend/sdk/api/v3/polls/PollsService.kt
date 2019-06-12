package org.tokend.sdk.api.v3.polls

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.PollResource
import org.tokend.sdk.api.generated.resources.VoteResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface PollsService {
    @GET("v3/polls")
    @JvmSuppressWildcards
    fun getPolls(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<PollResource>>>

    @GET("v3/polls/{id}")
    @JvmSuppressWildcards
    fun getPollById(@Path("id") id: String,
                    @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<PollResource>>

    @GET("v3/polls/{id}/relationships/votes")
    @JvmSuppressWildcards
    fun getVotes(@Path("id") pollId: String,
                 @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<VoteResource>>>

    @GET("v3/polls/{id}/relationships/votes/{voter}")
    @JvmSuppressWildcards
    fun getVoteById(@Path("id") pollId: String,
                    @Path("voter") voterAccountId: String,
                    @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<VoteResource>>

    @GET("v3/votes/{voter}")
    fun getVotesByVoter(@Path("voter") accountId: String,
                        @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<VoteResource>>>
}
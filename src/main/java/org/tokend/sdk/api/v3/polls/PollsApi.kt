package org.tokend.sdk.api.v3.polls

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.PollResource
import org.tokend.sdk.api.generated.resources.VoteResource
import org.tokend.sdk.api.v3.polls.params.PollParams
import org.tokend.sdk.api.v3.polls.params.PollsPageParams
import org.tokend.sdk.api.v3.polls.params.VoteParams
import org.tokend.sdk.api.v3.polls.params.VotesPageParams

open class PollsApi(
        protected open val pollsService: PollsService
) {
    open fun getPolls(params: PollsPageParams? = null): ApiRequest<DataPage<PollResource>> {
        return MappedRetrofitApiRequest(
                pollsService.getPolls(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getPollById(pollId: String,
                         params: PollParams? = null): ApiRequest<PollResource> {
        return MappedRetrofitApiRequest(
                pollsService.getPollById(
                        pollId,
                        params.map()
                ),
                JSONAPIDocument<PollResource>::get
        )
    }

    open fun getVotes(pollId: String,
                      params: VotesPageParams? = null): ApiRequest<DataPage<VoteResource>> {
        return MappedRetrofitApiRequest(
                pollsService.getVotes(
                        pollId,
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getVoteById(pollId: String,
                         voterAccountId: String,
                         params: VoteParams?): ApiRequest<VoteResource> {
        return MappedRetrofitApiRequest(
                pollsService.getVoteById(
                        pollId,
                        voterAccountId,
                        params.map()
                ),
                JSONAPIDocument<VoteResource>::get
        )
    }
}
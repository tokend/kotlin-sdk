package org.tokend.sdk.api.integrations.booking

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.booking.model.CreateBookingRequestAttributes
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BookingResource
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BusinessResource
import org.tokend.sdk.api.integrations.booking.params.BookingsPageParams
import org.tokend.sdk.api.integrations.booking.params.BusinessesPageParams

open class BookingApi(
        protected open val bookingService: BookingService
) {
    @JvmOverloads
    open fun getBusinesses(params: BusinessesPageParams? = null): ApiRequest<DataPage<BusinessResource>> {
        return MappedRetrofitApiRequest(
                bookingService.getBusinesses(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getBusiness(businessId: String): ApiRequest<BusinessResource> {
        return MappedRetrofitApiRequest(
                bookingService.getBusinessById(businessId),
                JSONAPIDocument<BusinessResource>::get
        )
    }

    @JvmOverloads
    open fun getBookings(businessId: String,
                         params: BookingsPageParams? = null): ApiRequest<DataPage<BookingResource>> {
        return MappedRetrofitApiRequest(
                bookingService.getBookings(
                        businessId,
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun createBooking(businessId: String,
                           attributes: CreateBookingRequestAttributes): ApiRequest<BookingResource> {
        return MappedRetrofitApiRequest(
                bookingService.createBooking(
                        businessId,
                        attributes
                ),
                JSONAPIDocument<BookingResource>::get
        )
    }

    open fun cancelBooking(businessId: String,
                           bookingId: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                bookingService.cancelBooking(businessId, bookingId)
        )
    }
}
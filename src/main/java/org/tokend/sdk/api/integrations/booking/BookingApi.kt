package org.tokend.sdk.api.integrations.booking

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.booking.model.CreateBookingRequestAttributes
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BookingResource
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BusinessResource
import org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources.FreeBusyResource
import org.tokend.sdk.api.integrations.booking.params.BookingsPageParams
import org.tokend.sdk.api.integrations.booking.params.BusinessesPageParams
import org.tokend.sdk.api.integrations.booking.params.FreeBusyParams
import org.tokend.sdk.api.integrations.booking.params.FreeRoomsParams

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
    open fun getBusinessBookings(businessId: String,
                                 params: BookingsPageParams? = null): ApiRequest<DataPage<BookingResource>> {
        return MappedRetrofitApiRequest(
                bookingService.getBusinessBookings(
                        businessId,
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getBookings(params: BookingsPageParams? = null): ApiRequest<DataPage<BookingResource>> {
        return MappedRetrofitApiRequest(
                bookingService.getBookings(
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getPaymentAccountId(): ApiRequest<String> {
        return MappedRetrofitApiRequest(
                bookingService.getPaymentAccount(),
                { it.get().id }
        )
    }

    open fun createBooking(businessId: String,
                           attributes: CreateBookingRequestAttributes): ApiRequest<BookingResource> {
        return MappedRetrofitApiRequest(
                bookingService.createBooking(
                        businessId,
                        DataEntity(attributes)
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

    open fun getFreeRooms(calendarId: String,
                          params: FreeRoomsParams): ApiRequest<List<String>> {
        return MappedRetrofitApiRequest(
                bookingService.getFreeRooms(
                        calendarId,
                        params.map()
                ),
                { it.get().map(BaseResource::getId) }
        )
    }

    open fun getFreeBusy(calendarId: String,
                         params: FreeBusyParams): ApiRequest<List<FreeBusyResource>> {
        return MappedRetrofitApiRequest(
                bookingService.getFreeBusy(
                        calendarId,
                        params.map()
                ),
                JSONAPIDocument<List<FreeBusyResource>>::get
        )
    }

    open fun getBookingByReference(reference: String): ApiRequest<BookingResource> {
        return MappedRetrofitApiRequest(
                bookingService.getBookingByReference(reference),
                JSONAPIDocument<BookingResource>::get
        )
    }
}
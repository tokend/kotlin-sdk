package org.tokend.sdk.api.integrations.booking

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.booking.model.CreateBookingRequestAttributes
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BookingResource
import org.tokend.sdk.api.integrations.booking.model.generated.resources.BusinessResource
import retrofit2.Call
import retrofit2.http.*

interface BookingService {
    @GET("integrations/booking/businesses")
    @JvmSuppressWildcards
    fun getBusinesses(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BusinessResource>>>

    @GET("integrations/booking/businesses/{id}")
    @JvmSuppressWildcards
    fun getBusinessById(@Path("id") businessId: String): Call<JSONAPIDocument<BusinessResource>>

    @GET("integrations/booking/businesses/{businessId}/bookings")
    @JvmSuppressWildcards
    fun getBusinessBookings(@Path("businessId") businessId: String,
                            @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BookingResource>>>

    @GET("integrations/booking/bookings")
    @JvmSuppressWildcards
    fun getBookings(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BookingResource>>>

    @GET("integrations/booking/address")
    fun getPaymentAccount(): Call<JSONAPIDocument<BaseResource>>

    @POST("integrations/booking/businesses/{businessId}/bookings")
    @JvmSuppressWildcards
    fun createBooking(@Path("businessId") businessId: String,
                      @Body body: DataEntity<CreateBookingRequestAttributes>): Call<JSONAPIDocument<BookingResource>>

    @GET("integrations/booking/businesses/{businessId}/bookings/{id}")
    @JvmSuppressWildcards
    fun cancelBooking(@Path("businessId") businessId: String,
                      @Path("id") bookingId: String): Call<Void>
}
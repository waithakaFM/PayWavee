package com.francis.paywavee.model.service.mpesa

import com.github.daraja.driver.DarajaDriver
import com.github.daraja.model.requests.STKPushRequest
import com.github.daraja.utils.*

val darajaDriver = DarajaDriver(
    consumerKey ="esjwBhoauI9WZlY8qBKx4laROuBkd7aI",
    consumerSecret = "NcXG72gKwHNBctYy",
    environment = Environment.SandBox()
)

fun darajaSTK(phone: String, amount: String) = STKPushRequest(
        businessShortCode = "174379",
        password = getPassword("174379",
            "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
            timestamp),
        timestamp = timestamp,
        mpesaTransactionType = TransactionType.CustomerPayBillOnline(),
        amount = amount,
        partyA = sanitizePhoneNumber("254$phone"),
        partyB = "174379",
        phoneNumber = sanitizePhoneNumber("254$phone"),
        callBackURL = "https://mydomain.com/path",
        accountReference = "Dlight", // Account reference
        transactionDesc = "Dlight STK PUSH" // Transaction description
    )
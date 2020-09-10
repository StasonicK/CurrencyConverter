package com.eburg_soft.currencyconverter.utils

import com.eburg_soft.currencyconverter.data.datasource.database.models.CurrencyConversionEntity
import com.eburg_soft.currencyconverter.data.datasource.network.Rates
import com.eburg_soft.currencyconverter.data.datasource.network.models.CurrencyConversionResponse
import java.util.Collections

object TestUtil {

    val CURRENCY_CONVERSION_ONE = CurrencyConversionEntity(1.0, "USD", 1.30, "CAD", "01.09.2020")
    val CURRENCY_CONVERSION_TWO = CurrencyConversionEntity(1.0, "USD", 7.75, "HKD", "02.09.2020")

    val CURRENCY_CONVERSION_RES_ONE = CurrencyConversionResponse(
        Rates(
            CAD = 1.3014098607,
            HKD = 7.7500625678,
            ISK = 137.2320013348,
            PHP = 48.5284057729,
            DKK = 6.2095603571,
            HUF = 295.3366146659,
            CZK = 21.8787019271,
            GBP = 0.742262451,
            RON = 4.0375406691,
            SEK = 8.6431133728,
            IDR = 14570.0008342371,
            INR = 72.9194126971,
            BRL = 5.4330524735,
            RUB = 73.535830483,
            HRK = 6.2834737632,
            JPY = 105.8813714858,
            THB = 31.0753316092,
            CHF = 0.9063985985,
            EUR = 0.8342370902,
            MYR = 4.142487695,
            BGN = 1.631600901,
            TRY = 7.3576374406,
            CNY = 6.8189705514,
            NOK = 8.7075998999,
            NZD = 1.4788520898,
            ZAR = 16.639609577,
            USD = 1.0,
            MXN = 21.7133561358,
            SGD = 1.3576374406,
            AUD = 1.3549678819,
            ILS = 3.3522148995,
            KRW = 1184.7167765079,
            PLN = 3.6643864186
        ), "USD", "2020-09-01"
    )

    val CURRENCY_CONVERSION_RES_TWO = CurrencyConversionResponse(
        Rates(
            CAD = 1.3063822612,
            HKD = 7.7501896973,
            ISK = 138.6898237923,
            PHP = 48.6173172582,
            DKK = 6.273670011,
            HUF = 302.4787117444,
            CZK = 22.205547593,
            GBP = 0.7490093584,
            RON = 4.0825394149,
            SEK = 8.6894022426,
            IDR = 14744.9962060535,
            INR = 73.199983138,
            BRL = 5.4131186241,
            RUB = 74.2496416828,
            HRK = 6.3514880701,
            JPY = 106.2305033302,
            THB = 31.27476604,
            CHF = 0.9104628615,
            EUR = 0.8430992328,
            MYR = 4.1460247871,
            BGN = 1.6489334795,
            TRY = 7.3845375601,
            CNY = 6.8270803474,
            NOK = 8.7732906163,
            NZD = 1.4788803642,
            ZAR = 16.7906584605,
            USD = 1.0,
            MXN = 21.8489166175,
            SGD = 1.3618581907,
            AUD = 1.3633757693,
            ILS = 3.3640502487,
            KRW = 1187.1427367001,
            PLN = 3.72531827
        ), "USD", "2020-09-02"
    )

    val CURRENCY_CONVERSIONS: List<CurrencyConversionResponse> = Collections.unmodifiableList(
        object : ArrayList<CurrencyConversionResponse>() {
            init {
                add(
                    CurrencyConversionResponse(
                        Rates(
                            CAD = 1.3014098607,
                            HKD = 7.7500625678,
                            ISK = 137.2320013348,
                            PHP = 48.5284057729,
                            DKK = 6.2095603571,
                            HUF = 295.3366146659,
                            CZK = 21.8787019271,
                            GBP = 0.742262451,
                            RON = 4.0375406691,
                            SEK = 8.6431133728,
                            IDR = 14570.0008342371,
                            INR = 72.9194126971,
                            BRL = 5.4330524735,
                            RUB = 73.535830483,
                            HRK = 6.2834737632,
                            JPY = 105.8813714858,
                            THB = 31.0753316092,
                            CHF = 0.9063985985,
                            EUR = 0.8342370902,
                            MYR = 4.142487695,
                            BGN = 1.631600901,
                            TRY = 7.3576374406,
                            CNY = 6.8189705514,
                            NOK = 8.7075998999,
                            NZD = 1.4788520898,
                            ZAR = 16.639609577,
                            USD = 1.0,
                            MXN = 21.7133561358,
                            SGD = 1.3576374406,
                            AUD = 1.3549678819,
                            ILS = 3.3522148995,
                            KRW = 1184.7167765079,
                            PLN = 3.6643864186
                        ), "USD", "2020-09-01"
                    )
                )
                add(
                    CurrencyConversionResponse(
                        Rates(
                            CAD = 1.3063822612,
                            HKD = 7.7501896973,
                            ISK = 138.6898237923,
                            PHP = 48.6173172582,
                            DKK = 6.273670011,
                            HUF = 302.4787117444,
                            CZK = 22.205547593,
                            GBP = 0.7490093584,
                            RON = 4.0825394149,
                            SEK = 8.6894022426,
                            IDR = 14744.9962060535,
                            INR = 73.199983138,
                            BRL = 5.4131186241,
                            RUB = 74.2496416828,
                            HRK = 6.3514880701,
                            JPY = 106.2305033302,
                            THB = 31.27476604,
                            CHF = 0.9104628615,
                            EUR = 0.8430992328,
                            MYR = 4.1460247871,
                            BGN = 1.6489334795,
                            TRY = 7.3845375601,
                            CNY = 6.8270803474,
                            NOK = 8.7732906163,
                            NZD = 1.4788803642,
                            ZAR = 16.7906584605,
                            USD = 1.0,
                            MXN = 21.8489166175,
                            SGD = 1.3618581907,
                            AUD = 1.3633757693,
                            ILS = 3.3640502487,
                            KRW = 1187.1427367001,
                            PLN = 3.72531827
                        ), "USD", "2020-09-02"
                    )
                )
            }
        }
    )
}
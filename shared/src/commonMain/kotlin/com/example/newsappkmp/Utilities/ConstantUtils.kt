package com.example.newsappkmp.Utilities

class ConstantUtils {
    object Constants{
        const val API_KEY = "6d9ecbb0bd2e40b7a698551447573071"
    }

    object NetworkConfig{
        const val BASE_URL : String = "https://newsapi.org"
    }

    object ApiEndPoints{
        const val TOP_HEADLINES : String = "/v2/top-headlines"
        const val TOP_HEADLINES_SOURCES : String = "/v2/top-headlines/sources"
    }

    object ApiParameters{
        const val PARAMETER_API_KEY = "apiKey"
        const val PARAMETER_LANGUAGE = "language"
    }

    object LanguageCodes{
        const val LANG_CODE_ENGLISH = "en"
    }

    object HttpErrorCodes{
        const val SUCCESS = 200
    }

    object HttpStatusDescription{
        const val RESPONSE_SUCCESS = "ok"
        const val RESPONSE_FAILURE = "error"
    }
}
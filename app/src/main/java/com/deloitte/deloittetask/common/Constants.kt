package com.deloitte.deloittetask.common

class Constants {
    object Languages {
        const val ENGLISH = "en"
        const val ARABIC = "ar"
    }
    object Endpoints {
        const val BASE_URL = "https://api.nytimes.com/"
        const val API_KEY ="HsAguwwBi5eQDfHe3AI0fBpCcnpHzppB"
        const val VIEWED_ARTICLES = "svc/mostpopular/v2/viewed/{period}.json"
    }

    object RoomDB {
        const val DATABASE_NAME = "article_database"
    }
    /////////////////////////////////////SHARED PREFERENCE KEYS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    object SharedPreferenceKeys {
        const val LOGGED_USER_ID = "loggedUserId"
    }


    /////////////////////////////////////NETWORK STATUS CODES\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    object NetworkStatusCode {
        const val STATUS_OK = 200
        const val STATUS_CREATED = 203
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val NOT_FOUND = 404
        const val INTERNAL_SERVER_ERROR = 500


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

}
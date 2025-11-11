package io.lackstudio.module.kmp.apiclient.unsplash.utils.constants

internal object JsonKeys {
    // Common
    object Common {
        const val USER_ID = "user_id"
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"

        const val PROFILE_IMAGE = "profile_image"
        const val FOR_HIRE = "for_hire"
    }

    object Photo {
        const val BLUR_HASH = "blur_hash"
        const val ASSET_TYPE = "asset_type"
    }

    // User
    object User {
        const val ACCEPTED_TOS = "accepted_tos"
        const val ALLOW_MESSAGES = "allow_messages"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val NUMERIC_ID = "numeric_id"
    }

    object Social {
        const val TWITTER_USERNAME = "twitter_username"
        const val PORTFOLIO_URL = "portfolio_url"
        const val INSTAGRAM_USERNAME = "instagram_username"
        const val PAYPAL_EMAIL = "paypal_email"
    }

    object Statistics {
        const val TOTAL_COLLECTIONS = "total_collections"
        const val TOTAL_LIKES = "total_likes"
        const val TOTAL_PHOTOS = "total_photos"
        const val TOTAL_PROMOTED_PHOTOS = "total_promoted_photos"
        const val TOTAL_ILLUSTRATIONS = "total_illustrations"
        const val TOTAL_PROMOTED_ILLUSTRATIONS = "total_promoted_illustrations"
    }

    object Urls {
        const val SMALL_S3 = "small_s3"
    }
}
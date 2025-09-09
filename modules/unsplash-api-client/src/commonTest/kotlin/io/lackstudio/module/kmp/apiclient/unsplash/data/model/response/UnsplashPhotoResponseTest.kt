package io.lackstudio.module.kmp.apiclient.unsplash.data.model.response

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class UnsplashPhotoResponseTest {
    private val json = Json {
        ignoreUnknownKeys = true // 測試時也建議開啟，模擬實際情況
        isLenient = true
    }

    @Test
    fun `UnsplashPhotoResponse should deserialize correctly when all fields are present`() {
        val jsonString = """
            {
                "id": "4ICax0QMs8U",
                "slug": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                "alternative_slugs": {
                    "en": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                    "es": "un-hombre-con-una-camisa-verde-sosteniendo-un-telefono-celular-4ICax0QMs8U",
                    "ja": "緑色のシャツを着た男性が携帯電話を手に-4ICax0QMs8U",
                    "fr": "un-homme-en-chemise-verte-tenant-un-telephone-portable-4ICax0QMs8U",
                    "it": "un-uomo-con-una-camicia-verde-che-tiene-un-cellulare-4ICax0QMs8U",
                    "ko": "초록색-셔츠를-입은-남자가-휴대전화를-들고-있다-4ICax0QMs8U",
                    "de": "ein-mann-in-einem-grunen-hemd-mit-einem-handy-in-der-hand-4ICax0QMs8U",
                    "pt": "um-homem-em-uma-camisa-verde-segurando-um-telefone-celular-4ICax0QMs8U",
                    "id": "seorang-pria-berbaju-hijau-memegang-ponsel-4ICax0QMs8U"
                },
                "created_at": "2024-07-03T23:17:57Z",
                "updated_at": "2025-07-29T21:50:47Z",
                "promoted_at": null,
                "width": 4000,
                "height": 6000,
                "color": "#59c073",
                "blur_hash": "LGDddu4prJb@].cCP*w30{paIC'$'l",
                "description": null,
                "alt_description": "A man in a green shirt holding a cell phone",
                "breadcrumbs": [],
                "urls": {
                    "raw": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8&ixlib=rb-4.1.0",
                    "full": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?crop=entropy&cs=srgb&fm=jpg&ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8&ixlib=rb-4.1.0&q=85",
                    "regular": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8&ixlib=rb-4.1.0&q=80&w=1080",
                    "small": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8&ixlib=rb-4.1.0&q=80&w=400",
                    "thumb": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8&ixlib=rb-4.1.0&q=80&w=200",
                    "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1720048171098-dba33b2c4806"
                },
                "links": {
                    "self": "https://api.unsplash.com/photos/a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                    "html": "https://unsplash.com/photos/a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                    "download": "https://unsplash.com/photos/4ICax0QMs8U/download?ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8",
                    "download_location": "https://api.unsplash.com/photos/4ICax0QMs8U/download?ixid=M3w3NTQyNTV8MHwxfGFsbHx8fHx8fHx8fDE3NTM4NDM2Mjl8"
                },
                "likes": 81,
                "liked_by_user": false,
                "current_user_collections": [],
                "sponsorship": null,
                "topic_submissions": {},
                "asset_type": "photo",
                "user": {
                    "id": "eySMK9KwmJU",
                    "updated_at": "2025-03-06T19:27:37Z",
                    "username": "samsungmemory",
                    "name": "Samsung Memory",
                    "first_name": "Samsung",
                    "last_name": "Memory",
                    "twitter_username": "SamsungSemiUS",
                    "portfolio_url": "http://www.samsung.com/us/computing/memory-storage/",
                    "bio": "Memory for every endeavor – get fast storage solutions that work seamlessly with your devices.",
                    "location": null,
                    "links": {
                        "self": "https://api.unsplash.com/users/samsungmemory",
                        "html": "https://unsplash.com/@samsungmemory",
                        "photos": "https://api.unsplash.com/users/samsungmemory/photos",
                        "likes": "https://api.unsplash.com/users/samsungmemory/likes",
                        "portfolio": "https://api.unsplash.com/users/samsungmemory/portfolio",
                        "following": "https://api.unsplash.com/users/samsungmemory/following",
                        "followers": "https://api.unsplash.com/users/samsungmemory/followers"
                    },
                    "profile_image": {
                        "small": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32",
                        "medium": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
                        "large": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
                    },
                    "instagram_username": "samsungsemiconductor",
                    "total_collections": 1,
                    "total_likes": 0,
                    "total_photos": 880,
                    "total_promoted_photos": 38,
                    "total_illustrations": 0,
                    "total_promoted_illustrations": 0,
                    "accepted_tos": true,
                    "for_hire": false,
                    "social": {
                        "instagram_username": "samsungsemiconductor",
                        "portfolio_url": "http://www.samsung.com/us/computing/memory-storage/",
                        "twitter_username": "SamsungSemiUS",
                        "paypal_email": null
                    }
                },
                "exif": {
                    "make": "SONY",
                    "model": "ILCE-7M3",
                    "name": "SONY, ILCE-7M3",
                    "exposure_time": "1/400",
                    "aperture": "2.8",
                    "focal_length": "47.0",
                    "iso": 250
                },
                "location": {
                    "name": null,
                    "city": null,
                    "country": null,
                    "position": {
                        "latitude": 0.0,
                        "longitude": 0.0
                    }
                },
                "meta": {
                    "index": true
                },
                "public_domain": false,
                "tags": [
                    {
                        "type": "search",
                        "title": "ssd"
                    },
                    {
                        "type": "search",
                        "title": "portable ssd"
                    },
                    {
                        "type": "search",
                        "title": "green"
                    },
                    {
                        "type": "search",
                        "title": "phone"
                    },
                    {
                        "type": "search",
                        "title": "mobile phone"
                    },
                    {
                        "type": "search",
                        "title": "bag"
                    },
                    {
                        "type": "search",
                        "title": "electronics"
                    },
                    {
                        "type": "search",
                        "title": "accessory"
                    },
                    {
                        "type": "search",
                        "title": "cell phone"
                    },
                    {
                        "type": "search",
                        "title": "accessories"
                    }
                ],
                "views": 5795784,
                "downloads": 27810,
                "topics": [],
                "related_collections": {
                    "total": 3,
                    "type": "related",
                    "results": [
                        {
                            "id": "rrn_S6yWq3g",
                            "title": "Anatomy and health",
                            "description": null,
                            "published_at": "2022-08-23T19:49:07Z",
                            "last_collected_at": "2024-11-08T19:48:17Z",
                            "updated_at": "2024-11-08T19:48:17Z",
                            "featured": false,
                            "total_photos": 9,
                            "private": false,
                            "share_key": "6931aa23d8998db1a88bc9daccdb3046",
                            "links": {
                                "self": "https://api.unsplash.com/collections/rrn_S6yWq3g",
                                "html": "https://unsplash.com/collections/rrn_S6yWq3g/anatomy-and-health",
                                "photos": "https://api.unsplash.com/collections/rrn_S6yWq3g/photos",
                                "related": "https://api.unsplash.com/collections/rrn_S6yWq3g/related"
                            },
                            "user": {
                                "id": "U25rpuDzveg",
                                "updated_at": "2025-04-28T18:36:41Z",
                                "username": "onequim",
                                "name": "Monique Bastos",
                                "first_name": "Monique",
                                "last_name": "Bastos",
                                "twitter_username": null,
                                "portfolio_url": null,
                                "bio": null,
                                "location": null,
                                "links": {
                                    "self": "https://api.unsplash.com/users/onequim",
                                    "html": "https://unsplash.com/@onequim",
                                    "photos": "https://api.unsplash.com/users/onequim/photos",
                                    "likes": "https://api.unsplash.com/users/onequim/likes",
                                    "portfolio": "https://api.unsplash.com/users/onequim/portfolio"
                                },
                                "profile_image": {
                                    "small": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=32&h=32",
                                    "medium": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=64&h=64",
                                    "large": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=128&h=128"
                                },
                                "instagram_username": null,
                                "total_collections": 2,
                                "total_likes": 32,
                                "total_photos": 0,
                                "total_promoted_photos": 0,
                                "total_illustrations": 0,
                                "total_promoted_illustrations": 0,
                                "accepted_tos": true,
                                "for_hire": false,
                                "social": {
                                    "instagram_username": null,
                                    "portfolio_url": null,
                                    "twitter_username": null,
                                    "paypal_email": null
                                }
                            },
                            "cover_photo": {
                                "id": "4ICax0QMs8U",
                                "slug": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                "alternative_slugs": {
                                    "en": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                    "es": "un-hombre-con-una-camisa-verde-sosteniendo-un-telefono-celular-4ICax0QMs8U",
                                    "ja": "緑色のシャツを着た男性が携帯電話を手に-4ICax0QMs8U",
                                    "fr": "un-homme-en-chemise-verte-tenant-un-telephone-portable-4ICax0QMs8U",
                                    "it": "un-uomo-con-una-camicia-verde-che-tiene-un-cellulare-4ICax0QMs8U",
                                    "ko": "초록색-셔츠를-입은-남자가-휴대전화를-들고-있다-4ICax0QMs8U",
                                    "de": "ein-mann-in-einem-grunen-hemd-mit-einem-handy-in-der-hand-4ICax0QMs8U",
                                    "pt": "um-homem-em-uma-camisa-verde-segurando-um-telefone-celular-4ICax0QMs8U",
                                    "id": "seorang-pria-berbaju-hijau-memegang-ponsel-4ICax0QMs8U"
                                },
                                "created_at": "2024-07-03T23:17:57Z",
                                "updated_at": "2025-07-29T21:50:47Z",
                                "promoted_at": null,
                                "width": 4000,
                                "height": 6000,
                                "color": "#59c073",
                                "blur_hash": "LGDddu4prJb@].cCP*w30{paIC'$'l",
                                "description": null,
                                "alt_description": "A man in a green shirt holding a cell phone",
                                "breadcrumbs": [],
                                "urls": {
                                    "raw": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0",
                                    "full": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                    "regular": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                    "small": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                    "thumb": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                    "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1720048171098-dba33b2c4806"
                                },
                                "links": {
                                    "self": "https://api.unsplash.com/photos/a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                    "html": "https://unsplash.com/photos/a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                    "download": "https://unsplash.com/photos/4ICax0QMs8U/download",
                                    "download_location": "https://api.unsplash.com/photos/4ICax0QMs8U/download"
                                },
                                "likes": 81,
                                "liked_by_user": false,
                                "current_user_collections": [],
                                "sponsorship": null,
                                "topic_submissions": {},
                                "asset_type": "photo",
                                "user": {
                                    "id": "eySMK9KwmJU",
                                    "updated_at": "2025-03-06T19:27:37Z",
                                    "username": "samsungmemory",
                                    "name": "Samsung Memory",
                                    "first_name": "Samsung",
                                    "last_name": "Memory",
                                    "twitter_username": "SamsungSemiUS",
                                    "portfolio_url": "http://www.samsung.com/us/computing/memory-storage/",
                                    "bio": "Memory for every endeavor – get fast storage solutions that work seamlessly with your devices.",
                                    "location": null,
                                    "links": {
                                        "self": "https://api.unsplash.com/users/samsungmemory",
                                        "html": "https://unsplash.com/@samsungmemory",
                                        "photos": "https://api.unsplash.com/users/samsungmemory/photos",
                                        "likes": "https://api.unsplash.com/users/samsungmemory/likes",
                                        "portfolio": "https://api.unsplash.com/users/samsungmemory/portfolio",
                                        "following": "https://api.unsplash.com/users/samsungmemory/following",
                                        "followers": "https://api.unsplash.com/users/samsungmemory/followers"
                                    },
                                    "profile_image": {
                                        "small": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32",
                                        "medium": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
                                        "large": "https://images.unsplash.com/profile-1602741027167-c4d707fcfc85image?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
                                    },
                                    "instagram_username": "samsungsemiconductor",
                                    "total_collections": 1,
                                    "total_likes": 0,
                                    "total_photos": 880,
                                    "total_promoted_photos": 38,
                                    "total_illustrations": 0,
                                    "total_promoted_illustrations": 0,
                                    "accepted_tos": true,
                                    "for_hire": false,
                                    "social": {
                                        "instagram_username": "samsungsemiconductor",
                                        "portfolio_url": "http://www.samsung.com/us/computing/memory-storage/",
                                        "twitter_username": "SamsungSemiUS",
                                        "paypal_email": null
                                    }
                                }
                            },
                            "preview_photos": [
                                {
                                    "id": "4ICax0QMs8U",
                                    "slug": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                    "created_at": "2024-07-03T23:17:57Z",
                                    "updated_at": "2025-07-29T21:50:47Z",
                                    "blur_hash": "LGDddu4prJb@].cCP*w30{paIC'$'l",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1720048171098-dba33b2c4806"
                                    }
                                },
                                {
                                    "id": "rG3N_CXMeio",
                                    "slug": "person-with-tension-bands-on-legs-rG3N_CXMeio",
                                    "created_at": "2019-03-10T13:59:30Z",
                                    "updated_at": "2025-07-29T21:32:59Z",
                                    "blur_hash": "LlI}edozNwof4TWBR*ayoeWBV@R%",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1552226351-1ac7863af926?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1552226351-1ac7863af926?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1552226351-1ac7863af926?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1552226351-1ac7863af926?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1552226351-1ac7863af926?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1552226351-1ac7863af926"
                                    }
                                },
                                {
                                    "id": "IZOAOjvwhaM",
                                    "slug": "orange-and-black-usb-cable-on-brown-wooden-surface-IZOAOjvwhaM",
                                    "created_at": "2020-03-20T20:26:17Z",
                                    "updated_at": "2025-07-30T01:49:06Z",
                                    "blur_hash": "L6Ex-B4TD%%g1KMx-VxaJCMx9ax]",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1584735935682-2f2b69dff9d2?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1584735935682-2f2b69dff9d2?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1584735935682-2f2b69dff9d2?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1584735935682-2f2b69dff9d2?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1584735935682-2f2b69dff9d2?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1584735935682-2f2b69dff9d2"
                                    }
                                },
                                {
                                    "id": "5S40ixhBK-I",
                                    "slug": "person-massaging-other-persons-foot-5S40ixhBK-I",
                                    "created_at": "2018-12-22T07:32:19Z",
                                    "updated_at": "2025-07-30T00:01:03Z",
                                    "blur_hash": "LTH2QM?w%MnNIVD%D*R-X:-;i_NH",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1545463913-5083aa7359a6?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1545463913-5083aa7359a6?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1545463913-5083aa7359a6?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1545463913-5083aa7359a6?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1545463913-5083aa7359a6?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1545463913-5083aa7359a6"
                                    }
                                }
                            ]
                        },
                        {
                            "id": "Uf9kskKCf0U",
                            "title": "Photography II",
                            "description": null,
                            "published_at": "2023-10-23T18:20:11Z",
                            "last_collected_at": "2025-07-27T16:43:41Z",
                            "updated_at": "2025-07-27T16:43:41Z",
                            "featured": false,
                            "total_photos": 657,
                            "private": false,
                            "share_key": "d78a6a6a7d4a37d0e21cd558199021b7",
                            "links": {
                                "self": "https://api.unsplash.com/collections/Uf9kskKCf0U",
                                "html": "https://unsplash.com/collections/Uf9kskKCf0U/photography-ii",
                                "photos": "https://api.unsplash.com/collections/Uf9kskKCf0U/photos",
                                "related": "https://api.unsplash.com/collections/Uf9kskKCf0U/related"
                            },
                            "user": {
                                "id": "Ag_Ap-zVlX0",
                                "updated_at": "2025-06-01T10:17:06Z",
                                "username": "alihuraira",
                                "name": "Ali huraira",
                                "first_name": "Ali",
                                "last_name": "huraira",
                                "twitter_username": null,
                                "portfolio_url": null,
                                "bio": null,
                                "location": null,
                                "links": {
                                    "self": "https://api.unsplash.com/users/alihuraira",
                                    "html": "https://unsplash.com/@alihuraira",
                                    "photos": "https://api.unsplash.com/users/alihuraira/photos",
                                    "likes": "https://api.unsplash.com/users/alihuraira/likes",
                                    "portfolio": "https://api.unsplash.com/users/alihuraira/portfolio"
                                },
                                "profile_image": {
                                    "small": "https://images.unsplash.com/profile-1740815298036-001da85e26aaimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=32&h=32",
                                    "medium": "https://images.unsplash.com/profile-1740815298036-001da85e26aaimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=64&h=64",
                                    "large": "https://images.unsplash.com/profile-1740815298036-001da85e26aaimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=128&h=128"
                                },
                                "instagram_username": "alihuraira27",
                                "total_collections": 7,
                                "total_likes": 76946,
                                "total_photos": 0,
                                "total_promoted_photos": 0,
                                "total_illustrations": 0,
                                "total_promoted_illustrations": 0,
                                "accepted_tos": true,
                                "for_hire": false,
                                "social": {
                                    "instagram_username": "alihuraira27",
                                    "portfolio_url": null,
                                    "twitter_username": null,
                                    "paypal_email": null
                                }
                            },
                            "cover_photo": {
                                "id": "pUqX_VLlxDo",
                                "slug": "a-green-droplet-on-a-white-curved-bar-pUqX_VLlxDo",
                                "alternative_slugs": {
                                    "en": "a-green-droplet-on-a-white-curved-bar-pUqX_VLlxDo",
                                    "es": "una-gota-verde-en-una-barra-blanca-y-curva-pUqX_VLlxDo",
                                    "ja": "白い湾曲したバーに緑色の水滴が描かれています-pUqX_VLlxDo",
                                    "fr": "une-gouttelette-verte-sur-une-barre-blanche-et-incurvee-pUqX_VLlxDo",
                                    "it": "una-gocciolina-verde-su-una-barra-curva-bianca-pUqX_VLlxDo",
                                    "ko": "흰색의-구부러진-막대에-녹색-물방울이-있습니다-pUqX_VLlxDo",
                                    "de": "ein-gruner-tropfen-auf-einem-weissen-gebogenen-balken-pUqX_VLlxDo",
                                    "pt": "uma-gota-verde-em-uma-barra-branca-e-curva-pUqX_VLlxDo",
                                    "id": "tetesan-hijau-pada-batang-putih-melengkung-pUqX_VLlxDo"
                                },
                                "created_at": "2025-07-25T15:44:11Z",
                                "updated_at": "2025-07-29T21:21:55Z",
                                "promoted_at": "2025-07-26T00:12:00Z",
                                "width": 5250,
                                "height": 3750,
                                "color": "#d9d9d9",
                                "blur_hash": "L4P72%_3[}%3-;ofaej[L+Di6GO9",
                                "description": null,
                                "alt_description": "A green droplet on a white, curved bar.",
                                "breadcrumbs": [],
                                "urls": {
                                    "raw": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0",
                                    "full": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                    "regular": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                    "small": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                    "thumb": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                    "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1753458198977-d3687ac3fc67"
                                },
                                "links": {
                                    "self": "https://api.unsplash.com/photos/a-green-droplet-on-a-white-curved-bar-pUqX_VLlxDo",
                                    "html": "https://unsplash.com/photos/a-green-droplet-on-a-white-curved-bar-pUqX_VLlxDo",
                                    "download": "https://unsplash.com/photos/pUqX_VLlxDo/download",
                                    "download_location": "https://api.unsplash.com/photos/pUqX_VLlxDo/download"
                                },
                                "likes": 22,
                                "liked_by_user": false,
                                "current_user_collections": [],
                                "sponsorship": null,
                                "topic_submissions": {},
                                "asset_type": "photo",
                                "user": {
                                    "id": "SPYjm09zjBg",
                                    "updated_at": "2025-07-30T01:25:00Z",
                                    "username": "planetvolumes",
                                    "name": "Planet Volumes",
                                    "first_name": "Planet",
                                    "last_name": "Volumes",
                                    "twitter_username": null,
                                    "portfolio_url": null,
                                    "bio": "Anodé\r\nplanetvolumes@gmail.com",
                                    "location": "Solar System",
                                    "links": {
                                        "self": "https://api.unsplash.com/users/planetvolumes",
                                        "html": "https://unsplash.com/@planetvolumes",
                                        "photos": "https://api.unsplash.com/users/planetvolumes/photos",
                                        "likes": "https://api.unsplash.com/users/planetvolumes/likes",
                                        "portfolio": "https://api.unsplash.com/users/planetvolumes/portfolio"
                                    },
                                    "profile_image": {
                                        "small": "https://images.unsplash.com/profile-1753439624052-722cc533f6a1image?ixlib=rb-4.1.0&crop=faces&fit=crop&w=32&h=32",
                                        "medium": "https://images.unsplash.com/profile-1753439624052-722cc533f6a1image?ixlib=rb-4.1.0&crop=faces&fit=crop&w=64&h=64",
                                        "large": "https://images.unsplash.com/profile-1753439624052-722cc533f6a1image?ixlib=rb-4.1.0&crop=faces&fit=crop&w=128&h=128"
                                    },
                                    "instagram_username": null,
                                    "total_collections": 11,
                                    "total_likes": 2494,
                                    "total_photos": 1811,
                                    "total_promoted_photos": 552,
                                    "total_illustrations": 0,
                                    "total_promoted_illustrations": 0,
                                    "accepted_tos": true,
                                    "for_hire": false,
                                    "social": {
                                        "instagram_username": null,
                                        "portfolio_url": null,
                                        "twitter_username": null,
                                        "paypal_email": null
                                    }
                                }
                            },
                            "preview_photos": [
                                {
                                    "id": "pUqX_VLlxDo",
                                    "slug": "a-green-droplet-on-a-white-curved-bar-pUqX_VLlxDo",
                                    "created_at": "2025-07-25T15:44:11Z",
                                    "updated_at": "2025-07-29T21:21:55Z",
                                    "blur_hash": "L4P72%_3[}%3-;ofaej[L+Di6GO9",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1753458198977-d3687ac3fc67?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1753458198977-d3687ac3fc67"
                                    }
                                },
                                {
                                    "id": "uH-pxV6e-To",
                                    "slug": "an-island-football-field-stands-amidst-water-uH-pxV6e-To",
                                    "created_at": "2025-06-27T14:22:29Z",
                                    "updated_at": "2025-07-29T21:00:24Z",
                                    "blur_hash": "LMBy?9M_0ORkM{t8M|t7oNV[oyNF",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1751034005556-6c6ecfcfea32?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1751034005556-6c6ecfcfea32?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1751034005556-6c6ecfcfea32?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1751034005556-6c6ecfcfea32?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1751034005556-6c6ecfcfea32?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1751034005556-6c6ecfcfea32"
                                    }
                                },
                                {
                                    "id": "itEpkAMseE0",
                                    "slug": "yellow-tram-travels-down-a-narrow-city-street-itEpkAMseE0",
                                    "created_at": "2025-06-18T11:51:44Z",
                                    "updated_at": "2025-07-30T01:49:13Z",
                                    "blur_hash": "LeI#AB-oD%ofSjoJM{Rl~WoKM{t7",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1750247400011-1effe6b427a8?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1750247400011-1effe6b427a8?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1750247400011-1effe6b427a8?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1750247400011-1effe6b427a8?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1750247400011-1effe6b427a8?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1750247400011-1effe6b427a8"
                                    }
                                },
                                {
                                    "id": "Eq76mDacpto",
                                    "slug": "a-modern-workspace-with-computer-accessories-organized-Eq76mDacpto",
                                    "created_at": "2025-07-18T19:40:27Z",
                                    "updated_at": "2025-07-30T01:02:34Z",
                                    "blur_hash": "LMGatT0fo|R5J7ITg4tROYR+ogrq",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1752867494500-9ea9322f58c9?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1752867494500-9ea9322f58c9?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1752867494500-9ea9322f58c9?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1752867494500-9ea9322f58c9?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1752867494500-9ea9322f58c9?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1752867494500-9ea9322f58c9"
                                    }
                                }
                            ]
                        },
                        {
                            "id": "4PdTTeRhz2w",
                            "title": "images",
                            "description": null,
                            "published_at": "2024-12-30T06:58:37Z",
                            "last_collected_at": "2024-12-30T06:59:44Z",
                            "updated_at": "2024-12-30T06:59:48Z",
                            "featured": false,
                            "total_photos": 2,
                            "private": false,
                            "share_key": "fa318ba604f994eb1e69124003df51be",
                            "links": {
                                "self": "https://api.unsplash.com/collections/4PdTTeRhz2w",
                                "html": "https://unsplash.com/collections/4PdTTeRhz2w/images",
                                "photos": "https://api.unsplash.com/collections/4PdTTeRhz2w/photos",
                                "related": "https://api.unsplash.com/collections/4PdTTeRhz2w/related"
                            },
                            "user": {
                                "id": "Qtf6zvZ1-7U",
                                "updated_at": "2024-12-30T07:35:43Z",
                                "username": "behmah",
                                "name": "Behzad Yasini",
                                "first_name": "Behzad",
                                "last_name": "Yasini",
                                "twitter_username": null,
                                "portfolio_url": "https://images.unsplash.com/account",
                                "bio": null,
                                "location": "Herat",
                                "links": {
                                    "self": "https://api.unsplash.com/users/behmah",
                                    "html": "https://unsplash.com/@behmah",
                                    "photos": "https://api.unsplash.com/users/behmah/photos",
                                    "likes": "https://api.unsplash.com/users/behmah/likes",
                                    "portfolio": "https://api.unsplash.com/users/behmah/portfolio"
                                },
                                "profile_image": {
                                    "small": "https://images.unsplash.com/profile-1735542968173-8bc63da9c2ccimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=32&h=32",
                                    "medium": "https://images.unsplash.com/profile-1735542968173-8bc63da9c2ccimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=64&h=64",
                                    "large": "https://images.unsplash.com/profile-1735542968173-8bc63da9c2ccimage?ixlib=rb-4.1.0&crop=faces&fit=crop&w=128&h=128"
                                },
                                "instagram_username": null,
                                "total_collections": 1,
                                "total_likes": 0,
                                "total_photos": 0,
                                "total_promoted_photos": 0,
                                "total_illustrations": 0,
                                "total_promoted_illustrations": 0,
                                "accepted_tos": false,
                                "for_hire": false,
                                "social": {
                                    "instagram_username": null,
                                    "portfolio_url": "https://images.unsplash.com/account",
                                    "twitter_username": null,
                                    "paypal_email": null
                                }
                            },
                            "cover_photo": {
                                "id": "m27OTMegUyA",
                                "slug": "black-and-gray-treadmill-m27OTMegUyA",
                                "alternative_slugs": {
                                    "en": "black-and-gray-treadmill-m27OTMegUyA",
                                    "es": "cinta-de-correr-negra-y-gris-m27OTMegUyA",
                                    "ja": "黒とグレーのトレッドミル-m27OTMegUyA",
                                    "fr": "tapis-roulant-noir-et-gris-m27OTMegUyA",
                                    "it": "tapis-roulant-nero-e-grigio-m27OTMegUyA",
                                    "ko": "검은-색과-회색-디딜-방아-m27OTMegUyA",
                                    "de": "schwarzes-und-graues-laufband-m27OTMegUyA",
                                    "pt": "esteira-preta-e-cinza-m27OTMegUyA",
                                    "id": "treadmill-hitam-dan-abu-abu-m27OTMegUyA"
                                },
                                "created_at": "2018-11-21T02:20:16Z",
                                "updated_at": "2025-07-30T00:36:28Z",
                                "promoted_at": null,
                                "width": 4928,
                                "height": 3264,
                                "color": "#d9c0a6",
                                "blur_hash": "LOL|S[ITElR+.Sofsmoe}l%MM|od",
                                "description": null,
                                "alt_description": "black and gray treadmill",
                                "breadcrumbs": [],
                                "urls": {
                                    "raw": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0",
                                    "full": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                    "regular": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                    "small": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                    "thumb": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                    "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1542766788-a2f588f447ee"
                                },
                                "links": {
                                    "self": "https://api.unsplash.com/photos/black-and-gray-treadmill-m27OTMegUyA",
                                    "html": "https://unsplash.com/photos/black-and-gray-treadmill-m27OTMegUyA",
                                    "download": "https://unsplash.com/photos/m27OTMegUyA/download",
                                    "download_location": "https://api.unsplash.com/photos/m27OTMegUyA/download"
                                },
                                "likes": 229,
                                "liked_by_user": false,
                                "current_user_collections": [],
                                "sponsorship": null,
                                "topic_submissions": {
                                    "sports": {
                                        "status": "approved",
                                        "approved_on": "2020-06-03T15:54:24Z"
                                    }
                                },
                                "asset_type": "photo",
                                "user": {
                                    "id": "bEGt7tImbSo",
                                    "updated_at": "2024-10-11T04:59:36Z",
                                    "username": "shuttch",
                                    "name": "gina lin",
                                    "first_name": "gina",
                                    "last_name": "lin",
                                    "twitter_username": null,
                                    "portfolio_url": null,
                                    "bio": null,
                                    "location": null,
                                    "links": {
                                        "self": "https://api.unsplash.com/users/shuttch",
                                        "html": "https://unsplash.com/@shuttch",
                                        "photos": "https://api.unsplash.com/users/shuttch/photos",
                                        "likes": "https://api.unsplash.com/users/shuttch/likes",
                                        "portfolio": "https://api.unsplash.com/users/shuttch/portfolio"
                                    },
                                    "profile_image": {
                                        "small": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=32&h=32",
                                        "medium": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=64&h=64",
                                        "large": "https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-4.1.0&crop=faces&fit=crop&w=128&h=128"
                                    },
                                    "instagram_username": null,
                                    "total_collections": 0,
                                    "total_likes": 0,
                                    "total_photos": 10,
                                    "total_promoted_photos": 0,
                                    "total_illustrations": 0,
                                    "total_promoted_illustrations": 0,
                                    "accepted_tos": true,
                                    "for_hire": false,
                                    "social": {
                                        "instagram_username": null,
                                        "portfolio_url": null,
                                        "twitter_username": null,
                                        "paypal_email": null
                                    }
                                }
                            },
                            "preview_photos": [
                                {
                                    "id": "m27OTMegUyA",
                                    "slug": "black-and-gray-treadmill-m27OTMegUyA",
                                    "created_at": "2018-11-21T02:20:16Z",
                                    "updated_at": "2025-07-30T00:36:28Z",
                                    "blur_hash": "LOL|S[ITElR+.Sofsmoe}l%MM|od",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1542766788-a2f588f447ee?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1542766788-a2f588f447ee"
                                    }
                                },
                                {
                                    "id": "4ICax0QMs8U",
                                    "slug": "a-man-in-a-green-shirt-holding-a-cell-phone-4ICax0QMs8U",
                                    "created_at": "2024-07-03T23:17:57Z",
                                    "updated_at": "2025-07-29T21:50:47Z",
                                    "blur_hash": "LGDddu4prJb@].cCP*w30{paIC'$'l",
                                    "asset_type": "photo",
                                    "urls": {
                                        "raw": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0",
                                        "full": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb",
                                        "regular": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max",
                                        "small": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max",
                                        "thumb": "https://images.unsplash.com/photo-1720048171098-dba33b2c4806?ixlib=rb-4.1.0&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max",
                                        "small_s3": "https://s3.us-west-2.amazonaws.com/images.unsplash.com/small/photo-1720048171098-dba33b2c4806"
                                    }
                                }
                            ]
                        }
                    ]
                }
            }
        """.trimIndent()

        val photo = json.decodeFromString<UnsplashPhotoResponse>(jsonString)

        assertNotNull(photo)
    }
}
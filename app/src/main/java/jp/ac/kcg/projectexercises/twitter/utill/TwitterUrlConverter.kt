package jp.ac.kcg.projectexercises.twitter.utill

import android.net.Uri


/**
 */
object TwitterUrlConverter {

    fun convertOriginalProfileImageUrl(profileImageUrl: String): String {
        val splitUrl = profileImageUrl.split("_normal".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (splitUrl.lastIndex != 1) return profileImageUrl
        return splitUrl[0] + splitUrl[1]
    }

    fun convertBiggerProfileImageUrl(profileImageUrl: String): String {
        val splitUrl = profileImageUrl.split("_normal".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (splitUrl.lastIndex != 1) return profileImageUrl
        return splitUrl[0] + "_bigger" + splitUrl[1]
    }


    fun convertUserUri(screenName: String): Uri {
        return Uri.parse("https://twitter.com/" + screenName)
    }
}

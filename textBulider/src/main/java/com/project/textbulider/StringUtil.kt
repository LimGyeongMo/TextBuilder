package com.project.textbulider

object StringUtil {
    fun isNullOrEmpty(s: Any?): Boolean {
        if (s == null) {
            return true
        }
        if ((s is String) && !containsText(s)) {
            return true
        }
        if (s is Map<*, *>) {
            return s.isEmpty()
        }
        if (s is List<*>) {
            return s.isEmpty()
        }
        if (s is Array<*> && s.isArrayOf<Any>()) {
            return ((s as Array<Any?>).size == 0)
        }
        return false
    }

    private fun containsText(str: CharSequence): Boolean {
        val strLen = str.length
        for (i in 0 until strLen) {
            if (!Character.isWhitespace(str[i])) {
                return true
            }
        }
        return false
    }
}
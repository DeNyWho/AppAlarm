package me.denis.shared


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}

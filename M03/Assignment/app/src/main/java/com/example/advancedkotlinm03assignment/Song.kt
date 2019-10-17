package com.example.advancedkotlinm03assignment

sealed class Song (val name: String, val artist: String)

class Jazz(name: String, artist: String): Song(name, artist)
class Funk(name: String, artist: String): Song(name, artist)
class Fusion(name: String, artist: String): Song(name, artist)
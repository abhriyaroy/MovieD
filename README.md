# MovieD

<p align="center">
 <img src="resources/ic_movied.png" alt="MovieD" width=300 height=300>
 </a>
</p>

# Table of Contents

- [Introduction](#introduction) <br>
- [UI Samples](#ui-samples) <br>
- [Things to Look Out For](#things-to-look-out-for)<br>
- [Installation](#installation) <br>
- [Acknowledgements](#acknowledgements) <br>
- [About the Author](#about-the-author)<br>

## Introduction

MovieD is a beautiful looking Movie list based project to showcase the [Android Architecture components](https://developer.android.com/topic/libraries/architecture).
It implements:-
- the [MVVM](https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b) pattern
- DI using [Koin](https://github.com/InsertKoinIO/koin)
- using [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) to make network calls with the help of [Retrofit](https://square.github.io/retrofit/)
- serves and maintains the data using [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData)
- navigation across the app happens after harnessing the power of the [Navigation component](https://developer.android.com/guide/navigation), also showcasing how to achieve shared element transition using Navigation component

Most other examples of the Architecture components that I could find on GitHub were mostly a little too easy or a little too complex, here comes MovieD which maintains a fine balance of both. I have tried to keep it simple yet not too simple. I will be glad if even one persons gains something out of this! If you like it, please leave a ⭐ 😁

## UI Samples

<p align="center">
  <img height="400" src="resources/landscape.gif">
</p>


<p align="center">
  <img height="600" src="resources/portrait.gif">
</p>

## Things to Look Out For

The main features illustrated in this project are :-

- MVVM Architecture
- How a ViewModel can survive configuration changes
- Dependency Injection using KOIN
- How LiveData works
- Simple Navigation component
- Advanced Navigation component (Shared element transition)
- Coroutines basic

## Installation
To successfully run this project, you would need an `API key` which you can easily get from [here](https://www.themoviedb.org/login). Once you have obtained the `API key` please create a file named `ApiKey.kt` at the path :-
     <br> <br> `/app/src/main/java/com/zebrostudio/movied/utils/ApiKey.kt`<br><br>
and declare a field `const val API_KEY = <Your API key here>`

And you should be good to go!
              
## Acknowledgements
A big thanks to [TMDB](https://www.themoviedb.org/?language=en-US) for providing the amazing API from where the movies are sourced.<br>
The UI was inspired from this awesome [design](https://dribbble.com/shots/8257559-Movie-2-0) on Dribble


## About the Author

### Abhriya Roy

 Android Developer with 2 years of experience in building apps that look and feel great. 
 Enthusiastic towards writing clean and maintainable code.
 Open source contributor.

 <a href="https://www.linkedin.com/in/abhriya-roy/"><img src="https://i.imgur.com/toWXOAd.png" alt="LinkedIn" width=40 height=40></a>     &nbsp;
 <a href="https://twitter.com/AbhriyaR"><img src="https://i.imgur.com/ymEo5Iy.png" alt="Twitter" width=42 height=40></a> 
 &nbsp;
 <a href="https://stackoverflow.com/users/6197251/abhriya-roy"><img src="https://i.imgur.com/JakJaHP.png" alt="Stack Overflow" width=40  height=40></a> 
 &nbsp;
 <a href="https://angel.co/abhriya-roy?public_profile=1"><img src="https://i.imgur.com/TiwMDMK.png" alt="Angel List" width=40  height=40></a>
 &nbsp;
 <a href="https://play.google.com/store/apps/developer?id=Zebro+Studio"><img src="https://i.imgur.com/Rj1IsYI.png" alt="Angel List" width=40  height=40></a>

 <br>
          
  

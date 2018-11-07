# KotlinFliktos

## Design
MPV architecture is used, but with no direct references to the view objects. The idea is to send updates to view, rather than direct commands, allowing the view to respond to the updates. This makes easier to migrate to MVVM arcthitecture using eithe RxJava or data-binding to further de-couple the view and the presenter/view model as the scale grows. DI is implemented manually.

## Libraries
Picasso is used to load images with caching.
Retrofit is used to build the network layer.
For unit testing, Robolectric is used to provide mocked Android context (ie. Activity, Resources, Context).

## Test
Basic unit tests are written for the RecyclerView adapter and presenter where most logics are in this project.
Espresso is used to have some automated UI tests, but unless the network responses are mocked to have set responses, it is impossible to verify the actual content on display.

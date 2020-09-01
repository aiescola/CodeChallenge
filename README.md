I divided the code into modules for each layer. You can find the typical clean architecture data and domain layers, while the module 'app' implements both of them (it only needs Data for the Dependency Injection). I also created the player in a separate module.

I made the data module using TDD and it has unit tests both for the retrofit service and the repository.

I used suspending functions returning Resource (own implementation, similar to Arrow's Either, I don't like Kotlin's Result as it's coupled to Throwable), then Flow from the Repository and LiveData from the ViewModel to the Activity. Each layer has it's own models so everything is decoupled (ie: CourseDTO -> Course -> CourseUI). As for error handling, I avoided using Exception and implemented them through sealed classes, in the end, in the presentation layer I only handled a "GenericError" as an example, but it's easily scalable.

I prefer Single Activity Applications with fragments and jetpack's Navigation, but as the screens have different themes/status bar, I used activities and styles rather than setting up that stuff programmatically.

The list of libraries I used can be seen in gradle/libraries.gradle.

The url for the API is: http://mobile-assets.domestika.org/

## CoursesList

![CourseList](./screenshots/CourseList.png)

## CourseDetail

![CourseDetail](./screenshots/CourseDetail.png)

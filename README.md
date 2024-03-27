# Test App

## Usage 

You can either download the zip folder and open it with Android Studio or clone the repository and import it.


## Libraries and Frameworks

- `architecture components (StateFlow + ViewModel)` for implementing MVVM architecture.
- `mockk` and `JUnit` for testing (Unit tests).
- `hilt` for dependency injection.
- `coroutine` for background threading and handling database access.
- `compose` for creating UI.
- `compose navigation` for navigating within the app.


## App Architecture
**Definition**

This app contains two main screens:


### Authentication Screen

Where the user needs to enter its userId to be able to see their posts. You can try entering 1, 2, or 3. In case the id does not exist on the server, an error dialog shows up saying that the id is not correct.


### Posts Screen

Where you can find all the user posts. If the user has no posts, you will only see their email.


## Planned Improvements: 

### Fix Clean Architecture DDD (Domain Driven Design)

For that, I need to find a way to delete data module dependency on the presentation layer (the app). Right now, if I remove it, Hilt will not compile.

### UI Tests

Writing some UI tests with Compose rule.

### Doc

Writing some cool documentations

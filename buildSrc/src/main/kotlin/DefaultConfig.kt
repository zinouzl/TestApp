import org.gradle.api.JavaVersion

object DefaultConfig {


    object Sdk {
        const val compile = 34
        const val target = 34
        const val min = 26
    }

    object App {
        const val applicationId = "com.example.testapp"
        const val versionName = "1.0.0"
        const val versionCode = 1

        val javaVersion = JavaVersion.VERSION_17
        val javaVersionName = "17"
    }

    object Modules {
        const val DATA = ":data"
        const val DOMAIN = ":domain"
    }

}
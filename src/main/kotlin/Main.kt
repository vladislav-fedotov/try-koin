import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module

fun main() {
    // Start Koin
    startKoin {
        modules(appModule)
    }

    // Use the dependencies
    val service: HelloService = MyComponent().helloService
    println(service.sayHello())

    // Stop Koin before exiting
    stopKoin()
}

class MyComponent : KoinComponent {
    val helloService: HelloService by inject()
}

val appModule = module {
    single<HelloService> { HelloServiceAnotherImpl() }
    single { HelloServiceImpl() } bind HelloService::class
}

interface HelloService {
    fun sayHello(): String
}

class HelloServiceImpl : HelloService {
    override fun sayHello() = "Hello, Koin!"
}

class HelloServiceAnotherImpl : HelloService {
    override fun sayHello() = "Hello, Kirill!"
}

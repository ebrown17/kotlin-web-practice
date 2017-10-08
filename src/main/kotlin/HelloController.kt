import javax.servlet.annotation.WebServlet
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletContextListener
import javax.servlet.ServletContextEvent
import kotlin.concurrent.fixedRateTimer


var count = 0
 

@WebServlet(name = "Hello", value = "/hello")
class HelloController : HttpServlet() {
	override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
		res.writer.write("Hello, World! $count")
	}

}

@WebListener
class HelloListener : ServletContextListener {
	
	override fun contextInitialized(sce: ServletContextEvent) {
		println("server listner initialized")
		val ftr = fixedRateTimer(name = "hello",daemon = false, initialDelay = 5000, period = 1000) {
			println("${count++}")
			if (count >= 10){
				cancel()
			} 
		}


	}

	override fun contextDestroyed(sce: ServletContextEvent) {
	
	}

}		
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith


@RunWith(classOf[Cucumber])
@CucumberOptions(plugin = Array("pretty")) class RunCucumberTest {}
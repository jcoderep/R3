package test.resources.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public Logger logger;

    @BeforeClass
    public void Setup()
    {
        logger = LogManager.getLogger(this.getClass());
    }
}

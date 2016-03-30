package kapowRobots;import com.kapowtech.robosuite.api.java.repository.engine.RepositoryClient;
import com.kapowtech.robosuite.api.java.repository.engine.RepositoryClientException;
import com.kapowtech.robosuite.api.java.repository.engine.RepositoryClientFactory;
import com.kapowtech.robosuite.api.java.rql.RQLException;
import com.kapowtech.robosuite.api.java.rql.RQLResult;
import com.kapowtech.robosuite.api.java.rql.Request;
import com.kapowtech.robosuite.api.java.rql.construct.RepositoryRobotLibrary;

import java.util.Date;

/**
 * Created by Igor_Makarychev on 25.11.2015.
 */
public class RunRobot {

    public static void main(String[] args) throws RQLException, RepositoryClientException, InterruptedException {


        System.out.println(new Date());
        // Get the cluster info through the repository API, and register the cluster for Requests
        String repositoryUserName = null;
        String repositoryPassword = null;
        String repositoryURL = "http://roboserver:50080";
        String clusterName = "Production";
        RepositoryClient client = RepositoryClientFactory.createRepositoryClient(repositoryURL, repositoryUserName, repositoryPassword);
        Request.registerCluster(client, clusterName);

        // Create the Request
        Request request = new Request("Library:/TestConcurrency.robot");
        request.setStopOnConnectionLost(true);
        request.setStopRobotOnApiException(true);
        request.setMaxExecutionTime(3600); // maximum 1 hour


        // Authentication is enabled on RoboServer, use these credentials
        String roboServerUserName = "serverUserName";
        String roboServerPassword = "serverPassword";

        // Use the repository as Robot Library
        RepositoryRobotLibrary lib = new RepositoryRobotLibrary(repositoryURL,"Default project", 60000L, repositoryUserName, repositoryPassword);
        request.setRobotLibrary(lib);
// set RoboServer credentials for Request
        request.setUsername(roboServerUserName);
        request.setPassword(roboServerPassword);
        // execute the request, and process the result if one is returned
        RQLResult result = request.execute(clusterName);
        System.out.println(new Date());
    }

}

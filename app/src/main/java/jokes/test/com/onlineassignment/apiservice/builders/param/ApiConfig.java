package jokes.test.com.onlineassignment.apiservice.builders.param;

/**
 * Created by Gaurav on 1/17/2018.
 * Configuration class which holde api data
 */

public class ApiConfig {
    /*Parameters*/
    public String type, firstName, lastName;
    public int limit;

    /*Paramter initialization in constructor*/
    public ApiConfig(Configuration configuration) {
        type = configuration.type;
        firstName = configuration.firstName;
        lastName = configuration.lastName;
        limit = configuration.limit;
    }

    /*Static class for initialize builder patter*/
    public static class Configuration {
        String type;
        String firstName;
        String lastName;
        int limit;

        /**
         * Setter for type
         *
         * @return Configuration object
         */
        public Configuration setType(String type) {
            this.type = type;
            return this;
        }

        /*Setter for first name*/
        public void setFname(String firstName) {
            this.firstName = firstName;
        }

        /*Setter for last name*/
        public void setLname(String lastName) {
            this.lastName = lastName;
        }

        /*Setter for limit*/
        public void setLimit(int limit) {
            this.limit = limit;
        }

        /*Create ApiConfig Object and initialize with configuration*/
        public ApiConfig configure() {
            return new ApiConfig(this);
        }
    }
}

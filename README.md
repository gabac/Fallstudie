# Fallstudie Gruppe C

## Setup Development Environment

1. Checkout source into your workspace

    ```
    $ git clone git@github.com:gabac/Fallstudie.git
    ```

2. Run Maven installation

    ```
    $ mvn install eclipse:eclipse
    ```

3. Download [Tomcat 7](http://tomcat.apache.org/download-70.cgi)

4. Download [MySQL Connector/J](http://dev.mysql.com/downloads/connector/j/) and copy `mysql-connector-java-*.jar` into `<tomcat_dir>/lib`

5. Create a database `socialnetwork` and execute the file `sql/create.sql`

6. Ready to start hacking


## Project Documentation

All documentation can be found in the [Wiki](https://github.com/gabac/Fallstudie/wiki).
